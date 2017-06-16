package dev.game.bomberman.graphic;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Klasa odpowiadajaca za wczytywanie pliku graficznego.
 * @author Tomasz Kurek
 *
 */
public class ImageLoader {

	/**
	 * Metoda wczytuje obrazek z podanego katalogu.
	 * @param path Sciezka do pliku
	 * @return Wczytany obrazek
	 */
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
