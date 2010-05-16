package org.kyotogtug.vnc;

import org.apache.commons.codec.binary.Base64;
import org.kyotogtug.vnc.events.Event;
import org.kyotogtug.vnc.events.ImageEvent;
import org.kyotogtug.vnc.events.ImageRequestEvent;
import org.kyotogtug.vnc.events.MouseClickEvent;
import org.kyotogtug.vnc.events.MouseMoveEvent;

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
		Event event = null;
		String[] data = str.split("\\|");
		if( data.length < 3 ){
			throw new IllegalArgumentException("invalid format!");
		}
		
		System.out.println("EventBuilder.parseEvent event="+data[0]);
		
		String eventType = data[0];
		
		// 画像更新リクエストイベント
		if( eventType.equals(Event.HEADER_IMAGE_REQUEST ) ){
			event = new ImageRequestEvent();
			
		// マウスMoveイベント
		}else if( eventType.equals(Event.HEADER_CURSOR_MOVE)){
			event = new MouseMoveEvent();
			MouseMoveEvent mouseMoveEvent = (MouseMoveEvent)event;
			
			String mouseMoveData = data[3];
			String[] split = mouseMoveData.split(",");
			int i = 0;
			int x = Integer.parseInt(split[ i++ ]);
			int y = Integer.parseInt(split[ i++ ]);
			
			mouseMoveEvent.setX(x);
			mouseMoveEvent.setY(y);
			
		// マウスクリックイベント
		}else if( eventType.equals(Event.HEADER_MOUSE_CLICK ) ){
			event = new MouseClickEvent();
			MouseClickEvent mouseClickEvent = (MouseClickEvent)event;
			
			String mouseMoveData = data[3];
			String[] split = mouseMoveData.split(",");
			int i = 0;
			int x = Integer.parseInt(split[ i++ ]);
			int y = Integer.parseInt(split[ i++ ]);
			int button = Integer.parseInt(split[ i++ ]);
			mouseClickEvent.setX(x);
			mouseClickEvent.setY(y);
			mouseClickEvent.setButton(button);
		}else{
			event = new Event();
		}
		
		event.setEventType(data[0]);
		event.setSequence(getSequence(data[1]));
		event.setTimestamp(getTimestamp(data[2]));
		
		return event;
	}
	
	
	
	/**
	 * シーケンス番号をパースする
	 * パースに失敗した場合は-1を返す
	 * @param seq
	 * @return
	 */
	private int getSequence( String seq ){
		try{
			int ret = Integer.parseInt(seq);
			return ret;
		}catch ( Exception e ){
			return -1;
		}
	}
	/**
	 * タイムスタンプをパースする
	 * パースに失敗した場合は-1を返す
	 * @param seq
	 * @return
	 */
	private long getTimestamp( String val ){
		try{
			long ret = Long.parseLong(val);
			return ret;
		}catch ( Exception e ){
			return -1;
		}
	}
	

}
