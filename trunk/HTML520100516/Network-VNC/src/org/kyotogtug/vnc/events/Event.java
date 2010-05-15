package org.kyotogtug.vnc.events;

/**
 * データの送受信時に使用するイベント
 * @author Kenji
 *
 */
public class Event {
	
	
	/** 画像送信イベント */
	public static final String HEADER_IMAGE = "IMAGE";
	
	/** 画面更新リクエストイベント
	 *  画面を更新したいタイミングでクライアントからサーバに子のイベントを送信する
	 *  このイベントをサーバが受け取ると、画面をキャプチャしてその画像データをIMAGEイベントでクライアントに送信する */
	public static final String HEADER_IMAGE_REQUEST = "IMAGE_REQUEST";
	
	/** データの種類を示す文字列 IMAGE=画像データなど */
	protected String eventType;
	
	/** データのシーケンス 0からの連番 */
	protected int sequence;
	
	/** データを送信した時刻のタイムスタンプ */
	protected long timestamp;
	
	/** 受信データ本体 */
	protected String data;
		
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
