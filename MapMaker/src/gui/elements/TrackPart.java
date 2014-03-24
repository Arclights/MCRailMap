package gui.elements;

import gui.Utility;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import settings.GUISettings;
import container.Node;

public abstract class TrackPart {

	Node n;
	ArrayList<TrackPart> neighbours;

	public TrackPart(Node n) {
		this.n = n;
		neighbours = new ArrayList<>();
	}

	public void addNeightbour(TrackPart neighbour) {
		neighbours.add(neighbour);
	}

	public abstract boolean needRepaint(Point p);

	public abstract boolean isOnPart(Point p);

	public void move(int x, int z) {
		n.pos.x = x;
		n.pos.z = z;
	}

	public abstract void paintPart(Graphics2D g);

	public void paintLines(Graphics2D g) {
		for (TrackPart neighbour : neighbours) {
			paintLine(g, n.pos.x, n.pos.z, neighbour.n.pos.x, neighbour.n.pos.z);
		}
	}

//	protected void paintLine(Graphics2D g) {
//		for (TrackPart tp : neighbours) {
//			paintLine(g, n.pos.x, n.pos.z, tp.n.pos.x, tp.n.pos.z);
//		}
//	}

	private static void paintLine(Graphics2D g, int xFrom, int zFrom, int xTo,
			int zTo) {
		g.setStroke(new BasicStroke(GUISettings.lineWidth));
		g.setColor(GUISettings.lineColor);
		g.drawLine(xFrom, zFrom, xTo, zTo);
		Utility.resetStroke(g);
	}

}
