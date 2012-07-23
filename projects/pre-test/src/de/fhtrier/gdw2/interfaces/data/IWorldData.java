package de.fhtrier.gdw2.interfaces.data;

import java.util.HashMap;

import org.newdawn.slick.Color;

import de.fhtrier.gdw2.interfaces.objects.IItem;
import de.fhtrier.gdw2.interfaces.objects.IPlayer;

public interface IWorldData {
	
	Color getBackgroundColor();
	void setBackgroundColor(Color color);
	
	HashMap<Integer, IPlayer> getPlayers();
	HashMap<Integer, IItem> getItems();

	
}
