package phone.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

/**
 * �_�C�������
 *
 * ����:
 *   getIntent().getStringExtra(PARAM)�Ŏ󂯎��B
 * RotaryDial.PARAM_DIAL_PERSON_NAME    String �����ɕ\�����閼�O
 * RotaryDial.PARAM_DIAL_PERSON_NUMBER  String �����ɕ\������d�b�ԍ�
 *
 * �߂�l:
 *   �ȉ��̏����Ŗ߂�l��߂��B
 *   Intent.putExtra(PARAM,value);
 *   setResult(RESULT_OK, intent);
 *   finish();
 * RotaryDial.PARAM_DIAL_PERSON_NUMBER  String �d�b��������ԍ�
 * RotaryDial.PARAM_DIAL_CALL_FLG       int    ���C����ʂŎ��ۂɔ��M���邩�ǂ����̃t���O 0=���M���Ȃ� 1=���M���Ȃ�
 *
 * @author KENJI
 *
 */
public class DialActivity extends Activity {

	private Button backButton;
	private Button callButton;
	public EditText editText;

	private String number;
	private String name;
	private int yutori;

	//bitmap�ۑ�
	private DialActivityView surfaceView;
	private ViewGroup frameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);

		setContentView(R.layout.dial);

		frameLayout = (ViewGroup) findViewById(R.id.dial_info);
	    surfaceView = new DialActivityView(this);

	    //addContentView(surfaceView, new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));


	    if( getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NAME) != null ){
	    	name = getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NAME);
	    }

	    if( getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER) != null ){
	    	number = getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER);
	    }

	    if( getIntent().getIntExtra(RotaryDial.PARAM_YUTORI_MODE_FLG,0) != 0 ){
	    	yutori = getIntent().getIntExtra(RotaryDial.PARAM_YUTORI_MODE_FLG,0);
	    }

	    if(yutori != 0 ){
    		Intent intent = new Intent();
    		intent.putExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER, number);
    		intent.putExtra(RotaryDial.PARAM_DIAL_CALL_FLG, 1);
    		setResult(RESULT_OK, intent);
            finish();

	    }

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
	    editText.setText(number);

	    String number = getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER);
	    String name = getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NAME);

	    if( number != null ){
	    	/*
	    	TextView textView = (TextView) findViewById(R.id.dial_mmeo);
	    	textView.setText("memo: name="+name+" number="+number);
	    	*/
	    	TextView textDialNumber = (TextView) findViewById(R.id.DialNumber);
	    	/* textDialNumber.setText("Number: "+number); */
	    	textDialNumber.setText(number); /* 2009.09.06 H.Murai �_�C�����ԍ��݂̂ɏC�� */
	    	TextView textDialMemo   = (TextView) findViewById(R.id.DialMemo);
	    	/* textDialMemo.setText("Memo: "+name); */
	    	textDialMemo.setText(name); /* 2009.09.06 H.Murai �����i�ďo��j�݂̂ɏC�� */
	    }

	}


	@Override
	protected void onPause() {
		super.onPause();

	};


}
