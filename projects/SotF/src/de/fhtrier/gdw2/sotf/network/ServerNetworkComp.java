package de.fhtrier.gdw2.sotf.network;

import de.fhtrier.gdw2.sotf.network.datagrams.Datagram;
import de.fhtrier.gdw2.sotf.network.datagrams.DatagramFactory;
import de.fhtrier.gdw2.sotf.network.datagrams.PlayerPositionDatagram;


public class ServerNetworkComp implements INetworkComp {

	private Server server;
	private WorldState world;
	
	public ServerNetworkComp(WorldState world, String ip, int port) {
		server = new Server(ip, port);
		this.world = world;  
	}
	
	public void handleIncoming() {
		if (server != null) {
			for(ClientHandler ch : server.clientHandlers) {
				handleMessagesFromClient(ch);
			}
		}
	}

	private void handleMessagesFromClient(ClientHandler ch) {
		while(!ch.incomingMessages.isEmpty()) {
			Datagram d = ch.incomingMessages.poll();

			switch(d.getId()) {
				case INetworkComp.MessageType.PLAYER_POSITION:
					handleClientPlayerPosition(ch, (PlayerPositionDatagram)d);
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
		d.x = world.entity.position.x;
		d.y = world.entity.position.y;
		ch.outgoingMessages.add(d);
	}

	private void handleClientPlayerPosition(ClientHandler ch, PlayerPositionDatagram d)
	{
		world.entity.position.x = d.x;
		world.entity.position.y = d.y;
	}
}
