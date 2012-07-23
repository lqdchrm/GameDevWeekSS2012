package de.fhtrier.gdw2.interfaces.base;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface IRenderable {
	void render(GameContainer container, Graphics graphics) throws SlickException;
}
