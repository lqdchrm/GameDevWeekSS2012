package de.fhtrier.gdw2.interfaces.states;


public interface IClientLobbyScreen extends IGDWGameState {
	void joinTeam();
	void ready();
	void returnToTitleScreen();
}
