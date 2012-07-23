package de.fhtrier.gdw2.sotf.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import de.fhtrier.gdw2.sotf.interfaces.IWorld;

public class World implements IWorld {
	
	TiledMap map;
	
	public World(String file) throws FileNotFoundException, SlickException {
		InputStream inStream = new FileInputStream(file);
		map = new TiledMap(inStream, "C:\\Projects\\GDW3\\assets");
	}
	
	@Override
	public void render(Graphics g) throws SlickException {
		if (map != null)
			map.render(0, 0);
	}

	@Override
	public void update(int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
