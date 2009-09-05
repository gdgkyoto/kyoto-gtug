/**
 * org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.util
 * TempUtil.java
 *
 * @author KATOU
 */
package org.kyotogtug.hackathon090905.gamet.kaodroid.game.cora.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.MediaColumns;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Images.Media;

/**
 * 
 * 
 * @author KATOU
 */
public class TempUtil {

	/** インスタンス */
	static private TempUtil instance = new TempUtil();

	/**
	 * コンストラクタ
	 */
	private TempUtil() {
	}

	/**
	 * インスタンスを取得する
	 * 
	 * @return インスタンス
	 */
	static public TempUtil getInstance() {
		return instance;
	}

	/**
	 * 画像データをSDCardに保存する
	 * 
	 * @param inData
	 * @param inDataName
	 * @return 画像ファイルパス
	 * @throws IOException
	 */
	public String saveDataToSdCard(byte[] inData, String inDataName) throws IOException {
		String path = "/sdcard/" + inDataName;
		Bitmap bitmap = BitmapFactory.decodeByteArray(inData, 0, inData.length);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return path;
	}

	/**
	 * 画像データをContentResolverを使用して保存する
	 * 
	 * @param inContext
	 * @param inData
	 * @param inDataName
	 * @return 画像ファイルURI
	 * @throws IOException
	 */
	public Uri saveDataToURI(Context inContext, byte[] inData, String inDataName) throws IOException {
		Bitmap bitmap = BitmapFactory.decodeByteArray(inData, 0, inData.length);
		ContentValues values = new ContentValues();
		values.put(MediaColumns.DISPLAY_NAME, inDataName);
		values.put(ImageColumns.DESCRIPTION, "taken with G1");
		values.put(MediaColumns.MIME_TYPE, "image/jpeg");
		Uri uri = inContext.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
		OutputStream os = null;
		try {
			inContext.getContentResolver().openOutputStream(uri);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
		} finally {
			if (os != null) {
				os.close();
			}
		}
		return uri;
	}
}
