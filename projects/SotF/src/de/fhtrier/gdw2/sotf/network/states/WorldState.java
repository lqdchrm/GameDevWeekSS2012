package de.fhtrier.gdw2.sotf.network.states;

import org.newdawn.slick.GameContainer;

import de.fhtrier.gdw2.sotf.impl.Entity;
import de.fhtrier.gdw2.sotf.settings.GlobalSettings;

public class WorldState {
	public Entity[] entities;
	public int playerid;
	
	public WorldState(GameContainer container) {
		entities = new Entity[GlobalSettings.MAX_PLAYERS];
		for(int i=0; i<GlobalSettings.MAX_PLAYERS; ++i) {
			entities[i] = new Entity(container);
		}
		playerid = 0;
	}
}
