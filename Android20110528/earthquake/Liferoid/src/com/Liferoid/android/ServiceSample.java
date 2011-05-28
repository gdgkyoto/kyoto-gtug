package com.Liferoid.android;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

public class ServiceSample extends Service {
	private final String BUTTON_CLICK_ACTION1 = "BUTTON_CLICK_ACTION1";
	private final String BUTTON_CLICK_ACTION2 = "BUTTON_CLICK_ACTION2";

	String[] stringArray = { "jp.r246.twicca.timelines.Home",
			"jp.r246.twicca.statuses.Send" };
	private static int IV1, IV2;
	
	void setX(int x) {
		IV1 = x;
	}

	int getX() {
		return IV1;
	}

	void setY(int y) {
		IV2 = y;
	}

	int getY() {
		return IV2;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		SharedPreferences sharedPreferences1 = PreferenceManager
				.getDefaultSharedPreferences(this);
		String list_preference1 = sharedPreferences1.getString("pref1",
				"unknown");
		IV1 = Integer.parseInt(list_preference1);

		SharedPreferences sharedPreferences2 = PreferenceManager
				.getDefaultSharedPreferences(this);
		String list_preference2 = sharedPreferences2.getString("pref2",
				"unknown");
		IV2 = Integer.parseInt(list_preference2);



		// ボタン1が押された時に発行されたインテントの場合はアプリ起動
		if (BUTTON_CLICK_ACTION1.equals(intent.getAction())) {

			// パッケージ名, クラス名をセット
			intent.setClassName("jp.r246.twicca", stringArray[IV1]);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// アプリを起動
			startActivity(intent);
		}
		// ボタン2が押された時に発行されたインテントの場合はアプリ起動
		if (BUTTON_CLICK_ACTION2.equals(intent.getAction())) {

			// パッケージ名, クラス名をセット
			intent.setClassName("jp.r246.twicca", stringArray[IV2]);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// アプリを起動
			startActivity(intent);
		}


	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
