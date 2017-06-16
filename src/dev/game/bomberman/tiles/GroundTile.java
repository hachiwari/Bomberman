package dev.game.bomberman.tiles;

import dev.game.bomberman.graphic.Assets;

/**
 * Klasa odpowiadajaca za obiekt ziemi, po ktorym mozna sie poruszac.
 * @author Tomasz Kurek
 *
 */
public class GroundTile extends Tile {

	/**
	 * Konstruuje nowy GroundTile o podanym indeksie.
	 * @param index Indeks
	 */
	public GroundTile(int index) {
		super(Assets.ground, index);
	}

	/**
	 * Metoda zwraca prawde w przypadku, gdy mozna po nim przejsc.
	 */
	@Override
	public boolean isSolid() {
		return false;
	}
	
	/**
	 * Metoda zwraca prawde w przypadku, gdy mozna obiekt zniszczyc za pomoca bomby.
	 */
	@Override
	public boolean canDestry() {
		return true;
	}
}
