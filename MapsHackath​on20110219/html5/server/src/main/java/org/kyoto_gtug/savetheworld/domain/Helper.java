package org.kyoto_gtug.savetheworld.domain;

import org.kyoto_gtug.savetheworld.ws.HelpStandByWebSocket;

public class Helper {
	
	private HelpStandByWebSocket socket;

	public Helper(HelpStandByWebSocket helpStandByWebSocket) {
		this.socket = helpStandByWebSocket;
	}

	public void notifyHelp(Help help) {
		socket.notifyHelp(help);
	}

}
