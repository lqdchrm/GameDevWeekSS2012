package de.fhtrier.gdw2.interfaces.network;

import java.io.IOException;


public interface INetworkManager {
	
	boolean isServer();
	void setAsServer() throws IOException;
	void setAsClient();
}
