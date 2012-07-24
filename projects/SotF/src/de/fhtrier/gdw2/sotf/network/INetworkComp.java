package de.fhtrier.gdw2.sotf.network;

import org.newdawn.slick.Graphics;

public interface INetworkComp {

	public static class MessageType {
		public static final byte WORLD = 0;
		public static final byte PLAYER_POSITION = 1;
		public static final byte PLAYER_ID = 2;
	}
	
	void handleIncoming();
	
	void handleOutgoing();
	
	void render(Graphics g);
}
