package de.fhtrier.gdw2.interfaces.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.interfaces.base.IRenderable;
import de.fhtrier.gdw2.interfaces.base.IUpdateable;
import de.fhtrier.gdw2.interfaces.data.IWorldData;

public interface IWorld extends IUpdateable, IRenderable {
	
	IWorldData getData();
	
	void init(GameContainer container) throws SlickException;
}
