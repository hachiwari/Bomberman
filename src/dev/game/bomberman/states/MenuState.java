package dev.game.bomberman.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.bomberman.Handler;
import dev.game.bomberman.graphic.Assets;
import dev.game.bomberman.graphic.ImageLoader;
import dev.game.bomberman.ui.ClickListener;
import dev.game.bomberman.ui.ImageButton;
import dev.game.bomberman.ui.UIManager;

/**
 * Klasa odpowiadajaca za rysowanie Menu gry.
 * @author Tomasz Kurek
 *
 */
public class MenuState extends State {

	private UIManager uiManager = null;
	private BufferedImage background = null;

	/**
	 * Tworzy przyciski.
	 * @param handler Handler
	 */
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		background = ImageLoader.loadImage("/textures/backgroundMenu.png");
		
		initButton();
	}

	/**
	 * Metoda wykonywana co klatke.
	 */
	@Override
	public void tick() {
		uiManager.tick();
	}

	/**
	 * Metoda rysuje Menu gry.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
		uiManager.render(g);
	}
	
	private void initButton() {
		uiManager.addObject(new ImageButton(handler.getGame().getWidth() / 2 - Assets.BTN_WIDTH / 2, 180, Assets.BTN_WIDTH, Assets.BTN_HEIGHT, Assets.btnStart, new ClickListener() {
			
			@Override
			public void onClick() {
				handler.getWorld().newGame("res/map/map0");
				State.setState(handler.getGame().gameState);
			}
		}));
		uiManager.addObject(new ImageButton(handler.getGame().getWidth() / 2 - Assets.BTN_WIDTH / 2, 280, Assets.BTN_WIDTH, Assets.BTN_HEIGHT, Assets.btnHelp, new ClickListener() {
			
			@Override
			public void onClick() {
				State.setState(handler.getGame().helpState);
			}
		}));
		uiManager.addObject(new ImageButton(handler.getGame().getWidth() / 2 - Assets.BTN_WIDTH / 2, 380, Assets.BTN_WIDTH, Assets.BTN_HEIGHT, Assets.btnExit, new ClickListener() {
			
			@Override
			public void onClick() {
				System.exit(1);
			}
		}));
	}
}
