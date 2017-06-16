package dev.game.bomberman.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 * Klasa implementujaca obiekt interfejsu uzytkownika.
 * @author Tomasz Kurek
 *
 */
public abstract class UIObject {

	protected int width, height;
	protected double x, y;
	protected boolean hovering = false;
	protected Rectangle bounds;
	
	/**
	 * Konstruuje nowy obiekt.
	 * @param x Polozenie obiektu na osi X
	 * @param y Polozenie obiektu na osi Y
	 * @param width Szerokosc obiektu
	 * @param height Wysokosc obiektu
	 */
	public UIObject(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle((int)x, (int)y, width, height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	/**
	 * Metoda odpowiedzialna za zmiane wygladu obiektu po najechaniu na niego myszka.
	 * @param e MouseEvent
	 */
	public void onMouseMove(MouseEvent e) {
		if (bounds.contains(e.getX(), e.getY())) {
			hovering = true;
		} else {
			hovering = false;
		}
	}
	
	/**
	 * Metoda odpowiedzialna za wykonanie dzialania po nacisnieciu na przycisk.
	 * @param e MouseEvent
	 */
	public void onMouseRelease(MouseEvent e) {
		if (hovering) {
			onClick();
		}
	}
}
