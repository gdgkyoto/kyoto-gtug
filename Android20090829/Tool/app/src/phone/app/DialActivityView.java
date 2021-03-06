package phone.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DialActivityView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private SurfaceHolder holder;//サーフェイスホルダー
    private Thread        thread;//スレッド
    private DialActivity  parent;//親アクティビティ

    private Bitmap analogDial;	//ダイアル画像
    private Bitmap base;		//背景画像
    private long   mNextTime;

    private float degrees;
    private Canvas canvas;
    private final Paint paint = new Paint();

	public DialActivityView(Context context){

		super(context);
		// TODO 自動生成されたコンストラクター・スタブ

        //サーフェイスホルダーの生成
        holder=getHolder();
        holder.addCallback(this);
        holder.setFixedSize(getWidth(),getHeight());
        holder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

        //アクティビティの保存
        parent = (DialActivity) context;

		//画像ファイルの読み込み
        base	   = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_base);
        analogDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_dial);

        //回転ロジックの初期化
        initNum();

	}
    /**
     * XMLより呼び出す際のコンストラクタ
     * @param context, attrs
     */
    public DialActivityView(Context context, AttributeSet attrs){

    	super(context, attrs);
		// TODO 自動生成されたコンストラクター・スタブ

    	//サーフェイスホルダーの生成
        holder=getHolder();
        holder.addCallback(this);
        holder.setFixedSize(getWidth(),getHeight());
        holder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

        //アクティビティの保存
        parent = (DialActivity) context;

		//画像ファイルの読み込み
        base	   = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_base);
        analogDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_dial);

        //回転ロジックの初期化
        initNum();
	}


	public void setRotateBitmap(float deg){
		degrees = deg;
	}

	//サーフェイスの生成
    public void surfaceCreated(SurfaceHolder holder) {
    	Log.d("phone", "surfaceCreated");
        thread=new Thread(this);
        thread.start();
    }

    //サーフェイスの終了
    public void surfaceDestroyed(SurfaceHolder holder) {
    	Log.d("phone", "surfaceDestroyed");
        thread=null;
    }

    //サーフェイスの変更
    public void surfaceChanged(SurfaceHolder holder,
        int format,int w,int h) {
    	Log.d("phone", "surfaceChanged");
    }

    //スレッドの処理
    public void run() {

        while(thread!=null) {
            //ロック

            long current = SystemClock.uptimeMillis();
            if (mNextTime < current) {
                // 50ms周期でタイマーイベントが発生
                mNextTime = current + 50;
                resetDialPosition();
            }

            canvas=holder.lockCanvas();

            //ダイアラの位置を補正
            Matrix matrix = new Matrix();//回転行列
            matrix.postScale(1.0f, 1.0f);
            matrix.postRotate(degrees, (analogDial.getWidth()/2), (analogDial.getWidth()/2) );
            matrix.postTranslate(160 - (analogDial.getWidth()/2)  , 240 - (analogDial.getWidth()/2));

            canvas.drawBitmap(base, 0, 0, paint);
            canvas.drawBitmap(analogDial, matrix, paint);

            //Log.d("phone", "deg:" + String.valueOf(degrees));

            //アンロック
            holder.unlockCanvasAndPost(canvas);

            //スリープ
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }

    //ダイアルを初期位置に変更
    private double startDeg;
    private double nowDeg;
    private double endDeg;

    //押下開始電話番号
    private int startPhoneNumber;

    //ダイヤルを元の位置に戻すフラグ
    private boolean IsResetPosition = false;

    //文字盤の数字位置
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

            	//押下ポイントの計算
            	IsResetPosition  = false;
            	rotateDeg		 = 0;
                startDeg		 = calcDegree(x, y);
                startPhoneNumber = getInputNumber(startDeg);

                //現在の入力番号を表示
                if(startPhoneNumber >=0 ){
               		parent.dialNumber.setText(String.valueOf(startPhoneNumber));
                }

            	Log.i("phone","ActionDown, startDeg:" + String.valueOf(startDeg));
                break;

            case MotionEvent.ACTION_MOVE:

                IsResetPosition = false;
                nowDeg = calcDegree(x,y);
                rotateDeg = (startDeg - nowDeg);

            	//ダイアルを回転する
            	rotateDial( (float)rotateDeg );
                break;

            case MotionEvent.ACTION_UP:

            	//離したポイントの計算、入力文字列の確定
                IsResetPosition = true;
                endDeg  = calcDegree(x, y);
                rotateDeg = startDeg - endDeg;
            	Log.i("phone","ActionUp, endDeg:" + String.valueOf(endDeg) + ", rotateDeg:" + String.valueOf(rotateDeg));

            	//もし、入力が成功していれば、Callする番号に追加
                if(IsDial(endDeg) && startPhoneNumber >=0 ){
               		Editable str = parent.editText.getText();
               		parent.editText.setText(str.toString() + String.valueOf(startPhoneNumber));
                }

                //入力中の文字を元に戻す
                parent.dialNumber.setText("-");

                break;
        }
        return true;
    }

    /**
     * 回転角の計算
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

    	Log.v("phone", "calcDegree: "+ String.valueOf((int)deg)
    			+ "(x,y): " + String.valueOf((int)x) + "," + String.valueOf((int)y));
    	return deg;
    }

    /**
     * 入力用文字盤が数字を[1,2,…,9,0]と、14等分している
     */
    private void initNum(){
    	for(int i = 1;i<phoneNumber.length;i++){
    		phoneNumber[i] = STD_DEG*(i) + STD_DEG/2;
    	}
    	phoneNumber[0] = STD_DEG*10 + STD_DEG/2;
    }

    /**
     * 入力角度のチェックロジック
     * @param degree
     * @return
     */
    private int getInputNumber(double degree){
    	for(int i = 0;i<phoneNumber.length;i++){
    		if (phoneNumber[i]-STD_DEG/2 <= degree  && degree < (phoneNumber[i]+STD_DEG/2) ){
    			return i;
    		}
    	}
    	return -1;
    }

    /**
     * 入力完了判定
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
     * 文字盤の表示位置を元に戻す
     */
    private double rotateDeg;
    private static int STOP_MOTION = 0;

    private void resetDialPosition(){

    	if(IsResetPosition){

    		Log.d("phone","rotate:" + String.valueOf((int)rotateDeg) +
    				",start:" + String.valueOf((int)startDeg) +
    				",end:" + String.valueOf((int)endDeg) );

    		//開始位置と終了位置の不整合チェック(左回転であれば、モーションを停止する)
    		if ( getInputNumber(startDeg)!=0 && (getInputNumber(startDeg)<= getInputNumber(endDeg) ) ){
    			rotateDeg = STOP_MOTION;
    			IsResetPosition = false;
    		}else

    		/* 元の位置に戻った場合 */
    		if(rotateDeg < -360 || ( rotateDeg < 0 && (startDeg - endDeg > 0)) ){
    			rotateDeg = STOP_MOTION;
    			IsResetPosition = false;
    		}else{
    			rotateDeg -= 15;
    		}

    		rotateDial( (float)rotateDeg );
     	}
    }

	/**
	 * 画像を回転させるロジック
	 * @param degrees
	 */
	private void rotateDial(float degrees){

		setRotateBitmap(degrees);

	}
}
