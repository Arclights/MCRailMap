package gui.elements;

import gui.Utility;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import settings.GUISettings;

public abstract class MovingTrackPart {

	int x;
	int z;
	TrackPart tp;
	MovingTrackPart neighbour;

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

	public abstract void paint(Graphics2D g);

	protected void paintLine(Graphics2D g) {
		if (neighbour != null) {
			paintLine(g, x, z, neighbour.x, neighbour.z);
		}
	}

	public static void paintLine(Graphics2D g, int xFrom, int zFrom, int xTo,
			int zTo) {
		g.setStroke(new BasicStroke(GUISettings.lineWidth));
		g.setColor(GUISettings.lineColor);
		g.drawLine(xFrom, zFrom, xTo, zTo);
		Utility.resetStroke(g);
	}

}
