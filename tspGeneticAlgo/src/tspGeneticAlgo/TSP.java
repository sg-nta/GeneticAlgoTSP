package tspGeneticAlgo;


public class TSP {
	public static void main (String [] args) {
		final int numNodes = 100;
		final int maxGenerations = 10000; 
		Node nodes[] = new Node[numNodes];
		for (int i = 0; i < numNodes; i++) {
			int xPos = (int) (100 * Math.random());
			int yPos = (int) (100 * Math.random());

			nodes[i] = new Node(xPos, yPos);
		}
		
		GA ga = new GA(100, 0.001, 0.9, 7, 5);
		Population population = ga.initPopulation(numNodes);

		ga.updateFitness(population, nodes);

		Route startRoute = new Route(population.getIndividualByFitnessRank(0), nodes);
		System.out.println("Start Distance: " + startRoute.totalDistance());
		int generation = 1;
		
		while(generation < maxGenerations) {
			Route route = new Route(population.getIndividualByFitnessRank(0), nodes);
			System.out.println("G"+generation+" Best distance: " + route.totalDistance());
			
			population = ga.crossOver(population);
			population = ga.mutation(population);
			ga.updateFitness(population, nodes);
			generation ++;
		}

		System.out.println("Stopped after " + 10000 + " generations.");
		Route route = new Route(population.getIndividualByFitnessRank(0), nodes);
		System.out.println("Best distance: " + route.totalDistance());


	}
	

}
