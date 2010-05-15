package org.kyotogtug.vnc.events;

/**
 * マウス移動イベント
 * @author Kenji
 *
 */
public class MouseMoveEvent extends Event{
	
	private int x;
	private int y;
	
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

}
