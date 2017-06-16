package dev.game.bomberman.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Klasa odpowiada za utworzenie okna.
 * @author Tomasz Kurek
 *
 */
public class Display {

	private JFrame frame = null;
	private Canvas canvas = null;
	
	private int width, height;
	private String title;
	
	/**
	 * Konstruktor tworzy nowe okno.
	 * @param title Nazwa okna
	 * @param width Szerokosc okna
	 * @param height Wysokosc okna
	 */
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		try {
			initDisplay();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initDisplay() throws IOException {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(ImageIO.read(Display.class.getResource("/icon.png")));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	/**
	 * Metoda zwraca obiekt Canvas
	 * @return Obiekt Canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Metoda zwraca obiekt okna.
	 * @return Obiekt okna
	 */
	public JFrame getFrame() {
		return frame;
	}
}
