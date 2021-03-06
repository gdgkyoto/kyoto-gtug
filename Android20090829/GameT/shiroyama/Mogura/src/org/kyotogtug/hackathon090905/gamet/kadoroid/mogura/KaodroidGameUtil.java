/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.game.slot.util
 * KaodroidGameActivity.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kadoroid.mogura;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

/**
 * é¡droidã²ã¼ã??ã¦ã¼ã?£ã«ã¯ã©ã¹
 * 
 * @version 1.0
 * @author KATOU
 */
public class KaodroidGameUtil {

	/**
	 * ã°ã«ã¼ããã¼ã¿ã¯ã©ã¹
	 * 
	 * @author KATOU
	 */
	static public class Group {

		/** ID */
		private long id = 0L;

		/** åå */
		private String name = null;

		/** æ¥ä»?*/
		private String date = null;

		/** ç»åãªã¹ã?*/
		private List<Image> images = null;

		/**
		 * ã³ã³ã¹ãã©ã¯ã¿
		 */
		public Group() {
		}

		/**
		 * IDãåå¾ãã?
		 * 
		 * @return ID
		 */
		public long getId() {
			return this.id;
		}

		/**
		 * IDãè¨­å®ãã?
		 * 
		 * @param inId ID
		 */
		public void setId(long inId) {
			this.id = inId;
		}

		/**
		 * ååãåå¾ãã?
		 * 
		 * @return åå
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * ååãè¨­å®ãã?
		 * 
		 * @param inName åå
		 */
		public void setName(String inName) {
			this.name = inName;
		}

		/**
		 * æ¥ä»ãåå¾ãã?
		 * 
		 * @return æ¥ä»?
		 */
		public String getDate() {
			return this.date;
		}

		/**
		 * æ¥ä»ãè¨­å®ãã?
		 * 
		 * @param inDate æ¥ä»?
		 */
		public void setDate(String inDate) {
			this.date = inDate;
		}

		/**
		 * ç»åãªã¹ããåå¾ãã?
		 * 
		 * @return ç»åãªã¹ã?
		 */
		public List<Image> getImages() {
			return this.images;
		}

		/**
		 * ç»åãªã¹ããè¨­å®ãã?
		 * 
		 * @param inImages ç»åãªã¹ã?
		 */
		public void setImages(List<Image> inImages) {
			this.images = inImages;
		}
	}

	/**
	 * ç»åãã¼ã¿ã¯ã©ã¹
	 * 
	 * @author KATOU
	 */
	static public class Image {

		/** ID */
		private long id = 0L;

		/** ã°ã«ã¼ãID */
		private long groupId = 0L;

		/** ãããã?ã?? */
		private Bitmap bitmap = null;

		/** åå */
		private String name = null;

		/**
		 * ã³ã³ã¹ãã©ã¯ã¿
		 */
		public Image() {
		}

		/**
		 * IDãåå¾ãã?
		 * 
		 * @return ID
		 */
		public long getId() {
			return this.id;
		}

		/**
		 * IDãè¨­å®ãã?
		 * 
		 * @param inId ID
		 */
		public void setId(long inId) {
			this.id = inId;
		}

		/**
		 * ã°ã«ã¼ãIDãåå¾ãã?
		 * 
		 * @return ã°ã«ã¼ãID
		 */
		public long getGroupId() {
			return this.groupId;
		}

		/**
		 * ã°ã«ã¼ãIDãè¨­å®ãã?
		 * 
		 * @param inGroupId ã°ã«ã¼ãID
		 */
		public void setGroupId(long inGroupId) {
			this.groupId = inGroupId;
		}

		/**
		 * ãããã?ã?? ãåå¾ãã?
		 * 
		 * @return ãããã?ã??
		 */
		public Bitmap getBitmap() {
			return this.bitmap;
		}

		/**
		 * ãããã?ã?? ãè¨­å®ãã?
		 * 
		 * @param inBitmap ãããã?ã??
		 */
		public void setBitmap(Bitmap inBitmap) {
			this.bitmap = inBitmap;
		}

		/**
		 * ååãåå¾ãã?
		 * 
		 * @return åå
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * ååãè¨­å®ãã?
		 * 
		 * @param inName åå
		 */
		public void setName(String inName) {
			this.name = inName;
		}
	}

	static private final KaodroidGameUtil instance = new KaodroidGameUtil();

	static private final String GROUP_ID_KEY = "GROUP_ID";

	static private final String AUTHORITY = "org.kyotogtug.hackathon090905.gamet.kaodroid.provider.GroupManager";

	/**
	 * ã³ã³ã¹ãã©ã¯ã¿
	 */
	private KaodroidGameUtil() {
	}

	/**
	 * ã¤ã³ã¹ã¿ã³ã¹ãåå¾ãã?
	 * 
	 * @return ã¤ã³ã¹ã¿ã³ã¹
	 */
	static public KaodroidGameUtil getInstance() {
		return instance;
	}

	public long getGroupId(Bundle inBundle) {
		return inBundle.getLong(GROUP_ID_KEY);
	}

	/**
	 * ã°ã«ã¼ããã¼ã¿ãèª­ã¿è¾¼ã?
	 * 
	 * @param inActivity
	 * @return ã°ã«ã¼ããã¼ã¿
	 */
	public Group roadGroup(Activity inActivity) {

		//long groupId = -1L;
		long groupId = 0L;
		Bundle bundle = inActivity.getIntent().getExtras();
		if (bundle != null) {
			groupId = this.getGroupId(bundle);
		}

		Group group = null;
		if (groupId==0) {
			return group;
		}
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
	 * ç»åãªã¹ããèª­ã¿è¾¼ã?
	 * 
	 * @param inActivity
	 * @returnã?ª­ã¿è¾¼ãã ç»åãªã¹ã?
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
