package de.fhtrier.gdw2.impl.data;

import java.util.HashMap;

import org.newdawn.slick.Color;

import de.fhtrier.gdw2.interfaces.data.IWorldData;
import de.fhtrier.gdw2.interfaces.objects.IItem;
import de.fhtrier.gdw2.interfaces.objects.IPlayer;

public class WorldData implements IWorldData {

	private Color backgroundColor;
	private HashMap<Integer, IPlayer> players;
	private HashMap<Integer, IItem> items;
	
	public WorldData() {
		backgroundColor = Color.black;
		players = new HashMap<>();
		items = new HashMap<>();
	}
	
	@Override
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}

	@Override
	public HashMap<Integer, IPlayer> getPlayers() {
		return players;
	}

	@Override
	public HashMap<Integer, IItem> getItems() {
		return items;
	}
}
