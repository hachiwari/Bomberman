package dev.game.bomberman.graphic;

import java.awt.image.BufferedImage;

/**
 * Klasa implementujaca animacje.
 * Jej zadaniem jest zmiana indeksu aktualnego obrazka co pewien czas.
 * @author Tomasz Kurek
 *
 */
public class Animation {
	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	/**
	 * Konstruktor tworzy obiekt animacji, przechowujacy tablice obrazkow.
	 * @param speed Szybkosc zmiany [w milisekundach]
	 * @param frames Tablica obrazkow
	 */
	public Animation(int speed, BufferedImage[] frames){
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	/**
	 * Metoda wykonywana co klatke, co okreslony czas zmienia indeks.
	 */
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	/**
	 * Metoda zwraca aktualny obraz.
	 * @return Obraz
	 */
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
}