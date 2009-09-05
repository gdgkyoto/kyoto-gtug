package org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.data.KaoRect;
import org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.util.KaodroidGameUtil;
import org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.util.TempUtil;

import android.app.Activity;
import android.content.Intent;
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
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 
 * 
 * 
 * @author KATOU
 */
public class KaodroidCora extends Activity {

	static public final String INTENT_RECT_KEY = "rect";

	private ImageView imageView = null;

	private AutoCompleteTextView editText = null;

	private Button searchButton = null;

	private Button submitButton = null;

	private byte[] data = null;

	private List<KaoRect> kaoRects = null;

	private Bitmap bitmap = null;

	/**
	 * 
	 * @param inBundle
	 */
	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);

		this.setContentView(R.layout.cora);

		this.searchButton = (Button) this.findViewById(R.id.searchButton);
		this.searchButton.setOnClickListener(new OnClickListener() {

			public void onClick(View inView) {
				KaodroidCora.this.doSearch();
			}
		});
		this.submitButton = (Button) this.findViewById(R.id.submitButton);
		this.submitButton.setOnClickListener(new OnClickListener() {

			public void onClick(View inView) {
				KaodroidCora.this.doSubmit();
			}
		});
		this.imageView = (ImageView) this.findViewById(R.id.imageView);
		this.editText = (AutoCompleteTextView) this.findViewById(R.id.editText);
		{
			String[] tags = { "http://taboonochou.files.wordpress.com/2009/07/new-smap.jpg", "http://www.geocities.co.jp/Outdoors-Mountain/6996/profile/images/tashiro.jpg",
					"http://av.watch.impress.co.jp/docs/20050331/maxm2.jpg", "http://thumbnail.image.rakuten.co.jp/@0_mall/book/cabinet/8470/84702924.jpg" };
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tags);
			this.editText.setAdapter(adapter);
		}
	}

	protected void doSearch() {

		String text = this.editText.getEditableText().toString();
		if (TextUtils.isEmpty(text)) {
			return;
		}

		HttpURLConnection http = null;
		InputStream is = null;
		try {
			URL url = new URL(text);
			http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			http.connect();
			is = http.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[0x1000];
			int size = 0;
			while ((size = is.read(buf)) != -1) {
				baos.write(buf, 0, size);
			}
			baos.flush();
			this.data = baos.toByteArray();
		} catch (Exception e) {
			return;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
				is = null;
			}
			if (http != null) {
				try {
					http.disconnect();
				} catch (Exception e) {
				}
				http = null;
			}
		}

		this.bitmap = BitmapFactory.decodeByteArray(this.data, 0, this.data.length);
		if (this.bitmap != null) {
			this.bitmap = this.decorateKao(this.bitmap);
		}
		this.imageView.setImageBitmap(this.bitmap);
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

	protected void doSubmit() {
		if ((this.kaoRects == null) || this.kaoRects.isEmpty()) {
			return;
		}
		try {
			String dataPath = TempUtil.getInstance().saveDataToSdCard(this.data, "kaodroidcora.photo.jpg");
			Intent intent = new Intent(this.getApplicationContext(), KaoRecognizeActivity.class);
			intent.putExtra(KaoRecognizeActivity.PATH_KEY, dataPath);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			intent.putExtra(INTENT_RECT_KEY, this.kaoRects.get((new Random(System.currentTimeMillis()).nextInt(this.kaoRects.size()))));
			KaodroidGameUtil.getInstance().setGroupId2NextIntent(intent, this);
			this.startActivity(intent);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
