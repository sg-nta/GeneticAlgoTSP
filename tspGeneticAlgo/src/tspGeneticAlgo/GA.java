package tspGeneticAlgo;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class GA {
	private double mutationRate;
	private double crossOverRate;
	private int populationSize;
    private int numKeep;
	protected int tournamentSize;


	public GA(int populationSize, double mutationRate, double crossOverRate, int numKeep,
			int tournamentSize) {
		this.crossOverRate = crossOverRate;
		this.mutationRate = mutationRate;
		this.populationSize = populationSize;
        this.numKeep = numKeep;
		this.tournamentSize = tournamentSize;

	}
	public Population initPopulation(int chromosomeLength) {
		return new Population(this.populationSize, chromosomeLength);
	}
	public void updateFitness(Population population, Node[] nodes) {
		for (Individual individual : population.getPopulation()) {
			Route route = new Route(individual, nodes);
			double fitness = 1/route.totalDistance();
			individual.setFitness(fitness);
		}
	}
	public Individual selectMother(Population population, Individual father) {
		ArrayList<Individual> tournament = new ArrayList<Individual>();
		int[] indexList = new int[this.tournamentSize];
		while (tournament.size() < this.tournamentSize) {
			int rand = (int) (Math.random()*population.getSize());
			int check = 0;
			for (int index:indexList) {
				if (rand == index) {
					check += 1;
				}
			}
			if (check == 0 && population.getIndividual(rand) != father) {
				tournament.add(population.getIndividual(rand));
			}
		}
		List<Individual> result = (ArrayList<Individual>) tournament.stream().sorted(Comparator.comparing(Individual::getFitness).reversed()).collect(Collectors.toList());
		return result.get(0);
	}
	
	public Population crossOver(Population population) {
		Population result = new Population(population.getSize());
		for (int i = 0; i < population.getSize(); i++) {
			Individual father = population.getIndividualByFitnessRank(i);
			if (this.crossOverRate > Math.random() && i >= this.numKeep ) {
				Individual mother = this.selectMother(population, father);
				ArrayList<Integer> childChromosome = new ArrayList<Integer>(Collections.nCopies(father.getLength(), -1));
				
				Individual child = new Individual(childChromosome);
				int rand1 = (int) (Math.random()*father.getLength());
				int rand2 = (int) (Math.random()*father.getLength());
				
				final int start = Math.min(rand1, rand2);
				final int end = Math.max(rand1, rand2);
				for (int j = start; j < end; j++) {
					child.setElement(j, father.getElement(j));
				}
				for (int j = 0; j < father.getLength(); j++) {
					int index = j + end;
					if (index >= father.getLength()) {
						index -= father.getLength();
					}
					if (child.containElement(mother.getElement(index)) == false) {
						for (int k = 0; k < child.getLength(); k++) {
							if (child.getElement(k) == -1) {
								child.setElement(k, mother.getElement(index));
								break;
							}
						}
					}
				}
				result.setIndividual(i, child);
			}
			else {
				result.setIndividual(i, father);
			}
		}
		return result;
	}
	
	public Population mutation(Population population) {
		Population result = new Population(population.getSize());
		for (int i = 0; i < population.getSize(); i++) {
			Individual individual = population.getIndividualByFitnessRank(i);
			
			if (i >= this.numKeep) {
				if (this.mutationRate >= Math.random()) {

						int rand1 = (int) (Math.random()*individual.getLength());
						int rand2 = (int) (Math.random()*individual.getLength());
						
						final int start = Math.min(rand1, rand2);
						final int end = Math.max(rand1, rand2);
						
						ArrayList<Integer> mutationList = new ArrayList<Integer>();
						for (int j = start; j < end; j ++) {
							mutationList.add(individual.getElement(j));
						}
						
						for (int j = start; j < end; j ++) {
							individual.setElement(j, mutationList.get(mutationList.size() - 1));
							mutationList.remove(mutationList.size() - 1);
						}
						 
				}
			}
			result.setIndividual(i, individual);
		}
		return result;
	}
	
	

}
