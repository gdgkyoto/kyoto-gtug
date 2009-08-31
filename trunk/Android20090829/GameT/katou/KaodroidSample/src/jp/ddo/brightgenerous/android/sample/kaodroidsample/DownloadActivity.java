/**
 * jp.ddo.brightgenerous.android.sample.kaodroidsample
 * DownloadActivity.java
 *
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.kaodroidsample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jp.ddo.brightgenerous.android.sample.kaodroidsample.util.TempUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 
 * 
 * @author KATOU
 */
public class DownloadActivity extends Activity {

	private ImageView imageView = null;

	private EditText editText = null;

	private Button searchButton = null;

	private Button submitButton = null;

	private byte[] data = null;

	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);

		this.setContentView(R.layout.download);

		this.searchButton = (Button) this.findViewById(R.id.searchButton);
		this.searchButton.setOnClickListener(new OnClickListener() {

			public void onClick(View inView) {
				DownloadActivity.this.doSearch();
			}
		});
		this.submitButton = (Button) this.findViewById(R.id.submitButton);
		this.submitButton.setOnClickListener(new OnClickListener() {

			public void onClick(View inView) {
				DownloadActivity.this.doSubmit();
			}
		});
		this.imageView = (ImageView) this.findViewById(R.id.imageView);
		this.editText = (EditText) this.findViewById(R.id.editText);
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

		Bitmap bitmap = BitmapFactory.decodeByteArray(this.data, 0, this.data.length);
		this.imageView.setImageBitmap(bitmap);
	}

	protected void doSubmit() {
		if (this.data == null) {
			return;
		}
		try {
			String dataPath = TempUtil.getInstance().saveDataToSdCard(this.data, "kaodroid.photo.jpg");
			Intent intent = new Intent(this.getApplicationContext(), KaoRecognizeActivity.class);
			intent.putExtra(KaoRecognizeActivity.PATH_KEY, dataPath);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			this.startActivity(intent);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
