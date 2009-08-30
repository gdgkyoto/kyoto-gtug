/**
 * jp.ddo.brightgenerous.android.sample.slot
 * ActiveObjectHandler.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot;

import android.os.Handler;
import android.os.Message;

/**
 * ActiveObjectハンドラクラス。
 * 
 * @version 1.0
 * @author KATOU
 */
public class ActiveObjectHandler extends Handler {

	/** アクティブオブジェクト */
	private ActiveObject activeObject;

	/**
	 * コンストラクタ。
	 * 
	 * @param inActiveObject アクティブオブジェクト
	 */
	public ActiveObjectHandler(ActiveObject inActiveObject) {
		this.activeObject = inActiveObject;
	}

	/**
	 * メッセージ処理をする。
	 * 
	 * @param inMessage メッセージ
	 */
	@Override
	public void handleMessage(Message inMessage) {
		this.activeObject.update();
	}

	/**
	 * 指定した時間制止する。
	 * 
	 * @param inSleepTime 制止時間
	 */
	public void sleep(long inSleepTime) {
		this.removeMessages(0);
		this.sendMessageDelayed(this.obtainMessage(0), inSleepTime);
	}
}
