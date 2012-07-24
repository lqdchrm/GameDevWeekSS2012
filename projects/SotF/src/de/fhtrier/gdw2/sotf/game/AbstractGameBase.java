package de.fhtrier.gdw2.sotf.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.sotf.gamestates.PlayingState;
import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.states.WorldState;

public abstract class AbstractGameBase extends StateBasedGame {

	public INetworkComp networkComp;
	private WorldState worldState;
		
	public AbstractGameBase(String title) {
		super(title);
	}

	@Override
	protected void preUpdateState(GameContainer container, int delta) throws SlickException {
		networkComp.handleIncoming();
	}
	
	@Override
	protected void postUpdateState(GameContainer container, int delta) throws SlickException {
		networkComp.handleOutgoing();
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		worldState = new WorldState(container);
		networkComp = createNetworkComp(worldState);
					
		addState(new PlayingState(worldState));
	}
	
	public abstract INetworkComp createNetworkComp(WorldState worldState);
}
