package dev.game.bomberman.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import dev.game.bomberman.Handler;
import dev.game.bomberman.tiles.Tile;

/**
 * Klasa abstrakcyjna odpowiadajaca za wszystkie istoty w grze.
 * @author Tomasz Kurek
 *
 */
public abstract class Entity {

	protected Handler handler;
	protected double x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected Random random;
	
	private int findNextDirection;
	
	/**
	 * Konstruktor tworzy nowa istote.
	 * @param handler Handler
	 * @param x Polozenie istoty na osi X
	 * @param y Polozenie istoty na osi Y
	 * @param width Szerokosc istoty
	 * @param height Wysokosc istoty
	 */
	public Entity(Handler handler,double x, double y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.findNextDirection = -1;
		
		bounds = new Rectangle(0, 0, width, height);
		random = new Random();
	}

	/**
	 * Metoda abstrakcyjna wykonywana co klatke.
	 */
	public abstract void tick();
	/**
	 * Metoda abstrakcyjna odpowiadajaca za rysowanie elementow.
	 * @param g Obiekt rysujacy
	 */
	public abstract void render(Graphics g);

	/**
	 * Metoda zwraca nowy kierunek poruszania.
	 * @return Kierunek poruszania
	 */
	public int getNextRandomDirection() {
		boolean emptyTile[] = getEmptyTile();
		boolean find = false;
		int temp = -1;

		while(!find) {
			temp = random.nextInt(4);
			if (emptyTile[temp]) {
				if (findNextDirection == temp) {
					findNextDirection = -1;
				} else {
					find = true;
				}
			}
		}
		findNextDirection = temp;
		return temp;
	}
	
	/**
	 * Metoda odnajduje wolne pola.
	 * @return Tablica zawierajaca informacje, w ktorym kierunku jest wolne pole
	 */
	public boolean[] getEmptyTile() {
		boolean emptyTile[] = new boolean[4];
		int col = getCol(), row = getRow();

		if (handler.getWorld().getTile(col, row-1) == Tile.groundTile) {
			emptyTile[0] = true;
		}
		if (handler.getWorld().getTile(col, row+1) == Tile.groundTile) {
			emptyTile[1] = true;
		}
		if (handler.getWorld().getTile(col-1, row) == Tile.groundTile) {
			emptyTile[2] = true;
		}
		if (handler.getWorld().getTile(col+1, row) == Tile.groundTile) {
			emptyTile[3] = true;
		}
		
		return emptyTile;
	}

	/**
	 * Metoda sprawdza, czy wystapi kolizja jak poruszy sie postac o podane przesuniecie.
	 * @param xOffset Przesuniecie w osi X
	 * @param yOffset Przesuniecie w osi Y
	 * @return Prawda, w przypadku wystapienia kolizji
	 */
	public boolean checkEntityCollisions(double xOffset, double yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) { continue; }
			else if (e instanceof Bomb) { continue; }
			
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metoda sprawdza, czy wstapi kolizja z ogniem jak poruszy sie postac o podane przesuniecie.
	 * @param xOffset Przesuniecie w osi X
	 * @param yOffset Przesuniecie w osi Y
	 * @return Prawda, w przypadku wstapienia kolizji
	 */
	public boolean checkEntityWithFireCollisions(double xOffset, double yOffset) {
		for(Fire e : handler.getWorld().getEntityManager().getFires()) {
			if (e.equals(this)) { continue; }

			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metoda sprawdza, czy wystapi kolizja z obiektem, ktory jest mozliwy do zniszczenia.
	 * @param col Numer kolumny
	 * @param row Numer wiersza
	 * @return Prawda, w przypadku wystapienia kolizji
	 */
	public boolean checkEntityWithTileCollisions(int col, int row) {
		if (handler.getWorld().getTile(col, row).getIndex() == Tile.ruinWall.getIndex()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metoda sprawdza, czy wystapi kolizja z obiektem, dzieki ktoremu wygrywamy gre.
	 * @param col Numer kolumny
	 * @param row Numer wiersza
	 * @return True/False
	 */
	public boolean checkEntityWithLuckyBoxCollisions(int col, int row) {
		if (handler.getWorld().getTile(col, row).getIndex() == Tile.luckyBox.getIndex()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metoda zwraca prostokat przesuniety o podane wymiary.
	 * @param xOffset Przesuniecie w osi X
	 * @param yOffset Przesuniecie w osi Y
	 * @return Prostokat
	 */
	public Rectangle getCollisionBounds(double xOffset, double yOffset) {
		return new Rectangle ((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	/**
	 * Metoda zwraca polozenie na osi X.
	 * @return Polozenie na osi X
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Metoda ustawia pozycje na osiach.
	 * @param x Polozenie na osi X
	 * @param y Polozenie na osi Y
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Metoda ustawia polozenie na osi X.
	 * @param x Polozene na osi X
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Metoda zwraca polozenie na osi Y	.
	 * @return Polozenie na osi Y
	 */
	public double getY() {
		return y;
	}

	/**
	 * Metoda ustawia polozenie na osi Y.
	 * @param y Polozenie na osi Y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Metoda zwraca szerokosc istoty.
	 * @return Szerokosc
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Metoda ustawia wysokosc istoty.
	 * @param width Wysokosc
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Metoda zwraca szerokosc istoty.
	 * @return Szerkosc
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Metoda ustawia szerokosc istoty.
	 * @param height Szerokosc
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Metoda zwraca kolumne na ktorej znajduje sie istota.
	 * @return Kolumna
	 */
	public int getCol() {
		return (int)((x + bounds.x) / Tile.DEFAULT_SIZE);
	}
	
	/**
	 * Metoda zwraca wiersz na ktorej znajduje sie istota.
	 * @return Wiersz
	 */
	public int getRow() {
		return (int)((y + bounds.y) / Tile.DEFAULT_SIZE);
	}
}

