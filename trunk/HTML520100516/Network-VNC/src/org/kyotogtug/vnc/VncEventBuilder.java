package org.kyotogtug.vnc;

import org.kyotogtug.vnc.events.Event;
import org.kyotogtug.vnc.events.ImageEvent;

/**
 * 通信イベント作成用クラス
 * @author Kenji
 *
 */
public class VncEventBuilder {
	
	/**
	 * 画像データ送信用イベントを生成するメソッド
	 * 引数にjpegファイルのバイナリデータを設定する
	 * @param bytes
	 * @return
	 */
	public Event createImageEvent( byte[] bytes ){
		ImageEvent event = new ImageEvent();
		
		
		return null;
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
		
		return null;
	}
	
	

}
