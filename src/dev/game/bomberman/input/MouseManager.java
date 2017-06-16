package dev.game.bomberman.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.game.bomberman.states.State;
import dev.game.bomberman.ui.UIManager;

/**
 * Klasa implementujaca Menadzer myszki.
 * @author Tomasz Kurek
 *
 */
public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	/**
	 * Metoda ustawia Menadzer interfejsu uzytkownika
	 * @param uiManager Menadzer interfejsu uzytkownika
	 */
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	/**
	 * Metoda zwraca prawde, gdy jest wcisniety LPM lub falsz w przeciwnym przypadku.
	 * @return False/True
	 */
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	/**
	 * Metoda zwraca prawde, gdy jest wcisniety PRM lub falsz w przeciwnym przypadku.
	 * @return False/True
	 */
	public boolean isRightPressed() {
		return rightPressed;
	}

	/**
	 * Metoda zwraca polozenie myszki na osi X
	 * @return Polozenie myszki na osi X
	 */
	public int getMouseX() {
		return mouseX;
	}
	
	/**
	 * Metoda zwraca polozenie myszki na osi Y
	 * @return Polozenie myszki na osi Y
	 */
	public int getMouseY() {
		return mouseY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (State.isPaussed()) { return; }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (State.isPaussed()) { return; }

		mouseX = e.getX();
		mouseY = e.getY();
		
		if (uiManager != null) {
			uiManager.onMouseMove(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (State.isPaussed()) { return; }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (State.isPaussed()) { return; }
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (State.isPaussed()) { return; }
	}

	/**
	 * Metoda wykonywana przy nacisnieciu przycisku myszki
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (State.isPaussed()) { return; }

		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = true;
		}
	}

	/**
	 * Metoda wykonywana przy puszczeniu przucisku myszki
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (State.isPaussed()) { return; }

		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = false;
		}
		
		if (uiManager != null) {
			uiManager.onMouseRelease(e);
		}
	}
}
