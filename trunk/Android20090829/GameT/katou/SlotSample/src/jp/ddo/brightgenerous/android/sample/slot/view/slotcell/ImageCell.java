/**
 * jp.ddo.brightgenerous.android.sample.slot.view.slotcell
 * ImageCell.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot.view.slotcell;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import jp.ddo.brightgenerous.android.sample.slot.ImageManager;
import jp.ddo.brightgenerous.android.sample.slot.view.SlotView;

/**
 * 画像スロットセルクラス。
 * 
 * @version 1.0
 * @author KATOU
 */
public class ImageCell extends AbstractSlotCell {

	/** value */
	private int value = 0;

	/** paint */
	private final Paint paint = new Paint();

	/** 画像ID配列 */
	private int[] ids = null;

	/** 画像配列 */
	private Bitmap[] bitmaps = null;

	/**
	 * コンストラクタ。
	 * 
	 * @param inSlotView スロットビュー
	 * @param inFpv FPV
	 * @param inIds 画像ID配列
	 */
	public ImageCell(SlotView inSlotView, int inFpv, int[] inIds) {
		super(inSlotView, inFpv);

		this.ids = inIds;
	}

	/**
	 * リセットする。
	 */
	public void reset() {
		this.bitmaps = new Bitmap[this.ids.length];
		for (int i = 0; i < this.ids.length; ++i) {
			this.bitmaps[i] = ImageManager.getInstance().getImage(this.ids[i]);
		}
	}

	/**
	 * 描画処理をする。
	 * 
	 * @param inCanvas キャンバス
	 */
	public void draw(Canvas inCanvas) {
		float x = 0.0f;
		float y = 0.0f;
		if (this.updateable()) {
			Bitmap nextBitmap = this.bitmaps[this.getNextValue()];
			Bitmap bitmap = this.bitmaps[this.getValue()];
			x = (this.getWidth() - bitmap.getWidth()) / 2;
			y = this.getHeight() * this.getRate();
			inCanvas.drawBitmap(bitmap, x, y, this.paint);
			if (this.isDirectionDown()) {
				if (this.getRate() != 0.0f) {
					float nextY = y - this.getHeight();
					inCanvas.drawBitmap(nextBitmap, x, nextY, this.paint);
				}
			} else {
				if (this.getRate() != 0.0f) {
					float nextY = y - this.getHeight();
					inCanvas.drawBitmap(nextBitmap, x, nextY, this.paint);
				}
			}
		} else {
			Bitmap bitmap = this.bitmaps[this.getValue()];
			x = (this.getWidth() - bitmap.getWidth()) / 2;
			y = 0;
			inCanvas.drawBitmap(bitmap, x, y, this.paint);
		}
	}

	/**
	 * 値を取得する。
	 * 
	 * @return 値
	 */
	@Override
	public int getValue() {
		return this.value;
	}

	/**
	 * 次の値に移行する。
	 */
	@Override
	protected void next() {
		this.next1();
	}

	private void next1() {
		this.value = this.getNextValue();
	}

	/**
	 * 次の値を取得する。
	 * 
	 * @return 次の値
	 */
	@Override
	protected int getNextValue() {
		int result = this.value + 1;
		if (this.bitmaps.length - 1 < result) {
			result = 0;
		}
		return result;
	}

	/**
	 * 前の値に移行する。
	 */
	@Override
	protected void prev() {
		this.prev1();
	}

	private void prev1() {
		this.value = this.getPrevValue();
	}

	/**
	 * 前の値を取得する。
	 * 
	 * @return 前の値
	 */
	@Override
	protected int getPrevValue() {
		int result = this.value - 1;
		if (result < 0) {
			result = this.bitmaps.length - 1;
		}
		return result;
	}

	/**
	 * 値を結果に変換する。
	 * 
	 * @param inValue 値
	 * @return 結果
	 */
	@Override
	protected int convValue2Result(int inValue) {
		return this.ids[inValue];
	}
}
