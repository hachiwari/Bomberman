package dev.game.bomberman.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.bomberman.Handler;
import dev.game.bomberman.graphic.ImageLoader;

/**
 * Klasa odpowiadajaca za rysowanie komunikatu o przegranej grze.
 * @author Tomasz Kurek
 *
 */
public class GameOver extends State {

	private BufferedImage background = null;

	/**
	 * Konstruuje nowy GameOver.
	 * @param handler Handler
	 */
	public GameOver(Handler handler) {
		super(handler);
		background = ImageLoader.loadImage("/textures/background.png");
	}

	/**
	 * Metoda wykonywana co klatke, sprawdza czy zostal nacisniety klawisz.
	 */
	@Override
	public void tick() {
		inputKeyBoard();
	}

	/**
	 * Metoda rysuje komunikat o przegranej grze.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
		g.setFont(new Font("label", Font.BOLD, 50));
		g.setColor(Color.black);
		g.drawString("GAME OVER", handler.getGame().getWidth() / 2 - 150, handler.getGame().getHeight() / 2);
		g.setFont(new Font("label", Font.BOLD, 30));
		g.drawString("[PRESS ESCAPE]", handler.getGame().getWidth() / 2 - 125, handler.getGame().getHeight() / 2 + 40);
	}

	private void inputKeyBoard() {
		if (handler.getKeyManager().escape)
			State.setState(handler.getGame().menuState);
	}
}
