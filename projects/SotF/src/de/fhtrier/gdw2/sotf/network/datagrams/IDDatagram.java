package de.fhtrier.gdw2.sotf.network.datagrams;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import de.fhtrier.gdw2.sotf.network.INetworkComp;

public class IDDatagram extends Datagram {

	public int playerid;

	public IDDatagram() {
		super(INetworkComp.MessageType.PLAYER_ID, 4);
	}
	
	@Override
	public void readFromSocketChannel(SocketChannel channel) throws IOException {
		super.readFromSocketChannel(channel);
		playerid = buffer.getInt();
	}
	
	@Override
	public void writeToSocketChannel(SocketChannel channel) throws IOException {
		buffer.clear();
		buffer.put(id);
		buffer.putInt(playerid);
		super.writeToSocketChannel(channel);
	}

}
