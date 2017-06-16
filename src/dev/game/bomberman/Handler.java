package dev.game.bomberman;

import dev.game.bomberman.input.KeyManager;
import dev.game.bomberman.input.MouseManager;

/**
 * Klasa odpowiadajaca za dostep do najwazniejszych obiektow.
 * @author Tomasz Kurek
 *
 */
public class Handler {

	private Game game;
	private World world;
	
	/**
	 * Konstruktor tworzy obiekt.
	 * @param game Obiekt gry
	 */
	public Handler(Game game) {
		this.game = game;
	}

	/**
	 * Metoda zwraca obiekt Menadzera klawiatury.
	 * @return Menadzer klawiatury
	 */
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	/**
	 * Metoda zwraca obiekt Menadzera myszki.
	 * @return Menadzer myszki
	 */
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	/**
	 * Metoda zwraca obiekt gry.
	 * @return Obiekt gry
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Metoda zwraca obiekt swiata gry.
	 * @return Obiekt swiata gry
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Metoda ustawia obiekt swiata gry.
	 * @param world Obiekt swiata gry
	 */
	public void setWorld(World world) {
		this.world = world;
	}
}
