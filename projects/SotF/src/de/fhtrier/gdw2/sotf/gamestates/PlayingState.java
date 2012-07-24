package de.fhtrier.gdw2.sotf.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.sotf.interfaces.IEntity;

public class PlayingState extends BasicGameState {

	IEntity[] entities;
		
	public PlayingState(IEntity[] entities) {
		this.entities = entities;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	
		for(IEntity e : entities)
			e.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		for (IEntity e : entities)
			e.update(delta);
	}

	@Override
	public int getID() {
		return GameStates.PLAYINGSTATE;
	}

}
