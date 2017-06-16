package dev.game.bomberman.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.bomberman.Handler;
import dev.game.bomberman.graphic.Animation;
import dev.game.bomberman.graphic.Assets;
import dev.game.bomberman.states.State;

/**
 * Klasa odpowiadajaca za przeciwnikow w grze.
 * @author Tomasz Kurek
 *
 */
public class Enemy extends Character {

	/**
	 * Domyslny rozmiar przecinika.
	 */
	public static final int DEFAULT_SIZE = 28;
	private static final int TIME_TO_NEW_DIRECTION = 300;
	private final double SPEED = speed * 1/5;

	private Animation animationDown, animationRight, animationUp, animationLeft;
	private int direction;
	private int timePerNewDirection = 0;

	/**
	 * Konstruktor tworzy nowego przecinika.
	 * @param handler Handler
	 * @param x Polozenie przeciwnika na osi X
	 * @param y Polozenie przeciwnika na osi Y
	 * @param width Szerokosc przeciwnika
	 * @param height Wysokosc przeciwnika
	 */
	public Enemy(Handler handler, double x, double y, int width, int height) {
		super(handler, x, y, width, height);

		setBounds();
		initAnimation();
	}

	/**
	 * Metoda wykonywana co klatke odpowiada za animacje i ruch przeciwnika.
	 */
	@Override
	public void tick() {
		if (State.isPaussed()) { return; }

		timePerNewDirection++;
		animationTick();
		updateMove();

		if (timePerNewDirection == TIME_TO_NEW_DIRECTION) {
			changeDirection();
			timePerNewDirection = 0;
		}

		move();
	}

	/**
	 * Metoda rysuje przeciwnika.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAniamtionFrame(), (int)x, (int)y, width, height, null);
	}
	
	/**
	 * Metoda odpowiada za smierc przeciwnika.
	 */
	@Override
	public void die() {
		handler.getWorld().getEntityManager().removeEntity(this);
		handler.getWorld().decreaseEnemyCount();
	}

	private void updateMove() {
		xMove = 0; yMove = 0;

		if (direction == 0) { // up
			yMove -= SPEED;
		} else if (direction == 1) { // down
			yMove += SPEED;
		} else if (direction == 2) { // left
			xMove -= SPEED;
		} else if (direction == 3) { // right
			xMove += SPEED;
		}
	}
	
	private void changeDirection() {
		direction = getNextRandomDirection();
	}
	
	private void initAnimation() {
		animationDown = new Animation(500, Assets.enemy_down);
		animationRight = new Animation(500, Assets.enemy_right);
		animationUp = new Animation(500, Assets.enemy_up);
		animationLeft = new Animation(500, Assets.enemy_left);
	}
	
	private void animationTick() {
		animationDown.tick();
		animationRight.tick();
		animationUp.tick();
		animationLeft.tick();
	}
	
	private void setBounds() {
		bounds.x = 5;
		bounds.y = 10;
		bounds.width = 18;
		bounds.height = 18;
	}

	private BufferedImage getCurrentAniamtionFrame() {
		if (direction == 2) {
			return animationLeft.getCurrentFrame();
		} else if (direction == 3) {
			return animationRight.getCurrentFrame();
		} else if (direction == 1) {
			return animationDown.getCurrentFrame();
		} else if (direction == 0) {
			return animationUp.getCurrentFrame();
		} else {
			return animationDown.getCurrentFrame();
		}
	}
}
