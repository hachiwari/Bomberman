package dev.game.bomberman;

/**
 * Klasa odpowiada za utworzenie i wystartowanie gry
 * @author Tomasz Kurek
 *
 */
public class Launcher {

	public static void main(String[] args){
		Game game = new Game("Bomberman", 544, 544);
		game.start();
	}
	
}
