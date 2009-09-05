package phone.app;

import android.app.Activity;
import android.content.Intent;
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
	
	/** ���M���s�t���O 0=���M�����s���Ȃ�  1=DIAL_PERSON_NUMBER�̒l�Ŕ��M���s�� */
	public static final String PARAM_DIAL_CALL_FLG = "DIAL_CALL_FLG";
	
	/** ���C����ʂɖ߂��Ă����Ƃ��ɁA���̂܂܃_�C������ʂɑJ�ڂ��邩�ǂ����̃t���O
	 *  �d�b����ʂ���߂��Ă����ۂɁA�_�C������ʂɑJ�ڂ���Ƃ��Ɏg�p����
	 *  int 0=�������Ȃ� 1=�_�C������ʂɑJ�ڂ��� */
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
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	//super.onActivityResult(requestCode, resultCode, data);
    	
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
    	
    	Log.d("phone","Result Action="+data.getAction());
    	number = data.getStringExtra(PARAM_DIAL_PERSON_NUMBER);
    	Log.d("phone","NUMBER="+number);
    	StringBuilder sb = new StringBuilder();
    	sb.append("NUMBER="+number);
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
    	}else{
    		debugTextView.setText("Debug : ");
    	}
    }
}