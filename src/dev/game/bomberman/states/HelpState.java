package dev.game.bomberman.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.bomberman.Handler;
import dev.game.bomberman.graphic.ImageLoader;

/**
 * Klasa odpowiadajaca za rysowanie okna pomocy.
 * @author Tomasz Kurek
 *
 */
public class HelpState extends State {

	private BufferedImage background = null;

	/**
	 * Konstruuje nwoy HelpState.
	 * @param handler Handler
	 */
	public HelpState(Handler handler) {
		super(handler);
		background = ImageLoader.loadImage("/textures/backgroundHelp.png");
	}

	/**
	 * Metoda wykonywana co klatke, sprawdza czy zostal nacisniety klawisz.
	 */
	@Override
	public void tick() {
		inputKeyBoard();
	}

	/**
	 * Metoda rysuje okno pomocy.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
		g.setColor(Color.black);
		g.setFont(new Font("label", Font.BOLD, 30));
		g.drawString("BOMB", handler.getGame().getWidth() / 2 + 35, handler.getGame().getHeight() / 2 + 40);
		g.drawString("[PRESS ESCAPE]", handler.getGame().getWidth() / 2 - 125, handler.getGame().getHeight() / 2 + 200);
	}

	private void inputKeyBoard() {
		if (handler.getKeyManager().escape)
			State.setState(handler.getGame().menuState);
	}
}
