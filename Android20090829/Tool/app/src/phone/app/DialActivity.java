package phone.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
	    	TextView textDialNumber = (TextView) findViewById(R.id.DialNumber);
	    	textDialNumber.setText("Number: "+number);
	    	TextView textDialMemo   = (TextView) findViewById(R.id.DialMemo);
	    	textDialMemo.setText("Memo: "+name);
	    }

	    //回転用イメージ読み込み
        analogDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_dial);
	}



	//画像を回転させるロジック
	private void rotateDial(float degrees){

        int width = analogDial.getWidth();
        int height = analogDial.getHeight(); 
        
		// createa matrix for the manipulation
		Matrix matrix = new Matrix();
        matrix.postScale(1.0f, 1.0f);
		// rotate the Bitmap
        matrix.postRotate(degrees);

        rotateDial = Bitmap.createBitmap(analogDial, 0, 0,
        		width, height, matrix, true);

		BitmapDrawable bmd = new BitmapDrawable(rotateDial);

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
                //setSerPoint(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v("phone","ActionMove");
                movePoint(x,y);
                break;
            case MotionEvent.ACTION_UP:
                Log.v("phone","ActionMove");

                break;
        }
        return true;
    }


    private void setSerPoint(float x, float y){

    	return;
    }

    private void movePoint(float x, float y){
    	float degree = (float) (Math.atan2(y - 240, x - 160 ) * 180 / Math.PI);
    	Log.v("phone",String.valueOf(degree));
    	rotateDial(degree);
    	return;
    }
}
