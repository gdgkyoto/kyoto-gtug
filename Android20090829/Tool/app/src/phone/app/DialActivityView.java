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

    private SurfaceHolder holder;//�T�[�t�F�C�X�z���_�[
    private Thread        thread;//�X���b�h
    private DialActivity  parent;//�e�A�N�e�B�r�e�B

    private Bitmap analogDial;	//�_�C�A���摜
    private Bitmap base;		//�w�i�摜
    private long   mNextTime;

    private float degrees;
    private Canvas canvas;
    private final Paint paint = new Paint();

	public DialActivityView(Context context){

		super(context);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u

        //�T�[�t�F�C�X�z���_�[�̐���
        holder=getHolder();
        holder.addCallback(this);
        holder.setFixedSize(getWidth(),getHeight());
        holder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

        //�A�N�e�B�r�e�B�̕ۑ�
        parent = (DialActivity) context;

		//�摜�t�@�C���̓ǂݍ���
        base	   = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_base);
        analogDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_dial);

        //��]���W�b�N�̏�����
        initNum();

	}
    /**
     * XML���Ăяo���ۂ̃R���X�g���N�^
     * @param context, attrs
     */
    public DialActivityView(Context context, AttributeSet attrs){

    	super(context, attrs);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u

    	//�T�[�t�F�C�X�z���_�[�̐���
        holder=getHolder();
        holder.addCallback(this);
        holder.setFixedSize(getWidth(),getHeight());
        holder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

        //�A�N�e�B�r�e�B�̕ۑ�
        parent = (DialActivity) context;

		//�摜�t�@�C���̓ǂݍ���
        base	   = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_base);
        analogDial = BitmapFactory.decodeResource(getResources(),
                R.drawable.rotary_dial);

        //��]���W�b�N�̏�����
        initNum();
	}


	public void setRotateBitmap(float deg){
		degrees = deg;
	}

	//�T�[�t�F�C�X�̐���
    public void surfaceCreated(SurfaceHolder holder) {
    	Log.d("phone", "surfaceCreated");
        thread=new Thread(this);
        thread.start();
    }

    //�T�[�t�F�C�X�̏I��
    public void surfaceDestroyed(SurfaceHolder holder) {
    	Log.d("phone", "surfaceDestroyed");
        thread=null;
    }

    //�T�[�t�F�C�X�̕ύX
    public void surfaceChanged(SurfaceHolder holder,
        int format,int w,int h) {
    	Log.d("phone", "surfaceChanged");
    }

    //�X���b�h�̏���
    public void run() {

        while(thread!=null) {
            //���b�N

            long current = SystemClock.uptimeMillis();
            if (mNextTime < current) {
                // 50ms�����Ń^�C�}�[�C�x���g������
                mNextTime = current + 50;
                resetDialPosition();
            }

            canvas=holder.lockCanvas();

            //�_�C�A���̈ʒu��␳
            Matrix matrix = new Matrix();//��]�s��
            matrix.postScale(1.0f, 1.0f);
            matrix.postRotate(degrees, (analogDial.getWidth()/2), (analogDial.getWidth()/2) );
            matrix.postTranslate(160 - (analogDial.getWidth()/2)  , 240 - (analogDial.getWidth()/2));

            canvas.drawBitmap(base, 0, 0, paint);
            canvas.drawBitmap(analogDial, matrix, paint);

            //Log.d("phone", "deg:" + String.valueOf(degrees));

            //�A�����b�N
            holder.unlockCanvasAndPost(canvas);

            //�X���[�v
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }

    //�_�C�A���������ʒu�ɕύX
    private double startDeg;
    private double nowDeg;
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
            	IsResetPosition  = false;
            	rotateDeg		 = 0;
                startDeg		 = calcDegree(x, y);
                startPhoneNumber = getInputNumber(startDeg);

                //���݂̓��͔ԍ���\��
                if(startPhoneNumber >=0 ){
               		parent.dialNumber.setText(String.valueOf(startPhoneNumber));
                }

            	Log.i("phone","ActionDown, startDeg:" + String.valueOf(startDeg));
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
                IsResetPosition = true;
                endDeg  = calcDegree(x, y);
                rotateDeg = startDeg - endDeg;
            	Log.i("phone","ActionUp, endDeg:" + String.valueOf(endDeg) + ", rotateDeg:" + String.valueOf(rotateDeg));

            	//�����A���͂��������Ă���΁ACall����ԍ��ɒǉ�
                if(IsDial(endDeg) && startPhoneNumber >=0 ){
               		Editable str = parent.editText.getText();
               		parent.editText.setText(str.toString() + String.valueOf(startPhoneNumber));
                }

                //���͒��̕��������ɖ߂�
                parent.dialNumber.setText("-");

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

    	Log.v("phone", "calcDegree: "+ String.valueOf((int)deg)
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
    	phoneNumber[0] = STD_DEG*10 + STD_DEG/2;
    }

    /**
     * ���͊p�x�̃`�F�b�N���W�b�N
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
    private static int STOP_MOTION = 0;

    private void resetDialPosition(){

    	if(IsResetPosition){

    		Log.d("phone","rotate:" + String.valueOf((int)rotateDeg) +
    				",start:" + String.valueOf((int)startDeg) +
    				",end:" + String.valueOf((int)endDeg) );

    		//�J�n�ʒu�ƏI���ʒu�̕s�����`�F�b�N(����]�ł���΁A���[�V�������~����)
    		if ( getInputNumber(startDeg)!=0 && (getInputNumber(startDeg)<= getInputNumber(endDeg) ) ){
    			rotateDeg = STOP_MOTION;
    			IsResetPosition = false;
    		}else

    		/* ���̈ʒu�ɖ߂����ꍇ */
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
	 * �摜����]�����郍�W�b�N
	 * @param degrees
	 */
	private void rotateDial(float degrees){

		setRotateBitmap(degrees);

	}
}
