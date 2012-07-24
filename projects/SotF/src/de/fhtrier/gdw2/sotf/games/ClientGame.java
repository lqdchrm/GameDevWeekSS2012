package de.fhtrier.gdw2.sotf.games;

import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.client.ClientNetworkComp;
import de.fhtrier.gdw2.sotf.network.states.WorldState;

public class ClientGame extends AbstractGameBase {

	public ClientGame(String[] args) {
		super("SurvivalOfTheFattest - Client", args);
	}

	@Override
	public INetworkComp createNetworkComp(WorldState worldState, String ip, int port) {
		return new ClientNetworkComp(worldState, ip, port);
	}
}
