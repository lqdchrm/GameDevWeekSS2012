package de.fhtrier.gdw2;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdw2.impl.base.GDWGame;
import de.fhtrier.gdw2.impl.states.ClientLobbyScreenState;
import de.fhtrier.gdw2.impl.states.PlayingState;
import de.fhtrier.gdw2.impl.states.ServerLobbyScreenState;
import de.fhtrier.gdw2.impl.states.TitleScreenState;
import de.fhtrier.gdw2.interfaces.base.IGDWGame;

public class TestGame extends GDWGame implements IGDWGame {

	private TitleScreenState titleState;
	private ClientLobbyScreenState clientLobbyState;
	private ServerLobbyScreenState serverLobbyState;
	private PlayingState playingState;
	
	public TestGame() {
		super("TestGame");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		titleState = new TitleScreenState();
		clientLobbyState = new ClientLobbyScreenState();
		serverLobbyState = new ServerLobbyScreenState();
		playingState = new PlayingState();
		
		addState(titleState);
		addState(clientLobbyState);
		addState(serverLobbyState);
		addState(playingState);
	}
	
	public static void main(String[] args) {
	
		try { 
			AppGameContainer container = new AppGameContainer(new TestGame()); 
			container.setDisplayMode(800,600,false); 
			container.start(); 
		} catch (SlickException e) { 
			e.printStackTrace(); 
		}
	}
}
