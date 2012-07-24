package de.fhtrier.gdw2.sotf;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.sotf.game.AbstractGameBase;
import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.client.ClientNetworkComp;
import de.fhtrier.gdw2.sotf.network.states.WorldState;

public class ClientGame extends AbstractGameBase {

	public static void main(String[] args) {
		 try {
	            AppGameContainer app = new AppGameContainer(new ClientGame());
	            app.setTargetFrameRate(60);
	            app.setVSync(true);
	            app.setDisplayMode(800, 600, false);
	            app.setAlwaysRender(true);
	            app.start();
	        } catch (SlickException e) {
	            e.printStackTrace();
	        }		
	}
	
	public ClientGame() {
		super("SurvivalOfTheFattest - Client");
	}

	@Override
	public INetworkComp createNetworkComp(WorldState worldState) {
		return new ClientNetworkComp(worldState, "localhost", 49999);
	}
}
