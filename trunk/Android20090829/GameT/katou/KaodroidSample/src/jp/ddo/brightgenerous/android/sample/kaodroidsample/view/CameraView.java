/**
 * jp.ddo.brightgenerous.android.sample.kaodroidsample.view
 * CameraView.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.kaodroidsample.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.provider.MediaStore.MediaColumns;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Images.Media;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * カメラビュークラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback, PictureCallback {

	/**
	 * コールバックインタフェース
	 * 
	 * @author KATOU
	 */
	abstract public interface Callback {

		/**
		 * 画像データ保存直後のイベント
		 * 
		 * @param inCameraView
		 * @param inDataUri
		 */
		abstract public void onPictureSaved(CameraView inCameraView, Uri inDataUri);

		/**
		 * 画像データ保存直後のイベント
		 * 
		 * @param inCameraView
		 * @param inDataPath
		 */
		abstract public void onPictureSaved(CameraView inCameraView, String inDataPath);
	}

	static private ContentResolver contentResolver = null;

	/** カメラ */
	private Camera camera = null;

	private Callback callback = null;

	/**
	 * コンストラクタ
	 * 
	 * @param inContext
	 */
	public CameraView(Context inContext) {
		super(inContext);
		this.initialProcess(inContext, null);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param inContext コンテキスト
	 * @param inAttrs
	 */
	public CameraView(Context inContext, AttributeSet inAttrs) {
		super(inContext, inAttrs);
		this.initialProcess(inContext, inAttrs);
	}

	/**
	 * コンストラクタ
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
	 * コンストラクタによる初期化処理をする
	 * 
	 * @param inContext コンテキスト
	 * @param inAttrs
	 */
	private void initialProcess(Context inContext, AttributeSet inAttrs) {
		contentResolver = inContext.getContentResolver();

		this.getHolder().addCallback(this);
		this.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	/**
	 * コールバックインスタンスを設定する
	 * 
	 * @param inCallback
	 */
	public void setCallback(Callback inCallback) {
		this.callback = inCallback;
	}

	/**
	 * サーフェイス生成イベントの処理
	 * 
	 * @param inHolder ホルダ
	 */
	public void surfaceCreated(SurfaceHolder inHolder) {
		this.camera = Camera.open();
		this.setCameraParameters(this.camera);
		try {
			this.camera.setPreviewDisplay(inHolder);
		} catch (IOException e) {
			this.cameraRelease();
			throw new RuntimeException(e);
		}
	}

	private void setCameraParameters(Camera inCamera) {
		Parameters parameters = inCamera.getParameters();
		parameters.setPictureSize(480, 320);
		inCamera.setParameters(parameters);
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
		Parameters parameters = this.camera.getParameters();
		parameters.setPreviewSize(inWidth, inHeight);
		this.camera.setParameters(parameters);
		this.camera.startPreview();
	}

	/**
	 * サーフェイス解放イベントの処理
	 * 
	 * @param inHolder ホルダ
	 */
	public void surfaceDestroyed(SurfaceHolder inHolder) {
		this.cameraRelease();
	}

	/**
	 * タッチイベントの処理
	 * 
	 * @param inEvent
	 */
	@Override
	public boolean onTouchEvent(MotionEvent inEvent) {
		if (inEvent.getAction() == MotionEvent.ACTION_DOWN) {
			this.camera.takePicture(null, null, this);
			this.camera.startPreview();
		}
		return true;
	}

	/**
	 * カメラ撮影直後の処理
	 * 
	 * @param inData
	 * @param inCamera
	 */
	public void onPictureTaken(byte[] inData, Camera inCamera) {
		try {
			String dataName = "photo_" + String.valueOf(Calendar.getInstance().getTimeInMillis()) + ".jpg";
			// Uri dataUri = this.saveDataToURI(inData, dataName);
			String dataPath = this.saveDataToSdCard(inData, dataName);
			if (this.callback != null) {
				// this.callback.onPictureSaved(this, dataUri);
				this.callback.onPictureSaved(this, dataPath);
			}
		} catch (Exception e) {
			this.cameraRelease();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 画像データをSDCardに保存する
	 * 
	 * @param inData
	 * @param inDataName
	 * @return
	 * @throws Exception
	 */
	protected String saveDataToSdCard(byte[] inData, String inDataName) throws Exception {
		String path = "/sdcard/" + inDataName;
		Bitmap bitmap = BitmapFactory.decodeByteArray(inData, 0, inData.length);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
		} catch (IOException e) {
			this.cameraRelease();
			throw e;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return path;
	}

	/**
	 * 画像データをContentResolverを使用して保存する
	 * 
	 * @param inData
	 * @param inDataName
	 * @return
	 * @throws Exception
	 */
	protected Uri saveDataToURI(byte[] inData, String inDataName) throws Exception {
		Bitmap bitmap = BitmapFactory.decodeByteArray(inData, 0, inData.length);
		ContentValues values = new ContentValues();
		values.put(MediaColumns.DISPLAY_NAME, inDataName);
		values.put(ImageColumns.DESCRIPTION, "taken with G1");
		values.put(MediaColumns.MIME_TYPE, "image/jpeg");
		Uri uri = contentResolver.insert(Media.EXTERNAL_CONTENT_URI, values);
		try {
			OutputStream os = contentResolver.openOutputStream(uri);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, os);
			os.close();
		} catch (IOException e) {
			this.cameraRelease();
			throw e;
		}
		return uri;
	}

	private void cameraRelease() {
		if (this.camera != null) {
			this.camera.stopPreview();
			this.camera.release();
			this.camera = null;
		}
	}
}
