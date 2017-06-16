package dev.game.bomberman.graphic;

import java.awt.image.BufferedImage;

/**
 * Klasa odpowiadajaca za przycinanie elementow graficznych/
 * @author Tomasz Kurek
 *
 */
public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	/**
	 * Metoda zwraca wyciety obrazek.
	 * @param col Numer kolumny
	 * @param row Numer wiersza
	 * @param size Rozmiar
	 * @return Wyciety obrazek
	 */
	public BufferedImage grabImage(int col, int row, int size) {
		BufferedImage img = sheet.getSubimage((row * size) - size, (col * size) - size, size, size);
		return img;
	}

	/**
	 * Metoda zwraca wyciety obrazek.
	 * @param left Poczatek od lewej strony
	 * @param top Poczatek od gornej strony
	 * @param width Szerokosc 
	 * @param height Wysokosc 
	 * @return Wyciety obrazek
	 */
	public BufferedImage grabImage(int left, int top, int width, int height) {
		BufferedImage img = sheet.getSubimage(left, top, width, height);
		return img;
	}
}
