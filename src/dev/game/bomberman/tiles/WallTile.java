package dev.game.bomberman.tiles;

import dev.game.bomberman.graphic.Assets;

/**
 * Klasa odpowiadajaca za obiekt sciany, po ktorej nie mozna przejsc i nie jest zniszczalna przez bombe.
 * @author Tomasz Kurek
 *
 */
public class WallTile extends Tile {

	/**
	 * Konstruuje nowy StoneTile o podanym indeksie.
	 * @param index Indeks
	 */
	public WallTile(int index) {
		super(Assets.wall, index);
	}
}
