package org.kyotogtug.vnc.events;



/**
 * 画像送信イベント
 * サーバからクライアントに対し送信される
 * 
 * @author Kenji
 *
 */
public class ImageEvent extends Event{
	
	private byte[] base64imageData;
	
	
	public ImageEvent(){
		setEventType(Event.HEADER_IMAGE);
	}
	

	public byte[] getBase64imageData() {
		return base64imageData;
	}

	/**
	 * Base64でエンコードされた画像データを設定する。
	 * @param data
	 */
	public void setBase64imageData(byte[] base64imageData) {
		this.base64imageData = base64imageData;
	}
}
