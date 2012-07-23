package de.fhtrier.gdw2.interfaces.states;


public interface IServerLobbyScreen extends IGDWGameState {

	void selectMap();
	void startGame();
	void returnToTitleScreen();
}
