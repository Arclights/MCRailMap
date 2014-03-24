package gui.elements;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import container.Node;

public class Track {

	ArrayList<TrackPart> parts;
	MovingStation ms;

	public Track(Node startNode) {
		parts = new ArrayList<>();

		TrackPart currentTrackPart = new Station(startNode);
		parts.add(currentTrackPart);
		TrackPart lastTrackPart = currentTrackPart;
		Node n = startNode.getNeighbour();

		for (;;) {
			if (n.hasNeighbour()) {
				currentTrackPart = new Bend(n);
				parts.add(currentTrackPart);
				lastTrackPart.addNeightbour(currentTrackPart);
				currentTrackPart.addNeightbour(lastTrackPart);
				lastTrackPart = currentTrackPart;
			} else {
				if (n != startNode) {
					currentTrackPart = new Station(n);
					parts.add(currentTrackPart);
					lastTrackPart.addNeightbour(currentTrackPart);
					currentTrackPart.addNeightbour(lastTrackPart);
				}
				break;
			}
			n = n.getNeighbour();
		}
	}

	public void addPart(TrackPart tp) {
		parts.add(tp);
	}

	public boolean needRepaint(Point p) {
		for (TrackPart tp : parts) {
			if (tp.needRepaint(p)) {
				return true;
			}
		}
		return false;
	}

	public void mouseDragged(MouseEvent e) {
		Point mouseLocation = e.getPoint();
		if (ms == null) {
			for (TrackPart tp : parts) {
				if (tp.isOnPart(mouseLocation)) {
					ms = new MovingStation((Station) tp);
				}
			}
		}

		if (ms != null) {
			ms.updatePosition(mouseLocation.x, mouseLocation.y);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (ms != null) {
			ms.moveTrackPart();
			ms = null;
		}

	}

	public void paint(Graphics2D g) {
		for (TrackPart tp : parts) {
			tp.paintLines(g);
		}
		for (TrackPart tp : parts) {
			tp.paintPart(g);
		}
		if (ms != null) {
			ms.paint(g);
		}
	}

}
