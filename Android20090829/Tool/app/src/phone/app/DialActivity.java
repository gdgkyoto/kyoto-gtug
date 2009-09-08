package phone.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
	private int yutori;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.dial);
        initNum();

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

	    //��]�p�C���[�W�ǂݍ���
        analogDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_dial);
        imageView = (ImageView)findViewById(R.id.DialImageView02);
		bmd = new BitmapDrawable(analogDial);
		imageView.setImageDrawable(bmd);
		onAttachedToWindow();
	}


	@Override
	protected void onPause() {
		super.onPause();

		onDetachedFromWindow();
	};

    //�_�C�A���������ʒu�ɕύX
    private double startDeg;
    private double nowDeg;
    private double oldDeg;
    private double endDeg;

    //�����J�n�d�b�ԍ�
    private int startPhoneNumber;

    //�_�C���������̈ʒu�ɖ߂��t���O
    private boolean IsResetPosition = false;

    //�����Ղ̐����ʒu
    private double[] phoneNumber = new double[10];
    private  static final double STD_DEG = 25.714285714285714285714285714286;


    /**
     * onTouchEvent
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

            	//�����|�C���g�̌v�Z
            	Log.v("phone","ActionDown");

            	IsResetPosition  = false;
            	rotateDeg		 = 0;
                startDeg		 = calcDegree(x, y);
                startPhoneNumber = getInputNumber(startDeg);
                break;

            case MotionEvent.ACTION_MOVE:

                IsResetPosition = false;
                nowDeg = calcDegree(x,y);
                rotateDeg = (startDeg - nowDeg);

            	//�_�C�A������]����
            	rotateDial( (float)rotateDeg );
                break;

            case MotionEvent.ACTION_UP:

            	//�������|�C���g�̌v�Z�A���͕�����̊m��
                Log.v("phone","ActionUp");

                IsResetPosition = true;
                endDeg  = calcDegree(x, y);
                rotateDeg = startDeg - endDeg;

                if(IsDial(endDeg) && startPhoneNumber >=0 ){
               		Editable str = editText.getText();
               		editText.setText(str.toString() + String.valueOf(startPhoneNumber));
                }
                break;
        }
        return true;
    }

    /**
     * ��]�p�̌v�Z
     * @param x
     * @param y
     * @return
     */
    private double calcDegree(float x, float y){

    	double deg;
    	deg = (float) (Math.atan2(y - 280, x - 160 ) * 180 / Math.PI);


    	if( 0 >= deg && deg > -180){
    		deg *= -1;
    	}
    	else if( 0 < deg && deg <= 180 ){
    		deg *= -1;
    		deg += 360;
    	}

    	Log.d("phone", "calcDegree: "+ String.valueOf((int)deg)
    			+ "(x,y): " + String.valueOf((int)x) + "," + String.valueOf((int)y));
    	return deg;
    }

    /**
     * ���͗p�����Ղ�������[1,2,�c,9,0]�ƁA14�������Ă���
     */
    private void initNum(){
    	for(int i = 1;i<phoneNumber.length;i++){
    		phoneNumber[i] = STD_DEG*(i) + STD_DEG/2;
    	}
    	phoneNumber[0] = STD_DEG*10;
    }

    /**
     * ���͊p�x�̃`�F�b�N���W�b�N
     * @param degree
     * @return
     */
    private int getInputNumber(double degree){
    	for(int i = 1;i<phoneNumber.length;i++){
    		if (phoneNumber[i] <= degree  && degree < (phoneNumber[i]+STD_DEG) ){
    			return i;
    		}
    	}
    	return -1;
    }

    /**
     * ���͊�������
     * @param degree
     * @return
     */
    private boolean IsDial(double degree){

    	double min = STD_DEG*11 + STD_DEG/2;
    	double max = STD_DEG*13 + STD_DEG/2;

    	if(min <= degree && degree < max){
    		return true;
    	}
    	return false;
    }


    /**
     * �����Ղ̕\���ʒu�����ɖ߂�
     */
    private double rotateDeg;
    private void resetDialPosition(){

    	if(IsResetPosition){

    		Log.i("phone","rotate:" + String.valueOf((int)rotateDeg) +
    				",start:" + String.valueOf((int)startDeg) +
    				",end:" + String.valueOf((int)endDeg) );

    		/* ���̈ʒu�ɖ߂����ꍇ */
    		if(rotateDeg < 0 ){
    			rotateDeg = 0;
    			IsResetPosition = false;
    		}else{
    			rotateDeg -= 10;
    		}

    		rotateDial( (float)rotateDeg );
     	}
    }



	//bitmap�ۑ�
	private Bitmap analogDial;
	private Bitmap rotateDial;
	private BitmapDrawable bmd;
	private ImageView imageView;

	/**
	 * �摜����]�����郍�W�b�N
	 * @param degrees
	 */
	private void rotateDial(float degrees){

		int width = analogDial.getWidth();
        int height = analogDial.getHeight();

		// createa matrix for the manipulation
		Matrix matrix = new Matrix();
		// rotate the Bitmap
		matrix.postScale(1.0f, 1.0f);
        matrix.postRotate(degrees);

        rotateDial = Bitmap.createBitmap(analogDial, 0, 0,
        		width, height, matrix, true);

		bmd = new BitmapDrawable(rotateDial);

        // set the Drawable on the ImageView
        imageView.setImageDrawable(bmd);

	}

    private static final int INVALIDATE = 1;
    /**
     * �^�C�}�[�n���h���[
     */
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (mAnimate && msg.what == INVALIDATE) {
            	resetDialPosition();

                msg = obtainMessage(INVALIDATE);
                long current = SystemClock.uptimeMillis();
                if (mNextTime < current) {
                    // 100ms�����Ń^�C�}�[�C�x���g������
                    mNextTime = current + 200;
                }
                sendMessageAtTime(msg, mNextTime);
                // 100ms�����Ń^�C�}�[�C�x���g������
                mNextTime += 200;
            }
        }
    };
    /**
     * Window��Attach���ꂽ���̏���
     */
    protected void onAttachedToWindow(){
        mAnimate = true;
        Message msg = mHandler.obtainMessage(INVALIDATE);
        mNextTime = SystemClock.uptimeMillis();
        mHandler.sendMessageAtTime(msg, mNextTime);
    }

    /**
     * Window����Detach���ꂽ���̏���
     */
    protected void onDetachedFromWindow() {
         mAnimate = false;
    }
    private boolean         mAnimate;
    private long            mNextTime;
}
