package de.fhtrier.gdw2.impl.network;

import java.nio.ByteBuffer;

import de.fhtrier.gdw2.interfaces.network.IGameClient;

import gdwNet.client.BasicClient;
import gdwNet.client.IBasicClientListener;
import gdwNet.client.ServerInfo;

public class GameClient implements IBasicClientListener, IGameClient {

	@Override
	public void serverResponce(ServerInfo info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionUpdate(int msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionEstablished(BasicClient clientRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incommingMessage(ByteBuffer msg, boolean wasReliable) {
		// TODO Auto-generated method stub
		
	}

}
