package com.crocro.android.sample.chapter6;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.SurfaceView;
import android.content.Context;
import android.view.SurfaceHolder;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import java.util.Random;

import java.math.*;

import android.util.Log;

import com.crocro.android.sample.chapter6.R;

import android.view.MotionEvent;

public class ScreenSaver extends Activity {
	// グローバル変数
	SurfaceHolder mSurfaceHolder;
	SaverThread mSaverThread;
	Context mContext;
	
	int mMode;
	public static final int STATE_PAUSE = 1;
	public static final int STATE_RUNNING = 2;
	
	boolean mRun = false;
	
	int mCanvasW = 1;
	int mCanvasH = 1;
	
	long mLastTime;
	
	Bitmap mBgBitmap;
	Paint mPaint = new Paint();
	RectF mRectF = new RectF();
	Drawable mLifeDrawable;
	int mLifeW, mLifeH;
	
	//ライフ用変数
	Random mRnd = new Random();
	int[][] mTableArray;
	int mTableX, mTableY;
	int mTableW, mTableH;
	long mLastLifeTime;
	final int LIFE_DEFAULT = 100;

	//タッチパッド用変数
	float touchX,touchY = 0;
	final int TOUCH_MODIFY = 7;
	
	//サウンド用変数
	private MediaPlayer mBgmPlayer;
	private MediaPlayer[] mSePlayer;
	private final int SE_MAX = 4;
	
