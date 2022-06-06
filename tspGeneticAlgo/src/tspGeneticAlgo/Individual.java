package tspGeneticAlgo;

import java.util.ArrayList;

public class Individual {
	private ArrayList<Integer> chromosome = new ArrayList<Integer>();;
	private double fitness;
	public Individual(ArrayList<Integer> chromosome) {
		this.chromosome = chromosome;
		// TODO Auto-generated constructor stub
	}
	public Individual(int chromosomeLength) {
		ArrayList<Integer> individual = new ArrayList<Integer>();
		for (int gene = 0; gene < chromosomeLength; gene++) {
			individual.add(gene);
		}
		
		this.chromosome = individual;
	}
	public ArrayList<Integer> getChromosome() {
		return this.chromosome;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public int getElement(int index) {
		return this.chromosome.get(index);
	}
	public void setElement(int index, int value) {
		this.chromosome.set(index, value);
	}
	public int getLength() {
		return this.chromosome.size();
	}
	public boolean containElement(int value) {
		for (int e: this.chromosome) {
			if (e == value) {
				return true;
			}
		}
		return false;
	}
	
	
	

}
