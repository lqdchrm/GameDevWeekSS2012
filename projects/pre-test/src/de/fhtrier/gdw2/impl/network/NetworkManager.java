package de.fhtrier.gdw2.impl.network;

import java.io.IOException;

import de.fhtrier.gdw2.interfaces.network.INetworkManager;

public class NetworkManager implements INetworkManager {

	private boolean isServer;
	
	private GameServer server;
	private GameClient client;
	
	public NetworkManager() {
		client = new GameClient();
		isServer = false;
	}
	
	
	@Override
	public boolean isServer() {
		return isServer;
	}
	
	@Override
	public void setAsServer() throws IOException {
		if (!isServer) {
			isServer = true;
			server = new GameServer();
		}
	}

	@Override
	public void setAsClient() {
		if (isServer)
		{
			isServer = false;
			if (server != null)
			{
				server.shutMeDown();
				server = null;
			}
		}
	}
}
