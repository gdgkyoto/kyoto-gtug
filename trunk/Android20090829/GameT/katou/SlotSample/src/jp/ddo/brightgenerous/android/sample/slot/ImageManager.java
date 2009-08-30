/**
 * jp.ddo.brightgenerous.android.sample.slot
 * ImageManager.java
 *
 * @version 1.0
 * @author KATOU
 */
package jp.ddo.brightgenerous.android.sample.slot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * 
 * 
 * @version 1.0
 * @author KATOU
 */
public class ImageManager {

	static private final String COMMON_PREF = "COMMON_PREF";

	/** インスタンス */
	static private ImageManager instance = null;

	/** 画像ID-Image */
	private HashMap<Integer, ImageData> images = new HashMap<Integer, ImageData>();

	/** ランダム */
	protected Random random = new Random();

	/**
	 * コンストラクタ。
	 */
	private ImageManager() {
	}

	/**
	 * インスタンスを取得する。
	 * 
	 * @return インスタンス
	 */
	static public ImageManager getInstance() {
		if (instance == null) {
			synchronized (ImageManager.class) {
				if (instance == null) {
					instance = new ImageManager();
					instance.init();
				}
			}
		}
		return instance;
	}

	/**
	 * 初期化する。
	 */
	private void init() {
		int[] imageIds = new int[] { R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9 };
		int[] messageids = new int[] { R.string.n1, R.string.n2, R.string.n3, R.string.n4, R.string.n5, R.string.n6, R.string.n7, R.string.n8, R.string.n9 };
		CharSequence[] convImageNames = new CharSequence[] { "n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9" };
		CharSequence[] convMessageNames = new CharSequence[] { "n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9" };

		for (int i = 0; i < imageIds.length; ++i) {
			ImageData image = new ImageData();
			image.setImageId(Integer.valueOf(imageIds[i]));
			image.setMessageId(Integer.valueOf(messageids[i]));
			image.setConvImageName(convImageNames[i]);
			image.setConvMessageName(convMessageNames[i]);
			instance.images.put(Integer.valueOf(imageIds[i]), image);
		}
	}

	/**
	 * リセットする。
	 * 
	 * @param inContext コンテキスト
	 * @param inWidth 横幅
	 * @param inHeight 高さ
	 */
	public void reset(Context inContext, int inWidth, int inHeight) {
		int[] ids = this.getIds();
		for (int id : ids) {
			Integer iId = Integer.valueOf(id);
			ImageData image = this.images.get(iId);
			image.setBitmap(null);
			image.setMessage(null);
			if (image.getBitmap() == null) {
				CharSequence convImageName = image.getConvImageName();
				if (convImageName != null) {
					try {
						image.setBitmap(this.getConvImage(inContext, convImageName, inWidth, inHeight));
					} catch (FileNotFoundException e) {
					}
				}
			}
			if (image.getBitmap() == null) {
				Integer imageId = image.getImageId();
				if (imageId != null) {
					image.setBitmap(this.getResourceImage(inContext, imageId.intValue(), inWidth, inHeight));
				}
			}
			if (image.getMessage() == null) {
				CharSequence convMessageName = image.getConvMessageName();
				if (convMessageName != null) {
					image.setMessage(this.getConvMessage(inContext, convMessageName));
				}
			}
			if (image.getMessage() == null) {
				Integer messageId = image.getMessageId();
				if (messageId != null) {
					image.setMessage(inContext.getText(messageId.intValue()));
				}
			}
		}
	}

	/**
	 * 画像のID配列を取得する。
	 * 
	 * @return 画像のID配列
	 */
	public int[] getIds() {
		int[] result = new int[this.images.size()];
		int index = 0;
		for (Integer id : this.images.keySet()) {
			result[index] = id.intValue();
			++index;
		}
		return result;
	}

	/**
	 * 画像のID配列を取得する。
	 * 
	 * @return 画像のID配列
	 */
	private ArrayList<Integer> getIdsList() {
		return new ArrayList<Integer>(this.images.keySet());
	}

