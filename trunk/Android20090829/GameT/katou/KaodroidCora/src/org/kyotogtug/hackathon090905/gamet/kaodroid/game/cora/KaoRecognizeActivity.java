/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora
 * KaoRecognizeActivity.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.data.KaoRect;
import org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.util.KaodroidGameUtil;
import org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.util.KaodroidGameUtil.Group;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 顔を認識するアクティビティクラス
 * 
 * @author KATOU
 */
public class KaoRecognizeActivity extends Activity {

	/** 画像データURI取得キー */
	static public final String URI_KEY = "org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.KaoRecognizeActivity.URI_KEY";

	/** 画像データPATH取得キー */
	static public final String PATH_KEY = "org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.KaoRecognizeActivity.PATH_KEY";

	/** イメージビュー */
	private ImageView imageView = null;

	protected EditText editText = null;

	private Button button = null;

	private Bitmap bitmap = null;

	private Bitmap kaoBitmap = null;

	private KaoRect kaoRect = null;

	private KaoRect currentKaoRect = null;

	/**
	 * コンストラクタ
	 */
	public KaoRecognizeActivity() {
	}

	/**
	 * 
	 * 
	 * @param inBundle
	 */
	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);
		this.setContentView(R.layout.kaorecognize);

		Group group = KaodroidGameUtil.getInstance().roadGroup(this);
		this.kaoBitmap = group.getImages().get(0).getBitmap();

		this.kaoRect = (KaoRect) this.getIntent().getSerializableExtra(KaodroidCora.INTENT_RECT_KEY);

		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			Uri uri = (Uri) bundle.getParcelable(URI_KEY);
			String path = bundle.getString(PATH_KEY);
			InputStream is = null;
			try {
				if (uri != null) {
					is = this.getContentResolver().openInputStream(uri);
				} else if (path != null) {
					is = new FileInputStream(path);
				}
				this.bitmap = BitmapFactory.decodeStream(is);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
					}
				}
			}
		}

		this.currentKaoRect = this.recognizeKao(this.bitmap);
		this.bitmap = this.decorateKao();

		this.imageView = (ImageView) this.findViewById(R.id.imageView);
		this.imageView.setImageBitmap(this.bitmap);

		this.editText = (EditText) this.findViewById(R.id.editText);
		this.button = (Button) this.findViewById(R.id.button);
		this.button.setOnClickListener(new OnClickListener() {

			public void onClick(View inView) {
				if (KaoRecognizeActivity.this.doClickButton()) {
					KaoRecognizeActivity.this.finish();
				}
			}
		});
	}

	protected boolean doClickButton() {

		AlertDialog LDialog = new AlertDialog.Builder(this)
    .setMessage("Comming soon...")
    .setPositiveButton(android.R.string.ok, null).create();
    LDialog.show(); 
 
		return true;
	}

	private KaoRect recognizeKao(Bitmap inBitmap) {
		Bitmap decorateBitmap = inBitmap;
		FaceDetector.Face[] faces = new FaceDetector.Face[20];
		FaceDetector detector = new FaceDetector(decorateBitmap.getWidth(), decorateBitmap.getHeight(), faces.length);
		int numFaces = detector.findFaces(decorateBitmap, faces);
		KaoRect kr = null;
		if (numFaces > 0) {
			// Canvasのコンストラクタにはコピーを渡さないとエラーになる
			decorateBitmap = decorateBitmap.copy(Bitmap.Config.RGB_565, true);
			{
				Face face = faces[(new Random(System.currentTimeMillis())).nextInt(numFaces)];
				PointF midPoint = new PointF(0, 0);
				face.getMidPoint(midPoint);
				// 顔の中心と両目間の距離から顔のサイズを推定する
				float distance = face.eyesDistance() * 4;
				RectF rect = new RectF();
				rect.left = midPoint.x - distance / 2;
				rect.top = midPoint.y - distance / 2;
				rect.right = midPoint.x + distance / 2;
				rect.bottom = midPoint.y + distance / 2;

				kr = new KaoRect();
				kr.setX((int) (rect.left < 0 ? 0 : rect.left));
				kr.setY((int) (rect.top < 0 ? 0 : rect.top));
				kr.setWidth((int) (rect.right < this.bitmap.getWidth() ? rect.right - kr.getX() : this.bitmap.getWidth() - kr.getX()));
				kr.setHeight((int) (rect.bottom < this.bitmap.getHeight() ? rect.bottom - kr.getY() : this.bitmap.getHeight() - kr.getY()));
			}
		}
		return kr;
	}

	private Bitmap decorateKao() {

		Bitmap buf = this.bitmap.copy(this.bitmap.getConfig(), true);
		Canvas canvas = new Canvas(buf);
		Paint paint = new Paint();
		this.kaoBitmap = Bitmap.createScaledBitmap(this.kaoBitmap, this.currentKaoRect.getWidth(), this.currentKaoRect.getHeight(), false);
		canvas.drawBitmap(this.kaoBitmap, this.currentKaoRect.getX(), this.currentKaoRect.getY(), paint);

		return buf;
	}
}
