/**
 * jp.ddo.brightgenerous.android.sample.kaodroidsample
 * KaodroidSample.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.kaodroidsample;

import java.util.ArrayList;
import java.util.List;

import jp.ddo.brightgenerous.android.sample.kaodroidsample.data.Group;
import jp.ddo.brightgenerous.android.sample.kaodroidsample.data.KaodroidDbManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 顔droidサンプル メインのアクティビティクラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class KaodroidSample extends ListActivity {

	/** グループIDのキー */
	static public final String GROUP_ID_KEY = "GROUP_ID";

	/** 各ゲームのIntent-filterのActionの値 */
	static public final String INTENT_ACTION = "org.kyotogtug.hackathon090905.gamet.kaodroid.intent.action.GAME";

	/**
	 * グループのListActivityの表示の仲介をするアダプタクラス
	 * 
	 * @version 1.0
	 * @author KATOU
	 */
	static private class GroupAdapter extends BaseAdapter {

		private LayoutInflater inflater = null;

		private List<Group> groups = null;

		/**
		 * コンストラクタ
		 * 
		 * @param inContext
		 * @param inGroups ListActivityに表示するグループのリスト
		 */
		public GroupAdapter(Context inContext, List<Group> inGroups) {
			this.inflater = LayoutInflater.from(inContext);
			this.groups = inGroups;
		}

		/**
		 * 表示するリストのサイズを返す
		 * 
		 * @return 表示するリストのサイズ
		 */
		public int getCount() {
			return this.groups.size();
		}

		/**
		 * 引数のインデックスに対応するリストのアイテムを返す
		 * 
		 * @param inPosition リストのインデックス
		 * @return リストのアイテム
		 */
		public Object getItem(int inPosition) {
			return this.groups == null ? null : this.groups.get(inPosition);
		}

		/**
		 * 引数のインデックスに対応するリストのアイテムのIDを返す
		 * 
		 * @param inPosition リストのインデックス
		 * @return リストのアイテム
		 */
		public long getItemId(int inPosition) {
			return ((Group) this.getItem(inPosition)).getId();
		}

		public View getView(int inPosition, View inView, ViewGroup inViewGroup) {
			View result = inView;
			ViewHolder holder = null;

			if (inView == null) {
				result = this.inflater.inflate(R.layout.group_list_row, null);

				holder = new ViewHolder();
				holder.textView = (TextView) result.findViewById(R.id.text);

				result.setTag(holder);

			} else {
				holder = (ViewHolder) result.getTag();
			}

			holder.textView.setText(((Group) this.getItem(inPosition)).getName());

			return result;
		}

		static class ViewHolder {

			TextView textView;
		}
	}

	/**
	 * コンストラクタ
	 */
	public KaodroidSample() {
	}

	/**
	 * 
	 * 
	 * @param inBundle
	 */
	@Override
	public void onCreate(Bundle inBundle) {
		super.onCreate(inBundle);
	}

	/**
	 * 
	 */
	@Override
	public void onResume() {
		super.onResume();

		this.resetAdapter();
	}

	private void resetAdapter() {
		String[] cols = { "_id", KaodroidDbManager.Groups.COLUMN_NAME };
		Cursor mCursor = this.managedQuery(KaodroidDbManager.Groups.CONTENT_URI, cols, null, null, null);
		List<Group> groups = new ArrayList<Group>();
		while (mCursor.moveToNext()) {
			Group g = new Group();
			g.setId(mCursor.getLong(0));
			g.setName(mCursor.getString(1));
			groups.add(g);
		}
		this.setListAdapter(new GroupAdapter(this, groups));
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
		Intent intent = new Intent();
		intent.setAction(INTENT_ACTION);
		Group g = (Group) this.getListAdapter().getItem(inPosition);
		intent.putExtra(GROUP_ID_KEY, g.getId());
		this.startActivity(intent);
	}

	/**
	 * メニューを表示する直前のイベント
	 * 
	 * @param inMenu
	 * @return 成否
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu inMenu) {
		super.onCreateOptionsMenu(inMenu);

		inMenu.add(0, 0, Menu.NONE, R.string.menu_camera);
		inMenu.add(0, 1, Menu.NONE, R.string.menu_download);
		if(0 < this.getListAdapter().getCount()) {
			inMenu.add(0, 2, Menu.NONE, R.string.menu_clear);
		}

		return true;
	}

	/**
	 * メニューを選択されたイベント
	 * 
	 * @param inMenuItem
	 * @return 成否
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem inMenuItem) {

		switch (inMenuItem.getItemId()) {
			case 0: {
				Intent intent = new Intent(this.getApplicationContext(), CameraActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				this.startActivity(intent);
				break;
			}
			case 1: {
				Intent intent = new Intent(this.getApplicationContext(), DownloadActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				this.startActivity(intent);
				break;
			}
			case 2: {
				this.getContentResolver().delete(KaodroidDbManager.Images.CONTENT_URI, null, null);
				this.getContentResolver().delete(KaodroidDbManager.Groups.CONTENT_URI, null, null);
				this.resetAdapter();
				break;
			}
		}

		return super.onOptionsItemSelected(inMenuItem);
	}
}
