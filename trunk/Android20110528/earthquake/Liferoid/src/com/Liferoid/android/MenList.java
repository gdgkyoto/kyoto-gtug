package com.Liferoid.android;

import java.util.ArrayList;
import com.Liferoid.android.R.id;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MenList extends PreferenceActivity {

	static final String TAG = "MenList";

	private static final String PREFS_NAME = "com.Liferoid.android";
	private static final String PREF_PREFIX_KEY = "prefix_";

	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

	public MenList() {
		super();
	}

	// リストPreferenceの　PreferenceChangeリスナー
	private OnPreferenceChangeListener listPreference_OnPreferenceChangeListener = new OnPreferenceChangeListener() {

		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			return listPreference_OnPreferenceChange(preference, newValue);

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {

		// Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_button);
		addPreferencesFromResource(R.xml.widget_setting);

		// キーを基に、リスト設定のインスタンスを取得する
		CharSequence cs1 = getText(R.string.pref1);
		CharSequence cs2 = getText(R.string.pref2);
		ListPreference pref1 = (ListPreference) findPreference(cs1);
		ListPreference pref2 = (ListPreference) findPreference(cs2);
		// リスナーを設定する
		pref1.setOnPreferenceChangeListener(listPreference_OnPreferenceChangeListener);
		pref2.setOnPreferenceChangeListener(listPreference_OnPreferenceChangeListener);

		// 前回の設定値をSummaryに表示
		ListPreference list_preference1 = (ListPreference) getPreferenceScreen()
				.findPreference("pref1");
		list_preference1.setSummary(list_preference1.getEntry());
		ListPreference list_preference2 = (ListPreference) getPreferenceScreen()
				.findPreference("pref2");
		list_preference2.setSummary(list_preference2.getEntry());

		// pref3 = this.findPreference("pref3");
		// pref3.setOnPreferenceClickListener(onPreferenceClickListener1);

		Preference pref3 = (Preference) getPreferenceScreen().findPreference(
				"pref3");
		pref3.setOnPreferenceClickListener(onPreferenceClickListener1);

		// pref4 = this.findPreference("pref4");
		// pref4.setOnPreferenceClickListener(onPreferenceClickListener2);

		// Set the result to CANCELED. This will cause the widget host to cancel
		// out of the widget placement if they press the back button.
		setResult(RESULT_CANCELED);
		
		
		// color pickerリセット
		//SharedPreferences pref1 = getSharedPreferences("pref_location", MODE_PRIVATE);
		//Editor e = pref1.edit();
		//e.putString("Location", spinner2.getSelectedItem().toString());
		//e.commit();
		

		// Find the widget id from the intent.
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);

		}

		// If they gave us an intent without the widget id, just bail.
		if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			finish();
		}

		Button button1 = (Button) findViewById(id.button1);
		// ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ボタンがクリックされた時に呼び出されます

				final Context context = MenList.this;

				// Widget更新
				AppWidgetManager appWidgetManager = AppWidgetManager
						.getInstance(context);
				RemoteViews views = new RemoteViews(context.getPackageName(),
						R.layout.widget_layout);
				appWidgetManager.updateAppWidget(mAppWidgetId, views);

				// Make sure we pass back the original appWidgetId
				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
						mAppWidgetId);
				setResult(RESULT_OK, resultValue);
				finish();
			}
		});

		Button button2 = (Button) findViewById(id.button2);
		// ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ボタンがクリックされた時に呼び出されます

				finish();
			}
		});

	}

	public boolean listPreference_OnPreferenceChange(Preference preference,
			Object newValue) {

		// 設定値をsummaryに反映
		ListPreference listpref = (ListPreference) preference;
		String summary = String.format("entry=%s , value=%s",
				listpref.getEntry(), listpref.getValue());
		preference.setSummary(summary);

		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// 保存時の処理
		super.onSaveInstanceState(outState);
	}

	private Preference.OnPreferenceClickListener onPreferenceClickListener1 = new Preference.OnPreferenceClickListener() {
		@Override
		public boolean onPreferenceClick(Preference preference) {

			Context context = getApplicationContext();
			CharSequence text = "Hello toast!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

			return true;
		}
	};

	// private Preference.OnPreferenceClickListener onPreferenceClickListener2 =
	// new Preference.OnPreferenceClickListener() {
	// @Override
	// public boolean onPreferenceClick(Preference preference) {

	// Context context = getApplicationContext();
	// CharSequence text = "Hello toast!";
	// int duration = Toast.LENGTH_SHORT;

	// Toast toast = Toast.makeText(context, text, duration);
	// toast.show();

	// finish();
	// return true;
	// }
	// };

	// TODO preferenceactivityのボタンの実装

	// Write the prefix to the SharedPreferences object for this widget
	static void saveTitlePref(Context context, int appWidgetId, String text) {
		SharedPreferences.Editor prefs = context.getSharedPreferences(
				PREFS_NAME, 0).edit();
		prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
		prefs.commit();
	}

	// Read the prefix from the SharedPreferences object for this widget.
	// If there is no preference saved, get the default from a resource
	static String loadTitlePref(Context context, int appWidgetId) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
		String prefix = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
		if (prefix != null) {
			return prefix;
		} else {
			return context.getString(R.string.pref4);
		}
	}

	static void deleteTitlePref(Context context, int appWidgetId) {
	}

	static void loadAllTitlePrefs(Context context,
			ArrayList<Integer> appWidgetIds, ArrayList<String> texts) {
	}
}
