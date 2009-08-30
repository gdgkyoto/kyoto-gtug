/**
 * jp.ddo.brightgenerous.android.sample.kaodroidsample
 * Camera.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.kaodroidsample;

import jp.ddo.brightgenerous.android.sample.kaodroidsample.view.CameraView;
import jp.ddo.brightgenerous.android.sample.kaodroidsample.view.CameraView.Callback;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * カメラ撮影アクティビティクラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class CameraActivity extends Activity implements Callback {

	/** カメラビュー */
	protected CameraView cameraView = null;

	/**
	 * コンストラクタ
	 */
	public CameraActivity() {
	}

	/**
	 * 
	 * 
	 * @param inBundle
	 */
	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);
		this.setContentView(R.layout.camera);

		this.cameraView = (CameraView) this.findViewById(R.id.viewCamera);
		this.cameraView.setCallback(this);
	}

	/**
	 * 画像データ保存直後のイベント
	 * 
	 * @param inCameraView
	 * @param inDataUri
	 */
	public void onPictureSaved(CameraView inCameraView, Uri inDataUri) {
		Intent intent = new Intent(this.getApplicationContext(), KaoRecognizeActivity.class);
		intent.putExtra(KaoRecognizeActivity.URI_KEY, inDataUri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		this.startActivity(intent);
	}

	/**
	 * 画像データ保存直後のイベント
	 * 
	 * @param inCameraView
	 * @param inDataPath
	 */
	public void onPictureSaved(CameraView inCameraView, String inDataPath) {
		Intent intent = new Intent(this.getApplicationContext(), KaoRecognizeActivity.class);
		intent.putExtra(KaoRecognizeActivity.PATH_KEY, inDataPath);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		this.startActivity(intent);
	}
}
