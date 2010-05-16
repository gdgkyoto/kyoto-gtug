package org.kyotogtug.proxy;

import java.util.Map;
import java.util.Set;

import org.eclipse.jetty.websocket.WebSocket;

public class SocketManager {

	private Set<ProxyClientSocket> clientSockets;
	
	private static SocketManager manager;

	static {
		manager = new SocketManager();
	}

	private SocketManager() {}

	public ProxyClientSocket getSocket() {
		if (clientSockets.size() > 0) {
			return null;
		} else {
			throw new RuntimeException("socket is empty"); 
		}
	}
	
	public static ProxyClientSocket createSocket() {
		ProxyClientSocket socket = new ProxyClientSocket();
		manager.clientSockets.add(socket);
		return socket;
	}

	public static SocketManager getInstance() {
		return manager;
	}

	
	static class ProxyClientSocket implements WebSocket {

		private Outbound bound;

		private boolean connected = false;

		@Override
		public void onConnect(Outbound arg0) {
			this.bound = arg0;
			this.connected = true;
			onMessage((byte) 0, "WebSocket is success!!!");
		}

		@Override
		public void onDisconnect() {
			this.connected = false;
		}

		public boolean isConnecting() {
			return connected;
		}

		public String forwardRequest(Map headers, String bodyText) {

			// bound.sendMessage("");
			return "";
		}

		@Override
		public void onMessage(byte frame, String data) {

		}

		@Override
		public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {

		}

		private String hashToJson(Map<String, String> headers) {
			for (Map.Entry<String, String> obj : headers.entrySet()) {

			}
			return "";
		}
	}
}
