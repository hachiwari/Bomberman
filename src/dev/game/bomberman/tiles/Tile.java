package dev.game.bomberman.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Klasa implementujaca statyczny obiekt na mapie.
 * @author Tomasz Kurek
 *
 */
public class Tile {

	/**
	 * Domyslny rozmiar obiektu.
	 */
	public static final int DEFAULT_SIZE = 32;
	
	/**
	 * Lista rodzajow obiektu. 
	 */
	public static Tile[] tiles = new Tile[5];
	public static Tile groundTile = new GroundTile(0);
	public static Tile wallTile = new WallTile(1);
	public static Tile stoneTile = new StoneTile(2);
	public static Tile ruinWall = new RuinWall(3);
	public static Tile luckyBox = new LuckyBox(4);

	protected BufferedImage texture;
	protected final int index;
	
	/**
	 * Konstruuje obiekt.
	 * @param texture Obrazek
	 * @param index Indeks
	 */
	public Tile(BufferedImage texture, int index) {
		this.texture = texture;
		this.index = index;
		
		tiles[index] = this;
	}

	public void tick() {
		
	}
	
	/**
	 * Metoda rysuje obiekt o podanych wspolrzednych na mapie.
	 * @param g Obiekt rysujacy
	 * @param x Polozenie obiektu na osi X
	 * @param y Polozenie obiektu na osi Y
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, DEFAULT_SIZE, DEFAULT_SIZE, null);
	}
	
	/**
	 * Metoda zwraca indeks obiektu.
	 * @return Indeks
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Metoda zwraca prawde w przypadku, gdy mozna po nim przejsc.
	 * @return true
	 */
	public boolean isSolid() {
		return true;
	}
	
	/**
	 * Metoda zwraca prawde w przypadku, gdy mozna obiekt zniszczyc za pomoca bomby.
	 * @return false
	 */
	public boolean canDestry() {
		return false;
	}
}
