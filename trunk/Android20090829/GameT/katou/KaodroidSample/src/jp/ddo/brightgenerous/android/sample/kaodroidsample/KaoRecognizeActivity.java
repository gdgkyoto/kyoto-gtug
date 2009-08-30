/**
 * jp.ddo.brightgenerous.android.sample.kaodroidsample
 * KaoRecognizeActivity.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.kaodroidsample;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jp.ddo.brightgenerous.android.sample.kaodroidsample.data.KaoRect;
import jp.ddo.brightgenerous.android.sample.kaodroidsample.data.KaodroidDbManager;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
	static public final String URI_KEY = "jp.ddo.brightgenerous.android.sample.kaodroidsample.KaoRecognizeActivity.URI_KEY";

	/** 画像データPATH取得キー */
	static public final String PATH_KEY = "jp.ddo.brightgenerous.android.sample.kaodroidsample.KaoRecognizeActivity.PATH_KEY";

	/** イメージビュー */
	private ImageView imageView = null;

	protected EditText editText = null;

	private Button button = null;

	private Bitmap bitmap = null;

	private List<KaoRect> kaoRects = null;

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

		Bitmap decorateBitmap = this.decorateKao(this.bitmap);

		this.imageView = (ImageView) this.findViewById(R.id.imageView);
		this.imageView.setImageBitmap(decorateBitmap);

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
		if ((this.kaoRects == null) || this.kaoRects.isEmpty()) {
			return false;
		}
		String name = this.editText.getEditableText().toString();
		if (TextUtils.isEmpty(name)) {
			return false;
		}

		// グループのデータを登録
		long groupId = 0L;
		{
			ContentValues values = new ContentValues();
			values.put(KaodroidDbManager.Groups.COLUMN_NAME, name);
			Uri uri = this.getContentResolver().insert(KaodroidDbManager.Groups.CONTENT_URI, values);
			groupId = ContentUris.parseId(uri);
		}
		// 顔画像のデータを登録
		for (KaoRect kaoRect : this.kaoRects) {
			Bitmap kaoBitmap = Bitmap.createBitmap(this.bitmap, kaoRect.getX(), kaoRect.getY(), kaoRect.getWidth(), kaoRect.getHeight());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			kaoBitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
			ContentValues values = new ContentValues();
			values.put(KaodroidDbManager.Images.COLUMN_GROUP_ID, Long.valueOf(groupId));
			values.put(KaodroidDbManager.Images.COLUMN_DATA, baos.toByteArray());
			this.getContentResolver().insert(KaodroidDbManager.Images.CONTENT_URI, values);
		}
		return true;
	}

	private Bitmap decorateKao(Bitmap inBitmap) {
		Bitmap decorateBitmap = inBitmap;
		FaceDetector.Face[] faces = new FaceDetector.Face[20];
		FaceDetector detector = new FaceDetector(decorateBitmap.getWidth(), decorateBitmap.getHeight(), faces.length);
		int numFaces = detector.findFaces(decorateBitmap, faces);
		if (numFaces > 0) {
			// 赤い四角を描画しています
			Paint paint = new Paint();
			paint.setColor(Color.argb(255, 255, 0, 0));
			paint.setStyle(Style.STROKE);
			// Canvasのコンストラクタにはコピーを渡さないとエラーになる
			decorateBitmap = decorateBitmap.copy(Bitmap.Config.RGB_565, true);
			Canvas canvas = new Canvas(decorateBitmap);
			this.kaoRects = new ArrayList<KaoRect>();
			for (int i = 0; i < numFaces; i++) {
				Face face = faces[i];
				PointF midPoint = new PointF(0, 0);
				face.getMidPoint(midPoint);
				// 顔の中心と両目間の距離から顔のサイズを推定する
				float distance = face.eyesDistance() * 4;
				RectF rect = new RectF();
				rect.left = midPoint.x - distance / 2;
				rect.top = midPoint.y - distance / 2;
				rect.right = midPoint.x + distance / 2;
				rect.bottom = midPoint.y + distance / 2;
				canvas.drawRect(rect, paint);

				KaoRect kaoRect = new KaoRect();
				kaoRect.setX((int) (rect.left < 0 ? 0 : rect.left));
				kaoRect.setY((int) (rect.top < 0 ? 0 : rect.top));
				kaoRect.setWidth((int) (rect.right < this.bitmap.getWidth() ? rect.right - kaoRect.getX() : this.bitmap.getWidth() - kaoRect.getX()));
				kaoRect.setHeight((int) (rect.bottom < this.bitmap.getHeight() ? rect.bottom - kaoRect.getY() : this.bitmap.getHeight() - kaoRect.getY()));
				this.kaoRects.add(kaoRect);
			}
		}
		return decorateBitmap;
	}
}
