package org.gtug.kyoto.android.devilear;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ItemsDbAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_TYPE = "type";
    public static final String KEY_ICON_ID = "icon_id";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DATE = "date";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";
    
    private static final String[] COLUMNS = {KEY_ROWID, KEY_TYPE,
        KEY_ICON_ID, KEY_DESCRIPTION, KEY_DATE, KEY_LATITUDE,
        KEY_LONGITUDE};

    private static final String TAG = "ItemsDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE = "create table items (_id integer primary key autoincrement, "
            + "type TEXT NOT NULL, "
            + "icon_id INTEGER, "
            + "description TEXT, "
            + "date INTEGER, "
            + "latitude REAL NOT NULL, " + "longitude REAL NOT NULL);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "items";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS items");
            onCreate(db);
        }
    }

    /**
     * 
     * @param context
     */
    public ItemsDbAdapter(Context context) {
        mCtx = context;
    }

    public ItemsDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createItem(String type, Long iconId, String descriptioin,
            Long date, double latitude, double longitude) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TYPE, type);
        initialValues.put(KEY_ICON_ID, iconId);
        initialValues.put(KEY_DESCRIPTION, descriptioin);
        initialValues.put(KEY_LATITUDE, latitude);
        initialValues.put(KEY_LONGITUDE, longitude);
        initialValues.put(KEY_DATE, date);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the note with the given rowId
     * 
     * @param rowId
     *            id of note to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteItem(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all notes in the database
     * 
     * @return Cursor over all notes
     */
    public Cursor fetchAllItems() {
        return mDb.query(DATABASE_TABLE, COLUMNS, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     * 
     * @param rowId
     *            id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException
     *             if note could not be found/retrieved
     */
    public Cursor fetchItem(long rowId) throws SQLException {
        Cursor mCursor = mDb.query(true, DATABASE_TABLE, COLUMNS, KEY_ROWID + "=" + rowId,
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

}
