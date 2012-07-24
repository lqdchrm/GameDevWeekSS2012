package de.fhtrier.gdw2.sotf.network.states;

import org.newdawn.slick.GameContainer;

import de.fhtrier.gdw2.sotf.impl.Entity;

public class WorldState {
	public Entity[] entities;
	public int playerid;
	
	public WorldState(GameContainer container) {
		entities = new Entity[8];
		for(int i=0; i<8; ++i) {
			entities[i] = new Entity(container);
		
		}
		playerid = 0;
	}
}
