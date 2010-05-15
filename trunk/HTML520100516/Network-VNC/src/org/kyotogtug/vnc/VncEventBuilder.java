package org.kyotogtug.vnc;

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
	public VncEvent createImageEvent( byte[] bytes ){
		return null;
	}
	
	
	/**
	 * クライアントから受信したイベントをパースしてVncEventクラスのインスタンスを生成する
	 * @param data
	 * @return
	 */
	public VncEvent parseEvent( String str ){
		VncEvent vncEvent = new VncEvent();
		String[] data = str.split("\\|");
		if( data.length <= 4 ){
			
		}
		return null;
	}
	
	

}
