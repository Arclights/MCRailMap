package gui;

import gui.elements.Bend;
import gui.elements.Station;
import gui.elements.Track;
import gui.elements.Tracks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import container.Graph;
import container.Node;

public class Map extends JPanel implements MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Tracks tracks;

	public Map(Graph graph) {
		setBackground(Color.WHITE);
		addMouseMotionListener(this);

		this.tracks = new Tracks();
		for (Node n : graph) {
			Track t = new Track();
			t.addPart(new Station(n));
			Node startNode = n;
			for (;;) {
				if (n.hasNeighbour()) {
					n = n.getNeighbour();
					t.addPart(new Bend(n));
				} else {
					if (n != startNode) {
						t.addPart(new Station(n));
					}
					break;
				}
			}
			tracks.addTrack(t);
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Track t : tracks) {
			t.paint((Graphics2D) g);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point mouseLocation = e.getPoint();
		for (Track t : tracks) {
			if (t.needRepaint(mouseLocation)) {
				repaint();
			}
		}

	}
}
