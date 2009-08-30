/**
 * jp.ddo.brightgenerous.android.sample.slot
 * ImageList.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * 
 * @version 1.0
 * @author KATOU
 */
public class ImageList extends ListActivity {

	/**  */
	static public final String ID_NAME = "ID_NAME";

	/**
	 * 画像アダプタ。
	 * 
	 * @version 1.0
	 * @author KATOU
	 */
	static private class ImageAdapter extends BaseAdapter {

		private LayoutInflater inflater = null;

		private int[] ids = null;

		public ImageAdapter(Context inContext, int[] inIds) {
			this.inflater = LayoutInflater.from(inContext);
			this.ids = inIds;
		}

		public int getCount() {
			return this.ids.length;
		}

		public Object getItem(int inPosition) {
			return Integer.valueOf(this.ids[inPosition]);
		}

		public long getItemId(int inPosition) {
			return this.ids[inPosition];
		}

		public View getView(int inPosition, View inView, ViewGroup inViewGroup) {
			View result = inView;
			ViewHolder holder = null;

			if (inView == null) {
				result = this.inflater.inflate(R.layout.image_list_row, null);

				holder = new ViewHolder();
				holder.textView = (TextView) result.findViewById(R.id.text);
				holder.imageView = (ImageView) result.findViewById(R.id.image);

				result.setTag(holder);
			} else {
				holder = (ViewHolder) result.getTag();
			}

			holder.textView.setText(ImageManager.getInstance().getMessage((int) this.getItemId(inPosition)));
			holder.imageView.setImageBitmap(ImageManager.getInstance().getImage((int) this.getItemId(inPosition)));

			return result;
		}

		static class ViewHolder {

			TextView textView;

			ImageView imageView;
		}
	}

	/**
	 * 
	 * 
	 * @param inBundle
	 */
	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);

		this.setListAdapter(new ImageAdapter(this, ImageManager.getInstance().getIds()));
	}

	/**
	 * 
	 */
	@Override
	protected void onStart() {
		super.onStart();

		ImageManager.getInstance().reset(this.getApplicationContext(), 50, 50);
	}

	/**
	 * This method will be called when an item in the list is selected.
	 * 
	 * @param inListView
	 * @param inView
	 * @param inPosition
	 * @param inId
	 */
	@Override
	protected void onListItemClick(ListView inListView, View inView, int inPosition, long inId) {
		super.onListItemClick(inListView, inView, inPosition, inId);

		Intent intent = new Intent(this.getApplicationContext(), Detail.class);
		intent.putExtra(ID_NAME, ImageManager.getInstance().getIds()[inPosition]);
		this.startActivity(intent);
	}
}
