package de.fhtrier.gdw2.sotf.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.sotf.interfaces.IEntity;
import de.fhtrier.gdw2.sotf.interfaces.IWorld;

public class PlayingState extends BasicGameState {

	IWorld world;
	IEntity entity;
		
	public PlayingState(IWorld world, IEntity entity) {
		if (world == null)
			throw new NullPointerException("world");
		
		this.world = world;
		this.entity = entity;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	
		world.render(g);
		entity.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		world.update(delta);
		entity.update(delta);
	}

	@Override
	public int getID() {
		return GameStates.PLAYINGSTATE;
	}

}
