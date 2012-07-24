package de.fhtrier.gdw2.sotf.network.datagrams;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Datagram {

	protected byte id;
	protected ByteBuffer buffer;
	
	public Datagram(byte id, int size)
	{
		this.id = id;
		this.buffer = ByteBuffer.allocate(size+1);
	}
	
	public int getId() { return id; }
	
	public void readFromSocketChannel(SocketChannel channel) throws IOException
	{
		buffer.clear();
		buffer.put(id);
		while(buffer.hasRemaining()) {
			channel.read(buffer);
		}
		buffer.flip();
		int _id = buffer.get();
		assert(_id == id);
	}
	
	public void writeToSocketChannel(SocketChannel channel) throws IOException
	{
		buffer.flip();
		while(buffer.hasRemaining())
			channel.write(buffer);
	}
}
