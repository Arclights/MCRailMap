package gui.elements;

import gui.Utility;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

import settings.GUISettings;
import container.Node;

public abstract class TrackPart {

	Node n;

	public TrackPart(Node n) {
		this.n = n;
	}

	public abstract boolean needRepaint(Point p);

	public abstract void paint(Graphics2D g);

	protected void paintLine(Graphics2D g) {
		if (n.hasNeighbour()) {
			paintLine(g, n.pos.x, n.pos.z, n.getNeighbour().pos.x,
					n.getNeighbour().pos.z);
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
