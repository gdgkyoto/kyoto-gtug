package org.kyotogtug.vnc.events;

public class MouseReleaseEvent extends Event {
	
	private int x;
	private int y;
	private int button;
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getButton() {
		return button;
	}
	public void setButton(int button) {
		this.button = button;
	}


}
