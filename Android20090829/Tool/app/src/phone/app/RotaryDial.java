package phone.app;


import android.app.Activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;


/**
 * Rotaly_dial
 * @author kitamura
 *
 */
public class RotaryDial extends Activity {

	private RotaryDialView dialView;
	
	public static final String PARAM_DIAL_PERSON_NUMBER = "DIAL_PERSON_NUMBER";
	public static final String PARAM_DIAL_PERSON_NAME = "DIAL_PERSON_NAME";
	
	/** ���M���s�t���O 0=���M�����s���Ȃ�  1=DIAL_PERSON_NUMBER�̒l�Ŕ��M���s�� */
	public static final String PARAM_DIAL_CALL_FLG = "DIAL_CALL_FLG";
	
	/** ���C����ʂɖ߂��Ă����Ƃ��ɁA���̂܂܃_�C������ʂɑJ�ڂ��邩�ǂ����̃t���O
	 *  �d�b����ʂ���߂��Ă����ۂɁA�_�C������ʂɑJ�ڂ���Ƃ��Ɏg�p����
	 *  int 0=�������Ȃ� 1=�_�C������ʂɑJ�ڂ��� */
	public static final String PARAM_MOVE_TO_DIAL_MODE = "MOVE_TO_DIAL_MODE";
	
	/** int 0=normal 1=yutori */
	public static final String PARAM_YUTORI_MODE_FLG = "YUTORI_MODE_FLG";
	
	private MediaPlayer callingPlayer;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    	callingPlayer    = MediaPlayer.create(this, R.raw.dial1);       	
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        dialView = new RotaryDialView(this);
        dialView.setDialEventListener(new DialEventListener(){
        	@Override
        	public void openContactListView() {
        		openContactListActivity();
        	}
        	@Override
        	public void openDialView() {
        		openDialActivity();
        	}
        });
        setContentView(dialView);
    }

    /**
     * �R���^�N�g���X�g��ʂ֑J�ڂ���B
     * �R���^�N�g���X�gActivity���N��
     */
    private void openContactListActivity(){
    	Intent intent = new Intent(RotaryDial.this,ContactListActivity.class);
		startActivityForResult(intent, 0);
    }
    
    /**
     * �_�C������ʂ֑J�ڂ���B
     * �_�C�������Activity���N��
     */
    private void openDialActivity(){
    	Intent intent = new Intent(RotaryDial.this,DialActivity.class);
		startActivityForResult(intent, 0);
    }

    // ���ۂɓd�b���R�[������
    private void onAcutalCall(String phoneNumber) {

    	callingPlayer.setLooping(true);
   		callingPlayer.seekTo(0);
   		callingPlayer.start();
	    try{
	    	   Thread.sleep(1000);
	    	}catch(InterruptedException e){ }
   		callingPlayer.pause();

   		
   		
    	Intent callIntent = new 
    	Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));

    	callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(callIntent);
    }   
    
    /**
     * SubActity����̌��ʎ󂯎��
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d("phone", "onActivityResult");
    	if( resultCode == RESULT_OK ){
        	Log.d("phone", "RESULT_OK!");
    	}else{
        	Log.d("phone", "RESULT_NG!");
    	}
    	
    	if( data == null ){
    		return ;
    	}
		
    	// �d�b�ԍ��̎擾
    	String number;
    	number = data.getStringExtra(PARAM_DIAL_PERSON_NUMBER);
    	Log.d("phone","onActivityResult.NUMBER="+number);
    	
    	// �����鑊��̖��O�擾
    	String name;
    	name = data.getStringExtra(PARAM_DIAL_PERSON_NAME);
    	
    	if( name != null ){
        	Log.d("phone","NAME="+name);
    	}

    	if( data.getIntExtra(PARAM_MOVE_TO_DIAL_MODE, 0) == 1 ){
    		Intent intent = new Intent(RotaryDial.this,DialActivity.class);
    		intent.putExtra(PARAM_DIAL_PERSON_NAME, name);
    		intent.putExtra(PARAM_DIAL_PERSON_NUMBER, number);
			startActivityForResult(intent, 0);
    	}
    	
    	if( data.getIntExtra(PARAM_DIAL_CALL_FLG, 0) == 1 ){

    		Log.d("phone", "Debug : CALL!! number=" + number);
    		onAcutalCall(number);
    	}else{
    		Log.d("phone", "Debug : nothing ");
    	}
    }
}
