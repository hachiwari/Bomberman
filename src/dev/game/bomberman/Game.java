package dev.game.bomberman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import dev.game.bomberman.display.Display;
import dev.game.bomberman.graphic.Assets;
import dev.game.bomberman.input.KeyManager;
import dev.game.bomberman.input.MouseManager;
import dev.game.bomberman.states.GameOver;
import dev.game.bomberman.states.GameState;
import dev.game.bomberman.states.HelpState;
import dev.game.bomberman.states.MenuState;
import dev.game.bomberman.states.State;
import dev.game.bomberman.states.WinGame;

/**
 * Klasa odpowiada za uruchomienie watku gry.
 * @author Tomasz Kurek
 *
 */
public class Game implements Runnable {

	/**
	 * Nazwa okna
	 */
	public String title;
	/**
	 * Szerokosc okna
	 */
	public int width;
	/**
	 * Wysokosc okna
	 */
	public int height;

	private boolean running = false;

	private Display display = null;
	private Thread thread = null;
	private BufferStrategy bs = null;
	private Graphics g = null;
	private KeyManager keyManager = null;
	private MouseManager mouseManager = null;

	/**
	 * Obiekt odpowiedzialny za rozgrywke.
	 */
	public State gameState = null;
	/**
	 * Obiekt odpowiedzialny za menu gry.
	 */
	public State menuState = null;
	/**
	 * Obiekt odpowiedzialny za pomoc gry.
	 */
	public State helpState = null;
	/**
	 * Obiekt odpowiedzialny za koniec gry.
	 */
	public State gameOver = null;
	/**
	 * Obiekt odpowiedzialny za wygrana.
	 */
	public State winGame = null;
	
	private Handler handler = null;

	/**
	 * Konstruktor tworzy nowy obiekt Game.
	 * @param title Nazwa okna
	 * @param width Szerokosc okna
	 * @param height Wysokosc okna
	 */
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	private void init(){
		Assets.init();

		handler = new Handler(this);
		keyManager = new KeyManager(handler);
		mouseManager = new MouseManager();
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		helpState = new HelpState(handler);
		gameOver = new GameOver(handler);
		winGame = new WinGame(handler);

		State.setState(menuState);
	}

	private void tick() {
		if (keyManager != null) {
			keyManager.tick();
		}

		if (State.getState() != null) {
			State.getState().tick();
		}
	}
	
	private void paused(Graphics g) {
		g.setFont(new Font("label", Font.BOLD, 50));
		g.setColor(Color.black);
		g.drawString("PAUSED", getWidth() / 2 - 100, getHeight() / 2);
	}
	
	private void render() throws IOException {
		if (State.getState() == null) { return; }

		bs = display.getCanvas().getBufferStrategy();

		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		if (State.isPaussed()) {
			paused(g);
		} else {
			g.clearRect(0, 0, width, height);
			State.getState().render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	/**
	 * Metoda odpowiedzialna prace calej gry.
	 * W petli wyswietla obraz gry.
	 */
	@Override
	public void run(){
		long lastTime = System.nanoTime();
		final double fps = 60.0;
		double ns = 1000000000 / fps;
		double delta = 0;
		int ticks = 0, frames = 0;
		long timer = System.currentTimeMillis(), now = 0;

		init();

		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}

			try {
				render();
			} catch (IOException e) {
				e.printStackTrace();
			}
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("TIC:" + ticks + " FPS:" + frames);
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	/**
	 * Metoda odpowiada za wystartowanie watku gry
	 */
	public synchronized void start(){
		if(running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Metoda odpowiada za blokade watku
	 */
	public synchronized void stop(){
		if(!running) {
			return;
		}

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda zwraca obiekt menadzera klaawiatury
	 * @return Menadzer klawiatury
	 */
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	/**
	 * Metoda zwraca obiekt menadzera myszki
	 * @return Menadzer myszki
	 */
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	/**
	 * Metoda zwraca szerokosc okna
	 * @return Szerokosc okna
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Metoda zwraca wysokosc okna
	 * @return Wysokosc okna
	 */
	public int getHeight() {
		return height;
	}
}