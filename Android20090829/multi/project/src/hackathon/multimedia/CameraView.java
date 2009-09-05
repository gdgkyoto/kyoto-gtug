package hackathon.multimedia;

import android.content.Context;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.FileOutputStream;

//�J�����̐���
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;//�z���_�[
    private Camera        camera;//�J����

    //�R���X�g���N�^
    public CameraView(Context context) {
        super(context);
        
        //�T�[�t�F�C�X�z���_�[�̐���
        holder=getHolder();
        holder.addCallback(this);
        
        //�v�b�V���o�b�b�t�@�̎w��
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    //�T�[�t�F�C�X�����C�x���g�̏���
    public void surfaceCreated(SurfaceHolder holder) {
        //�J�����̏�����
    	try {
            camera=Camera.open();
            camera.setPreviewDisplay(holder);
    	} catch (Exception e) {
    	}
    }

    //�T�[�t�F�C�X�ύX�C�x���g�̏���
    public void surfaceChanged(SurfaceHolder holder,int format,int w,int h) {
        //�J�����̃v���r���[�J�n
        Camera.Parameters parameters=camera.getParameters();
        parameters.setPreviewSize(w,h);
        camera.setParameters(parameters);
        camera.startPreview();
    }    
    
    //�T�[�t�F�C�X����C�x���g�̏���
    public void surfaceDestroyed(SurfaceHolder holder) {
        //�J�����̃v���r���[��~
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera=null;
    }
    //�^�b�`�C�x���g�̏���
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN) {
            takePicture();
            camera.startPreview();
        }
        return true;
    }
    
    //�ʐ^�B�e
    public void takePicture() {
        //�J�����̃X�N���[���V���b�g�̎擾
        camera.takePicture(null,null,new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data,Camera camera) {
                try {
                    data2sd(getContext(),data,"test.jpg");
                } catch (Exception e) {
                    android.util.Log.e("",""+e.toString());
                }
            }
        }); 
    }

    //�o�C�g�f�[�^��SD�J�[�h
    private static void data2sd(Context context,
        byte[] w,String fileName) throws Exception {
        //SD�J�[�h�ւ̃f�[�^�ۑ�
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream("/sdcard/"+fileName);
            fos.write(w);
            fos.close();
        } catch (Exception e) {
            if (fos!=null) fos.close();
            throw e;
        }
    }
}