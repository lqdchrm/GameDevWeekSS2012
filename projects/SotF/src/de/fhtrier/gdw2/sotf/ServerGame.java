package de.fhtrier.gdw2.sotf;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.sotf.game.AbstractGameBase;
import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.server.ServerNetworkComp;
import de.fhtrier.gdw2.sotf.network.states.WorldState;

public class ServerGame extends AbstractGameBase {

	public static void main(String[] args) {
		 try { 	
	            AppGameContainer app = new AppGameContainer(new ServerGame(args));
	            app.setTargetFrameRate(60);
	            app.setVSync(true);
	            app.setDisplayMode(800, 600, false);
	            app.setAlwaysRender(true);
	            app.start();
	        } catch (SlickException e) {
	            e.printStackTrace();
	        }		
	}
		
	public ServerGame(String[] args) {
		super("SurvivalOfTheFattest - Server", args);
	}

	@Override
	public INetworkComp createNetworkComp(WorldState worldState, String ip, int port) {
		return new ServerNetworkComp(worldState, ip, port);
	}

}
