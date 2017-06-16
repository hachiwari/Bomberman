package dev.game.bomberman.graphic;

import java.awt.image.BufferedImage;

import dev.game.bomberman.entities.Bomb;
import dev.game.bomberman.entities.Enemy;
import dev.game.bomberman.entities.Fire;
import dev.game.bomberman.entities.Player;
import dev.game.bomberman.tiles.Tile;

/**
 * Klasa odpowiadajaca za wczytanie i przetrzymywanie elementow graficznych.
 * @author Tomasz Kurek
 *
 */
public class Assets {

	/**
	 * Szerokosc i wysokosc przycisku.
	 * Domyslna maksymalna ilosc animacji.
	 */
	public static final int BTN_WIDTH = 200, BTN_HEIGHT = 60;
	public static final int MAX_ANIMATION = 3;

	/**
	 * Pociete elementy graficzne.
	 */
	public static BufferedImage[] player_down, player_right, player_up, player_left;
	public static BufferedImage[] enemy_down, enemy_right, enemy_up, enemy_left;
	public static BufferedImage wall, ground, stone, ruinWall, luckyBox;
	public static BufferedImage[] btnStart, btnHelp, btnExit;
	public static BufferedImage[] bomb;
	public static BufferedImage heart;
	public static BufferedImage fire_central, fire_left, fire_up, fire_right, fire_down;

	/**
	 * Metoda wycina elementy graficzne.
	 */
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite_sheet.png"));
		
		player_down = new BufferedImage[MAX_ANIMATION];
		player_right = new BufferedImage[MAX_ANIMATION];
		player_up = new BufferedImage[MAX_ANIMATION];
		player_left = new BufferedImage[MAX_ANIMATION];

		for(int ani = 0; ani < MAX_ANIMATION; ani++) {
			player_down[ani] = sheet.grabImage(1, 1+ani, Player.DEFAULT_SIZE);
			player_right[ani] = sheet.grabImage(2, 1+ani, Player.DEFAULT_SIZE);
			player_up[ani] = sheet.grabImage(3, 1+ani, Player.DEFAULT_SIZE);
			player_left[ani] = sheet.grabImage(4, 1+ani, Player.DEFAULT_SIZE);
		}
		
		enemy_down = new BufferedImage[MAX_ANIMATION];
		enemy_right = new BufferedImage[MAX_ANIMATION];
		enemy_up = new BufferedImage[MAX_ANIMATION];
		enemy_left = new BufferedImage[MAX_ANIMATION];

		for(int ani = 0; ani < MAX_ANIMATION * Enemy.DEFAULT_SIZE; ani+=Enemy.DEFAULT_SIZE) {
			enemy_down[ani/Enemy.DEFAULT_SIZE] = sheet.grabImage(180+ani, 0, Enemy.DEFAULT_SIZE, Enemy.DEFAULT_SIZE);
			enemy_right[ani/Enemy.DEFAULT_SIZE] = sheet.grabImage(180+ani, 28, Enemy.DEFAULT_SIZE, Enemy.DEFAULT_SIZE);
			enemy_up[ani/Enemy.DEFAULT_SIZE] = sheet.grabImage(180+ani, 56, Enemy.DEFAULT_SIZE, Enemy.DEFAULT_SIZE);
			enemy_left[ani/Enemy.DEFAULT_SIZE] = sheet.grabImage(180+ani, 84, Enemy.DEFAULT_SIZE,Enemy.DEFAULT_SIZE);
		}
		
		bomb = new BufferedImage[MAX_ANIMATION];
		
		for(int ani = 0; ani < MAX_ANIMATION * Bomb.DEFAULT_SIZE; ani+=Bomb.DEFAULT_SIZE) {
			bomb[ani/Bomb.DEFAULT_SIZE] = sheet.grabImage(84+ani, 96, Bomb.DEFAULT_SIZE, Bomb.DEFAULT_SIZE);
		}

		stone = sheet.grabImage(84, 32, Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE);
		wall = sheet.grabImage(84, 64, Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE);
		ground = sheet.grabImage(116, 0, Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE);
		ruinWall = sheet.grabImage(116, 32, Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE);
		luckyBox = sheet.grabImage(148, 32, Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE);
		
		btnStart = new BufferedImage[2];
		btnHelp = new BufferedImage[2];
		btnExit = new BufferedImage[2];
		
		btnStart[0] = sheet.grabImage(0, 128, BTN_WIDTH, BTN_HEIGHT);
		btnStart[1] = sheet.grabImage(0, 188, BTN_WIDTH, BTN_HEIGHT);
		btnHelp[0] = sheet.grabImage(0, 248, BTN_WIDTH, BTN_HEIGHT);
		btnHelp[1] = sheet.grabImage(0, 308, BTN_WIDTH, BTN_HEIGHT);
		btnExit[0] = sheet.grabImage(0, 368, BTN_WIDTH, BTN_HEIGHT);
		btnExit[1] = sheet.grabImage(0, 428, BTN_WIDTH, BTN_HEIGHT);
		
		heart = sheet.grabImage(148, 0, Tile.DEFAULT_SIZE, Tile.DEFAULT_SIZE);
		
		fire_central = sheet.grabImage(264, 0, Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE);
		fire_left = sheet.grabImage(264, 32, Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE);
		fire_up = sheet.grabImage(264, 64, Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE);
		fire_right = sheet.grabImage(264, 96, Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE);
		fire_down = sheet.grabImage(264, 128, Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE);
	}
}
