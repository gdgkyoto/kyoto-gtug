package org.kyotogtug.vnc;

/**
 * データの送受信時に使用するイベント
 * @author Kenji
 *
 */
public class VncEvent {
	
	/** データの種類を示す文字列 IMAGE=画像データなど */
	private String eventType;
	
	/** データのシーケンス 0からの連番 */
	private int sequence;
	
	/** データを送信した時刻のタイムスタンプ */
	private long timestamp;
	
	/** データ本体 */
	private String data;
		
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
