package org.kyotogtug.vnc.events;


/**
 * キー同期用イベント
 * クライアントからサーバに対して送信される
 * @author Kenji
 *
 */
public class KeyPressEvent extends Event {
	
	private int keyCode;

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

}
