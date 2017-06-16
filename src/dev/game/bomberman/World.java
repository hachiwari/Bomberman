package dev.game.bomberman;

import java.awt.Graphics;
import java.util.Random;

import dev.game.bomberman.entities.EntityManager;
import dev.game.bomberman.entities.Player;
import dev.game.bomberman.states.State;
import dev.game.bomberman.tiles.Tile;
import dev.game.bomberman.ui.UIPlayerStatus;
import dev.game.bomberman.utils.Utils;

/**
 * Klasa odpowiadajaca za swiat gry.
 * @author Tomasz Kurek
 *
 */
public class World {

	/**
	 * Maksymalna ilosc wierszy.
	 */
	public int maxRow;
	/**
	 * Maksymalna ilosc kolumn.
	 */
	public int maxCol;
	/**
	 * Zmienna przechowujaca wspolrzedna osi X, na ktorej pojawia sie gracz.
	 */
	public int spawnX;
	/**
	 * Zmienna przechowujaca wspolrzedna osi Y, na ktorej pojawia sie gracz.
	 */
	public int spawnY;
	/**
	 * Ilosc obiektow do zniszczenia.
	 */
	public int blockCount;
	/**
	 * Ilosc przeciwnikow do zabicia.
	 */
	public int enemyCount;
	public int[][] enemySpawn;
	private int[][] grid;
	private EntityManager entityManager;
	private Handler handler;
	private UIPlayerStatus playerStatus;
	
	/**
	 * Konstruktor tworzy obiekt swiata gry.
	 * @param handler Handler
	 */
	public World(Handler handler) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 0, 0));
	}
	
	/**
	 * Metoda wykonywana co klatke.
	 */
	public void tick() {
		entityManager.tick();
		playerStatus.tick();
	}
	
	/**
	 * Metoda rysuje cala plansze gry, istoty oraz panel statusu gracza.
	 * @param g Obiekt rysujacy
	 */
	public void render(Graphics g) {
		for(int row = 0; row < maxRow; row++) {
			for(int col = 0; col < maxCol; col++) {
				getTile(col, row).render(g, col * Tile.DEFAULT_SIZE, row * Tile.DEFAULT_SIZE);
			}
		}
		
		entityManager.render(g);
		playerStatus.render(g);
	}
	
	/**
	 * Metoda zwraca obiekt planszy jaki znajduje sie na podanej kolumnie i wieszu.
	 * @param col Numer kolumny
	 * @param row Numer wiersza
	 * @return Obiekt planszy
	 */
	public Tile getTile(int col, int row) {
		Tile t = Tile.tiles[grid[col][row]];
		if (t == null)
			return Tile.wallTile;
		return t;
	}
	
	/**
	 * Metoda ustawia obiekt na podanej kolumnie i wierszy.
	 * @param col Numer kolumny
	 * @param row Numer wiersza
	 */
	public void setTile(int col, int row) {
		grid[col][row] = Tile.groundTile.getIndex();
	}
	
	/**
	 * Metoda zwraca obiekt Menadzera istotami
	 * @return Menadzer istot
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Metoda dodaje na mapie LuckyBox w losowym, dostepnym miejscu na mapie.
	 */
	private void addLuckyBox() {
		Random random = new Random();
		boolean good = false;
		int col = -1, row = -1;

		while(!good) {
			col = random.nextInt(17);
			row = random.nextInt(17);
			if (getTile(col, row).getIndex() == Tile.groundTile.getIndex()) {
				good = true;
			}
		}
		grid[col][row] = Tile.luckyBox.getIndex();
	}

	private void initWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		int i = 0;

		maxRow = Utils.parseToInt(tokens[i++]);
		maxCol = Utils.parseToInt(tokens[i++]);
		spawnX = Utils.parseToInt(tokens[i++]);
		spawnY = Utils.parseToInt(tokens[i++]);
		blockCount = Utils.parseToInt(tokens[i++]);
		enemyCount = Utils.parseToInt(tokens[i++]);
		
		enemySpawn = new int[enemyCount][2];

		for(int e = 0; e < enemyCount; e++) {
			enemySpawn[e][0] = Utils.parseToInt(tokens[i++]) * Tile.DEFAULT_SIZE;
			enemySpawn[e][1] = Utils.parseToInt(tokens[i++]) * Tile.DEFAULT_SIZE;
		}
		
		grid = new int[maxCol][maxRow];
		
		for(int row = 0; row < maxRow; row++) {
			for(int col = 0; col < maxCol; col++) {
				grid[col][row] = Utils.parseToInt(tokens[(col + row * maxCol) + i]);
			}
		}
		addLuckyBox();
	}
	
	/**
	 * Metoda zmniejsza ilosc obiektow o 1.
	 */
	public void decreaseBlockCount() {
		setBlockCount(this.blockCount - 1);
	}

	/**
	 * Metoda zwraca ilosc obiektow do zniszczenia.
	 * @return Ilosc obiektow
	 */
	public int getBlockCount() {
		return blockCount;
	}

	/**
	 * Metoda ustawia nowa ilosc obiektow do zniszczenia oraz w przypadku zniszczenia wszystkich inicjuje status wygranej gry.
	 * @param blockCount Nowa ilosc obiektow
	 */
	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
		
		if (this.blockCount <= 0 && this.enemyCount <= 0) {
			State.winGame();
		}
	}

	/**
	 * Metoda zwraca ilosc przeciwnikow do zabicia.
	 * @return Ilosc pozsotalych przeciwnikow
	 */
	public int getEnemyCount() {
		return enemyCount;
	}

	/**
	 * Metoda zmniejsza ilosc przeciwnkow do zabicia o 1.
	 */
	public void decreaseEnemyCount() {
		setEnemyCount(this.enemyCount - 1);
	}

	/**
	 * Metoda ustawia nowa ilosc przeciwnikow do zabicia oraz w przypadku zniszczenia wszystkich inicjuje status wygranej gry.
	 * @param enemyCount Ilosc przeciwnikow
	 */
	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
		
		if (this.blockCount <= 0 && this.enemyCount <= 0) {
			State.winGame();
		}
	}

	private void initPlayerStatus() {
		playerStatus = new UIPlayerStatus(handler);
	}

	/**
	 * Metoda wczytuje mape oraz panel statusu gracza.
	 * @param path Sciezka do mapy
	 */
	public void newGame(String path) {
		initWorld(path);
		initPlayerStatus();
		
		entityManager.newGame();
	}
}
