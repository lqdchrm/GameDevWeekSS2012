package de.fhtrier.gdw2.impl.base;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.impl.network.NetworkManager;
import de.fhtrier.gdw2.interfaces.base.IGDWGame;
import de.fhtrier.gdw2.interfaces.network.INetworkManager;
import de.fhtrier.gdw2.interfaces.states.IGDWGameState;

public abstract class GDWGame extends StateBasedGame implements IGDWGame {

	private INetworkManager networkManager;

	public GDWGame(String name) {
		super(name);
		
		networkManager = new NetworkManager();
	}
		
	@Override
	public INetworkManager getNetwork() {
		return networkManager;
	}

	@Override
	public void addState(IGDWGameState state) {
		super.addState(state);
	}

	@Override
	public IGDWGameState getState(int id) {
		return (IGDWGameState)super.getState(id);
	}
	
	@Override
	public IGDWGameState getCurrentState() {
		return (IGDWGameState)super.getCurrentState();
	}

	@Override
	public void render(Graphics g) throws SlickException {
		super.render(getContainer(), g);
	}

	@Override
	public void update(int delta) throws SlickException {
		super.update(getContainer(), delta);
	}
}
