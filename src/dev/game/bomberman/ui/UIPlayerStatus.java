package dev.game.bomberman.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.bomberman.Handler;
import dev.game.bomberman.graphic.Assets;
import dev.game.bomberman.graphic.ImageLoader;
import dev.game.bomberman.tiles.Tile;

/**
 * Klasa implementujaca panel statusu gracza.
 * Panel wyswietla ile zyc ma gracz.
 * @author Tomasz Kurek
 *
 */
public class UIPlayerStatus {
	
	private Handler handler;
	private int countOfLife;
	private BufferedImage background = null;
	
	/**
	 * Konstruuje nowy panel.
	 * @param handler Handler
	 */
	public UIPlayerStatus(Handler handler) {
		this.handler = handler;
		background = ImageLoader.loadImage("/textures/backgroundStatus.png");
	}
	
	/**
	 * Metoda wykonywana co klatke, aktualizuje ilosc zyc gracza.
	 */
	public void tick() {
		countOfLife = handler.getWorld().getEntityManager().getPlayer().getCountOfLife();
	}
	
	/**
	 * Metoda rysuje panel statusu gracza.
	 * @param g Obiekt rysujacy
	 */
	public void render(Graphics g) {
		g.drawImage(background, handler.getGame().getWidth() / 2 - 48, 0, 96, 32, null);
		for(int i = 0; i < countOfLife; i++) {
			g.drawImage(Assets.heart, handler.getGame().getWidth() / 2 - 48 + (i * Tile.DEFAULT_SIZE), 0, Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE, null);
		}
	}

}
