package gui.elements;

import gui.elements.Track;

import java.util.ArrayList;
import java.util.Iterator;

public class Tracks implements Iterable<Track> {
	ArrayList<Track> tracks;

	// HashMap<Position, Node> startNodes;

	public Tracks() {
		tracks = new ArrayList<>();
		// startNodes = new HashMap<>();
	}

	public void addTrack(Track t) {
		tracks.add(t);
	}

	// public void addStartNode(Node n) {
	// startNodes.put(n.pos, n);
	// }

	// @Override
	// public Iterator<Node> iterator() {
	// return startNodes.values().iterator();
	// }
	
	@Override
	public Iterator<Track> iterator() {
		return tracks.iterator();
	}

}
