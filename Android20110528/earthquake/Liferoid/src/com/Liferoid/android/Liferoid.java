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
		// �����ꂽ�Ƃ��̏���
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			// �I�����ꂽ�Ƃ��̏���
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner1 = (Spinner) parent;
				// �e�L�X�g�r���[�̃e�L�X�g��ݒ肵�܂�
				Log.d("Spinner01", spinner1.getSelectedItem().toString());

				SL1 = spinner1.getSelectedItem().toString();

			}

			// �I������Ȃ��Ȃ����Ƃ��̏���
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Spinner spinner2 = (Spinner) findViewById(R.id.Spinner02);
		// �����ꂽ�Ƃ��̏���
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			// �I�����ꂽ�Ƃ��̏���
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner2 = (Spinner) parent;
				// �e�L�X�g�r���[�̃e�L�X�g��ݒ肵�܂�
				Log.d("Spinner02", spinner2.getSelectedItem().toString());

				if (SL1.equals("�蓮")) {

					SL2 = spinner2.getSelectedItem().toString();

				} 

			}

			// �I������Ȃ��Ȃ����Ƃ��̏���
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		Button button1 = (Button) findViewById(id.button1);
		// �{�^�����N���b�N���ꂽ���ɌĂяo�����R�[���o�b�N���X�i�[��o�^���܂�
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// �{�^�����N���b�N���ꂽ���ɌĂяo����܂�

				Intent i = new Intent(getApplicationContext(), Output.class);
				startActivity(i);
			}
		});

	}
}