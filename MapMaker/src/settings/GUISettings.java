package settings;

import java.awt.Color;

public class GUISettings {
	public static Color focusColor = new Color(64, 113, 167);

	public static Color[] focusArray = new Color[] {
			new Color(focusColor.getRed(), focusColor.getGreen(),
					focusColor.getBlue(), 255),
			new Color(focusColor.getRed(), focusColor.getGreen(),
					focusColor.getBlue(), 170),
			new Color(focusColor.getRed(), focusColor.getGreen(),
					focusColor.getBlue(), 110) };

	public static int defaultStrokeWidth = 1;
	public static Color circleBackgroundColor = Color.WHITE;
	public static Color circleOutlineColor = Color.BLACK;
	public static int circleDiameter = 50;
	public static int circleRadius = circleDiameter / 2;
	
	public static Color movingCircleBackgroundColor = new Color(0, 0, 0, 0.10f);
	public static Color movingCircleOutlineColor = new Color(0, 0, 0, 0.50f);
	public static Color movingLineColor = new Color(0, 0, 0, 0.20f);

	public static int lineWidth = 3;
	public static Color lineColor = new Color(0, 133, 63);

}
