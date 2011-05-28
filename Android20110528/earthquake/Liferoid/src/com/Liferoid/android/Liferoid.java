package com.Liferoid.android;

import com.Liferoid.android.R.id;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class Liferoid extends Activity {
	/** Called when the activity is first created. */

	Spinner spinner1;
	String Select_location;
	String Set_location;

	private static String SL1, SL2;

	void setX(String x) {
		SL1 = x;
	}

	String getX() {
		return SL1;
	}

	void setY(String y) {
		SL2 = y;
	}

	String getY() {
		return SL2;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Spinner spinner1 = (Spinner) findViewById(R.id.Spinner01);
		// 押されたときの処理
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			// 選択されたときの処理
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner1 = (Spinner) parent;
				// テキストビューのテキストを設定します
				Log.d("Spinner01", spinner1.getSelectedItem().toString());

				SL1 = spinner1.getSelectedItem().toString();

			}

			// 選択されなくなったときの処理
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner spinner2 = (Spinner) findViewById(R.id.Spinner02);
		// 押されたときの処理
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			// 選択されたときの処理
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner2 = (Spinner) parent;
				// テキストビューのテキストを設定します
				Log.d("Spinner02", spinner2.getSelectedItem().toString());

				if (SL1.equals("手動")) {

					SL2 = spinner2.getSelectedItem().toString();

				} 

			}

			// 選択されなくなったときの処理
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Button button1 = (Button) findViewById(id.button1);
		// ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ボタンがクリックされた時に呼び出されます

				Intent i = new Intent(getApplicationContext(), Output.class);
				startActivity(i);
			}
		});

	}
}