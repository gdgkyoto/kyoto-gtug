package hackathon.multimedia;

import hackathon.multimedia.CameraView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class PanoramaCamera extends Activity {
    //アプリの初期化
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new CameraView(this));
    }
}