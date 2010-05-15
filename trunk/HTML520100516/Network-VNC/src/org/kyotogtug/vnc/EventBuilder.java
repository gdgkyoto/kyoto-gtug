package org.kyotogtug.vnc;

import org.apache.commons.codec.binary.Base64;
import org.kyotogtug.vnc.events.Event;
import org.kyotogtug.vnc.events.ImageEvent;
import org.kyotogtug.vnc.events.ImageRequestEvent;

/**
 * 通信イベント作成用クラス
 * @author Kenji
 *
 */
public class EventBuilder {
	
	/**
	 * 画像データ送信用イベントを生成するメソッド
	 * 引数にjpegファイルのバイナリデータを設定する
	 * @param bytes
	 * @return
	 */
	public Event createImageEvent( byte[] bytes ){
		ImageEvent event = new ImageEvent();
		event.setBase64imageData(Base64.encodeBase64(bytes));
		return event;
	}
	
	
	/**
	 * クライアントから受信したイベントをパースしてVncEventクラスのインスタンスを生成する
	 * @param data
	 * @return
	 */
	public Event parseEvent( String str ){
		Event vncEvent = new Event();
		String[] data = str.split("\\|");
		if( data.length <= 4 ){
			throw new IllegalArgumentException("invalid format!");
		}
		
		if( data[0].equals(Event.HEADER_IMAGE_REQUEST ) ){
			ImageRequestEvent event = new ImageRequestEvent();
			return event;
		}
		
		return null;
	}
	
	

}
