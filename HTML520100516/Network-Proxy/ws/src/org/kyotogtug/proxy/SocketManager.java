package org.kyotogtug.proxy;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.jetty.websocket.WebSocket;

public class SocketManager {

	LinkedList<ProxyClientSocket> clientSockets = new LinkedList<ProxyClientSocket>();

	private static SocketManager manager;

	static {
		manager = new SocketManager();
	}

	private SocketManager() {}

	// ソケットを返す
	public synchronized ProxyClientSocket getSocket() {
		while(clientSockets.size() > 0) {
			ProxyClientSocket socket = clientSockets.remove(0);
			if(socket.isConnecting()) {
				return socket;
			}
		}
		
		throw new RuntimeException("connecting socket is empty");
	}

	public ProxyClientSocket createSocket() {
		ProxyClientSocket socket = new ProxyClientSocket();
		clientSockets.add(socket);
		return socket;
	}
	
	public void sendMessageAll(byte frame , String data) {
		for (ProxyClientSocket client : SocketManager.getInstance().clientSockets) {
			try {
				client.bound.sendMessage(frame, data);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public static SocketManager getInstance() {
		return manager;
	}

	static class ProxyClientSocket implements WebSocket {

		private Outbound bound;

		private boolean connected = false;
		
		String responseData = null;
		
		@Override
		public void onConnect(Outbound arg0) {
			this.bound = arg0;
			this.connected = true;
		}

		@Override
		public void onDisconnect() {
			this.connected = false;
		}

		@Override
		/*
		 * Proxyクライアントからのレスポンスイベント
		 */
		public void onMessage(byte frame, String data) {
			System.out.println(data);
			System.out.println(frame);
			responseData = data;
			SocketManager.getInstance().clientSockets.add(this);
		}

		@Override
		public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {
			
		}

		public boolean isConnecting() {
			return connected;
		}

		public Map<String, Object> forwardRequest(Map<String, String> headers, String bodyText, final String url) throws IOException {
			this.bound.sendMessage((byte)0 , url);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			

			Map <String, String>rheaders = new HashMap<String, String>();
			rheaders.put("Location", url);
			rheaders.put("Content-Type", "Content-Type: text/html; charset=UTF-8");
			rheaders.put("Content-Length", responseData.length() + "");
			
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("HEADERS", rheaders);
			response.put("BODY", responseData);
			return response;
		}

		private String hashToJson(Map<String, String> headers) {
			for (Map.Entry<String, String> obj : headers.entrySet()) {

			}
			return "";
		}
	}
}
