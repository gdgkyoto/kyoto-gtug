/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid
 * KaodroidTestActivity.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid;

import java.util.ArrayList;
import java.util.List;

import org.kyotogtug.hackathon090905.gamet.kaodroid.data.Image;
import org.kyotogtug.hackathon090905.gamet.kaodroid.data.KaodroidDbManager;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * 顔ギャラリーアクティビティクラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class KaodroidGalleryActivity extends Activity {

	/**
	 * ギャラリーの表示の仲介をするアダプタクラス
	 * 
	 * @version 1.0
	 * @author KATOU
	 */
	static private class ImageAdapter extends BaseAdapter {

		private Context context;

		private List<Image> images;

		public ImageAdapter(Context inContext, List<Image> inImages) {
			this.context = inContext;
			this.images = inImages;
		}

		/*
		 * (非 Javadoc)
		 * 
		 * @see android.widget.Adapter#getCount()
		 */
		public int getCount() {
			return this.images == null ? 0 : this.images.size();
		}

		/*
		 * (非 Javadoc)
		 * 
		 * @see android.widget.Adapter#getItem(int)
		 */
		public Object getItem(int inPosition) {
			return this.images.get(inPosition);
		}

		/*
		 * (非 Javadoc)
		 * 
		 * @see android.widget.Adapter#getItemId(int)
		 */
		public long getItemId(int inPosition) {
			return ((Image) this.getItem(inPosition)).getId();
		}

		/*
		 * (非 Javadoc)
		 * 
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		public View getView(int inPosition, View inConvertView, ViewGroup inParent) {
			ImageView imageView = new ImageView(this.context);

			imageView.setImageBitmap(((Image) this.getItem(inPosition)).getBitmap());
			imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);

			return imageView;
		}
	}

	/**
	 * コンストラクタ
	 */
	public KaodroidGalleryActivity() {
	}

	/**
	 * 
	 * 
	 * @param inBundle
	 */
	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);
		this.setContentView(R.layout.gallery);

		long groupId = 0L;
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			groupId = bundle.getLong(Kaodroid.GROUP_ID_KEY);
		}

		List<Image> images = new ArrayList<Image>();
		String[] cols = { "_id", KaodroidDbManager.Images.COLUMN_DATA };
		Cursor mCursor = this.managedQuery(KaodroidDbManager.Images.CONTENT_URI, cols, "c_group_id=" + groupId, null, null);
		while (mCursor.moveToNext()) {
			long id = mCursor.getLong(0);
			byte[] data = mCursor.getBlob(1);
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			Image image = new Image();
			image.setId(id);
			image.setBitmap(bitmap);
			images.add(image);
		}

		Gallery g = (Gallery) this.findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this, images));
	}
}
