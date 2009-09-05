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
 * ���d�b�A�v���@���C�����View
 * @author KENJI
 *
 */
public class RotaryDialView extends View {
	
	/** ���C�����Activity�փC�x���g��ʒm���邽�߂̃��X�i
	 *  ���C�����Activity����Z�b�g�����B */
	private DialEventListener dialEventListener;
	
	private android.view.SurfaceHolder surfaceHolder;
	private Canvas canvas;
	
	private Bitmap backImage;
	private Bitmap tableImage;
	private Bitmap nyaunPageImage;
	private Bitmap phoneBodyWithReceiverImage;
	private Bitmap phoneBodyWithOutReceiverImage;
	private Bitmap phoneReceiverImage;
	
	/** ��b����^�b�`�����Ɣ��肷�邽�߂̎�b��Y�ʒu */
	private int receiverDefaultPotisionY = 180;
	
	/** ��b����^�b�`�����Ɣ��肷�邽�߂̎�b��T�C�Y */
	private int receiverHeight = 70;
	
	/** ��b��ړ����̍��W */
	private float receiverX;
	
	/** ��b��ړ����̍��W */
	private float receiverY;
	
	/** ��b��ړ��t���O
	 *  ��b����h���b�O���Ă���Ƃ���true�ɂȂ� */
	private boolean isReceiverMove;

	/**
	 * �R���X�g���N�^
	 * @param context
	 */
	public RotaryDialView(Context context) {
		super(context);
		Log.d("phone","RotaryDialView constructor.");
		
		//�摜�̓ǂݍ���
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
	 * ��ʕ`��
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
    	// �`��
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
			canvas.drawText("��b�킪�グ��ꂽ!!", 0, 0, p);
		}

		if( isReceiverMove ){
			canvas.drawBitmap(phoneBodyWithOutReceiverImage, phoneBodyX, phoneBodyY + 3, null);
			canvas.drawBitmap(phoneReceiverImage, receiverX - phoneReceiverImage.getWidth() /2 , receiverY - phoneReceiverImage.getHeight() / 2, null);
		}else{
			canvas.drawBitmap(phoneBodyWithReceiverImage, phoneBodyX, phoneBodyY, null);
		}
    }
    
    /**
     * �^�b�`�C�x���g���X�i
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
					
					// �ɂႤ��y�[�W�^�b�`���o
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
					// ��b�킪�グ���Ă��āA����b�킪�K��̏ꏊ�ɗ��Ă���ꍇ
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
     * ��b������񂾂��ǂ����̔���
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
     * ��b�킪�h���b�O����A��b�킪�グ��ꂽ���ǂ����𔻒肷��B
     * @return
     */
    private boolean isReceiverMove( float x , float y){
    	// ��b������񂾂��ǂ����̔���𗘗p���A���͈̔͊O�Ȃ��b�킪�グ��ꂽ�Ɣ���
    	return !isReceiverGrab(x,y);
    }
    
	public DialEventListener getDialEventListener() {
		return dialEventListener;
	}

	public void setDialEventListener(DialEventListener dialEventListener) {
		this.dialEventListener = dialEventListener;
	}
}
