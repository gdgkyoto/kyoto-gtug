package org.hackathon.map2;

import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VoiceRecognitionActivity extends Activity {

	private static final int REQUEST_CODE = 0;

	private Double lat;

	private Double lon;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.voice);

		Intent intent = getIntent();
		lat = intent.getDoubleExtra("lat",0.0);
		lon = intent.getDoubleExtra("lon",0.0);

		Toast.makeText(VoiceRecognitionActivity.this, lat + ":" + lon,
				Toast.LENGTH_LONG);

		Button button = (Button) findViewById(R.id.Button01);

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				try {

					Intent intent = new Intent(
							RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
							RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

					intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
							"VoiceRecognition");
					startActivityForResult(intent, REQUEST_CODE);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(VoiceRecognitionActivity.this,
							"ActivityNotFoundException", Toast.LENGTH_LONG)
							.show();
				}

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			String resultsString = "";

			List<String> results = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

			for (int i = 0; i < results.size(); i++) {
				resultsString += results.get(i);
			}

			Intent i = new Intent(getApplicationContext(),
					ViewMapActivity.class);
			i.putExtra("voice", resultsString);
			i.putExtra("lat", lat);
			i.putExtra("lon", lon);

			startActivity(i);

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
