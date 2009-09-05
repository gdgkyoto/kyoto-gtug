package phone.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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

	private Button backButton;
	private Button callButton;
	private EditText editText;

	private String number;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.dial);

	    if( getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NAME) != null ){
	    	name = getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NAME);
	    }

	    if( getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER) != null ){
	    	number = getIntent().getStringExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER);
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
	    	TextView textView = (TextView) findViewById(R.id.dial_mmeo);
	    	textView.setText("memo: name="+name+" number="+number);
	    }

	    //��]�p�C���[�W�ǂݍ���
        Bitmap analogDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_dial);

	}
	//bitmap�ۑ�
	private Bitmap analogDial;
}
