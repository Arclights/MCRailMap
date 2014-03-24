package gui.elements;

import java.awt.Graphics2D;

public class MovingBend extends MovingTrackPart{

	public MovingBend(TrackPart tp) {
		super(tp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics2D g) {
		paintLine(g);
	}

}
