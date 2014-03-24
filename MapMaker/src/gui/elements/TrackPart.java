package gui.elements;

import gui.Utility;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

import settings.GUISettings;
import container.Node;

public abstract class TrackPart {

	public Node n;
	public TrackPart prevNeighbour;
	public TrackPart nextNeighbour;
	boolean pressed;

	public TrackPart(Node n) {
		this.n = n;
	}

	public void setPrevNeightbour(TrackPart neighbour) {
		prevNeighbour = neighbour;
	}

	public void setNextNeightbour(TrackPart neighbour) {
		nextNeighbour = neighbour;
	}

	public abstract boolean needRepaint(Point p);

	public abstract boolean isOnPart(Point p);

	public boolean isPressed() {
		return pressed;
	}

	public void move(int x, int z) {
		n.pos.x = x;
		n.pos.z = z;
	}

	public abstract void paintPart(Graphics2D g);

	public void paint(Graphics2D g) {
		paintLines(g);
		paintPart(g);
		if (nextNeighbour != null) {
			nextNeighbour.paint(g);
		}
	}

	public void paintLines(Graphics2D g) {
		if (nextNeighbour != null) {
			paintLine(g, n.pos.x, n.pos.z, nextNeighbour.n.pos.x,
					nextNeighbour.n.pos.z);
		}
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	private static void paintLine(Graphics2D g, int xFrom, int zFrom, int xTo,
			int zTo) {
		g.setStroke(new BasicStroke(GUISettings.lineWidth));
		g.setColor(GUISettings.lineColor);
		g.drawLine(xFrom, zFrom, xTo, zTo);
		Utility.resetStroke(g);
	}

}
