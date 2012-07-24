package de.fhtrier.gdw2.sotf.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.sotf.interfaces.IEntity;
import de.fhtrier.gdw2.sotf.network.states.WorldState;

public class PlayingState extends BasicGameState {

	WorldState worldState;
		
	public PlayingState(WorldState worldState) {
		this.worldState = worldState;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	
		for(IEntity e : worldState.entities)
			e.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (worldState.playerid > -1) {
			worldState.entities[worldState.playerid].update(delta);
		}
	}

	@Override
	public int getID() {
		return GameStates.PLAYINGSTATE;
	}

}
