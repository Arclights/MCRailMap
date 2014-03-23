package gui.elements;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Track {

	ArrayList<TrackPart> parts;

	public Track() {
		parts = new ArrayList<>();
	}

	public void addPart(TrackPart tp) {
		parts.add(tp);
	}

	public boolean needRepaint(Point p) {
		for (TrackPart tp : parts) {
			if (tp.needRepaint(p)) {
				return true;
			}
		}
		return false;
	}
	
	public void paint(Graphics2D g){
		for(TrackPart tp:parts){
			tp.paint(g);
		}
	}

}
