package de.fhtrier.gdw2.interfaces.base;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.transition.Transition;

import de.fhtrier.gdw2.interfaces.network.INetworkManager;
import de.fhtrier.gdw2.interfaces.states.IGDWGameState;

public interface IGDWGame extends Game {

	INetworkManager getNetwork();
	
	void addState(IGDWGameState state);
	boolean closeRequested();
	void enterState(int id);
	void enterState(int id, Transition leave, Transition enter);
	GameContainer getContainer();
	IGDWGameState getCurrentState();
	int getCurrentStateID();
	IGDWGameState getState(int id);
	int getStateCount();
	String getTitle();
	void init(GameContainer container) throws SlickException;
	void initStatesList(GameContainer container) throws SlickException;
	void render(Graphics g) throws SlickException;
	void update(int delta) throws SlickException;
}
