package de.fhtrier.gdw2.impl.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.impl.states.abstrct.GDWGameState;
import de.fhtrier.gdw2.interfaces.base.IGDWGame;
import de.fhtrier.gdw2.interfaces.states.ITitleScreen;

public class TitleScreenState extends GDWGameState implements ITitleScreen {
				
	@Override
	public void init(GameContainer container, IGDWGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics graphics) throws SlickException {
		graphics.setBackground(Color.green);
		graphics.clear();
	}

	@Override
	public void update(int delta) throws SlickException {
		Input input = container.getInput();
		handleInput(delta, input);
	}

	@Override
	public int getID() {
		return GameStates.TITLESCREEN;
	}

	private void handleInput(int delta, Input input) throws SlickException {
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			exitGame();
		}
	}
	
	@Override
	public void exitGame() {
		container.exit();
	}

	@Override
	public void enter() throws SlickException {
	}

	@Override
	public void leave() throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
