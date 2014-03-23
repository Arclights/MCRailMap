package gui;

import javax.swing.JFrame;

import container.Graph;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainWindow(int width, int height, Graph graph) {
		setSize(width, height);
		add(new Map(graph));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
