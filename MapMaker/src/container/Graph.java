package container;

import java.util.HashMap;
import java.util.Iterator;

public class Graph implements Iterable<Node> {

	HashMap<Position, Node> startNodes;

	public Graph() {
		startNodes = new HashMap<>();
	}

	public void addStartNode(Node n) {
		startNodes.put(n.pos, n);
	}

	@Override
	public Iterator<Node> iterator() {
		return startNodes.values().iterator();
	}

}
