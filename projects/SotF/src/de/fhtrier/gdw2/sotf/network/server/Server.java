package de.fhtrier.gdw2.sotf.network.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.network.client.ClientHandler;
import de.fhtrier.gdw2.sotf.network.datagrams.DatagramFactory;
import de.fhtrier.gdw2.sotf.network.datagrams.IDDatagram;
import de.fhtrier.gdw2.sotf.network.notifications.INetworkEventListener;
import de.fhtrier.gdw2.sotf.network.notifications.NetworkEvent;
import de.fhtrier.gdw2.sotf.settings.GlobalSettings;

public class Server extends Thread implements INetworkEventListener {

	public ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	
	private boolean running;
	private ServerSocketChannel serverChannel;
	
	public Server(String ip, int port)
	{

		try {
			serverChannel = ServerSocketChannel.open();
			serverChannel.bind(new InetSocketAddress(ip, port));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		running = true;
		start();
	}
	
	@Override
	public void run() {
		while(running) {
			try {
				SocketChannel incomingChannel = serverChannel.accept();
				if (clientHandlers.size() < GlobalSettings.MAX_PLAYERS) {
					ClientHandler client = new ClientHandler(incomingChannel, clientHandlers.size()); 
					client.add(this);
					clientHandlers.add(client);
					IDDatagram datagram = (IDDatagram)DatagramFactory.getDatagram(INetworkComp.MessageType.PLAYER_ID);
					datagram.playerid = client.getPlayerId();
					client.addOutgoingMessage(datagram);
				} else {
					// TODO inform client that server is full
				}
			} catch (IOException e) {
				handleAcceptFailed();
			}
		}
	}

	private void handleAcceptFailed() {
		try {
			System.err.format("Accept on :%s.", serverChannel.getLocalAddress().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void HandleNetworkEvent(ClientHandler sender, NetworkEvent event) {
		switch(event) {
		case Disconnect:
			System.out.println("SERVER: Client " + sender.getNum() + " is not reachable, probably disconnected");
			sender.shutdown();
		}
	}
}
