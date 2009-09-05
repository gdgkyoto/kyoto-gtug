package hackathon.multimedia;

import android.content.Context;
import android.graphics.*;

import java.io.FileOutputStream;
import  	java.nio.Buffer;
import java.io.*;

public class LargeImage {
	Bitmap largeImage;
	Buffer tempBuffer;
	Canvas canvas;
	
	public LargeImage() {
	}
	
	public void createImage( int width, int height )
	{
		largeImage = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565 );
		//canvas = new Canvas( largeImage );
		byte[] w=bmp2data(largeImage, Bitmap.CompressFormat.JPEG, 80);
			
        try {
        	data2sd( w, "result.jpg");
        } catch (Exception e) {
            android.util.Log.e("taniguchi",""+e.toString());
        }	
	}

	public void pasteImage()
	{
		
	}
	
    private static void data2sd(byte[] w,String fileName) throws Exception {
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

    //Bitmap→バイトデータ   http://www.saturn.dti.ne.jp/npaka/android/SnapShotEx/index.html
    private static byte[] bmp2data(Bitmap src, Bitmap.CompressFormat format,int quality) {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        src.compress(format,quality,os);            
        return os.toByteArray();
    }

}