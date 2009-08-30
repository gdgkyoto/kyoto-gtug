/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.game
 * KaodroidGallery.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid.game;

import java.util.List;

import org.kyotogtug.hackathon090905.gamet.kaodroid.fw.KaodroidGameUtil;
import org.kyotogtug.hackathon090905.gamet.kaodroid.fw.KaodroidGameUtil.Group;
import org.kyotogtug.hackathon090905.gamet.kaodroid.fw.KaodroidGameUtil.Image;

import android.app.Activity;
import android.content.Context;
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
public class KaodroidGallery extends Activity {

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
	public KaodroidGallery() {
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

		Gallery g = (Gallery) this.findViewById(R.id.gallery);
		Group group = KaodroidGameUtil.getInstance().roadGroup(this, inBundle);
		g.setAdapter(new ImageAdapter(this, group.getImages()));
	}
}
