package gtug.kyoto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class GeoSample extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View mapButton = this.findViewById(R.id.map_button);
        mapButton.setOnClickListener(this);
        View testButton = this.findViewById(R.id.test_button);
        testButton.setOnClickListener(this);
        View exitButton = this.findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.map_button:
			startActivity(new Intent(this, Map.class));
			break;
		case R.id.test_button:
			startActivity(new Intent(this, Test.class));
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}
}