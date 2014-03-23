package container;

public class Node {
	public int direction;
	public Position pos;
	Node neighbour;

	public Node(int direction, Position pos) {
		this.direction = direction;
		this.pos = pos;
		neighbour = null;
	}

	public void addNeighbour(Node n) {
		neighbour = n;
	}

	public void setNeighbour(Node neighbour) {
		this.neighbour = neighbour;
	}

	public Node getNeighbour() {
		return neighbour;
	}

	public boolean hasNeighbour() {
		return neighbour != null;
	}

}
