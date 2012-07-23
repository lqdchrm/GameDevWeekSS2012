package de.fhtrier.gdw2.sotf.network.datagrams;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.channels.SocketChannel;

import de.fhtrier.gdw2.sotf.network.INetworkComp;

public class PlayerPositionDatagram extends Datagram {

	private static final int size = 9; 
	public float x;
	public float y;
	
	PlayerPositionDatagram() {
		super(INetworkComp.MessageType.PLAYER_POSITION, size);
	}

	@Override
	public void readFromSocketChannel(SocketChannel channel) throws IOException {
		super.readFromSocketChannel(channel);
		
		int _id = buffer.get();
		assert(_id == id);
		
		FloatBuffer _buffer = buffer.asFloatBuffer();
		x = _buffer.get();
		y = _buffer.get();
	}

	@Override
	public void writeToSocketChannel(SocketChannel channel) throws IOException {
		buffer.clear();
		buffer.put(id);
		buffer.putFloat(x);
		buffer.putFloat(y);
		
		super.writeToSocketChannel(channel);
	}
}