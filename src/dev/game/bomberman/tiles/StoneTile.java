package dev.game.bomberman.tiles;

import dev.game.bomberman.graphic.Assets;

/**
 * Klasa odpowiadajaca za obiekt kamienia, po ktorym nie mozna przejsc i nie jest zniszczalny przez bombe.
 * @author Tomasz Kurek
 *
 */
public class StoneTile extends Tile {

	/**
	 * Konstruuje nowy StoneTile o podanym indeksie.
	 * @param index Indeks
	 */
	public StoneTile(int index) {
		super(Assets.stone, index);
		
	}
}
