package de.fhtrier.gdw2.sotf.network;

public interface INetworkComp {

	public static class MessageType {
		public static final byte WORLD = 0;
		public static final byte PLAYER_POSITION = 1;
	}
	
	void handleIncoming();
	
	void handleOutgoing();
}
