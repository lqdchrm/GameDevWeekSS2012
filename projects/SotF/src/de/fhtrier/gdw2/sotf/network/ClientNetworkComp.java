package de.fhtrier.gdw2.sotf.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import de.fhtrier.gdw2.sotf.network.datagrams.Datagram;
import de.fhtrier.gdw2.sotf.network.datagrams.DatagramFactory;
import de.fhtrier.gdw2.sotf.network.datagrams.PlayerPositionDatagram;


public class ClientNetworkComp implements INetworkComp {

	private ClientHandler client;
	private WorldState world;
	
	public ClientNetworkComp(WorldState world, String ip, int port) {
		
		this.world = world;  
		SocketChannel channel;

		try {
			channel = SocketChannel.open(new InetSocketAddress(ip, port));
			client = new ClientHandler(channel);
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
		world.entity.position.x = d.x;
		world.entity.position.y = d.y;
	}

	public void handleOutgoing() {
		PlayerPositionDatagram d = (PlayerPositionDatagram)DatagramFactory.getDatagram(INetworkComp.MessageType.PLAYER_POSITION);
		d.x = world.entity.position.x;
		d.y = world.entity.position.y;
		
		client.outgoingMessages.add(d);
		System.out.println("Client: send " + client.outgoingMessages.size() + " messages");
		client.sendPendingMessages();	
	}
}
