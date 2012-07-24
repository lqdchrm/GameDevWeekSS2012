package de.fhtrier.gdw2.sotf.network.client;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.fhtrier.gdw2.sotf.network.datagrams.Datagram;
import de.fhtrier.gdw2.sotf.network.datagrams.DatagramFactory;
import de.fhtrier.gdw2.sotf.network.notifications.INetworkEventListener;
import de.fhtrier.gdw2.sotf.network.notifications.NetworkEvent;

public class ClientHandler extends Thread {

	private SocketChannel communicationChannel;
	private boolean running = true;
	private int num;

	private ConcurrentLinkedQueue<Datagram> incomingMessages = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Datagram> outgoingMessages = new ConcurrentLinkedQueue<>();
	
	private List<INetworkEventListener> networkEventListeners = new ArrayList<>();
	private ByteBuffer idBuffer = ByteBuffer.allocate(1);
	
	public ClientHandler(SocketChannel communicationChannel) throws IOException {
		this(communicationChannel, 0);
	}
	
	public ClientHandler(SocketChannel communicationChannel, int num) throws IOException{
		communicationChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
		this.communicationChannel = communicationChannel;
		this.num = num;
		start();
	}
	
	public void run()
	{
		while(running) {
			try {
				idBuffer.clear();
				communicationChannel.read(idBuffer);
				idBuffer.flip();
				Datagram d = DatagramFactory.getDatagram(idBuffer.get());
				d.readFromSocketChannel(communicationChannel);
				incomingMessages.add(d);
				
			} catch (IOException e) {
				e.printStackTrace();

				handleIOException();
			}
		}
	}

	public boolean hasIncomingMessages() { return !incomingMessages.isEmpty(); }
	public int numIncomingMessages() { return incomingMessages.size(); }
	public Datagram getNextIncomingMessage() { return incomingMessages.poll(); }
	
	public boolean hasOutgoingMessages() { return !outgoingMessages.isEmpty(); }
	public int numOutgoingMessages() { return outgoingMessages.size(); }
	public boolean addOutgoingMessage(Datagram d) { if (running) { outgoingMessages.add(d); } return running; }

	public void sendPendingMessages()
	{
		if (running) {
			try {
				while(!outgoingMessages.isEmpty()) {
					outgoingMessages.poll().writeToSocketChannel(communicationChannel);
				}
			} catch (IOException e) {
				e.printStackTrace();
				handleIOException();
			}
		}
	}

	public void add(INetworkEventListener l) {
		if (!networkEventListeners.contains(l))
			networkEventListeners.add(l);
	}

	public void shutdown() {
		running = false;
	}
	
	public int getNum() {
		return num;
	}
	
	private void handleIOException() {
		fireEvent(NetworkEvent.Disconnect);
	}

	private void fireEvent(NetworkEvent event) {
		for (INetworkEventListener l : networkEventListeners) {
			if (l != null)
				l.HandleNetworkEvent(this, event);
		}
	}

	public int getPlayerId() {
		return getNum() + 1;
	}
}
