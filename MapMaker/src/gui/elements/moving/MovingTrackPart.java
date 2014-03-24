package gui.elements.moving;

import gui.Utility;
import gui.elements.TrackPart;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import settings.GUISettings;

public abstract class MovingTrackPart {

	int x;
	int z;
	TrackPart tp;
	public MovingTrackPart prevNeighbour;
	public MovingTrackPart nextNeighbour;

	public MovingTrackPart(TrackPart tp) {
		this.tp = tp;
		x = tp.n.pos.x;
		z = tp.n.pos.z;
	}

	public void moveTrackPart() {
		tp.move(x, z);
	}

	public void updatePosition(int x, int z) {
		this.x = x;
		this.z = z;
	}

	protected abstract void paintPart(Graphics2D g);

	public void paintForward(Graphics2D g) {
		paintLineForward(g);
		paintPart(g);
		if (nextNeighbour != null) {
			nextNeighbour.paintForward(g);
		}
	}

	public void paintBackward(Graphics2D g) {
		paintLineBackward(g);
		paintPart(g);
		if (prevNeighbour != null) {
			prevNeighbour.paintBackward(g);
		}
	}

	private void paintLineForward(Graphics2D g) {
		if (nextNeighbour != null) {
			paintLine(g, x, z, nextNeighbour.x, nextNeighbour.z);
		}
	}
	private void paintLineBackward(Graphics2D g) {
		if (prevNeighbour != null) {
			paintLine(g, x, z, prevNeighbour.x, prevNeighbour.z);
		}
	}

	public static void paintLine(Graphics2D g, int xFrom, int zFrom, int xTo,
			int zTo) {
		g.setStroke(new BasicStroke(GUISettings.lineWidth));
		g.setColor(GUISettings.movingLineColor);
		g.drawLine(xFrom, zFrom, xTo, zTo);
		Utility.resetStroke(g);
	}

}
