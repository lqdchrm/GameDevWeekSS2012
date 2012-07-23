package de.fhtrier.gdw2.interfaces.base;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public interface IUpdateable {
	void update(GameContainer container, int delta) throws SlickException;
}