    //Activity 作成
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //「SaverView」を画面に設置
        SaverView saverView = new SaverView(this);
        setContentView(
        		saverView,
        		new ViewGroup.LayoutParams(
        				ViewGroup.LayoutParams.FILL_PARENT,
        				ViewGroup.LayoutParams.FILL_PARENT
        		)
        );
    }
    
    //一時停止
    @Override
    protected void onPause(){
    	super.onPause();
    	mSaverThread.pause();
    	stopBgm();
    	Log.e("@@@", "onPause");
    }
    
    //「SaverView」クラス
    class SaverView extends SurfaceView implements SurfaceHolder.Callback{
    	//コンストラクター
    	public SaverView(Context context){
    		super(context);
    		
    		mSurfaceHolder = getHolder();
    		mSurfaceHolder.addCallback(this);
    		
    		mContext = context;
    		
    		setFocusable(true);
    	}
    
    	//画面のフォーカス変更（一時停止／復帰）
    	@Override
    	public void onWindowFocusChanged(boolean hasWindowFocus){
    		if(mSaverThread != null){
    			if(!hasWindowFocus){
    				mSaverThread.pause();
    			}else{
    				mSaverThread.unpause();
    			}
    		}
    	}
    
    	//画面の変更（画面の再構築）
    	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
    		mSaverThread.setSurfaceSize(width, height);
    	}
    
    	//画面作成（スレッド開始）
    	public void surfaceCreated(SurfaceHolder holder){
    		mSaverThread = new SaverThread();
    		mSaverThread.unpause();
    		mSaverThread.setRunning(true);
    		mSaverThread.start();
    		initSound();
    		playBgm();
    	}
    
    	//画面の破棄（スレッド終了）
    	public void surfaceDestroyed(SurfaceHolder holder){
    		boolean retry = true;
    		mSaverThread.setRunning(false);
    		while (retry){
    			try{
    				mSaverThread.join();
    				retry = false;
    			}catch(InterruptedException e){
    			}
    		}
    	}
    	
    	//タッチイベント
    	@Override
    	public boolean onTouchEvent(MotionEvent event){
    		return doTouchEvent(event);
    	}
    	
    	//タッチイベントの実行
    	boolean doTouchEvent(MotionEvent event){
    		synchronized (mSurfaceHolder){
    			touchX = event.getX();
                touchY = event.getY();
                playSe();
    		}
    		return true;
    	}
    }
    
	//サウンドの初期化
	public void initSound(){
		//BGMの作成
		try{
			mBgmPlayer = MediaPlayer.create(mContext, R.raw.bgm);
			mBgmPlayer.setLooping(true);
		}catch(Exception e){
		}
		//SEの作成
		try{
			mSePlayer = new MediaPlayer[SE_MAX];
			for(int i = 0; i < SE_MAX; i ++){
				mSePlayer[i] = MediaPlayer.create(mContext, R.raw.se);
				mSePlayer[i].setLooping(false);
			}
		}catch(Exception e){
		}	
	}
	
	//BGMの再生
	public void playBgm(){
		if(mBgmPlayer == null) return;
		try{
			if(mBgmPlayer.isPlaying()) return;
			mBgmPlayer.start();
		}catch(Exception e){
		}
	}
	
	//BGMの停止
	public void stopBgm(){
		if(mBgmPlayer == null) return;
		try{
			if(!mBgmPlayer.isPlaying()) return;
			mBgmPlayer.pause();
		}catch(Exception e){
		}
	}
	
	//SEの再生
	public void playSe(){
		if(mSePlayer == null) return;
		try{
			for (int i = 0; i < SE_MAX; i ++){
				if(mSePlayer[i] == null) continue;
				if(mSePlayer[i].isPlaying()) continue;
				mSePlayer[i].seekTo(0);
				mSePlayer[i].start();
				break;
			}
		}catch(Exception e){
		}
	}
    
    //「SaverThread」クラス
    class SaverThread extends Thread{
    	//コンストラクター
    	public SaverThread(){
    		//描画関係のオブジェクトの初期化
    		mBgBitmap = Bitmap.createBitmap(1,1,Bitmap.Config.RGB_565);
    		mPaint.setAntiAlias(true);
    		
    		//ライフの読み込み
    		mLifeDrawable = ScreenSaver.this.getResources()
    			.getDrawable(R.drawable.life);
    		mLifeW = mLifeDrawable.getIntrinsicWidth();
    		mLifeH = mLifeDrawable.getIntrinsicHeight();
    	}
    	
    	//一時停止
    	public void pause(){
    		synchronized(mSurfaceHolder){
    			mMode = STATE_PAUSE;
    		}
    	}
    	
    	//復帰
    	public void unpause(){
    		synchronized(mSurfaceHolder){
    			mMode = STATE_RUNNING;
    		}
    	}
    	
    	//run
    	@Override
    	public void run(){
    		while(mRun){
    			Canvas c = null;
    			try{
    				c = mSurfaceHolder.lockCanvas(null);
    				synchronized(mSurfaceHolder){
    					if(mMode == STATE_RUNNING) updateCalc();
    					doDraw(c);
    				}
    			}finally{
    				if(c != null){
    					mSurfaceHolder.unlockCanvasAndPost(c);
    				}
    			}
    		}
    	}
    	
    	//実行状態設定
    	public void setRunning(boolean b){
    		mRun = b;
    	}
    	
    	//画面サイズ設定
    	public void setSurfaceSize(int width, int height){
    		synchronized(mSurfaceHolder){
    			//画面の構築
    			mCanvasW = width;
    			mCanvasH = height;
    			
    			mBgBitmap = Bitmap.createScaledBitmap(mBgBitmap, width, height, true);
    			
    			//ライフの構築
    			mTableW = mLifeW / 2;
    			mTableH = mLifeH / 2;
    			mTableX = mCanvasW / mTableW + 1;
    			mTableY = mCanvasH / mTableH + 1;
    			mTableArray = new int[mTableX][mTableY];
    			for (int i = 0; i < mTableX * mTableY / 4; i ++){
    				mTableArray[mRnd.nextInt(mTableX)][mRnd.nextInt(mTableY)]
    				    += LIFE_DEFAULT;
    			}
    		}
    	}
    	
    	//描画
    	private void doDraw(Canvas canvas){
    		//背景更新
    		canvas.drawBitmap(mBgBitmap, 0, 0, null);
    		
    		//マス描画
    		for (int x = 0; x < mTableX; x ++){
    			for (int y = 0; y < mTableY; y++){
    				if(mTableArray[x][y] > 0){
    					doDrawTable(canvas, x, y);
    				}
    			}
    		}
    	}
    	
    	//マス描画
    	private void doDrawTable(Canvas canvas, int x, int y){
    		//変数の初期化
    		float fDegree, fX, fY, fSize;
    		
    		//値の初期化
    		fDegree = ((int)(mLastTime / 2 / (x * y % 5 + 1) + 360) % 360);
    		fSize = (mTableW + mTableH) / 4;
    		
    		//タッチした座標から表示座標に追加する値を取得
    		int addTouchX = (int)Math.floor(touchX / TOUCH_MODIFY);
    		int addTouchY = (int)Math.floor(touchY / TOUCH_MODIFY);
    		
    		//タッチされたらライフの表示位置を変更する
    		//座標の取り方がおかしいよねこれ。。わかってるよ。。。
    		if(touchX != 0 && touchY != 0){
           		fX = x * mTableW + mTableW / 4;
        		fY = y * mTableH + mTableH / 4;
    		}else{
    			fX = x * mTableW + mTableW / 4 + mRnd.nextInt(mTableW / 4);
        		fY = y * mTableH + mTableH / 4 + mRnd.nextInt(mTableH / 4);
    		}
    		
    		//ライフ描画
    		canvas.save();
    		canvas.rotate(fDegree, fX, fY);
    		mLifeDrawable.setBounds(
    				//(int)(fX - fSize), (int)(fX - fSize),
    				//(int)(fY - fSize), (int)(fY - fSize)
    				(int)addTouchX,(int)addTouchX,
    				(int)addTouchY,(int)addTouchY
    		);
    		mLifeDrawable.draw(canvas);
    		canvas.restore();
    	}
    	
    	//計算
    	private void updateCalc(){
    		//経過時間の初期化
    		long now = System.currentTimeMillis();
    		double elapsed = (now - mLastTime) / 1000.0;
    		mLastTime = now;
    		if(elapsed > 1){
    			mLastLifeTime = mLastTime;
    			return;
    		}
    		
    		if(mLastTime - 1 < mLastLifeTime) return;
    		mLastLifeTime = mLastTime;
    		
    		//計算
    		int pow;
    		int cnt = 0;
    		for (int x = 0; x < mTableX; x ++){
    			for (int y = 0; y < mTableY; y ++){
    				if (mTableArray[x][y] > 0) cnt ++;
    			}
    		}
    		for (int x = 0; x < mTableX; x ++){
    			for (int y = 0; y < mTableY; y ++){
    				//周囲の値を求める
    				pow = 0;
    				if(x > 0)           pow += mTableArray[x-1][y];
    				if(x < mTableX - 1) pow += mTableArray[x+1][y];
    				if(y > 0)           pow += mTableArray[x][y-1];
    				if(y > mTableY - 1) pow += mTableArray[x][y+1];
    				
    				//周囲の値により自分を変化させる
    				if(pow > LIFE_DEFAULT * 3
    						&& mTableArray[x][y] == 0
    						&& cnt <= (mTableX * mTableY) / 2
    				){
    					mTableArray[x][y] = LIFE_DEFAULT;
    				}else
    					if (pow >= LIFE_DEFAULT * 2
    					 && pow <= LIFE_DEFAULT * 3
    					 && cnt <= (mTableX * mTableY) / 2
    					) {
    						mTableArray[x][y] += LIFE_DEFAULT / 5;
    					}else
    						if(cnt >= (mTableX * mTableY) / 4){
    							mTableArray[x][y] -= LIFE_DEFAULT / 5;
    						}
    						if(mTableArray[x][y] >= LIFE_DEFAULT * 2) {
    							mTableArray[x][y] = LIFE_DEFAULT * 2;
    						}
    						if(mTableArray[x][y] < 0) {
    							mTableArray[x][y] = 0;
    						}
    						if(mTableArray[x][y] < 0) mTableArray[x][y] = 0;
    			}
    		}
    	}
    	    	
    	
    }
    
}

