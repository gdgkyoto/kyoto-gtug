/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.data
 * GroupManager.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * コンテンツプロバイダのための定数クラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class KaodroidDbManager {

	static public final String AUTHORITY = "org.kyotogtug.hackathon090905.gamet.kaodroid.provider.GroupManager";

	/**
	 * コンストラクタ
	 */
	private KaodroidDbManager() {
	}

	/**
	 * グループテーブルに関する定数クラス
	 * 
	 * @author KATOU
	 */
	static public final class Groups implements BaseColumns {

		static public final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/groups");

		static public final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.org.kyotogtug.hackathon090905.gamet.kaodroid.group";

		static public final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.org.kyotogtug.hackathon090905.gamet.kaodroid.group";

		static public final String TABLE = "t_group";

		static public final String COLUMN_NAME = "c_name";

		static public final String COLUMN_DATE = "c_date";

		/**
		 * コンストラクタ
		 */
		private Groups() {
		}
	}

	/**
	 * 画像テーブルに関する定数クラス
	 * 
	 * @author KATOU
	 */
	static public final class Images implements BaseColumns {

		static public final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/images");

		static public final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.org.kyotogtug.hackathon090905.gamet.kaodroid.image";

		static public final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.org.kyotogtug.hackathon090905.gamet.kaodroid.image";

		static public final String TABLE = "t_image";

		static public final String COLUMN_GROUP_ID = "c_group_id";

		static public final String COLUMN_DATA = "c_data";

		static public final String COLUMN_NAME = "c_name";

		/**
		 * コンストラクタ
		 */
		private Images() {
		}
	}
}
