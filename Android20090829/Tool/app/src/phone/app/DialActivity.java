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
 * ダイヤル画面
 *
 * 引数:
 *   getIntent().getStringExtra(PARAM)で受け取る。
 * RotaryDial.PARAM_DIAL_PERSON_NAME    String メモに表示する名前
 * RotaryDial.PARAM_DIAL_PERSON_NUMBER  String メモに表示する電話番号
 *
 * 戻り値:
 *   以下の処理で戻り値を戻す。
 *   Intent.putExtra(PARAM,value);
 *   setResult(RESULT_OK, intent);
 *   finish();
 * RotaryDial.PARAM_DIAL_PERSON_NUMBER  String 電話をかける番号
 * RotaryDial.PARAM_DIAL_CALL_FLG       int    メイン画面で実際に発信するかどうかのフラグ 0=発信しない 1=発信しない
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
	    	textDialNumber.setText("Number: "+number);
	    	TextView textDialMemo   = (TextView) findViewById(R.id.DialMemo);
	    	textDialMemo.setText("Memo: "+name);
	    }

	    //回転用イメージ読み込み
        analogDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_dial);
        ImageView imageView = (ImageView)findViewById(R.id.DialImageView02);
		bmd = new BitmapDrawable(analogDial);
		imageView.setImageDrawable(bmd);
	}



	//画像を回転させるロジック
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

        ImageView imageView = (ImageView)findViewById(R.id.DialImageView02);

        // set the Drawable on the ImageView
        imageView.setImageDrawable(bmd);

	}


	//bitmap保存
	private Bitmap analogDial;
	private Bitmap rotateDial;
	private BitmapDrawable bmd;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.v("phone","ActionDown");
                IsResetPosition = false;
                onDetachedFromWindow();
                //startPoint(x,y);

                int num = checkNum(x,y);
                if( num >= 0){
                	Editable str = editText.getText();
                	editText.setText(str.toString() + String.valueOf(num));
                }

                break;
            case MotionEvent.ACTION_MOVE:
                Log.v("phone","ActionMove");
                Log.d("phone",String.valueOf(x) + "," +String.valueOf(y));
                IsResetPosition = false;
                onDetachedFromWindow();
                movePoint(x,y);
                break;
            case MotionEvent.ACTION_UP:
                Log.v("phone","ActionUp");
                IsResetPosition = true;
                onAttachedToWindow();
                break;
        }
        return true;
    }

    private void startPoint(float x, float y){
    	Log.d("phone", String.valueOf(x) + "," + String.valueOf(y));
    	start_degree = (float) (Math.atan2(y - 280, x - 160 ) * 180 / Math.PI);
    	Log.d("phone","start_degree:" + String.valueOf(degree));
    	return;
    }



    private void movePoint(float x, float y){
    	Log.d("phone", String.valueOf(x) + "," + String.valueOf(y));
    	degree = (float) (Math.atan2(y - 280, x - 160 ) * 180 / Math.PI);
    	Log.d("phone","degree:" + String.valueOf(degree));

    	//degree = start_degree - degree;
    	rotateDial(degree);
    	return;
    }

    //Numbers
    private Rect[] teleNum = new Rect[10];
    private void initNum(){

    	teleNum[0] = new Rect(150, 390, 170, 410);
    	teleNum[9] = new Rect(100, 380, 120, 400);
    	teleNum[8] = new Rect( 65, 340,  85, 360);
    	teleNum[7] = new Rect( 30, 310,  40, 330);
    	teleNum[6] = new Rect( 40, 300,  60, 320);
    	teleNum[5] = new Rect( 50, 260,  70, 280);
    	teleNum[4] = new Rect( 90, 200, 110, 220);
    	teleNum[3] = new Rect(135, 180, 155, 200);
    	teleNum[2] = new Rect(180, 200, 200, 220);
    	teleNum[1] = new Rect(230, 220, 250, 240);

    	for(int i=0;i<10;i++){
    		teleNum[i].left   -=5;
    		teleNum[i].right  +=5;
    		teleNum[i].top    -=5;
    		teleNum[i].bottom +=5;
    	}

    }

    private int checkNum(float x,float y){

    	for(int i=0; i<10;i++){

    		if ( teleNum[i].left <= x && teleNum[i].right >= x){
    			if( teleNum[i].top <= y && teleNum[i].bottom >= y){
    				Log.v("phone", "Num:" + String.valueOf(i));
    				return i;
    			}
    		}
    	}

    	return -1;
    }

    //ダイアルを初期位置に変更
    private float degree;
    private float start_degree;
    private boolean IsResetPosition = false;
    private void resetDialPosition(){

    	if(IsResetPosition){

    		if(degree < -10){
    			degree = degree + 10;
    		}
    		else if(degree > 10){
    			degree = degree - 10;
    		}
    		else{
    			IsResetPosition = false;
    			degree = 0;
    		}

			rotateDial(degree);
    	}
    }
    private static final int INVALIDATE = 1;
    /**
     * タイマーハンドラー
     */
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (mAnimate && msg.what == INVALIDATE) {
            	resetDialPosition();
            	Log.v("phone","timer in");

                msg = obtainMessage(INVALIDATE);
                long current = SystemClock.uptimeMillis();
                if (mNextTime < current) {
                    // 100ms周期でタイマーイベントが発生
                    mNextTime = current + 200;
                }
                sendMessageAtTime(msg, mNextTime);
                // 100ms周期でタイマーイベントが発生
                mNextTime += 200;
            }
        }
    };
    /**
     * WindowにAttachされた時の処理
     */
    protected void onAttachedToWindow(){
        mAnimate = true;
        Message msg = mHandler.obtainMessage(INVALIDATE);
        mNextTime = SystemClock.uptimeMillis();
        mHandler.sendMessageAtTime(msg, mNextTime);
    }

    /**
     * WindowからDetachされた時の処理
     */
    protected void onDetachedFromWindow() {
         mAnimate = false;
    }
    private boolean         mAnimate;
    private long            mNextTime;
}
