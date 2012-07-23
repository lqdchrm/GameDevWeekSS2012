package de.fhtrier.gdw2.sotf.impl;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.fhtrier.gdw2.sotf.interfaces.IEntity;

public class Entity implements IEntity {

	public Vector2f position;
	GameContainer container;
	
	public Entity(GameContainer container) {
		position = new Vector2f(200,200);
		this.container = container;
	}
	
	@Override
	public void render(Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.drawOval(position.x, position.y, 20, 20);
	}

	@Override
	public void update(int delta) throws SlickException {
		Input input = container.getInput();
		
		if (input.isKeyDown(Input.KEY_LEFT)) {
			position.x -= delta * 50/1000.0f;
		}
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			position.x += delta * 50/1000.0f;
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			position.y -= delta * 50/1000.0f;
		}
		
		if (input.isKeyDown(Input.KEY_DOWN)) {
			position.y += delta * 50/1000.0f;
		}
	}

}
