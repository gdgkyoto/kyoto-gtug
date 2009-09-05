package org.kyotogtug.hackathon090905.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.kyotogtug.hackathon090905.games.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable, SensorEventListener {

	  // サーフェイスホルダー
	  private SurfaceHolder holder;
	  private static final int MAX_COUNT = 130;
	  private List<Block> blocks = Collections.synchronizedList(new ArrayList<Block>(MAX_COUNT));
	  private Drawable drawable;
	  //ScheduledExecutorService executor;
	  private Thread thread;
	  //Player
	  private Player player;
	  //回転角
	  private float roll = 0;

	  // SensorManagerのインスタンス
	  private SensorManager sensorManager;
	  //フィルタ
	  private static final float FILTERING_VALUE = 0.1f;
	  //ローパスフィルタされた値
	  private float lowY;
	  //ハイパスフィルタされた値
	  private float highY;
	  //画面からスクロールした値
	  private int angle=0;
	  // オフスクリーン用
	  private Bitmap offBitmap = null;
	  // オフスクリーンキャンバス
	  private Canvas offCanvas = null;

	  public GameSurfaceView(Context context) {
	    super(context);
	    initialize(context);
	  }

	  public GameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    initialize(context);
	  }

	  public GameSurfaceView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    initialize(context);
	  }

	  private void initialize(Context context) {
	    holder = getHolder();
	    holder.addCallback(this);
	    holder.setFixedSize(getWidth(), getHeight());
	    drawable = context.getResources().getDrawable(R.drawable.ball);
	  }

	  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	  }

	  public void surfaceCreated(SurfaceHolder holder) {

	    // SensorManagerのインスタンスを取得
	    sensorManager = (SensorManager)getContext().getSystemService(Context.SENSOR_SERVICE);
	    // リスナーの登録
	    sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	    		SensorManager.SENSOR_DELAY_FASTEST);
	    sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
	            SensorManager.SENSOR_DELAY_NORMAL);

	    //別スレッド
	    thread = new Thread(this);
	    thread.start();

	    player = new Player(drawable, new Rect(getLeft(), getTop(), getRight(), getBottom()),
	            10, 400);
	    for (int i=0; i<20; i++) {
		    blocks.add(new Block(drawable, new Rect(getLeft(), getTop(), getRight(), getBottom()),
		            80 + 4*i, 380));
	    }
	  }

	  public void surfaceDestroyed(SurfaceHolder holder) {
	    thread = null;
	    sensorManager.unregisterListener(this);
	  }

	  @Override
	  public boolean onTouchEvent(MotionEvent event) {

	    //TODO エミュレータのテスト用に仮に
		  Random random = new Random();
		  player.direction.setDx(random.nextInt(6)-3);
		  player.direction.setDy(random.nextInt(20)-20);
	    return true;
	  }

	  public void run() {
	    Canvas canvas = null;
		
	    if(offBitmap == null){
	    	offBitmap = Bitmap.createBitmap(getWidth(), getHeight()*2, Bitmap.Config.RGB_565);
	    }

	    if(offCanvas == null){
		    offCanvas = new Canvas(offBitmap);	    	
	    }
	    
	    Paint p = new Paint();
	    p.setColor(Color.WHITE);
	    while (thread != null) {
	      try {
	        canvas = holder.lockCanvas();
	        
	        offCanvas.drawColor(Color.WHITE);
	        
	        synchronized (blocks) {
	          // 描画
	          for (Block block : blocks) {
	        	  block.draw(offCanvas);
	          }
		      // playerと壁の衝突
		      for (Block block : blocks) {
		    	  block.changeDirectionIfEncountered(player);
		      }

	          Paint paint = new Paint();
	          paint.setStyle(Paint.Style.FILL);
	          paint.setColor(0xFF000000);
	          canvas.drawText("Roll:"+ (int)roll/10*-1,10,10,paint);
	          canvas.drawText("Roll:"+ (int)highY*-1,10,20,paint);

	          player.move((int) roll/10*-1, (int) highY*-1);
	          player.draw(offCanvas);
	        }
	        scrollAngle(player);
	        canvas.drawBitmap(offBitmap, 0, angle, null);
	      } finally {
	        if (canvas != null)
	          holder.unlockCanvasAndPost(canvas);
	      }
	    }
	  }

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO 自動生成されたメソッド・スタブ

	}
	
private float x=0;
private float z=0;
	public void onSensorChanged(SensorEvent event) {
	    switch(event.sensor.getType()) {
 	      // 加速度
	      case Sensor.TYPE_ACCELEROMETER: {
	    	  float y = event.values[SensorManager.DATA_Y];
	    	  lowY = y * FILTERING_VALUE + lowY * (1.0f - FILTERING_VALUE);
	    	  highY = y - lowY;
	    	  if (x<highY) x=highY;
	    	  if (z>highY) z=highY;
	    	  Log.v("x", ":"+x);
	    	  Log.v("z", ":"+z);
	    	  break;
	      }
	      // 傾き
	      case Sensor.TYPE_ORIENTATION: {
	    	  roll = event.values[SensorManager.DATA_Z];
	    	  break;
	      }
	    }
	}
	
	// 画面をスクロールさせる
	private void scrollAngle(Player player){
		int y = player.rect.centerY();
		int height = getHeight();
		if(-angle + height * 0.25 > y){	// 画面上部にあるとき
			angle+=10;
			if(angle > 0){
				angle=0;
			}
		}else if (-angle + height * 0.75 < y) {		// 画面下部にあるとき
			angle-=10;
			if (angle < -height) {
				angle = -height;
			}
		}
	}
}