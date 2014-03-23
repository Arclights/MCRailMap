package gui;

import gui.elements.Track;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import container.Graph;

public class Map extends JPanel implements MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Graph graph;

	public Map(Graph graph) {
		setBackground(Color.WHITE);
		addMouseMotionListener(this);

		this.graph=graph;

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Track c : graph) {
			c.paint((Graphics2D) g);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point mouseLocation = e.getPoint();
		for (Track c : graph) {
			if (c.needRepaint(mouseLocation)) {
				repaint();
			}
		}

	}
}
