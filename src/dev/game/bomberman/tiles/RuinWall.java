package dev.game.bomberman.tiles;

import dev.game.bomberman.graphic.Assets;

/**
 * Klasa odpowiadajaca za obiekt sciany, ktora mozna zniszczyc.
 * @author Tomasz Kurek
 *
 */
public class RuinWall extends Tile {

	/**
	 * Konstruuje nowy RuinWall o podanym indeksie.
	 * @param index Indeks
	 */
	public RuinWall(int index) {
		super(Assets.ruinWall, index);
	}

	/**
	 * Metoda zwraca prawde w przypadku, gdy mozna obiekt zniszczyc za pomoca bomby.
	 */
	@Override
	public boolean canDestry() {
		return true;
	}
}
