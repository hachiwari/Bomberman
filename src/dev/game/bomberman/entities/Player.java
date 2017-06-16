package dev.game.bomberman.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.bomberman.Handler;
import dev.game.bomberman.graphic.Animation;
import dev.game.bomberman.graphic.Assets;
import dev.game.bomberman.states.State;

/**
 * Klasa implementujaca gracza.
 * @author Tomasz Kurek
 *
 */
public class Player extends Character {

	/**
	 * Domyslny rozmiar gracza.
	 */
	public static final int DEFAULT_SIZE = 28;
	/**
	 * Domyslna ilosc bomb gracza.
	 */
	public static final int DEFAULT_BOMB_COUNT = 1;
	private Animation animationDown, animationRight, animationUp, animationLeft;
	private int bombCount;

	/**
	 * Konstruktor towrzy nowego gracza.
	 * @param handler Handler
	 * @param x Polozenie gracza na osi X
	 * @param y Polozenie gracza na osi Y
	 */
	public Player(Handler handler, double x, double y) {
		super(handler, x, y, DEFAULT_SIZE, DEFAULT_SIZE);
		this.handler = handler;
		this.bombCount = DEFAULT_BOMB_COUNT;

		setBounds();
		initAnimation();
	}

	/**
	 * Metoda wykonywana co klatke, odpowiadajaca z animacje i sprawdzenia nacisniec klawiszy.
	 */
	@Override
	public void tick() {
		animationTick();
		inputKeyBoard();
		move();
	}

	/**
	 * Metoda rysuje gracza.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAniamtionFrame(), (int)x, (int)y, width, height, null);
	}
	
	/**
	 * Metoda odpowiadajaca za smierc gracza.
	 */
	@Override
	public void die() {
		setCountOfLife(getCountOfLife() - 1);
		setPosition(handler.getWorld().spawnX, handler.getWorld().spawnY);
		
		if (getCountOfLife() <= 0) {
			State.gameOver();
		}
	}
	
	private void inputKeyBoard() {
		xMove = 0; yMove = 0;

		if (handler.getKeyManager().up)
			yMove -= speed;
		if (handler.getKeyManager().down)
			yMove += speed;
		if (handler.getKeyManager().left)
			xMove -= speed;
		if (handler.getKeyManager().right)
			xMove += speed;
		if (handler.getKeyManager().escape)
			State.setState(handler.getGame().menuState);
	}
	
	private void initAnimation() {
		animationDown = new Animation(500, Assets.player_down);
		animationRight = new Animation(500, Assets.player_right);
		animationUp = new Animation(500, Assets.player_up);
		animationLeft = new Animation(500, Assets.player_left);
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
		if (xMove < 0) {
			return animationLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animationRight.getCurrentFrame();
		} else if (yMove > 0) {
			return animationDown.getCurrentFrame();
		} else if (yMove < 0) {
			return animationUp.getCurrentFrame();
		} else {
			return animationDown.getCurrentFrame();
		}
	}

	/**
	 * Metoda zwraca ilosc bomb.
	 * @return Ilosc bomb
	 */
	public int getBombCount() {
		return bombCount;
	}

	/**
	 * Metoda ustawia nowa ilosc bomb.
	 * @param bombCount Nowa ilosc bomb
	 */
	public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
		//System.out.println("BOMB:" + this.bombCount);
	}
	
	/**
	 * Metoda zmniejsza ilosc bomb o 1.
	 */
	public void decreaseBombCount() {
		setBombCount(getBombCount() - 1);
	}
	
	/**
	 * Metoda powieksza ilosc bomb o 1.
	 */
	public void increaseBombCount() {
		setBombCount(getBombCount() + 1);
	}
	
	/**
	 * Metoda zwraca ilosc zyc gracza.
	 */
	public int getCountOfLife() {
		return countOfLife;
	}
	
	/**
	 * Metoda ustawia nowa ilosc zyc gracza.
	 */
	public void setCountOfLife(int count) {
		countOfLife = count;
	}
	
	/**
	 * Metoda ustawia polozenie oraz domyslna ilosc bomb i zyc.
	 * @param spawnX Polozenie gracza na osi X
	 * @param spawnY Polozenie gracza na osi Y
	 */
	public void newGame(int spawnX, int spawnY) {
		setPosition(spawnX, spawnY);
		setBombCount(DEFAULT_BOMB_COUNT);
		setCountOfLife(DEFAULT_COUNT_OF_LIFE);
	}
}
