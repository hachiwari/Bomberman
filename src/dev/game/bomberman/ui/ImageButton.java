package dev.game.bomberman.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Klasa implementujaca przycisk skladajacy sie z obrazka.
 * @author Root
 *
 */
public class ImageButton extends UIObject{

	private BufferedImage[] textures;
	private ClickListener clicker;

	/**
	 * Konstruuje nowy przycisk.
	 * @param x Polozenie przycisku na osi X
	 * @param y Polozenie przycisku na osi Y
	 * @param width Szerokosc przycisku
	 * @param height Wysokosc przycisku
	 * @param textures Obrazek 
	 * @param clicker Obiekt odpowiadajacy za klikanie
	 */
	public ImageButton(double x, double y, int width, int height, BufferedImage[] textures, ClickListener clicker) {
		super(x, y, width, height);
		this.textures = textures;
		this.clicker = clicker;
	}

	/**
	 * Metoda wykonywana co klatke.
	 */
	@Override
	public void tick() {
		
	}

	/**
	 * Metoda rysuje przycisk.
	 */
	@Override
	public void render(Graphics g) {
		if (!hovering) {
			g.drawImage(textures[0], (int)x, (int)y, width, height, null);
		} else {
			g.drawImage(textures[1], (int)x, (int)y, width, height, null);
		}
	}

	/**
	 * Metoda odpowiedzialna za klikanie w przycisk.
	 */
	@Override
	public void onClick() {
		if (clicker != null) {
			clicker.onClick();
		}
	}

}
