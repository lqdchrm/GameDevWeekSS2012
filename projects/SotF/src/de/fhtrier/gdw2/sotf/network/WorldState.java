package de.fhtrier.gdw2.sotf.network;

import org.newdawn.slick.GameContainer;

import de.fhtrier.gdw2.sotf.impl.Entity;

public class WorldState {
	public Entity entity;
	
	public WorldState(GameContainer container) {
		entity = new Entity(container);
	}
}
