package dev.game.bomberman.tiles;

import dev.game.bomberman.graphic.Assets;

/**
 * Klasa odpowiadajaca za obiekt dzieki ktoremu po zniszczeniu automatycznie wygrywamy gre.
 * @author Tomasz Kurek
 *
 */
public class LuckyBox extends Tile {

	/**
	 * Konstruuje nowy LuckyBox o podanym indeksie.
	 * @param index Indeks
	 */
	public LuckyBox(int index) {
		super(Assets.luckyBox, index);
	}

	/**
	 * Metoda zwraca prawde w przypadku, gdy mozna po nim przejsc.
	 */
	@Override
	public boolean isSolid() {
		return true;
	}
	
	/**
	 * Metoda zwraca prawde w przypadku, gdy mozna obiekt zniszczyc za pomoca bomby.
	 */
	@Override
	public boolean canDestry() {
		return true;
	}
}
