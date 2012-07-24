package de.fhtrier.gdw2.sotf.network.server;

import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.client.ClientHandler;
import de.fhtrier.gdw2.sotf.network.datagrams.Datagram;
import de.fhtrier.gdw2.sotf.network.datagrams.DatagramFactory;
import de.fhtrier.gdw2.sotf.network.datagrams.PlayerPositionDatagram;
import de.fhtrier.gdw2.sotf.network.states.WorldState;


public class ServerNetworkComp implements INetworkComp {

	private Server server;
	private WorldState world;
	
	public ServerNetworkComp(WorldState world, String ip, int port) {
		server = new Server(ip, port);
		this.world = world;  
	}
	
	public void handleIncoming() {
		if (server != null) {
			for(int i=0; i<server.clientHandlers.size(); ++i) {
				ClientHandler ch = server.clientHandlers.get(i);
				handleMessagesFromClient(i, ch);
			}
		}
	}

	private void handleMessagesFromClient(int client, ClientHandler ch) {
		while(!ch.incomingMessages.isEmpty()) {
			Datagram d = ch.incomingMessages.poll();

			switch(d.getId()) {
				case INetworkComp.MessageType.PLAYER_POSITION:
					handleClientPlayerPosition(client, ch, (PlayerPositionDatagram)d);
					break;
				default:
					System.err.println("Unknown MessageType");
					break;
			}
		}
	}

	public void handleOutgoing() {
		for(ClientHandler ch : server.clientHandlers) {
			sendPlayerPosition(ch);
			ch.sendPendingMessages();
		}
	}
	
	private void sendPlayerPosition(ClientHandler ch) {
		PlayerPositionDatagram d = (PlayerPositionDatagram)DatagramFactory.getDatagram(INetworkComp.MessageType.PLAYER_POSITION);
		for (int i=0; i<8; ++i) {
			d.data[i].x = world.entities[i].position.x;
			d.data[i].y = world.entities[i].position.y;
		}
		ch.outgoingMessages.add(d);
	}

	private void handleClientPlayerPosition(int client, ClientHandler ch, PlayerPositionDatagram d)
	{
		world.entities[client].position.x = d.data[client].x;
		world.entities[client].position.y = d.data[client].y;
	}
}
