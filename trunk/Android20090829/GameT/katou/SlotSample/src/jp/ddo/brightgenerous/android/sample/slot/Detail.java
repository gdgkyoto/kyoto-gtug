/**
 * jp.ddo.brightgenerous.android.sample.slot
 * Detail.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import jp.ddo.brightgenerous.android.sample.slot.view.CameraView;

/**
 * 
 * 
 * @version 1.0
 * @author KATOU
 */
public class Detail extends Activity {

	private ImageView imageView = null;

	private TextView textView = null;

	/** カメラビュー */
	protected CameraView cameraView = null;

	/** メッセージ テキストボックス */
	private EditText messageEditText = null;

	/** 更新ボタン */
	private Button updateButton = null;

	/** id */
	private int id = 0;

	/** メッセージ */
	private CharSequence message = null;

	/**
	 * 
	 * 
	 * @param inBundle
	 */
	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);
		this.setContentView(R.layout.detail);

		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			this.id = bundle.getInt(ImageList.ID_NAME);
		}

		{
			this.textView = (TextView) this.findViewById(R.id.text);
		}
		{
			this.imageView = (ImageView) this.findViewById(R.id.image);
		}

		this.cameraView = (CameraView) this.findViewById(R.id.viewCamera);

		{
			this.messageEditText = (EditText) this.findViewById(R.id.editText);
		}

		{
			this.updateButton = (Button) this.findViewById(R.id.buttonUpdate);
			this.updateButton.setOnClickListener(new OnClickListener() {

				public void onClick(View inView) {
					Detail.this.update();
					Detail.this.cameraView.setData(null);
					Detail.this.finish();
				}
			});
		}
	}

	/**
	 * 
	 */
	@Override
	public void onStart() {
		super.onStart();

		ImageManager.getInstance().reset(this.getApplicationContext(), 50, 50);

		this.textView.setText(ImageManager.getInstance().getMessage(this.id));
		this.imageView.setImageBitmap(ImageManager.getInstance().getImage(this.id));
	}

	/**
	 * カメラを開始する。
	 */
	protected void cameraStart() {
		this.cameraView.start();
	}

	/**
	 * カメラを中止する。
	 */
	protected void cameraCancel() {
		this.cameraView.cancel();
	}

	/**
	 * カメラを撮影する。
	 */
	protected void cameraTake() {
		this.cameraView.take();
	}

	protected void update() {
		byte[] data = this.cameraView.getData();
		if ((data != null) && (0 < data.length)) {
			ImageManager.getInstance().setImage(this.getApplicationContext(), this.id, data);
		}
		this.message = this.messageEditText.getEditableText().toString();
		if ((this.message != null) && (0 < this.message.length())) {
			ImageManager.getInstance().setMessage(this.getApplicationContext(), this.id, this.message);
		}
	}

	/**
	 * 
	 * 
	 * @param inMenu
	 * @return 成否
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu inMenu) {
		super.onCreateOptionsMenu(inMenu);

		inMenu.add(0, 0, Menu.NONE, R.string.menu_reset);
		return true;
	}

	/**
	 * 
	 * 
	 * @param inMenuItem
	 * @return 成否
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem inMenuItem) {

		ImageManager.getInstance().setImage(this.getApplicationContext(), this.id, null);
		ImageManager.getInstance().setMessage(this.getApplicationContext(), this.id, null);

		this.finish();

		return super.onOptionsItemSelected(inMenuItem);
	}
}
