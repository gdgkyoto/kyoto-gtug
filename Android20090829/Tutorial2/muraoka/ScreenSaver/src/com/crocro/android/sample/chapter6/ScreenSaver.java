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
	// �O���[�o���ϐ�
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
	
	//���C�t�p�ϐ�
	Random mRnd = new Random();
	int[][] mTableArray;
	int mTableX, mTableY;
	int mTableW, mTableH;
	long mLastLifeTime;
	final int LIFE_DEFAULT = 100;

	//�^�b�`�p�b�h�p�ϐ�
	float touchX,touchY = 0;
	final int TOUCH_MODIFY = 7;
	
	//�T�E���h�p�ϐ�
	private MediaPlayer mBgmPlayer;
	private MediaPlayer[] mSePlayer;
	private final int SE_MAX = 4;
	
    //Activity �쐬
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //�uSaverView�v����ʂɐݒu
        SaverView saverView = new SaverView(this);
        setContentView(
        		saverView,
        		new ViewGroup.LayoutParams(
        				ViewGroup.LayoutParams.FILL_PARENT,
        				ViewGroup.LayoutParams.FILL_PARENT
        		)
        );
    }
    
    //�ꎞ��~
    @Override
    protected void onPause(){
    	super.onPause();
    	mSaverThread.pause();
    	stopBgm();
    	Log.e("@@@", "onPause");
    }
    
    //�uSaverView�v�N���X
    class SaverView extends SurfaceView implements SurfaceHolder.Callback{
    	//�R���X�g���N�^�[
    	public SaverView(Context context){
    		super(context);
    		
    		mSurfaceHolder = getHolder();
    		mSurfaceHolder.addCallback(this);
    		
    		mContext = context;
    		
    		setFocusable(true);
    	}
    
    	//��ʂ̃t�H�[�J�X�ύX�i�ꎞ��~�^���A�j
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
    
    	//��ʂ̕ύX�i��ʂ̍č\�z�j
    	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
    		mSaverThread.setSurfaceSize(width, height);
    	}
    
    	//��ʍ쐬�i�X���b�h�J�n�j
    	public void surfaceCreated(SurfaceHolder holder){
    		mSaverThread = new SaverThread();
    		mSaverThread.unpause();
    		mSaverThread.setRunning(true);
    		mSaverThread.start();
    		initSound();
    		playBgm();
    	}
    
    	//��ʂ̔j���i�X���b�h�I���j
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
    	
    	//�^�b�`�C�x���g
    	@Override
    	public boolean onTouchEvent(MotionEvent event){
    		return doTouchEvent(event);
    	}
    	
    	//�^�b�`�C�x���g�̎��s
    	boolean doTouchEvent(MotionEvent event){
    		synchronized (mSurfaceHolder){
    			touchX = event.getX();
                touchY = event.getY();
                playSe();
    		}
    		return true;
    	}
    }
    
	//�T�E���h�̏�����
	public void initSound(){
		//BGM�̍쐬
		try{
			mBgmPlayer = MediaPlayer.create(mContext, R.raw.bgm);
			mBgmPlayer.setLooping(true);
		}catch(Exception e){
		}
		//SE�̍쐬
		try{
			mSePlayer = new MediaPlayer[SE_MAX];
			for(int i = 0; i < SE_MAX; i ++){
				mSePlayer[i] = MediaPlayer.create(mContext, R.raw.se);
				mSePlayer[i].setLooping(false);
			}
		}catch(Exception e){
		}	
	}
	
	//BGM�̍Đ�
	public void playBgm(){
		if(mBgmPlayer == null) return;
		try{
			if(mBgmPlayer.isPlaying()) return;
			mBgmPlayer.start();
		}catch(Exception e){
		}
	}
	
	//BGM�̒�~
	public void stopBgm(){
		if(mBgmPlayer == null) return;
		try{
			if(!mBgmPlayer.isPlaying()) return;
			mBgmPlayer.pause();
		}catch(Exception e){
		}
	}
	
	//SE�̍Đ�
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
    
    //�uSaverThread�v�N���X
    class SaverThread extends Thread{
    	//�R���X�g���N�^�[
    	public SaverThread(){
    		//�`��֌W�̃I�u�W�F�N�g�̏�����
    		mBgBitmap = Bitmap.createBitmap(1,1,Bitmap.Config.RGB_565);
    		mPaint.setAntiAlias(true);
    		
    		//���C�t�̓ǂݍ���
    		mLifeDrawable = ScreenSaver.this.getResources()
    			.getDrawable(R.drawable.life);
    		mLifeW = mLifeDrawable.getIntrinsicWidth();
    		mLifeH = mLifeDrawable.getIntrinsicHeight();
    	}
    	
    	//�ꎞ��~
    	public void pause(){
    		synchronized(mSurfaceHolder){
    			mMode = STATE_PAUSE;
    		}
    	}
    	
    	//���A
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
    	
    	//���s��Ԑݒ�
    	public void setRunning(boolean b){
    		mRun = b;
    	}
    	
    	//��ʃT�C�Y�ݒ�
    	public void setSurfaceSize(int width, int height){
    		synchronized(mSurfaceHolder){
    			//��ʂ̍\�z
    			mCanvasW = width;
    			mCanvasH = height;
    			
    			mBgBitmap = Bitmap.createScaledBitmap(mBgBitmap, width, height, true);
    			
    			//���C�t�̍\�z
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
    	
    	//�`��
    	private void doDraw(Canvas canvas){
    		//�w�i�X�V
    		canvas.drawBitmap(mBgBitmap, 0, 0, null);
    		
    		//�}�X�`��
    		for (int x = 0; x < mTableX; x ++){
    			for (int y = 0; y < mTableY; y++){
    				if(mTableArray[x][y] > 0){
    					doDrawTable(canvas, x, y);
    				}
    			}
    		}
    	}
    	
    	//�}�X�`��
    	private void doDrawTable(Canvas canvas, int x, int y){
    		//�ϐ��̏�����
    		float fDegree, fX, fY, fSize;
    		
    		//�l�̏�����
    		fDegree = ((int)(mLastTime / 2 / (x * y % 5 + 1) + 360) % 360);
    		fSize = (mTableW + mTableH) / 4;
    		
    		//�^�b�`�������W����\�����W�ɒǉ�����l���擾
    		int addTouchX = (int)Math.floor(touchX / TOUCH_MODIFY);
    		int addTouchY = (int)Math.floor(touchY / TOUCH_MODIFY);
    		
    		//�^�b�`���ꂽ�烉�C�t�̕\���ʒu��ύX����
    		//���W�̎���������������˂���B�B�킩���Ă��B�B�B
    		if(touchX != 0 && touchY != 0){
           		fX = x * mTableW + mTableW / 4;
        		fY = y * mTableH + mTableH / 4;
    		}else{
    			fX = x * mTableW + mTableW / 4 + mRnd.nextInt(mTableW / 4);
        		fY = y * mTableH + mTableH / 4 + mRnd.nextInt(mTableH / 4);
    		}
    		
    		//���C�t�`��
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
    	
    	//�v�Z
    	private void updateCalc(){
    		//�o�ߎ��Ԃ̏�����
    		long now = System.currentTimeMillis();
    		double elapsed = (now - mLastTime) / 1000.0;
    		mLastTime = now;
    		if(elapsed > 1){
    			mLastLifeTime = mLastTime;
    			return;
    		}
    		
    		if(mLastTime - 1 < mLastLifeTime) return;
    		mLastLifeTime = mLastTime;
    		
    		//�v�Z
    		int pow;
    		int cnt = 0;
    		for (int x = 0; x < mTableX; x ++){
    			for (int y = 0; y < mTableY; y ++){
    				if (mTableArray[x][y] > 0) cnt ++;
    			}
    		}
    		for (int x = 0; x < mTableX; x ++){
    			for (int y = 0; y < mTableY; y ++){
    				//���͂̒l�����߂�
    				pow = 0;
    				if(x > 0)           pow += mTableArray[x-1][y];
    				if(x < mTableX - 1) pow += mTableArray[x+1][y];
    				if(y > 0)           pow += mTableArray[x][y-1];
    				if(y > mTableY - 1) pow += mTableArray[x][y+1];
    				
    				//���͂̒l�ɂ�莩����ω�������
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

