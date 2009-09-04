/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.game.slot.view
 * LogView.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid.game.slot.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * ログビュークラス。
 * 
 * @version 1.0
 * @author KATOU
 */
public class LogView extends ScrollView {

	/** テキストビュー */
	protected TextView textView = new TextView(this.getContext());
	{
		this.addView(this.textView);
	}

	/** メッセージハンドラ */
	private Handler scrollHandler = new Handler() {

		/**
		 * メッセージ処理をする。
		 * 
		 * @param inMessage メッセージ
		 */
		@Override
		public void handleMessage(Message inMessage) {
			LogView.this.scrollTo(0, LogView.this.textView.getBottom());
		}
	};

	/**
	 * コンストラクタ。
	 * 
	 * @param inContext コンテキスト
	 */
	public LogView(Context inContext) {
		super(inContext);
		this.initialProcess(inContext, null);
	}

	/**
	 * コンストラクタ。
	 * 
	 * @param inContext コンテキスト
	 * @param inAttrs
	 */
	public LogView(Context inContext, AttributeSet inAttrs) {
		super(inContext, inAttrs);
		this.initialProcess(inContext, inAttrs);
	}

	/**
	 * コンストラクタ。
	 * 
	 * @param inContext コンテキスト
	 * @param inAttrs
	 * @param inDefStyle
	 */
	public LogView(Context inContext, AttributeSet inAttrs, int inDefStyle) {
		super(inContext, inAttrs, inDefStyle);
		this.initialProcess(inContext, inAttrs);
	}

	/**
	 * コンストラクタによる初期化処理をする。
	 * 
	 * @param inContext コンテキスト
	 * @param inAttrs
	 */
	private void initialProcess(Context inContext, AttributeSet inAttrs) {
	}

	/**
	 * ログを追加する。
	 * 
	 * @param inLog ログ
	 */
	public void addLog(CharSequence inLog) {
		this.textView.append(inLog + "\n");
		this.scrollHandler.removeMessages(0);
		this.scrollHandler.sendMessageDelayed(this.scrollHandler.obtainMessage(0), 100);
	}
}
