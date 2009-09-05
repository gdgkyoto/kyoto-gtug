package phone.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialActivity extends Activity {
	
	private Button backButton;
	private Button callButton;
	private EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.dial);
	    
	    callButton = (Button)findViewById(R.id.dial_call_button);
	    callButton.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v) {
	    		Intent intent = new Intent();
	    		intent.setAction("DIAL!!");
	    		intent.putExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER, editText.getText().toString());
	    		intent.putExtra(RotaryDial.PARAM_DIAL_CALL_FLG, 1);
	    		setResult(RESULT_OK, intent);
	            finish(); 
	    	}
	    });
	    
	    backButton = (Button) findViewById(R.id.dial_back_button);
	    backButton.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v) {
	    		Intent intent = new Intent();
	    		intent.putExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER, editText.getText().toString());
	    		intent.putExtra(RotaryDial.PARAM_DIAL_CALL_FLG, 0);
	    		setResult(RESULT_OK, intent);
	            finish(); 
	    	}
	    });
	    editText = (EditText)findViewById(R.id.dial_number);
	    editText.setText("000-123-1234");
	    
	    
	    String number = getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER);
	    String name = getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER);
	    
	    if( number != null ){
	    	TextView textView = (TextView) findViewById(R.id.dial_mmeo);
	    	textView.setText("memo: name="+name+" number="+number);
	    	
	    }
	    
	}
}
