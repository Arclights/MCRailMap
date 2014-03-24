package gui.elements.moving;

import gui.elements.Station;
import gui.elements.TrackPart;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class MovingTrack {
	ArrayList<MovingTrackPart> parts;

	public MovingTrack(Station station) {
		parts = new ArrayList<>();

		MovingTrackPart currentTrackPart = new MovingStation(station);
		parts.add(currentTrackPart);
		MovingTrackPart lastTrackPart = currentTrackPart;
		TrackPart n;
		if (station.nextNeighbour != null) {
			n = station.nextNeighbour;
		} else {
			n = station.prevNeighbour;
		}

		for (;;) {
			if (n.nextNeighbour != null) {
				currentTrackPart = new MovingBend(n);
				parts.add(currentTrackPart);
				lastTrackPart.nextNeighbour = currentTrackPart;
				currentTrackPart.prevNeighbour = lastTrackPart;
				lastTrackPart = currentTrackPart;
			} else {
				break;
			}
			if (n.nextNeighbour != null) {
				n = n.nextNeighbour;
			} else {
				n = n.prevNeighbour;
			}
		}
	}

	public void moveTrack() {
		parts.get(0).moveTrackPart();
	}

	public void updatePosition(int x, int z) {
		parts.get(0).updatePosition(x, z);
	}

	public void paint(Graphics2D g) {
		MovingTrackPart start = parts.get(0);
		if (start.nextNeighbour != null) {
			start.paintForward(g);
		} else {
			start.paintBackward(g);

		}
	}
}
