package org.kyotogtug.hackathon090905.games.kitagawaSample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Context;
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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceViewSample extends SurfaceView implements SurfaceHolder.Callback, Runnable, SensorEventListener {

	  private SurfaceHolder holder;
	  private static final int MAX_COUNT = 130;
	  private List<Wall> walls = Collections.synchronizedList(new ArrayList<Wall>(MAX_COUNT));
	  private Drawable drawable;
	  //ScheduledExecutorService executor;
	  private Thread thread;
	  //Player
	  private Player player;
	  //回転角
	  private float roll;

	  public GameSurfaceViewSample(Context context) {
	    super(context);
	    initialize(context);
	  }

	  public GameSurfaceViewSample(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    initialize(context);
	  }

	  public GameSurfaceViewSample(Context context, AttributeSet attrs) {
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
	    thread = new Thread(this);
	    thread.start();
	    player = new Player(drawable, new Rect(getLeft(), getTop(), getRight(), getBottom()),
	            10, 400);
	    for (int i=0; i<20; i++) {
		    walls.add(new Wall(drawable, new Rect(getLeft(), getTop(), getRight(), getBottom()),
		            80 + 4*i, 380));
	    }
	    for (int i=0; i<30; i++) {
		    walls.add(new Wall(drawable, new Rect(getLeft(), getTop(), getRight(), getBottom()),
		            20+3*i, 350));
	    }
	  }

	  public void surfaceDestroyed(SurfaceHolder holder) {
	    thread = null;
	  }

	  @Override
	  public boolean onTouchEvent(MotionEvent event) {

	    //TODO 仮に
		  Random random = new Random();
		  player.direction.setDx(random.nextInt(6)-3);
		  player.direction.setDy(random.nextInt(20)-20);
	    return true;
	  }

	  public void run() {
	    Canvas canvas = null;
	    Paint p = new Paint();
	    p.setColor(Color.WHITE);
	    while (thread != null) {
	      try {
	        canvas = holder.lockCanvas();
	        canvas.drawRect(0, 0, getWidth(), getHeight(), p);
	        synchronized (walls) {
	          // 描画
	          for (Wall wall : walls) {
	        	  wall.draw(canvas);
	          }
		      // playerと壁の衝突
		      for (Wall wall : walls) {
		    	  wall.changeDirectionIfEncountered(player);
		      }

	          player.move();
	          player.draw(canvas);

	        }
	      } finally {
	        if (canvas != null)
	          holder.unlockCanvasAndPost(canvas);
	      }
	    }
	  }

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void onSensorChanged(SensorEvent event) {
		roll = event.values[SensorManager.DATA_Z];

	}
}