package gui.elements.moving;

import gui.elements.Station;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import settings.GUISettings;

public class MovingStation extends MovingTrackPart {

	public MovingStation(Station s) {
		super(s);
	}

	public void paintPart(Graphics2D g) {

		Shape shape = new Ellipse2D.Double(x - GUISettings.circleRadius, z
				- GUISettings.circleRadius, GUISettings.circleRadius * 2,
				GUISettings.circleRadius * 2);

		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));

		g.setColor(GUISettings.movingCircleBackgroundColor);
		g.fill(shape);
		g.setColor(GUISettings.movingCircleOutlineColor);
		g.draw(shape);
	}
}
