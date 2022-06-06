package tspGeneticAlgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;



public class Population {
	private ArrayList<Individual> population = new ArrayList<Individual>();

	public Population(int populationSize) {
		this.population = new ArrayList<Individual>(Collections.nCopies(populationSize, null));
	}
	public Population(int populationSize, int chromosomeLength) {
		this.population = new ArrayList<Individual>(Collections.nCopies(populationSize, null));
		for (int individualCount = 0; individualCount < populationSize; individualCount++) {
			Individual individual = new Individual(chromosomeLength);
			this.population.set(individualCount, individual);
		}
	}
	public Individual getIndividualByFitnessRank(int rank) {
		List<Individual> result = (ArrayList<Individual>) population.stream().sorted(Comparator.comparing(Individual::getFitness).reversed()).collect(Collectors.toList());
		return result.get(rank);
	}
	public ArrayList<Individual> getPopulation() {
		return population;
	}
	public void setIndividual(int index, Individual value) {
		this.population.set(index, value);
	}
	public Individual getIndividual(int index) {
		return this.population.get(index);
	}
	public int getSize() {
		return this.population.size();
	}
	
}
