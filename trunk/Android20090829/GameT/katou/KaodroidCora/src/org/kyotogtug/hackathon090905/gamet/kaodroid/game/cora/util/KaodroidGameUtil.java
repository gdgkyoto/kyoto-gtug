/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.game.slot.util
 * KaodroidGameActivity.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

/**
 * 顔droidゲームのユーティルクラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class KaodroidGameUtil {

	/**
	 * グループデータクラス
	 * 
	 * @author KATOU
	 */
	static public class Group {

		/** ID */
		private long id = 0L;

		/** 名前 */
		private String name = null;

		/** 日付 */
		private String date = null;

		/** 画像リスト */
		private List<Image> images = null;

		/**
		 * コンストラクタ
		 */
		public Group() {
		}

		/**
		 * IDを取得する
		 * 
		 * @return ID
		 */
		public long getId() {
			return this.id;
		}

		/**
		 * IDを設定する
		 * 
		 * @param inId ID
		 */
		public void setId(long inId) {
			this.id = inId;
		}

		/**
		 * 名前を取得する
		 * 
		 * @return 名前
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * 名前を設定する
		 * 
		 * @param inName 名前
		 */
		public void setName(String inName) {
			this.name = inName;
		}

		/**
		 * 日付を取得する
		 * 
		 * @return 日付
		 */
		public String getDate() {
			return this.date;
		}

		/**
		 * 日付を設定する
		 * 
		 * @param inDate 日付
		 */
		public void setDate(String inDate) {
			this.date = inDate;
		}

		/**
		 * 画像リストを取得する
		 * 
		 * @return 画像リスト
		 */
		public List<Image> getImages() {
			return this.images;
		}

		/**
		 * 画像リストを設定する
		 * 
		 * @param inImages 画像リスト
		 */
		public void setImages(List<Image> inImages) {
			this.images = inImages;
		}
	}

	/**
	 * 画像データクラス
	 * 
	 * @author KATOU
	 */
	static public class Image {

		/** ID */
		private long id = 0L;

		/** グループID */
		private long groupId = 0L;

		/** ビットマップ */
		private Bitmap bitmap = null;

		/** 名前 */
		private String name = null;

		/**
		 * コンストラクタ
		 */
		public Image() {
		}

		/**
		 * IDを取得する
		 * 
		 * @return ID
		 */
		public long getId() {
			return this.id;
		}

		/**
		 * IDを設定する
		 * 
		 * @param inId ID
		 */
		public void setId(long inId) {
			this.id = inId;
		}

		/**
		 * グループIDを取得する
		 * 
		 * @return グループID
		 */
		public long getGroupId() {
			return this.groupId;
		}

		/**
		 * グループIDを設定する
		 * 
		 * @param inGroupId グループID
		 */
		public void setGroupId(long inGroupId) {
			this.groupId = inGroupId;
		}

		/**
		 * ビットマップ を取得する
		 * 
		 * @return ビットマップ
		 */
		public Bitmap getBitmap() {
			return this.bitmap;
		}

		/**
		 * ビットマップ を設定する
		 * 
		 * @param inBitmap ビットマップ
		 */
		public void setBitmap(Bitmap inBitmap) {
			this.bitmap = inBitmap;
		}

		/**
		 * 名前を取得する
		 * 
		 * @return 名前
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * 名前を設定する
		 * 
		 * @param inName 名前
		 */
		public void setName(String inName) {
			this.name = inName;
		}
	}

	static private final KaodroidGameUtil instance = new KaodroidGameUtil();

	static public final String GROUP_ID_KEY = "GROUP_ID";

	static private final String AUTHORITY = "org.kyotogtug.hackathon090905.gamet.kaodroid.provider.GroupManager";

	/**
	 * コンストラクタ
	 */
	private KaodroidGameUtil() {
	}

	/**
	 * インスタンスを取得する
	 * 
	 * @return インスタンス
	 */
	static public KaodroidGameUtil getInstance() {
		return instance;
	}

	public long getGroupId(Bundle inBundle) {
		return inBundle.getLong(GROUP_ID_KEY);
	}

	public void setGroupId2NextIntent(Intent inIntent, Activity inActivity) {
		long groupId = 0L;
		Bundle bundle = inActivity.getIntent().getExtras();
		if (bundle != null) {
			groupId = this.getGroupId(bundle);
		}
		inIntent.putExtra(GROUP_ID_KEY, groupId);
	}

	/**
	 * グループデータを読み込む
	 * 
	 * @param inActivity
	 * @return グループデータ
	 */
	public Group roadGroup(Activity inActivity) {

		long groupId = -1L;
		Bundle bundle = inActivity.getIntent().getExtras();
		if (bundle != null) {
			groupId = this.getGroupId(bundle);
		}

		Group group = null;
		String[] cols = { "c_name", "c_date" };
		Cursor mCursor = inActivity.managedQuery(Uri.withAppendedPath(Uri.parse("content://" + AUTHORITY + "/groups"), String.valueOf(groupId)), cols, null, null, null);
		if (mCursor.moveToNext()) {
			group = new Group();
			String name = mCursor.getString(0);
			String date = mCursor.getString(1);
			group.setName(name);
			group.setDate(date);

			group.setImages(this.roadImages(inActivity));
		}

		return group;
	}

	/**
	 * 画像リストを読み込む
	 * 
	 * @param inActivity
	 * @return　読み込んだ画像リスト
	 */
	protected List<Image> roadImages(Activity inActivity) {

		long groupId = -1L;
		Bundle bundle = inActivity.getIntent().getExtras();
		if (bundle != null) {
			groupId = bundle.getLong(GROUP_ID_KEY);
		}

		List<Image> images = new ArrayList<Image>();
		String[] cols = { "_id", "c_data", "c_name" };
		Cursor mCursor = inActivity.managedQuery(Uri.parse("content://" + AUTHORITY + "/images"), cols, "c_group_id=" + groupId, null, null);
		while (mCursor.moveToNext()) {
			long id = mCursor.getLong(0);
			byte[] data = mCursor.getBlob(1);
			String name = mCursor.getString(2);
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			Image image = new Image();
			image.setId(id);
			image.setGroupId(groupId);
			image.setBitmap(bitmap);
			image.setName(name);
			images.add(image);
		}

		return images;
	}

	public void updateGroup(Group inGroup) {
	}

	public void deleteGroup(Group inGroup) {
	}

	public void insertImage(Image inImage) {
	}

	public void updateImage(Image inImage) {
	}

	public void deleteImage(Image inImage) {
	}
}
