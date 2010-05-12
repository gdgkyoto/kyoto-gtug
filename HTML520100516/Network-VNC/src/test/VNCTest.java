package test;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import name.aikesommer.viewncontrol.protocol.SliceStore;
import name.aikesommer.viewncontrol.protocol.VNCSession;
import name.aikesommer.viewncontrol.protocol.VNCException.AuthenticationFailed;


/**
 * viewncontrolのVNCSessionを使ってVNCサーバに接続するテスト
 * TightVNCでAllow loopback connectionsにチェックを入れると接続可能。
 * ただし画面が24bitでないと先に進めない・・・。
 *
 * @author kitamura
 *
 */
public class VNCTest {

	public static void main( String[] args ){
		VNCTest test = new VNCTest();
		try {
			test.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void start() throws UnknownHostException{

		SliceStore store = new SliceStore(){
			@Override
			public void clear() {

			}
			@Override
			public void store(SliceType type, Slice slice) {

			}
		};

		// パスワード
		String secret = "hogehoge";

		VNCSession session = new VNCSession( InetAddress.getByName("localhost") , 5900 , secret, store);
		try {
			session.connect();
		} catch (AuthenticationFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
