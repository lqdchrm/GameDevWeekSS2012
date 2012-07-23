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
import de.fhtrier.gdw2.sotf.network.ClientNetworkComp;
import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.WorldState;

public class FoodmanGameClient extends StateBasedGame {

	public INetworkComp network;
	
	public static void main(String[] args) {
		 try {
	            AppGameContainer app = new AppGameContainer(new FoodmanGameClient());
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
	
	public FoodmanGameClient() {
		super("SurvivalOfTheFattest - Client");
	}

	@Override
	protected void preUpdateState(GameContainer container, int delta) throws SlickException {
		network.handleIncoming();
	}
	
	@Override
	protected void postUpdateState(GameContainer container, int delta) throws SlickException {
		network.handleOutgoing();
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		tiledFile = "C:\\Projects\\GDW2\\assets\\testMap.tmx";

		try {
			WorldState worldState = new WorldState(container);
			network = new ClientNetworkComp(worldState, "localhost", 49999);
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
