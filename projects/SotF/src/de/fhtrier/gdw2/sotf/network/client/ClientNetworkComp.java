package de.fhtrier.gdw2.sotf.network.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.datagrams.Datagram;
import de.fhtrier.gdw2.sotf.network.datagrams.DatagramFactory;
import de.fhtrier.gdw2.sotf.network.datagrams.PlayerPositionDatagram;
import de.fhtrier.gdw2.sotf.network.notifications.INetworkEventListener;
import de.fhtrier.gdw2.sotf.network.notifications.NetworkEvent;
import de.fhtrier.gdw2.sotf.network.states.WorldState;


public class ClientNetworkComp implements INetworkComp, INetworkEventListener {

	private ClientHandler client;
	private WorldState world;
	
	public ClientNetworkComp(WorldState world, String ip, int port) {
		
		this.world = world;  
		SocketChannel channel;

		try {
			channel = SocketChannel.open(new InetSocketAddress(ip, port));
			client = new ClientHandler(channel);
			client.add(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleIncoming() {
		for (Datagram d : client.incomingMessages) {
			switch(d.getId()) {
			case INetworkComp.MessageType.PLAYER_POSITION:
				handleServerPlayerPos((PlayerPositionDatagram)d);
				break;
			default:
				System.err.println("Unknown MessageType");
				break;
			}
		}
	}

	private void handleServerPlayerPos(PlayerPositionDatagram d) {
		for (int i=0; i<8; ++i) {
			world.entities[i].position.x = d.data[i].x;
			world.entities[i].position.y = d.data[i].y;
		}
	}

	public void handleOutgoing() {
		PlayerPositionDatagram d = (PlayerPositionDatagram)DatagramFactory.getDatagram(INetworkComp.MessageType.PLAYER_POSITION);
		
		for (int i=0; i<8; ++i) {
			d.data[i].x = world.entities[i].position.x;
			d.data[i].y = world.entities[i].position.y;
		}
		client.outgoingMessages.add(d);
		client.sendPendingMessages();	
	}

	@Override
	public void HandleNetworkEvent(ClientHandler sender, NetworkEvent event) {
		switch(event) {
		case Disconnect:
			System.err.println("Server not responding");
			sender.shutdown();
		}
	}
}