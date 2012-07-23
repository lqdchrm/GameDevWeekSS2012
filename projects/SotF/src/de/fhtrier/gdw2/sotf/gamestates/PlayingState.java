package de.fhtrier.gdw2.sotf.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.sotf.interfaces.IEntity;

public class PlayingState extends BasicGameState {

	IEntity entity;
		
	public PlayingState(IEntity entity) {
		this.entity = entity;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	
		entity.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entity.update(delta);
	}

	@Override
	public int getID() {
		return GameStates.PLAYINGSTATE;
	}

}
