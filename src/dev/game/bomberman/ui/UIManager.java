package dev.game.bomberman.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.game.bomberman.Handler;

/**
 * Klasa implementujaca Menadzer interfejsu uzytkownika odpowiadajacy za rysowanie przyciskow w Menu gry.
 * @author Tomasz Kurek
 *
 */
public class UIManager {

	private Handler handler;
	private ArrayList<UIObject> objects;
	
	/**
	 * Konstruuje nowy menadzer.
	 * @param handler Handler
	 */
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	/**
	 * Metoda odpowiedzialna za dodanie nowego obiektu do listy.
	 * @param object Nowy obiekt
	 */
	public void addObject(UIObject object) {
		objects.add(object);
	}
	
	/**
	 * Metoda odpowiedzialna za usuwanie obiektu z listy.
	 * @param object Obiekt
	 */
	public void removeObject(UIObject object) {
		objects.remove(object);
	}
	
	/**
	 * Metoda wykonywana co klatke.
	 */
	public void tick() {
		for(UIObject o : objects) {
			o.tick();
		}
	}
	
	/**
	 * Metoda rysyje obiekty z listy.
	 * @param g Obiekt rysujacy
	 */
	public void render(Graphics g) {
		for(UIObject o : objects) {
			o.render(g);
		}
	}
	
	/**
	 * Metoda odpowiedzialna za zmiane przycisku po najechaniu na niego myszka.
	 * @param e MouseEvent
	 */
	public void onMouseMove(MouseEvent e) {
		for(UIObject o : objects) {
			o.onMouseMove(e);
		}
	}

	/**
	 * Metoda odpowiedzialna za wykonanie dzialania po nacisnieciu na przycisk.
	 * @param e MouseEvent
	 */
	public void onMouseRelease(MouseEvent e) {
		for(UIObject o : objects) {
			o.onMouseRelease(e);
		}
	}
}
