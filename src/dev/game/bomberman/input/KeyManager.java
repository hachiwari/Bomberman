package dev.game.bomberman.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import dev.game.bomberman.Handler;
import dev.game.bomberman.states.State;

/**
 * Klasa odpowiadajaca za Menadzera klawiatury.
 * @author Tomasz Kurek
 *
 */
public class KeyManager implements KeyListener {

	private Handler handler;
	private static HashMap<Integer, Boolean> keys;
	/**
	 * Klawisze klawiatury.
	 */
	public boolean up, down, left, right, escape;
	
	/**
	 * Konstruktor tworzy Menadzera klawiatury
	 * @param handler Handler
	 */
	public KeyManager(Handler handler) {
		this.handler = handler;

		keys = new HashMap<Integer, Boolean>();
		keys.put(KeyEvent.VK_UP, false);
		keys.put(KeyEvent.VK_DOWN, false);
		keys.put(KeyEvent.VK_LEFT, false);
		keys.put(KeyEvent.VK_RIGHT, false);
		keys.put(KeyEvent.VK_ESCAPE, false);
	}
	
	/**
	 * Metoda wykonywana co klatke.
	 */
	public void tick() {
		up = keys.get(KeyEvent.VK_UP);
		down = keys.get(KeyEvent.VK_DOWN);
		left = keys.get(KeyEvent.VK_LEFT);
		right = keys.get(KeyEvent.VK_RIGHT);
		escape = keys.get(KeyEvent.VK_ESCAPE);
	}

	/**
	 * Metoda wykonywana przy wcisnieciu klawisza.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_T) {
			System.out.println(handler.getWorld().getBlockCount() + "/" + handler.getWorld().getEnemyCount());
		}
		if (State.isPaussed()) { return; }
		if (e.getKeyCode() > 256) { return; }

		keys.put(e.getKeyCode(), true);
	}

	/**
	 * Metoda wykonywana przy opuszczeniu klawisza.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (State.isPaussed() && keyCode != KeyEvent.VK_P) { return; }
		if (keyCode > 256) { return; }

		if (keyCode == KeyEvent.VK_SPACE) {
			handler.getWorld().getEntityManager().addBomb();
		} else if (keyCode == KeyEvent.VK_P) {
			State.setPaussed((State.isPaussed() ? false : true));
		}

		keys.put(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