	/**
	 * ランダムな画像のID配列を取得する。
	 * 
	 * @return ランダムな画像のID配列
	 */
	public int[] getRandomIds() {
		ArrayList<Integer> ids = this.getIdsList();
		int size = ids.size();
		int[] result = new int[size];
		for (int i = 0; i < size; ++i) {
			int ran = this.random.nextInt(ids.size());
			result[i] = ids.get(ran).intValue();
			ids.remove(ran);
		}

		return result;
	}

	/**
	 * 画像を取得する。
	 * 
	 * @param inId 画像ID
	 * @return 画像
	 */
	public Bitmap getImage(int inId) {
		return this.images.get(Integer.valueOf(inId)).getBitmap();
	}

	/**
	 * 画像を設定する。
	 * 
	 * @param inContext
	 * @param inId
	 * @param inData
	 */
	public void setImage(Context inContext, int inId, byte[] inData) {
		ImageData imageData = this.images.get(Integer.valueOf(inId));
		if ((inData != null) && (0 < inData.length)) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(inData, 0, inData.length);
			bitmap = this.resizeBitmap(bitmap, 100, 100);
			FileOutputStream out = null;
			try {
				out = inContext.openFileOutput(imageData.getConvImageName().toString(), Context.MODE_PRIVATE);
				bitmap.compress(Bitmap.CompressFormat.PNG, 80, out);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			imageData.setBitmap(bitmap);
		} else {
			inContext.deleteFile(imageData.getConvImageName().toString());
			imageData.setBitmap(this.getResourceImage(inContext, imageData.getImageId().intValue(), 100, 100));
		}
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @param inId 画像ID
	 * @return　メッセージ
	 */
	public CharSequence getMessage(int inId) {
		return this.images.get(Integer.valueOf(inId)).getMessage();
	}

	/**
	 * メッセージを設定する。
	 * 
	 * @param inContext
	 * @param inId
	 * @param inMessage
	 */
	public void setMessage(Context inContext, int inId, CharSequence inMessage) {
		ImageData imageData = this.images.get(Integer.valueOf(inId));
		if ((inMessage != null) && (0 < inMessage.length())) {
			SharedPreferences sp = inContext.getSharedPreferences(COMMON_PREF, Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
			Editor editor = sp.edit();
			editor.putString(imageData.getConvMessageName().toString(), inMessage.toString());
			editor.commit();
			imageData.setMessage(inMessage);
		} else {
			SharedPreferences sp = inContext.getSharedPreferences(COMMON_PREF, Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
			Editor editor = sp.edit();
			editor.remove(imageData.getConvMessageName().toString());
			editor.commit();
			imageData.setMessage(inContext.getText(imageData.getMessageId().intValue()));
		}
	}

	/**
	 * 画像IDから画像を取得する。
	 * 
	 * @param inContext コンテキスト
	 * @param inId 画像ID
	 * @param inWidth 横幅
	 * @param inHeight 高さ
	 * @return 画像
	 */
	private Bitmap getResourceImage(Context inContext, int inId, int inWidth, int inHeight) {
		Bitmap bitmap = BitmapFactory.decodeResource(inContext.getResources(), inId);
		return this.resizeBitmap(bitmap, inWidth, inHeight);
	}

	/**
	 * 置き換え画像名から画像を取得する。
	 * 
	 * @param inContext コンテキスト
	 * @param inName 置き換え画像名
	 * @param inWidth 横幅
	 * @param inHeight 高さ
	 * @return　画像
	 * @throws FileNotFoundException
	 */
	private Bitmap getConvImage(Context inContext, CharSequence inName, int inWidth, int inHeight) throws FileNotFoundException {
		InputStream in = inContext.openFileInput(inName.toString());
		Bitmap bitmap = BitmapFactory.decodeStream(in);
		return this.resizeBitmap(bitmap, inWidth, inHeight);
	}

	/**
	 * 画像をリサイズする。
	 * 
	 * @param inBitmap 画像
	 * @param inWidth 横幅
	 * @param inHeight 高さ
	 * @return 画像
	 */
	private Bitmap resizeBitmap(Bitmap inBitmap, int inWidth, int inHeight) {
		int width = inBitmap.getWidth();
		int height = inBitmap.getHeight();
		float scaleWidth = ((float) inWidth) / width;
		float scaleHeight = ((float) inHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap.createBitmap(inBitmap, 0, 0, width, height, matrix, true);
	}

	/**
	 * 置き換えメッセージ名からメッセージを取得する。
	 * 
	 * @param inContext コンテキスト
	 * @param inName 置き換えメッセージ名
	 * @return　メッセージ
	 */
	private CharSequence getConvMessage(Context inContext, CharSequence inName) {
		SharedPreferences sp = inContext.getSharedPreferences(COMMON_PREF, Context.MODE_WORLD_READABLE);
		return sp.getString(inName.toString(), null);
	}

	/**
	 * 画像データクラス。
	 * 
	 * @version 1.0
	 * @author KATOU
	 */
	static private class ImageData {

		/** 画像ID */
		private Integer imageId;

		/** メッセージID */
		private Integer messageId;

		/** 置き換え画像名 */
		private CharSequence convImageName;

		/** 置き換えメッセージ名 */
		private CharSequence convMessageName;

		/** 画像 */
		private Bitmap bitmap;

		/** メッセージ */
		private CharSequence message;

		/**
		 * コンストラクタ。
		 */
		public ImageData() {
		}

		/**
		 * 画像IDを取得する。
		 * 
		 * @return　画像ID
		 */
		public Integer getImageId() {
			return this.imageId;
		}

		/**
		 * 画像IDを設定する。
		 * 
		 * @param inImageId 画像ID
		 */
		public void setImageId(Integer inImageId) {
			this.imageId = inImageId;
		}

		/**
		 * メッセージIDを取得する。
		 * 
		 * @return メッセージID
		 */
		public Integer getMessageId() {
			return this.messageId;
		}

		/**
		 * メッセージIDを設定する。
		 * 
		 * @param inMessageId メッセージID
		 */
		public void setMessageId(Integer inMessageId) {
			this.messageId = inMessageId;
		}

		/**
		 * 置き換え画像名を取得する。
		 * 
		 * @return 置き換え画像名
		 */
		public CharSequence getConvImageName() {
			return this.convImageName;
		}

		/**
		 * 置き換え画像名を設定する。
		 * 
		 * @param inConvImageName 置き換え画像名
		 */
		public void setConvImageName(CharSequence inConvImageName) {
			this.convImageName = inConvImageName;
		}

		/**
		 * 置き換えメッセージ名を取得する。
		 * 
		 * @return 置き換えメッセージ名
		 */
		public CharSequence getConvMessageName() {
			return this.convMessageName;
		}

		/**
		 * 置き換えメッセージ名を設定する。
		 * 
		 * @param inConvMessageName 置き換えメッセージ名
		 */
		public void setConvMessageName(CharSequence inConvMessageName) {
			this.convMessageName = inConvMessageName;
		}

		/**
		 * 画像を取得する。
		 * 
		 * @return 画像
		 */
		public Bitmap getBitmap() {
			return this.bitmap;
		}

		/**
		 * 画像を設定する。
		 * 
		 * @param inBitmap 画像
		 */
		public void setBitmap(Bitmap inBitmap) {
			this.bitmap = inBitmap;
		}

		/**
		 * メッセージを取得する。
		 * 
		 * @return メッセージ
		 */
		public CharSequence getMessage() {
			return this.message;
		}

		/**
		 * メッセージを設定する。
		 * 
		 * @param inMessage メッセージ
		 */
		public void setMessage(CharSequence inMessage) {
			this.message = inMessage;
		}
	}
}
