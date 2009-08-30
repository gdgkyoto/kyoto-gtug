/**
 * jp.ddo.brightgenerous.android.sample.slot.view
 * CameraView.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot.view;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 * 
 * @version 1.0
 * @author KATOU
 */
public class CameraView extends SurfaceView {

	/** カメラ */
	protected Camera camera = null;

	/** カメラ起動フラグ */
	private boolean isRunning = false;

	/** データ */
	protected byte[] data = null;

	/** コールバック */
	private SurfaceHolder.Callback callBack = new SurfaceHolder.Callback() {

		/**
		 * サーフェイス生成イベントの処理
		 * 
		 * @param inHolder ホルダ
		 */
		public void surfaceCreated(SurfaceHolder inHolder) {
			CameraView.this.camera = null;
			System.gc();
			// カメラの初期化
			CameraView.this.camera = Camera.open();
			try {
				CameraView.this.camera.setPreviewDisplay(inHolder);
			} catch (IOException e) {
				CameraView.this.camera.release();
				CameraView.this.camera = null;
				throw new RuntimeException(e);
			}
		}

		/**
		 * サーフェイス変更イベントの処理
		 * 
		 * @param inHolder ホルダ
		 * @param inFormat
		 * @param inWidth
		 * @param inHeight
		 */
		public void surfaceChanged(SurfaceHolder inHolder, int inFormat, int inWidth, int inHeight) {
			// カメラのプレビュー開始
			Camera.Parameters parameters = CameraView.this.camera.getParameters();
			parameters.setPreviewSize(inWidth, inHeight);
			CameraView.this.camera.setParameters(parameters);
			CameraView.this.camera.startPreview();
		}

		/**
		 * サーフェイス解放イベントの処理
		 * 
		 * @param inHolder ホルダ
		 */
		public void surfaceDestroyed(SurfaceHolder inHolder) {
			// カメラのプレビュー停止
			CameraView.this.camera.stopPreview();
			CameraView.this.camera.release();
			CameraView.this.camera = null;
		}
	};

	/**
	 * コンストラクタ。
	 * 
	 * @param inContext
	 */
	public CameraView(Context inContext) {
		super(inContext);
		this.initialProcess(inContext, null);
	}

	/**
	 * コンストラクタ。
	 * 
	 * @param inContext コンテキスト
	 * @param inAttrs
	 */
	public CameraView(Context inContext, AttributeSet inAttrs) {
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
	public CameraView(Context inContext, AttributeSet inAttrs, int inDefStyle) {
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
		this.start();
	}

	/**
	 * タッチイベントの処理
	 * 
	 * @param inEvent
	 */
	@Override
	public boolean onTouchEvent(MotionEvent inEvent) {
		if (inEvent.getAction() == MotionEvent.ACTION_DOWN) {
			this.take();
		}
		return true;
	}

	/**
	 * 開始する。
	 */
	public void start() {
		if (!this.isRunning) {
			// サーフェイスホルダーの生成
			this.getHolder().addCallback(this.callBack);
			// プッシュバッッファの指定
			this.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

			this.isRunning = true;
		}
	}

	/**
	 * 写真を撮る。
	 */
	public void take() {
		if (this.isRunning) {
			this.camera.takePicture(null, null, new Camera.PictureCallback() {

				public void onPictureTaken(byte[] inData, Camera inCamera) {
					// CameraView.this.camera.stopPreview();
					CameraView.this.setData(inData);
				}
			});
			this.cancel();
		}
	}

	/**
	 * キャンセルする。
	 */
	public void cancel() {
		if (this.isRunning) {
			// サーフェイスホルダーの生成
			this.getHolder().removeCallback(this.callBack);

			this.isRunning = false;
		}
	}

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 */
	public byte[] getData() {
		return this.data;
	}

	/**
	 * データを設定する。
	 * 
	 * @param inData データ
	 */
	public void setData(byte[] inData) {
		this.data = inData;
	}
}
