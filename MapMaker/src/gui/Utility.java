package gui;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;

import settings.GUISettings;

public class Utility {

	public static void paintGlow(Graphics2D g, Shape shape) {
		int biggestStroke = 5;
		g.setStroke(new BasicStroke(biggestStroke));
		g.setColor(GUISettings.focusArray[2]);
		g.draw(shape);
		g.setStroke(new BasicStroke(biggestStroke / 2));
		g.setColor(GUISettings.focusArray[1]);
		g.draw(shape);
		g.setStroke(new BasicStroke(biggestStroke / 3));
		g.setColor(GUISettings.focusArray[0]);
		g.draw(shape);
		g.setStroke(new BasicStroke(1));
	}

	public static void resetStroke(Graphics2D g) {
		g.setStroke(new BasicStroke(GUISettings.defaultStrokeWidth));
	}

	

}
