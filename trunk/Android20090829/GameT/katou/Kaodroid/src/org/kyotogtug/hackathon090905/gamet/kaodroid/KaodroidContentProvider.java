/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid
 * KaodroidContentProvider.java
 *
 * @version 1.0
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid;

import java.util.HashMap;
import java.util.Map;

import org.kyotogtug.hackathon090905.gamet.kaodroid.data.KaodroidDbManager;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

/**
 * 顔droidのコンテントプロバイダクラス
 * 
 * @version 1.0
 * @author KATOU
 */
public class KaodroidContentProvider extends ContentProvider {

	/** DB名 */
	static private final String DATABASE_NAME = "kaodroid.db";

	/** DBのバージョン */
	static private final int DATABASE_VERSION = 1;

	/**
	 * SQLiteによるContentProviderサポートクラス
	 * 
	 * @author KATOU
	 */
	static private class DatabaseHelper extends SQLiteOpenHelper {

		/**
		 * コンストラクタ
		 * 
		 * @param inContext
		 */
		DatabaseHelper(Context inContext) {
			super(inContext, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/*
		 * (非 Javadoc)
		 * 
		 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
		 */
		@Override
		public void onCreate(SQLiteDatabase inDb) {
			StringBuilder sql = new StringBuilder();
			{
				sql.append("CREATE TABLE ");
				sql.append(KaodroidDbManager.Groups.TABLE);
				sql.append("(");
				sql.append("_id INTEGER PRIMARY KEY,");
				sql.append(KaodroidDbManager.Groups.COLUMN_NAME).append(" TEXT,");
				sql.append(KaodroidDbManager.Groups.COLUMN_DATE).append(" TEXT");
				sql.append(")");
				inDb.execSQL(sql.toString());
			}
			{
				sql = new StringBuilder();
				sql.append("CREATE TABLE ");
				sql.append(KaodroidDbManager.Images.TABLE);
				sql.append("(");
				sql.append("_id INTEGER PRIMARY KEY,");
				sql.append(KaodroidDbManager.Images.COLUMN_GROUP_ID).append(" INTEGER,");
				sql.append(KaodroidDbManager.Images.COLUMN_DATA).append(" BLOB,");
				sql.append(KaodroidDbManager.Images.COLUMN_NAME).append(" TEXT");
				sql.append(")");
				inDb.execSQL(sql.toString());
			}
		}

		/*
		 * (非 Javadoc)
		 * 
		 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
		 */
		@Override
		public void onUpgrade(SQLiteDatabase inDb, int inOldVersion, int inNewVersion) {
			StringBuilder sql = new StringBuilder();
			{
				sql.append("DROP TABLE IF EXISTS ");
				sql.append(KaodroidDbManager.Groups.TABLE);
				inDb.execSQL(sql.toString());
			}
			{
				sql.append("DROP TABLE IF EXISTS ");
				sql.append(KaodroidDbManager.Images.TABLE);
				inDb.execSQL(sql.toString());
			}
			this.onCreate(inDb);
		}
	}

	static private final int GROUPS = 1;

	static private final int GROUPS_ID = 2;

	static private final int IMAGES = 3;

	static private final int IMAGES_ID = 4;

	static private final UriMatcher URI_MATCHER;

	static private final Map<String, String> GROUPS_PROJECTION_MAP;

	static private final Map<String, String> IMAGES_PROJECTION_MAP;
	static {
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(KaodroidDbManager.AUTHORITY, "groups", GROUPS);
		URI_MATCHER.addURI(KaodroidDbManager.AUTHORITY, "groups/#", GROUPS_ID);
		URI_MATCHER.addURI(KaodroidDbManager.AUTHORITY, "images", IMAGES);
		URI_MATCHER.addURI(KaodroidDbManager.AUTHORITY, "images/#", IMAGES_ID);

		GROUPS_PROJECTION_MAP = new HashMap<String, String>();
		GROUPS_PROJECTION_MAP.put(BaseColumns._ID, "_id");
		GROUPS_PROJECTION_MAP.put(KaodroidDbManager.Groups.COLUMN_NAME, "c_name");
		GROUPS_PROJECTION_MAP.put(KaodroidDbManager.Groups.COLUMN_DATE, "c_date");

		IMAGES_PROJECTION_MAP = new HashMap<String, String>();
		IMAGES_PROJECTION_MAP.put(BaseColumns._ID, "_id");
		IMAGES_PROJECTION_MAP.put(KaodroidDbManager.Images.COLUMN_GROUP_ID, "c_group_id");
		IMAGES_PROJECTION_MAP.put(KaodroidDbManager.Images.COLUMN_NAME, "c_name");
		IMAGES_PROJECTION_MAP.put(KaodroidDbManager.Images.COLUMN_DATA, "c_data");
	}

	private DatabaseHelper dbHelper = null;

	/**
	 * コンストラクタ
	 */
	public KaodroidContentProvider() {
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		this.dbHelper = new DatabaseHelper(this.getContext());
		return true;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri inUri) {
		switch (URI_MATCHER.match(inUri)) {
			case GROUPS:
				return KaodroidDbManager.Groups.CONTENT_TYPE;
			case GROUPS_ID:
				return KaodroidDbManager.Groups.CONTENT_ITEM_TYPE;
			case IMAGES:
				return KaodroidDbManager.Images.CONTENT_TYPE;
			case IMAGES_ID:
				return KaodroidDbManager.Images.CONTENT_ITEM_TYPE;
			default:
				throw new IllegalArgumentException("Unknown URL " + inUri);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri inUri, String[] inProjection, String inSelection, String[] inSelectionArgs, String inSortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		String orderBy = null;
		switch (URI_MATCHER.match(inUri)) {
			case GROUPS: {
				qb.setTables(String.valueOf(KaodroidDbManager.Groups.TABLE));
				qb.setProjectionMap(GROUPS_PROJECTION_MAP);
				orderBy = !TextUtils.isEmpty(inSortOrder) ? inSortOrder : KaodroidDbManager.Groups.COLUMN_NAME;
				break;
			}
			case GROUPS_ID: {
				qb.setTables(String.valueOf(KaodroidDbManager.Groups.TABLE));
				qb.setProjectionMap(GROUPS_PROJECTION_MAP);
				qb.appendWhere("_id=" + inUri.getPathSegments().get(1));
				orderBy = inSortOrder;
				break;
			}
			case IMAGES: {
				qb.setTables(String.valueOf(KaodroidDbManager.Images.TABLE));
				qb.setProjectionMap(IMAGES_PROJECTION_MAP);
				orderBy = !TextUtils.isEmpty(inSortOrder) ? inSortOrder : KaodroidDbManager.Images.COLUMN_NAME;
				break;
			}
			case IMAGES_ID: {
				qb.setTables(String.valueOf(KaodroidDbManager.Images.TABLE));
				qb.setProjectionMap(IMAGES_PROJECTION_MAP);
				qb.appendWhere("_id=" + inUri.getPathSegments().get(1));
				orderBy = inSortOrder;
				break;
			}
		}

		SQLiteDatabase sdb = this.dbHelper.getReadableDatabase();
		Cursor cursor = qb.query(sdb, inProjection, inSelection, inSelectionArgs, null, null, orderBy);
		cursor.setNotificationUri(this.getContext().getContentResolver(), inUri);

		return cursor;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri inUri, ContentValues inInitialValues) {
		int code = URI_MATCHER.match(inUri);
		switch (code) {
			case GROUPS:
			case IMAGES: {
				break;
			}
			default:
				throw new IllegalArgumentException("Unknown URL *** " + inUri);
		}

		ContentValues values;
		if (inInitialValues != null) {
			values = new ContentValues(inInitialValues);
		} else {
			values = new ContentValues();
		}

		SQLiteDatabase sdb = this.dbHelper.getWritableDatabase();
		if (code == GROUPS) {
			long rowID = sdb.insert(KaodroidDbManager.Groups.TABLE, "NULL", values);
			if (0 < rowID) {
				Uri newUri = ContentUris.withAppendedId(KaodroidDbManager.Groups.CONTENT_URI, rowID);
				this.getContext().getContentResolver().notifyChange(newUri, null);
				return newUri;
			}
		} else if (code == IMAGES) {
			long rowID = sdb.insert(KaodroidDbManager.Images.TABLE, "NULL", values);
			if (0 < rowID) {
				Uri newUri = ContentUris.withAppendedId(KaodroidDbManager.Images.CONTENT_URI, rowID);
				this.getContext().getContentResolver().notifyChange(newUri, null);
				return newUri;
			}
		}

		throw new IllegalArgumentException("Failed to insert row into " + inUri);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri inUri, ContentValues inValues, String inWhere, String[] inWhereArgs) {
		SQLiteDatabase sdb = this.dbHelper.getWritableDatabase();
		int count;
		switch (URI_MATCHER.match(inUri)) {
			case GROUPS: {
				count = sdb.update(KaodroidDbManager.Groups.TABLE, inValues, inWhere, inWhereArgs);
				break;
			}
			case GROUPS_ID: {
				String where = "_id=" + inUri.getPathSegments().get(1);
				where += (!TextUtils.isEmpty(inWhere) ? (" AND (" + inWhere + ")") : "");
				count = sdb.update(KaodroidDbManager.Groups.TABLE, inValues, where, inWhereArgs);
				break;
			}
			case IMAGES: {
				count = sdb.update(KaodroidDbManager.Images.TABLE, inValues, inWhere, inWhereArgs);
				break;
			}
			case IMAGES_ID: {
				String where = "_id=" + inUri.getPathSegments().get(1);
				where += (!TextUtils.isEmpty(inWhere) ? (" AND (" + inWhere + ")") : "");
				count = sdb.update(KaodroidDbManager.Images.TABLE, inValues, where, inWhereArgs);
				break;
			}
			default: {
				throw new IllegalArgumentException("Unknown URL " + inUri);
			}
		}

		this.getContext().getContentResolver().notifyChange(inUri, null);

		return count;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri inUri, String inWhere, String[] inWhereArgs) {
		SQLiteDatabase sdb = this.dbHelper.getWritableDatabase();
		int count;
		switch (URI_MATCHER.match(inUri)) {
			case GROUPS: {
				count = sdb.delete(KaodroidDbManager.Groups.TABLE, inWhere, inWhereArgs);
				break;
			}
			case GROUPS_ID: {
				String where = "_id=" + inUri.getPathSegments().get(1);
				where += (!TextUtils.isEmpty(inWhere) ? (" AND (" + inWhere + ")") : "");
				count = sdb.delete(KaodroidDbManager.Groups.TABLE, where, inWhereArgs);
				break;
			}
			case IMAGES: {
				count = sdb.delete(KaodroidDbManager.Images.TABLE, inWhere, inWhereArgs);
				break;
			}
			case IMAGES_ID: {
				String where = "_id=" + inUri.getPathSegments().get(1);
				where += (!TextUtils.isEmpty(inWhere) ? (" AND (" + inWhere + ")") : "");
				count = sdb.delete(KaodroidDbManager.Images.TABLE, where, inWhereArgs);
				break;
			}
			default: {
				throw new IllegalArgumentException("Unknown URL " + inUri);
			}
		}

		this.getContext().getContentResolver().notifyChange(inUri, null);

		return count;
	}
}
