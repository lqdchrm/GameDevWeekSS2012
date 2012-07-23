package de.fhtrier.gdw2.impl.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.impl.data.WorldData;
import de.fhtrier.gdw2.interfaces.data.IWorldData;
import de.fhtrier.gdw2.interfaces.objects.IWorld;

public class World implements IWorld {

	private IWorldData worldData;
	
	public World() {
		worldData = new WorldData();
	}
	
	@Override
	public IWorldData getData() {
		return worldData;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics graphics)
			throws SlickException {
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
	}
}
