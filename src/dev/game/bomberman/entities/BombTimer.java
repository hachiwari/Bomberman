package dev.game.bomberman.entities;

import java.util.Timer;
import java.util.TimerTask;

import dev.game.bomberman.Handler;

/**
 * Klasa implementujaca znikniecie bomby po konkretnym czasie.
 * @author Tomasz Kurek
 *
 */
public class BombTimer {
	
	private Timer timer;
	private Entity entity;
	private Handler handler;

	/**
	 * Konstruktor tworzy nowe zadanie.
	 * @param seconds Czas trwania
	 * @param entity Obiekt bomby
	 * @param handler Handler
	 */
	public BombTimer(int seconds, Entity entity, Handler handler) {
		this.handler = handler;
		this.entity = entity;

		timer = new Timer();
		timer.schedule(new Task(), seconds * 1000);
	}

	/**
	 * Klasa odpowiadajaca za zadanie bomby.
	 * @author Tomasz Kurek
	 *
	 */
	class Task extends TimerTask {
		/**
		 * Metoda wykonujaca usuniecie bomby z listy obiektow oraz dodanie ognia.
		 */
		public void run() {
			double x = entity.getX(), y = entity.getY();
			handler.getWorld().getEntityManager().getPlayer().increaseBombCount();
			handler.getWorld().getEntityManager().removeEntity(entity);
			handler.getWorld().getEntityManager().addFire(x, y);
		}
	}
}
