package tspGeneticAlgo;

public class Node {
	private int x_pos;
	private int y_pos;
	
	public Node(int x, int y) {
		this.x_pos = x;
		this.y_pos = y;
	}
	public int getX() {
		return this.x_pos;
	}
	public int getY() {
		return this.y_pos;
	}
	public double getDistance(Node node) {
		double deltaXSq = Math.pow((node.getX() - this.getX()), 2);
		double deltaYSq = Math.pow((node.getY() - this.getY()), 2);

		double distance = Math.sqrt(Math.abs(deltaXSq + deltaYSq));
		return distance;
	}
}
