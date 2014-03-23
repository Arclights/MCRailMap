package test;

import gui.MainWindow;

import java.util.ArrayList;
import java.util.Random;

import container.Graph;
import container.Node;
import container.Position;

public class TestGUI {

	public static void main(String[] args) {
		Graph g = createMockGraph();
		new MainWindow(1000, 1000, g);
	}

	private static Graph createMockGraph() {
		Graph g = new Graph();
		ArrayList<Node> nodes = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < 9; i++) {
			nodes.add(new Node(0, new Position(rand.nextInt(800) + 50, rand
					.nextInt(800) + 50)));
		}

		for (int i = 0; i < 3; i++) {
			Node n1 = nodes.remove(nodes.size() - 1);
			Node n2 = nodes.remove(nodes.size() - 1);
			n1.addNeighbour(n2);
			Node n3 = nodes.remove(nodes.size() - 1);
			n2.addNeighbour(n3);
			g.addStartNode(n1);
		}
		return g;
	}
}
