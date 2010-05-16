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

	/** マウスカーソル同期イベント
	 *  クライアントからサーバに対して送信する。 */
	public static final String HEADER_CURSOR_MOVE = "CURSOR_MOVE";
	
	/**
	 * キー押下イベント
	 */
	public static final String HEADER_KEY_PRESS = "KEY_PRESS";
	
	/**
	 * キー解放イベント
	 */
	public static final String HEADER_KEY_RELEASE = "KEY_RELEASE";
	

	/**
	 * マウスボタンのプレス
	 * */
	public static final String HEADER_MOUSE_PRESS  = "MOUSE_PRESS";

	/** ファイルアップロードのイベント
	 *  クライアントからサーバに対してファイルをアップロードするときに使用する */
	public static final String HEADER_FILE_UPLOAD = "FILE_UPLOAD";
	
	/** ファイルダウンロードリクエストのイベント
	 *  このイベントはクライアントからサーバに対して送信する。
	 *  このイベントを受け取ると、サーバはファイル選択ダイアログを表示する。
	 *  ダイアログからファイルを選択すると、Webサーバとして見える位置にファイルをコピーし、
	 *  そのパスをFILE_DOWNLOAD_RESPONSEでクライアントに通知する。
	 */
	public static final String HEADER_FILE_DOWNLOAD_REQUEST = "FILE_DOWNLOAD_REQUEST";
	
	/**
	 * ファイルダウンロードのレスポンスイベント
	 * ダウンロード用URLをサーバからクライアントに対し通知する。
	 */
	public static final String HEADER_FILE_DOWNLOAD_RESPONSE = "FILE_DOWNLOAD_RESPONSE";
	
	
	/**
	 * マウスリリースイベント
	 * クライアントでクリックし、リリースしたイベントを、サーバに反映させるために送信する。
	 * data=X|Y|BUTTON
	 * data=123|456|0 
	 *  */
	public static final String HEADER_MOUSE_RELEASE = "MOUSE_RELEASE";
	
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
