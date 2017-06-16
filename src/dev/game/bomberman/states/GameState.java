package dev.game.bomberman.states;

import java.awt.Graphics;

import dev.game.bomberman.Handler;
import dev.game.bomberman.World;

/**
 * Klasa odpowiadajaca za rysowanie swiata gry.
 * @author Tomasz Kurek
 *
 */
public class GameState extends State {

	private World world;

	/**
	 * Tworzy nowy swiat gry.
	 * @param handler Handler
	 */
	public GameState(Handler handler) {
		super(handler);

		world = new World(handler);
		handler.setWorld(world);
	}

	/**
	 * Metoda wykonywana co klatke
	 */
	@Override
	public void tick() {
		world.tick();
	}

	/**
	 * Metoda rysuje swiat gry.
	 */
	@Override
	public void render(Graphics g) {
		world.render(g);
	}
}
