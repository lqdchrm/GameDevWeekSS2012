package de.fhtrier.gdw2.interfaces.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;

import de.fhtrier.gdw2.interfaces.base.IGDWGame;

public interface IGDWGameState extends GameState {
	
	void init(GameContainer container, IGDWGame game) throws SlickException;

	void render(Graphics g) throws SlickException;

	void update(int delta) throws SlickException;
	
	void enter() throws SlickException;
	
	void leave() throws SlickException;
}
