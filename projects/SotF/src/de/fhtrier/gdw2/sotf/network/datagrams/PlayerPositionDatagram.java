package de.fhtrier.gdw2.sotf.network.datagrams;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.channels.SocketChannel;

import org.newdawn.slick.geom.Vector2f;

import de.fhtrier.gdw2.sotf.network.INetworkComp;
import de.fhtrier.gdw2.sotf.settings.GlobalSettings;

public class PlayerPositionDatagram extends Datagram {

	private static final int size = GlobalSettings.MAX_PLAYERS*2*4; 
	public Vector2f[] data = new Vector2f[GlobalSettings.MAX_PLAYERS];
	
	PlayerPositionDatagram() {
		super(INetworkComp.MessageType.PLAYER_POSITION, size);
		
		for(int i=0; i<GlobalSettings.MAX_PLAYERS; ++i) {
			data[i] = new Vector2f(0,0);
		}
	}

	@Override
	public void readFromSocketChannel(SocketChannel channel) throws IOException {
		super.readFromSocketChannel(channel);
		
		FloatBuffer _buffer = buffer.asFloatBuffer();
		for (int i=0; i<GlobalSettings.MAX_PLAYERS; ++i) {
			data[i].x = _buffer.get();
			data[i].y = _buffer.get();
		}
	}

	@Override
	public void writeToSocketChannel(SocketChannel channel) throws IOException {
		buffer.clear();
		buffer.put(id);

		for (int i=0; i<GlobalSettings.MAX_PLAYERS; ++i) {
			buffer.putFloat(data[i].x);
			buffer.putFloat(data[i].y);
		}
		
		super.writeToSocketChannel(channel);
	}
}