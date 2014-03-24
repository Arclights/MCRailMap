package gui;

import gui.elements.Track;
import gui.elements.Tracks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import container.Graph;
import container.Node;

public class Map extends JPanel implements MouseInputListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Tracks tracks;

	public Map(Graph graph) {
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);

		this.tracks = new Tracks();
		for (Node n : graph) {
			tracks.addTrack(new Track(n));
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
		for (Track t : tracks) {
			t.mouseDragged(e);
		}
		repaint();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Track t : tracks) {
			t.mouseReleased(e);
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
