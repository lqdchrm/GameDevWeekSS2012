package de.fhtrier.gdw2.sotf.network.datagrams;

import de.fhtrier.gdw2.sotf.network.INetworkComp;

public class DatagramFactory {
	public static Datagram getDatagram(byte id) {
		switch(id) {
			case INetworkComp.MessageType.PLAYER_POSITION:
				return new PlayerPositionDatagram();
			default:
				throw new IllegalArgumentException("id: " + id);
		}
	}
}

