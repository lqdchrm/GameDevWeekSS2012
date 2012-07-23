package de.fhtrier.gdw2.impl.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.impl.states.abstrct.GDWGameState;
import de.fhtrier.gdw2.interfaces.base.IGDWGame;
import de.fhtrier.gdw2.interfaces.states.IPlaying;

public class PlayingState extends GDWGameState implements IPlaying {
		
	public PlayingState() {
	}
	
	@Override
	public void init(GameContainer container, IGDWGame game)
			throws SlickException {
	}

	@Override
	public void render(Graphics graphics) throws SlickException {
		graphics.setBackground(Color.black);
		graphics.clear();
	}

	@Override
	public void update(int delta) throws SlickException {
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			exit();
		}
	}

	@Override
	public int getID() {
		return GameStates.PLAYINGSCREEN;
	}

	@Override
	public void exit() {
		game.enterState(GameStates.TITLESCREEN);
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
