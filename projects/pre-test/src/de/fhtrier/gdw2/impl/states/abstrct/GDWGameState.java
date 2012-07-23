package de.fhtrier.gdw2.impl.states.abstrct;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.interfaces.base.IGDWGame;
import de.fhtrier.gdw2.interfaces.states.IGDWGameState;

public abstract class GDWGameState extends BasicGameState implements IGDWGameState {

	protected GameContainer container;
	protected IGDWGame game;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		this.container = container;
		this.game = (IGDWGame)game;
		init(container, this.game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		update(delta);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		enter();
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.leave(container, game);
		leave();
	}
}
