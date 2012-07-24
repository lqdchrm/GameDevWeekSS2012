package de.fhtrier.gdw2.sotf.games;

import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.server.ServerNetworkComp;
import de.fhtrier.gdw2.sotf.network.states.WorldState;

public class ServerGame extends AbstractGameBase {
		
	public ServerGame(String[] args) {
		super("SurvivalOfTheFattest - Server", args);
	}

	@Override
	public INetworkComp createNetworkComp(WorldState worldState, String ip, int port) {
		return new ServerNetworkComp(worldState, ip, port);
	}

}
