package org.kyotogtug.vnc.events;

public class KeyReleaseEvent extends Event{
	
	private int keyCode;

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
}
