package dev.game.bomberman.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import dev.game.bomberman.Handler;
import dev.game.bomberman.graphic.Assets;
import dev.game.bomberman.tiles.Tile;

/**
 * Klasa implementujaca Menadzer istot.
 * @author Tomasz Kurek
 *
 */
public class EntityManager {

	/**
	 * Obiekt handlera.
	 */
	public Handler handler;
	/**
	 * Obiekt gracza.
	 */
	public Player player;
	private ArrayList<Entity> entityContainer;
	private ArrayList<Fire> fireContainer;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
			if (a.getY() < b.getY())
				return -1;
			else if (b instanceof Player)
				return -1;
			return 1;
		}
	};
	
	/**
	 * Konsturktor tworzy Menadzer istot.
	 * @param handler Handler
	 * @param player Gracz
	 */
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entityContainer = new ArrayList<Entity>();
		fireContainer = new ArrayList<Fire>();
	}
	
	/**
	 * Metoda wykonywana co klatke.
	 */
	public synchronized void tick() {
		for(int i = 0; i < entityContainer.size(); i++){
			Entity e = entityContainer.get(i);
			e.tick();
		}
		for(int i = 0; i < fireContainer.size(); i++){
			Entity e = fireContainer.get(i);
			e.tick();
		}
		entityContainer.sort(renderSorter);
	}
	
	/**
	 * Metoda zwrace liste istot.
	 * @return Lista obiektow istot
	 */
	public ArrayList<Entity> getEntities() {
		return entityContainer;
	}
	
	/**
	 * Meotda zwraca liste ogni.
	 * @return Lista obietkow ognia
	 */
	public ArrayList<Fire> getFires() {
		return fireContainer;
	}

	/**
	 * Metoda odpowiadajaca za rysowanie istot i ogni.
	 * @param g Obiekt rysujacy
	 */
	public synchronized void render(Graphics g) {
		for(Entity e : entityContainer) {
			e.render(g);
			//g.setColor(Color.red);
			//g.fillRect((int)e.getX() + e.bounds.x, (int)e.getY() + e.bounds.y, e.bounds.width, e.bounds.height);
		}
		for(Entity e : fireContainer) {
			e.render(g);
			//g.setColor(Color.blue);
			//g.fillRect((int)e.getX() + e.bounds.x, (int)e.getY() + e.bounds.y, e.bounds.width, e.bounds.height);
		}
	}

	/**
	 * Metoda dodaje nowa istote do listy.
	 * @param e Istota
	 */
	public synchronized void addEntity(Entity e) {
		entityContainer.add(e);
	}
	
	/**
	 * Metoda usuwa podana istote z listy.
	 * @param e Istota do usuniecia
	 */
	public synchronized void removeEntity(Entity e) {
		entityContainer.remove(e);
	}

	/**
	 * Metoda dodaje bombe.
	 */
	public synchronized void addBomb() {
		if (player.getBombCount() <= 0) {
			return;
		}

		Entity bomb = new Bomb(handler, player.getCol() * Tile.DEFAULT_SIZE, player.getRow() * Tile.DEFAULT_SIZE, Bomb.DEFAULT_SIZE, Bomb.DEFAULT_SIZE);
		entityContainer.add(bomb);
		player.decreaseBombCount();
		
		Thread t = new Thread(new Runnable() {
	        public void run() {
	            new BombTimer(Bomb.TIME, bomb, handler);
	        }
	    });
	    t.start();
	}
	
	/**
	 * Metoda dodaje ogien.
	 * @param x Polozenie na osi X
	 * @param y Polozenie na osi Y
	 */
	public synchronized void addFire(double x, double y) {
		int col = getCol(x), row = getRow(y);

		createFire(new Fire(handler, (int)x, (int)y, Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE, Assets.fire_central));

		if (handler.getWorld().getTile(col, row-1).canDestry()) {
			createFire(new Fire(handler, (int)x, (int)(y - 32.0), Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE, Assets.fire_up));
		}
		if (handler.getWorld().getTile(col, row+1).canDestry()) {
			createFire(new Fire(handler, (int)x, (int)(y + 32.0), Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE, Assets.fire_down));
		}
		if (handler.getWorld().getTile(col-1, row).canDestry()) {
			createFire(new Fire(handler, (int)(x - 32.0), (int)y, Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE, Assets.fire_left));
		}
		if (handler.getWorld().getTile(col+1, row).canDestry()) {
			createFire(new Fire(handler, (int)(x + 32.0), (int)y, Fire.DEFAULT_SIZE, Fire.DEFAULT_SIZE, Assets.fire_right));
		}
	}
	
	private synchronized void createFire(Fire fire) {
		fireContainer.add(fire);
		
		Thread t = new Thread(new Runnable() {
	        public void run() {
	            new FireTimer(Fire.TIME, fire, handler);
	        }
	    });
	    t.start();
	}
	
	/**
	 * Metoda usuwa podany ogien z listy.
	 * @param fire Ogien do usuniecia
	 */
	public synchronized void removeFire(Fire fire) {
		fireContainer.remove(fire);
	}

	/**
	 * Metoda zwraca obiekt gracza.
	 * @return Obiekt gracza
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Metoda dodaje gracza i przeciwnikow do gry.
	 */
	public void newGame() {
		entityContainer.clear();
		getPlayer().newGame(handler.getWorld().spawnX, handler.getWorld().spawnY);
		addEntity(player);
		for(int i = 0; i < handler.getWorld().enemyCount; i++) {
			addEntity(new Enemy(handler, handler.getWorld().enemySpawn[i][0], handler.getWorld().enemySpawn[i][1], Enemy.DEFAULT_SIZE, Enemy.DEFAULT_SIZE));
		}
	}

	/**
	 * Metoda zwraca kolumne na ktorej znajduje sie ogien.
	 * @param x Polozenie na osi X
	 * @return Kolumna
	 */
	public int getCol(double x) {
		return (int)((x) / Fire.DEFAULT_SIZE);
	}
	
	/**
	 * Metoda zwraca wiersz na ktorej znajduje sie ogien.
	 * @param y Polozenie na osi Y
	 * @return Wiersz
	 */
	public int getRow(double y) {
		return (int)((y) / Fire.DEFAULT_SIZE);
	}
}
