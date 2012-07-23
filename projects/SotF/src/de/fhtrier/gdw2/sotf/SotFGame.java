package de.fhtrier.gdw2.sotf;

import java.io.FileNotFoundException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.fhtrier.gdw2.sotf.gamestates.PlayingState;
import de.fhtrier.gdw2.sotf.impl.World;
import de.fhtrier.gdw2.sotf.interfaces.IEntity;
import de.fhtrier.gdw2.sotf.interfaces.IWorld;
import de.fhtrier.gdw2.sotf.network.ServerNetworkComp;
import de.fhtrier.gdw2.sotf.network.WorldState;

public class SotFGame extends StateBasedGame {

	public ServerNetworkComp server;
	
	public static void main(String[] args) {
		 try {
	            AppGameContainer app = new AppGameContainer(new SotFGame());
	            app.setTargetFrameRate(60);
	            app.setVSync(true);
	            app.setDisplayMode(800, 600, false);
	            app.setAlwaysRender(true);
	            app.start();
	        } catch (SlickException e) {
	            e.printStackTrace();
	        }		
	}
	
	private String tiledFile;
	private IWorld world;
	
	private IEntity entity;
	
	public SotFGame() {
		super("SurvivalOfTheFattest");
	}

	@Override
	protected void preUpdateState(GameContainer container, int delta) throws SlickException {
		server.handleIncoming();
	}
	
	@Override
	protected void postUpdateState(GameContainer container, int delta) throws SlickException {
		server.handleOutgoing();
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		tiledFile = "C:\\Projects\\GDW2\\assets\\testMap.tmx";

		try {
			WorldState worldState = new WorldState(container);
			server = new ServerNetworkComp(worldState, "localhost", 49999);
			world = new World(tiledFile);
			
			entity = worldState.entity;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		addState(new PlayingState(world, entity));
	}
}
