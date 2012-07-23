package de.fhtrier.gdw2.impl.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.impl.states.abstrct.GDWGameState;
import de.fhtrier.gdw2.interfaces.base.IGDWGame;
import de.fhtrier.gdw2.interfaces.states.IServerLobbyScreen;

public class ServerLobbyScreenState extends GDWGameState implements IServerLobbyScreen {
	
	@Override
	public void init(GameContainer container, IGDWGame game)
			throws SlickException {
	}

	@Override
	public void render(Graphics g) throws SlickException {
		g.setBackground(Color.orange);
		g.clear();
	}

	@Override
	public void update(int delta) throws SlickException {
		Input input = container.getInput();
		handleInput(delta, input);
	}

	private void handleInput(int delta, Input input) {
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			returnToTitleScreen();
		}
		
		if (input.isKeyPressed(Input.KEY_S)) {
			startGame();
		}
	}
	
	@Override
	public void selectMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGame() {
		game.enterState(GameStates.PLAYINGSCREEN);
	}

	@Override
	public void returnToTitleScreen() {
		game.enterState(GameStates.TITLESCREEN);
	}

	@Override
	public int getID() {
		return GameStates.SERVERLOBBYSCREEN;
	}

	@Override
	public void enter() throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leave() throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
