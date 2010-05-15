package org.kyotogtug.vnc.events;



/**
 * 画像送信イベント
 * サーバからクライアントに対し送信される
 * 
 * @author Kenji
 *
 */
public class ImageEvent extends Event{
	
	
	public ImageEvent(){
		setEventType(Event.HEADER_IMAGE);
	}
	
	/**
	 * Base64でエンコードされた画像データを設定する。
	 * @param data
	 */
	public void setBase64ImageData( String data ){
		setData(data);
	}
}
