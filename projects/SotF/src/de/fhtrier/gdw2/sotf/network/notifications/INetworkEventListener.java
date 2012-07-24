package de.fhtrier.gdw2.sotf.network.notifications;

import de.fhtrier.gdw2.sotf.network.client.ClientHandler;

public interface INetworkEventListener {
	void HandleNetworkEvent(ClientHandler sender, NetworkEvent event);
}
