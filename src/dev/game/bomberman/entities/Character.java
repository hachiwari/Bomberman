package dev.game.bomberman.entities;

import dev.game.bomberman.Handler;
import dev.game.bomberman.tiles.Tile;

/**
 * Klasa abstrakcyjna odpowiadajaca za postacie w grze.
 * @author Tomasz Kurek
 *
 */
public abstract class Character extends Entity {
	
	/**
	 * Domyslna ilosc zyc postaci.
	 */
	public static final int DEFAULT_COUNT_OF_LIFE = 3;
	/**
	 * Domyslna predkosc postaci.
	 */
	public static final double DEFAULT_SPEED = 2.0f;

	protected int countOfLife;
	protected double speed;
	protected double xMove;
	protected double yMove;

	/**
	 * Konstrukor tworzy nowa postac.
	 * @param handler Handler
	 * @param x Polozenie postaci na osi X
	 * @param y Polozenie postaci na osi Y
	 * @param width Szerokosc postaci
	 * @param height Wysokosc postaci
	 */
	public Character(Handler handler, double x, double y, int width, int height) {
		super(handler, x, y, width, height);
		countOfLife = DEFAULT_COUNT_OF_LIFE;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	/**
	 * Metoda abstrkcyjna odpowiadajaca za smierc postaci.
	 */
	public abstract void die();
	
	/**
	 * Metoda odpowiada za ruch postaci.
	 */
	public void move() {
		if (!checkEntityCollisions(xMove, 0f) && !checkEntityWithFireCollisions(xMove, 0f)) {
			moveX();
		} else {
			die();
		}
		if (!checkEntityCollisions(0f, yMove) && !checkEntityWithFireCollisions(xMove, 0f)) {
			moveY();
		}else {
			die();
		}
	}
	
	private void moveX() {
		if (xMove > 0) {
			movingRight();
		} else if (xMove < 0) {
			movingLeft();
		}
	}
	
	private void moveY() {
		if (yMove > 0) {
			movingDown();
		} else if (yMove < 0) {
			movingUp();
		}
	}
	
	private void movingRight() {
		int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.DEFAULT_SIZE;
		if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.DEFAULT_SIZE) &&
				!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.DEFAULT_SIZE)) {
			x += xMove;
		} else {
			x = tx * Tile.DEFAULT_SIZE - bounds.x - bounds.width - 1;
		}
	}
	
	private void movingLeft() {
		int tx = (int) (x + xMove + bounds.x) / Tile.DEFAULT_SIZE;
		if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.DEFAULT_SIZE) &&
				!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.DEFAULT_SIZE)) {
			x += xMove;
		} else {
			x = tx * Tile.DEFAULT_SIZE + Tile.DEFAULT_SIZE - bounds.x;
		}
	}
	
	private void movingDown() {
		int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.DEFAULT_SIZE;
		if (!collisionWithTile((int) (x + bounds.x) / Tile.DEFAULT_SIZE, ty) &&
				!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.DEFAULT_SIZE, ty)) {
			y += yMove;
		} else {
			y = ty * Tile.DEFAULT_SIZE - bounds.y - bounds.height - 1;
		}
	}
	
	private void movingUp() {
		int ty = (int) (y + yMove + bounds.y) / Tile.DEFAULT_SIZE;
		if (!collisionWithTile((int) (x + bounds.x) / Tile.DEFAULT_SIZE, ty) &&
				!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.DEFAULT_SIZE, ty)) {
			y += yMove;
		} else {
			y = ty * Tile.DEFAULT_SIZE + Tile.DEFAULT_SIZE - bounds.y;
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	/**
	 * Metoda zwraca predkosc postaci.
	 * @return Predkosc postaci.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Metoda ustawia predkosc postaci.
	 * @param speed Nowa predkosc postaci.
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Metoda zwraca aktualna ilosc zyc postaci.
	 * @return Ilosc zyc postaci.
	 */
	public int getCountOfLife() {
		return countOfLife;
	}

	/**
	 * Metoda ustawia ilosc zyc postaici.
	 * @param countOfLife Nowa ilosc zyc postaci.
	 */
	public void setCountOfLife(int countOfLife) {
		this.countOfLife = countOfLife;
	}
}
