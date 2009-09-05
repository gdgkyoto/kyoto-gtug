package phone.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Rotaly_dial
 * @author kitamura
 *
 */
public class RotaryDial extends Activity {
	
	private Button toDialButton ;
	private Button toContactListButton;
	private TextView textView;
	private TextView debugTextView;
	
	public static final String PARAM_DIAL_PERSON_NUMBER = "DIAL_PERSON_NUMBER";
	public static final String PARAM_DIAL_PERSON_NAME = "DIAL_PERSON_NAME";
	
	/** 発信実行フラグ 0=発信を実行しない  1=DIAL_PERSON_NUMBERの値で発信を行う */
	public static final String PARAM_DIAL_CALL_FLG = "DIAL_CALL_FLG";
	
	/** メイン画面に戻ってきたときに、そのままダイヤル画面に遷移するかどうかのフラグ
	 *  電話帳画面から戻ってきた際に、ダイヤル画面に遷移するときに使用する
	 *  int 0=何もしない 1=ダイヤル画面に遷移する */
	public static final String PARAM_MOVE_TO_DIAL_MODE = "MOVE_TO_DIAL_MODE";
	
	/** int 0=normal 1=yutori */
	public static final String PARAM_YUTORI_MODE_FLG = "YUTORI_MODE_FLG";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        toDialButton = (Button)findViewById(R.id.Button02);
        toContactListButton = (Button)findViewById(R.id.Button01);
        textView = (TextView)findViewById(R.id.label);
        debugTextView = (TextView)findViewById(R.id.main_debug);
        toDialButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RotaryDial.this,DialActivity.class);
				startActivityForResult(intent, 0);
			}
        	
        });
        toContactListButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RotaryDial.this,ContactListActivity.class);
				startActivityForResult(intent, 0);
			}
        });
    }

    // 実際に電話をコールする
    private void onAcutalCall(String phoneNumber) {
    	
    	Intent callIntent = new 
    	Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));

    	callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(callIntent);
    	
    }   
    
    /**
     * SubActityからの結果受け取り
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d("phone", "onActivityResult");
    	if( resultCode == RESULT_OK ){
        	Log.d("phone", "RESULT_OK!");
    	}else{
        	Log.d("phone", "RESULT_NG!");
    	}
    	
    	if( data == null ){
    		return ;
    	}
		
    	String name;
    	String number;
    	
    	Log.d("phone","onActivityResult.Result Action="+data.getAction());
    	number = data.getStringExtra(PARAM_DIAL_PERSON_NUMBER);
    	Log.d("phone","onActivityResult.NUMBER="+number);
    	StringBuilder sb = new StringBuilder();
    	sb.append("onActivityResult.NUMBER="+number);
    	name = data.getStringExtra(PARAM_DIAL_PERSON_NAME);
    	if( name != null ){
        	Log.d("phone","NAME="+name);
        	sb.append(" NAME="+name );
    	}
    	textView.setText(sb.toString());
    	
    	
    	
    	if( data.getIntExtra(PARAM_MOVE_TO_DIAL_MODE, 0) == 1 ){
    		Intent intent = new Intent(RotaryDial.this,DialActivity.class);
    		intent.putExtra(PARAM_DIAL_PERSON_NAME, name);
    		intent.putExtra(PARAM_DIAL_PERSON_NUMBER, number);
			startActivityForResult(intent, 0);
    	}
    	
    	if( data.getIntExtra(PARAM_DIAL_CALL_FLG, 0) == 1 ){
    		debugTextView.setText("Debug : CALL!! number=" + number);
    		//
    		onAcutalCall(number);
    	}else{
    		debugTextView.setText("Debug : ");
    	}
    }
}
