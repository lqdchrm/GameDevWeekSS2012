package de.fhtrier.gdw2.sotf.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.fhtrier.gdw2.sotf.network.datagrams.Datagram;
import de.fhtrier.gdw2.sotf.network.datagrams.DatagramFactory;

public class ClientHandler extends Thread {

	private SocketChannel communicationChannel;
	public ConcurrentLinkedQueue<Datagram> incomingMessages = new ConcurrentLinkedQueue<>();
	public ConcurrentLinkedQueue<Datagram> outgoingMessages = new ConcurrentLinkedQueue<>();
	private boolean running = true;
	private ByteBuffer idBuffer = ByteBuffer.allocate(1);
	
	public ClientHandler(SocketChannel communicationChannel){
		this.communicationChannel = communicationChannel;
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
				// TODO: reading a message from a channel failed
				e.printStackTrace();
			}
		}
	}
	
	public void sendPendingMessages()
	{
		try {
			while(!outgoingMessages.isEmpty()) {
				outgoingMessages.poll().writeToSocketChannel(communicationChannel);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
