package gui.elements;

import gui.elements.moving.MovingTrack;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import container.Node;

public class Track {

	ArrayList<TrackPart> parts;
	MovingTrack mt;

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
				lastTrackPart.setNextNeightbour(currentTrackPart);
				currentTrackPart.setPrevNeightbour(lastTrackPart);
				lastTrackPart = currentTrackPart;
			} else {
				if (n != startNode) {
					currentTrackPart = new Station(n);
					parts.add(currentTrackPart);
					lastTrackPart.setNextNeightbour(currentTrackPart);
					currentTrackPart.setPrevNeightbour(lastTrackPart);
				}
				break;
			}
			n = n.getNeighbour();
		}
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
		if (mt == null) {
			for (TrackPart tp : parts) {
				if (tp.isOnPart(mouseLocation) && tp.isPressed()) {
					mt = new MovingTrack((Station) tp);
				}
			}
		}

		if (mt != null) {
			mt.updatePosition(mouseLocation.x, mouseLocation.y);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (mt != null) {
			mt.moveTrack();
			mt = null;
			
			for (TrackPart tp : parts) {
				tp.setPressed(false);
			}
		}
		
	}

	public void mousePressed(MouseEvent e) {
		Point mouseLocation = e.getPoint();
		for (TrackPart tp : parts) {
			if (tp.isOnPart(mouseLocation)) {
				tp.setPressed(true);
				break;
			}
		}
	}

	public void paint(Graphics2D g) {
//		for (TrackPart tp : parts) {
//			tp.paintLines(g);
//		}
//		for (TrackPart tp : parts) {
//			tp.paintPart(g);
//		}
		parts.get(0).paint(g);
		if (mt != null) {
			mt.paint(g);
		}
	}

}
