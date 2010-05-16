package org.kyotogtug.vnc;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.websocket.WebSocket;
import org.kyotogtug.vnc.events.Event;

public class VncWebSocket implements WebSocket{

	/** 画面をキャプチャし、画像ファイルを保存するFile
	 *  このFileにいったん保存し、そのファイルのバイトデータをクライアントに送信する */
	private File file;

	/** TCPで言うSocket的なやつ */
	private Outbound outbound ;
	
	/** 送信するイベントを作成したり、受信した文字列からイベントのインスタンスを生成する。 */
	private EventBuilder eventBuilder;
	
	/** 受信したイベントを処理するクラス
	 *  マウスカーソルの同期などなど */
	private EventProcessor eventProcessor;

	/** get log instance */
	private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
			.getLog(VncWebSocket.class);
	
	public VncWebSocket(){
		file = new File("screen.jpg");
		eventBuilder = new EventBuilder();
		eventProcessor = new EventProcessor();
	}

	@Override
	public void onConnect(Outbound outbound) {
		log.info("onConnect!!");
		this.outbound = outbound;
		
		// Outbountの設定
		eventProcessor.setOutbound(outbound);
		
		try {
			outbound.sendMessage((byte)0, "Connected!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onDisconnect() {
		log.info("onDisconnect!!");
	}

	/**
	 * データ受信時の処理
	 */
	@Override
	public void onMessage(byte arg0, String data) {
		log.debug("onMessage! 第1引数="+arg0+" データ="+data);

		// 受信したデータをパース
		Event event = eventBuilder.parseEvent(data);
		
		// 受信したイベントを実行
		eventProcessor.handleEvent(event);
//		
//		
//		try {
//
//			// 画面をキャプチャし、Jpegのバイト配列を取得する
//			byte[] bytes = capture();a
//
//			// Base64でエンコードし、クライアントに送信する
//			outbound.sendMessage((byte)0, Base64.encodeBase64(bytes));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * 画面をキャプチャし、バイトデータを取得する。
	 * @return
	 * @throws AWTException
	 * @throws IOException
	 */
	private byte[] capture() throws AWTException, IOException{
		log.debug("Capture start");
		Robot robot = new Robot();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage img = robot.createScreenCapture( new Rectangle(screenSize));
		ImageIO.write(img, "jpg", file);
		byte[] bytes = FileUtils.readFileToByteArray(file);
		log.debug("Capture end");
		return bytes;
	}

	@Override
	public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {
		log.info("onMessage2!");
	}
}
