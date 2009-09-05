package hackathon.multimedia;

import android.graphics.*;

import java.io.FileOutputStream;
import java.nio.Buffer;
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
		canvas = new Canvas( largeImage );		
	}

	public void catImage()
	{
		canvas.drawBitmap(BitmapFactory.decodeFile("test0.jpg"), 0.0f, 0.0f, null);
		canvas.drawBitmap(BitmapFactory.decodeFile("test1.jpg"), 2048.0f, 0.0f, null);

		byte[] w=bmp2data(largeImage, Bitmap.CompressFormat.JPEG, 80);
        try {
        	data2sd( w, "cat.jpg");
        } catch (Exception e) {
            android.util.Log.e("taniguchi",""+e.toString());
        }	
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