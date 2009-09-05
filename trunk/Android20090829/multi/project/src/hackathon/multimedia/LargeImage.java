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
	}

	public void catImage()
	{
		try {
			Paint paint = new Paint();
			largeImage = Bitmap.createBitmap(2048, 1536/2, Bitmap.Config.RGB_565 );
			canvas = new Canvas( largeImage );	
		Rect srcRect = new Rect(0, 0, 2047, 1535);
		Rect destRect = new Rect(0, 0, 2048 / 2, 1536 / 2);
		Bitmap bmp0 = BitmapFactory.decodeFile("test0.jpg");
		canvas.drawBitmap(bmp0, srcRect, destRect, paint);
//		destRect.left = 2048 / 2;
//		canvas.drawBitmap(BitmapFactory.decodeFile("test1.jpg"), srcRect, destRect, paint);
		} catch (Exception e) {
            android.util.Log.e("taniguchi00000",""+e.toString());
        }
		
		byte[] w=bmp2data(largeImage, Bitmap.CompressFormat.JPEG, 80);
        try {
        	data2sd( w, "cat.jpg");
        } catch (Exception e) {
            android.util.Log.e("taniguchi0001",""+e.toString());
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