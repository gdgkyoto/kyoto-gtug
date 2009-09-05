package hackathon.multimedia;

import android.content.Context;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.FileOutputStream;

//カメラの制御
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;//ホルダー
    private Camera        camera;//カメラ
    private int           count;

    //コンストラクタ
    public CameraView(Context context) {
        super(context);
        
        //サーフェイスホルダーの生成
        holder=getHolder();
        holder.addCallback(this);
        
        //プッシュバッッファの指定
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    //サーフェイス生成イベントの処理
    public void surfaceCreated(SurfaceHolder holder) {
        //カメラの初期化
    	try {
            camera=Camera.open();
            camera.setPreviewDisplay(holder);
    	} catch (Exception e) {
    	}
    }

    //サーフェイス変更イベントの処理
    public void surfaceChanged(SurfaceHolder holder,int format,int w,int h) {
        //カメラのプレビュー開始
        Camera.Parameters parameters=camera.getParameters();
        parameters.setPreviewSize(w,h);
        camera.setParameters(parameters);
        camera.startPreview();
    }    
    
    //サーフェイス解放イベントの処理
    public void surfaceDestroyed(SurfaceHolder holder) {
        //カメラのプレビュー停止
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera=null;
    }
    //タッチイベントの処理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN) {
            takePicture();
            camera.startPreview();
        }
        return true;
    }
    
    //写真撮影
    public void takePicture() {
        //カメラのスクリーンショットの取得
        camera.takePicture(null,null,new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data,Camera camera) {
                try {
                	String fn = "test"+String.valueOf(count)+".jpg";
                    data2sd(getContext(),data,fn);
                    count++;
                    android.util.Log.v("",fn);
                } catch (Exception e) {
                    android.util.Log.e("",""+e.toString());
                }
            }
        }); 
        
    }

    //バイトデータ→SDカード
    private static void data2sd(Context context,
        byte[] w,String fileName) throws Exception {
        //SDカードへのデータ保存
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

