package gui.elements;

import java.awt.Graphics2D;
import java.awt.Point;

import container.Node;

public class Bend extends TrackPart {

	public Bend(Node n) {
		super(n);
	}

	@Override
	public boolean needRepaint(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void paint(Graphics2D g) {
		paintLine(g);

	}

}
