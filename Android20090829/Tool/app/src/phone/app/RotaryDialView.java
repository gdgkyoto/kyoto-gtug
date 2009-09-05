package phone.app;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * 黒電話アプリ　メイン画面View
 * @author KENJI
 *
 */
public class RotaryDialView extends View {
	
	/** メイン画面Activityへイベントを通知するためのリスナ
	 *  メイン画面Activityからセットされる。 */
	private DialEventListener dialEventListener;
	
	private android.view.SurfaceHolder surfaceHolder;
	private Canvas canvas;
	
	private Bitmap backImage;
	private Bitmap tableImage;
	private Bitmap nyaunPageImage;
	private Bitmap phoneBodyWithReceiverImage;
	private Bitmap phoneBodyWithOutReceiverImage;
	private Bitmap phoneReceiverImage;
	
	/** 受話器をタッチしたと判定するための受話器Y位置 */
	private int receiverDefaultPotisionY = 180;
	
	/** 受話器をタッチしたと判定するための受話器サイズ */
	private int receiverHeight = 70;
	
	/** 受話器移動中の座標 */
	private float receiverX;
	
	/** 受話器移動中の座標 */
	private float receiverY;
	
	/** 受話器移動フラグ
	 *  受話器をドラッグしているときにtrueになる */
	private boolean isReceiverMove;
	
	private RotaryDial rotaryDial;

	/**
	 * コンストラクタ
	 * @param context
	 */
	public RotaryDialView(Context context) {
		super(context);
		rotaryDial = (RotaryDial)context;
		Log.d("phone","RotaryDialView constructor.");
		
		//画像の読み込み
        Resources r=getResources();
        backImage=BitmapFactory.decodeResource(r,R.drawable.back);
        tableImage = BitmapFactory.decodeResource(r,R.drawable.base1e);
        nyaunPageImage = BitmapFactory.decodeResource(r,R.drawable.base2e);
        phoneBodyWithReceiverImage = BitmapFactory.decodeResource(r,R.drawable.base3e);
        phoneReceiverImage = BitmapFactory.decodeResource(r,R.drawable.phone2e);
        phoneBodyWithOutReceiverImage = BitmapFactory.decodeResource(r,R.drawable.phone1e);
        
        Log.d("phone","load image="+backImage.getHeight());

        
        setOnTouchListener(new RotaryDialTouchListener());
	}
	
	
	/**
	 * 画面描画
	 */
    @Override
    protected void onDraw(Canvas canvas) {
    	
    	int phoneBodyX = 0;
    	int phoneBodyY = 170;
    	int tableX = 0;
    	int tableY = 240;
    	int nyaunPageX = 70;
    	int nyaunPageY = 330;
    	
    	Log.d("phone","RotaryDialView.onDraw");
    	// 描画
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(backImage      , 0, 0, null);
		canvas.drawBitmap(tableImage     , tableX, tableY, null);
		canvas.drawBitmap(nyaunPageImage , nyaunPageX, nyaunPageY, null);
		
		if( isReceiverMove && isReceiverMove(receiverX,receiverY)){
			Log.d("phone","receiver MOVE!!");
			Paint p = new android.graphics.Paint();
			p.setColor(Color.WHITE);
			p.setStrokeCap(Paint.Cap.SQUARE);
			p.setStrokeWidth(2);
			canvas.drawText("受話器が上げられた!!", 0, 40, p);
		}

		if( isReceiverMove ){
			canvas.drawBitmap(phoneBodyWithOutReceiverImage, phoneBodyX, phoneBodyY + 3, null);
			canvas.drawBitmap(phoneReceiverImage, receiverX - phoneReceiverImage.getWidth() /2 , receiverY - phoneReceiverImage.getHeight() / 2, null);
		}else{
			canvas.drawBitmap(phoneBodyWithReceiverImage, phoneBodyX, phoneBodyY, null);
		}
		Paint p = new android.graphics.Paint();
		p.setColor(Color.WHITE);
		p.setStrokeCap(Paint.Cap.SQUARE);
		p.setStrokeWidth(2);
		p.setTextSize(20);
		
		if( rotaryDial.getYutoriMode() == RotaryDial.MODE_YUTORI ){
			p.setColor(Color.BLACK);
			canvas.drawText("ゆとりモード (・∀・)ノ", 1, 22, p);
			canvas.drawText("ゆとりモード (・∀・)ノ", 2, 20, p);
			
			p.setColor(Color.WHITE);
			canvas.drawText("ゆとりモード (・∀・)ノ", 0, 20, p);
		}
    }
    
    /**
     * タッチイベントリスナ
     * @author KENJI
     *
     */
    class RotaryDialTouchListener implements View.OnTouchListener{
    	@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch( event.getAction() ){
				case MotionEvent.ACTION_DOWN :
					
					if( isReceiverGrab( event.getX() , event.getY() ) ){
						isReceiverMove = true;
					}
					
					// にゃうんページタッチ検出
					if( event.getX() > 100 && event.getY() > 300 ){
						dialEventListener.openContactListView();
					}
				break;
				case MotionEvent.ACTION_MOVE :
					if( isReceiverMove ){
						receiverX = event.getX();
						receiverY = event.getY();
						invalidate();
					}
				break;
				case MotionEvent.ACTION_UP :
					// 受話器が上げられていて、かつ受話器が規定の場所に来ている場合
					if( isReceiverMove && 
						isReceiverMove(event.getX(), event.getY()) ){
						dialEventListener.openDialView();
					}
					isReceiverMove = false;
				break;
				
			}
			
			Log.d("phone", "x="+event.getX()+"  y="+event.getY());
		     return true;
		}
    }
    
    /**
     * 受話器をつかんだかどうかの判定
     * @return
     */
    private boolean isReceiverGrab( float x , float y ){
    	if( y > receiverDefaultPotisionY - receiverHeight &&
    		y < receiverDefaultPotisionY + receiverHeight){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    
    /**
     * 受話器がドラッグされ、受話器が上げられたかどうかを判定する。
     * @return
     */
    private boolean isReceiverMove( float x , float y){
    	// 受話器をつかんだかどうかの判定を利用し、その範囲外なら受話器が上げられたと判定
    	return !isReceiverGrab(x,y);
    }
    
	public DialEventListener getDialEventListener() {
		return dialEventListener;
	}

	public void setDialEventListener(DialEventListener dialEventListener) {
		this.dialEventListener = dialEventListener;
	}
}
