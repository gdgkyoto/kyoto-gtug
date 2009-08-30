/**
 * jp.ddo.brightgenerous.android.sample.slot.view
 * SlotView.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import jp.ddo.brightgenerous.android.sample.slot.ActiveObject;
import jp.ddo.brightgenerous.android.sample.slot.ActiveObjectHandler;
import jp.ddo.brightgenerous.android.sample.slot.view.slotcell.SlotCell;

/**
 * スロットビュークラス。<br>
 * スロットの一つの表示セルを描画する。
 * 
 * @version 1.0
 * @author KATOU
 */
public class SlotView extends View implements ActiveObject {

	/** フレームの間隔 */
	private int delay = (int) (1000.0 / 30);

	/** 動作フラグ */
	private boolean isRunning = false;

	/** ActiveObjectハンドラ */
	private ActiveObjectHandler activeObjectHandler = new ActiveObjectHandler(this);

	/** スロットセル */
	private SlotCell slotCell = null;

	/**
	 * コンストラクタ。
	 * 
	 * @param inContext コンテキスト
	 */
	public SlotView(Context inContext) {
		super(inContext);
		this.initialProcess(inContext, null);
	}

	/**
	 * コンストラクタ。
	 * 
	 * @param inContext コンテキスト
	 * @param inAttrs
	 */
	public SlotView(Context inContext, AttributeSet inAttrs) {
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
	public SlotView(Context inContext, AttributeSet inAttrs, int inDefStyle) {
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
		this.setFocusable(true);
	}

	/**
	 * スロットセルを取得する。
	 * 
	 * @return スロットセル
	 */
	public SlotCell getCell() {
		return this.slotCell;
	}

	/**
	 * スロットセルを設定する。
	 * 
	 * @param inSlotCell スロットセル
	 */
	public void setCell(SlotCell inSlotCell) {
		this.slotCell = inSlotCell;
	}

	/**
	 * フレームレートの値を設定する。<br />
	 * アニメーションなので25～30あたりがよい。
	 * 
	 * @param inFps フレームレート
	 */
	public void setFps(int inFps) {
		if (inFps <= 0) {
			throw new IllegalArgumentException("FPSの値が不正");
		}
		this.delay = (int) (1000.0 / inFps);
	}

	/**
	 * スロットを開始する。
	 */
	public void start() {
		this.isRunning = true;
		this.slotCell.start();
		this.update();
	}

	/**
	 * スロットを停止する。
	 */
	public void stop() {
		this.isRunning = false;
		this.slotCell.stop();
	}

	/**
	 * 動作フラグを取得する。
	 * 
	 * @return 動作フラグ
	 */
	public boolean isRunning() {
		return this.isRunning;
	}

	/**
	 * スロットの数値を取得する。
	 * 
	 * @return スロっトの数値
	 */
	public int getValue() {
		return this.slotCell.getResult();
	}

	protected boolean updateable() {
		return this.slotCell.updateable();
	}

	/**
	 * 更新処理をする。<br>
	 * ActiveObject#update()の実装
	 */
	public void update() {
		if (this.updateable()) {
			this.slotCell.update();
		}
		this.invalidate();
		if (this.updateable()) {
			this.activeObjectHandler.sleep(this.delay);
		}
	}

	/**
	 * サイズ変更処理をする。
	 * 
	 * @param inWidth 横幅
	 * @param inHeight 高さ
	 * @param inOldWidth 以前の横幅
	 * @param inOldHeight 以前の高さ
	 */
	@Override
	protected void onSizeChanged(int inWidth, int inHeight, int inOldWidth, int inOldHeight) {
		this.slotCell.setSize(inWidth, inHeight);
	}

	/**
	 * キーダウン処理をする。
	 * 
	 * @param inCode コード
	 * @param inEvent イベント
	 */
	@Override
	public boolean onKeyDown(int inCode, KeyEvent inEvent) {
		return super.onKeyDown(inCode, inEvent);
	}

	/**
	 * 描画処理する。
	 * 
	 * @param inCanvas キャンバス
	 */
	@Override
	public void onDraw(Canvas inCanvas) {
		this.slotCell.draw(inCanvas);
	}
}
