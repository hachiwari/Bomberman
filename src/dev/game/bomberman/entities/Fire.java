package dev.game.bomberman.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.bomberman.Handler;
import dev.game.bomberman.states.State;

/**
 * Klasa odpowiadajaca za obiekt ognia.
 * @author Tomasz Kurek
 *
 */
public class Fire extends Entity {

	/**
	 * Domyslny rozmiar ognia.
	 */
	public static final int DEFAULT_SIZE = 32;
	/**
	 * Czas trwania ognia.
	 */
	public static final int TIME = 1;
	private BufferedImage image;

	/**
	 * Konstruktor tworzy obiekt ognia.
	 * @param handler Handler
	 * @param x Polozenie ognia na osi X
	 * @param y Polozenie ognia na osi Y
	 * @param width Szerokosc ognia
	 * @param height Wysokosc ognia
	 * @param image Obrazek ognia
	 */
	public Fire(Handler handler, double x, double y, int width, int height, BufferedImage image) {
		super(handler, x, y, width, height);
		this.image = image;
	}

	/**
	 * Metoda wykonywana co klatke, sprawdza kolizje z obiektami mozliwymi do zniszczenia.
	 * W przypadku zniszczenia LuckyBox'a wygrywamy gre.
	 */
	@Override
	public void tick() {
		if (checkEntityWithTileCollisions(getCol(), getRow())) {
			handler.getWorld().setTile(getCol(), getRow());
			handler.getWorld().decreaseBlockCount();
		}
		
		if (checkEntityWithLuckyBoxCollisions(getCol(), getRow())) {
			handler.getWorld().setTile(getCol(), getRow());
			State.setState(handler.getGame().winGame);
		}
	}

	/**
	 * Metoda rysuje ogien.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x, (int)y, width, height, null);
	}
}
