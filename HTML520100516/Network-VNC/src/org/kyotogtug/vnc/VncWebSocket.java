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

public class VncWebSocket implements WebSocket{

	/** 画面をキャプチャし、画像ファイルを保存するFile
	 *  このFileにいったん保存し、そのファイルのバイトデータをクライアントに送信する */
	private File file;

	/** TCPで言うSocket的なやつ */
	private Outbound outbound ;

	public VncWebSocket(){
		file = new File("screen.jpg");
	}

	@Override
	public void onConnect(Outbound outbound) {
		System.out.println("onConnect!");
		this.outbound = outbound;
		try {
			outbound.sendMessage((byte)0, "Connected!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onDisconnect() {
		System.out.println("onDisconnect!");
	}

	/**
	 * データ受信時の処理
	 */
	@Override
	public void onMessage(byte arg0, String data) {
		System.out.println("onMessage!");
		System.out.println(arg0);
		System.out.println(data);
		try {

			// 画面をキャプチャし、Jpegのバイト配列を取得する
			byte[] bytes = capture();

			// Base64でエンコードし、クライアントに送信する
			outbound.sendMessage((byte)0, Base64.encodeBase64(bytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 画面をキャプチャし、バイトデータを取得する。
	 * @return
	 * @throws AWTException
	 * @throws IOException
	 */
	private byte[] capture() throws AWTException, IOException{
		Robot robot = new Robot();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		BufferedImage img = robot.createScreenCapture( new Rectangle(screenSize));
		ImageIO.write(img, "jpg", file);
		byte[] bytes = FileUtils.readFileToByteArray(file);
		return bytes;
	}

	@Override
	public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3) {
		System.out.println("onMessage2!");
	}

}
