package phone.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

	private Button callButton;
	public EditText editText;
	public TextView dialNumber;

	private String number;
	private String name;
	private int yutori;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);

		setContentView(R.layout.dial);

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

/* Back�{�^���p�~�ɔ����A�R�����g��
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
*/

	    //�_�C�A���ԍ�
	    editText = (EditText)findViewById(R.id.CallNumber);
	    editText.setText(number);

	    //���ݓ��͒��̔ԍ�
	    dialNumber = (TextView)findViewById(R.id.dial_number);
	    dialNumber.setText("-");


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
