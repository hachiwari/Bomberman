package dev.game.bomberman.entities;

import java.awt.Graphics;

import dev.game.bomberman.Handler;
import dev.game.bomberman.graphic.Animation;
import dev.game.bomberman.graphic.Assets;

/**
 * Klasa implementujaca bombe.
 * @author Tomasz Kurek
 *
 */
public class Bomb extends Entity {

	/**
	 * Domyslny rozmiar bomby.
	 */
	public static final int DEFAULT_SIZE = 32;
	/**
	 * Czas po jakim czasie wybuchnie bomba po jej utworzeniu.
	 */
	public static final int TIME = 2;
	
	private Animation animationBomb;

	/**
	 * Konstruktor tworzy obiekt bomby.
	 * Inicjuje animacje.
	 * @param handler Handler
	 * @param x Polozenie na osi X
	 * @param y Polozenie na osi Y
	 * @param width Szerokosc bomby
	 * @param height Wysokosc bomby
	 */
	public Bomb(Handler handler, double x, double y, int width, int height) {
		super(handler, x, y, width, height);
		
		animationBomb = new Animation(500, Assets.bomb);
	}

	/**
	 * Metoda wykonywana co klatke odpowiada za animacje bomby.
	 */
	@Override
	public void tick() {
		animationBomb.tick();
	}

	/**
	 * Metoda rysuje obraz bomby.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(animationBomb.getCurrentFrame(), (int)x, (int)y, null);
	}
}
