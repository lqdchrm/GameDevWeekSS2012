package de.fhtrier.gdw2.sotf.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Server extends Thread {

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
				clientHandlers.add(new ClientHandler(incomingChannel));
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
	
}
