package com.google.ktygtug.droid2.tecchan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

public class Rakuten extends Activity {
    public final static String LOG_TAG = "tecchan";

    ImageButton btn01 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(Activity.RESULT_CANCELED);

        setContentView(R.layout.rakuten);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }



}
