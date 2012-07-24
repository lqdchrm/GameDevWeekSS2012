package de.fhtrier.gdw2.sotf.games;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.sotf.gamestates.PlayingState;
import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.states.WorldState;

public abstract class AbstractGameBase extends StateBasedGame {

	public INetworkComp networkComp;
	private WorldState worldState;
	private String ip;
	private int port;
	
	
	public AbstractGameBase(String title, String[] args) {
		super(title);
	 	parseIP(args);
	}

	@Override
	protected void preUpdateState(GameContainer container, int delta) throws SlickException {
		networkComp.handleIncoming();
	}
	
	@Override
	protected void postUpdateState(GameContainer container, int delta) throws SlickException {
		networkComp.handleOutgoing();
		
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		worldState = new WorldState(container);
		networkComp = createNetworkComp(worldState, ip, port);
					
		addState(new PlayingState(worldState));
	}
	
	@Override
	protected void postRenderState(GameContainer container, Graphics g)
			throws SlickException {
		super.postRenderState(container, g);
		
		networkComp.render(g);
	}
	
	public abstract INetworkComp createNetworkComp(WorldState worldState, String ip, int port);

	private void parseIP(String[] args) {
		ip = "localhost";
	 	port = 49999;
	 
	 	if (args.length > 1) {
	 		ip = args[1];
	 		if (args.length > 2) {
	 			port = Integer.parseInt(args[2]);
	 		}
	 	}
	}
}
