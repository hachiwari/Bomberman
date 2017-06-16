package dev.game.bomberman.states;

import java.awt.Graphics;

import dev.game.bomberman.Handler;

/**
 * Klasa odpowiadajaca za status gry.
 * @author Root
 *
 */
public abstract class State {
	
	private static State currentState = null;
	private static boolean isPaussed = false;
	protected static Handler handler = null;
	
	/**
	 * Konstruuje nowy State.
	 * @param handler Handler
	 */
	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	
	/**
	 * Zwraca astawia aktualny status gry.
	 * @param state Nowy status gry
	 */
	public static void setState(State state) {
		currentState = state;
	}
	
	/**
	 * Zwraca aktualny status gry.
	 * @return Stan gry.
	 */
	public static State getState() {
		return currentState;
	}
	
	/**
	 * Metoda zwraca prawde w przypadku, gdy gra jest wstrzymana.
	 * @return True/False
	 */
	public static boolean isPaussed() {
		return isPaussed;
	}

	/**
	 * Metoda ustawia pause w grze lub ja wylacza.
	 * @param isPaussed True/False
	 */
	public static void setPaussed(boolean isPaussed) {
		State.isPaussed = isPaussed;
	}

	/**
	 * Metoda ustawia status gry jako Przegrana gra.
	 */
	public static void gameOver() {
		setState(handler.getGame().gameOver);
	}
	
	/**
	 * Metoda ustawia status gry jako Wygrana gra.
	 */
	public static void winGame() {
		setState(handler.getGame().winGame);
	}
}
