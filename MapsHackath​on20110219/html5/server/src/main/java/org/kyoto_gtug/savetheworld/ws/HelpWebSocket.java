package org.kyoto_gtug.savetheworld.ws;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.websocket.WebSocket;
import org.kyoto_gtug.savetheworld.domain.Help;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpWebSocket implements WebSocket {
	
	private static Logger logger = LoggerFactory.getLogger(HelpWebSocket.class);

	private Outbound outbound;
	
	public void onConnect(Outbound outbound) {
		logger.info("Connected.");
		this.outbound = outbound;
	}

	public void onDisconnect() {
		logger.info("Disconnected");
	}

	public void onFragment(boolean arg0, byte arg1, byte[] arg2, int arg3,
			int arg4) {
		logger.info("onFragment");
	}

	/**
	 * 外部から受け取ったデータを処理する
	 */
	public void onMessage(byte arg0, String message) {
		logger.info("onMessage");
		System.out.println("onMessage" + message);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Help help = mapper.readValue(message, Help.class);
			System.out.println(help.getMessage());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {
		logger.info("onMessage2");
		System.out.println("onMessage2");
	}

}
