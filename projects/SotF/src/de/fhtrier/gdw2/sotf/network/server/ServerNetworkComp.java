package de.fhtrier.gdw2.sotf.network.server;

import org.newdawn.slick.Graphics;

import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.client.ClientHandler;
import de.fhtrier.gdw2.sotf.network.datagrams.Datagram;
import de.fhtrier.gdw2.sotf.network.datagrams.DatagramFactory;
import de.fhtrier.gdw2.sotf.network.datagrams.PlayerPositionDatagram;
import de.fhtrier.gdw2.sotf.network.states.WorldState;
import de.fhtrier.gdw2.sotf.settings.GlobalSettings;


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
					System.err.println("Server: Unknown MessageType: " + d.getId());
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

	@Override
	public void render(Graphics g) {
		StringBuffer buf = new StringBuffer();
		for (int i=0; i<server.clientHandlers.size(); ++i) {
			ClientHandler ch = server.clientHandlers.get(i);
			buf.append(ch.getPlayerId() + ": in - " + ch.incomingMessages.size() + " out - " + ch.outgoingMessages.size() + "\n"); 
		}
		
		g.drawString(buf.toString(), 20, 0);
	}
	
	private void sendPlayerPosition(ClientHandler ch) {
		PlayerPositionDatagram d = (PlayerPositionDatagram)DatagramFactory.getDatagram(INetworkComp.MessageType.PLAYER_POSITION);
		for (int i=0; i<GlobalSettings.MAX_PLAYERS; ++i) {
			d.data[i].x = world.entities[i].position.x;
			d.data[i].y = world.entities[i].position.y;
		}
		ch.outgoingMessages.add(d);
	}

	private void handleClientPlayerPosition(ClientHandler ch, PlayerPositionDatagram d)
	{
		int playerid = ch.getPlayerId();
		world.entities[playerid].position.x = d.data[playerid].x;
		world.entities[playerid].position.y = d.data[playerid].y;
	}
}
