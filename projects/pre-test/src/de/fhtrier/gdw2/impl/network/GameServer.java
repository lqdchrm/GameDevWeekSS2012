package de.fhtrier.gdw2.impl.network;

import gdwNet.server.BasicClientConnection;
import gdwNet.server.BasicServer;
import gdwNet.server.ConnectionInfo;

import java.io.IOException;
import java.nio.ByteBuffer;

import de.fhtrier.gdw2.impl.GameSettings;
import de.fhtrier.gdw2.interfaces.network.IGameServer;

public class GameServer extends BasicServer implements IGameServer {
		
	public GameServer() throws IOException {
		super(GameSettings.ServerSettings.MAX_PLAYERS);
	}

	@Override
	protected void playerDisconnected(BasicClientConnection client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void playerReconnected(BasicClientConnection client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected BasicClientConnection incomingConnection(ConnectionInfo info,
			ByteBuffer data) {
		// TODO Auto-generated method stub
		return null;
	}

}
