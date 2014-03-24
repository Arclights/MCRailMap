package gui.elements;

import gui.Utility;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import settings.GUISettings;
import container.Node;

public class Station extends TrackPart {

	boolean hover;
	Shape shape;

	public Station(Node n) {
		super(n);
	}

	public void move(int x, int z) {
		n.pos.x = x;
		n.pos.z = z;
	}

	public void paintPart(Graphics2D g) {

		shape = new Ellipse2D.Double(n.pos.x - GUISettings.circleRadius,
				n.pos.z - GUISettings.circleRadius,
				GUISettings.circleRadius * 2, GUISettings.circleRadius * 2);

		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));


		if (hover) {
			Utility.paintGlow(g, shape);
			Utility.resetStroke(g);
		}
		g.setColor(GUISettings.circleBackgroundColor);
		g.fill(shape);
		g.setColor(GUISettings.circleOutlineColor);
		g.draw(shape);
	}

	public boolean needRepaint(Point p) {
		boolean oldHover = hover;
		if (shape.contains(p)) {
			hover = true;
		} else {
			hover = false;
		}
		if (oldHover != hover) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isOnPart(Point p) {
		// TODO Auto-generated method stub
		return shape.contains(p);
	}
}
