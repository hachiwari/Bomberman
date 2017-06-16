package dev.game.bomberman.entities;

import java.util.Timer;
import java.util.TimerTask;

import dev.game.bomberman.Handler;

/**
 * Klasa implementujaca znikniecie ognia po konkretnym czasie.
 * @author Tomasz Kurek
 *
 */
public class FireTimer {

	private Timer timer;
	private Fire fire;
	private Handler handler;

	/**
	 * Konstruktor tworzy nowe zadanie.
	 * @param seconds Czas trwania
	 * @param fire Obiekt ognia
	 * @param handler Handler
	 */
	public FireTimer(int seconds, Fire fire, Handler handler) {
		this.handler = handler;
		this.fire = fire;

		timer = new Timer();
		timer.schedule(new Task(), seconds * 1000);
	}

	/**
	 * Klasa odpowiadajaca za zadanie ognia.
	 * @author Tomasz Kurek
	 *
	 */
	class Task extends TimerTask {
		/**
		 * Metoda wykonujaca usuniecie ognia z listy obiektow.
		 */
		public void run() {
			handler.getWorld().getEntityManager().removeFire(fire);
		}
	}
}
