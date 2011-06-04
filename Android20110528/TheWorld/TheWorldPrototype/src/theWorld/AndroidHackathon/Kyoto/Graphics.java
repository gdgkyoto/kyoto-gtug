package theWorld.AndroidHackathon.Kyoto;

/**
 * ※このクラスは未使用 
 *
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

//グラフィックスクラス(
// TODO OpenGLに書き換える
public class Graphics {
    private SurfaceHolder holder;
    private Paint         paint;
    private Canvas        canvas;

    //グラフィックス
    public Graphics(SurfaceHolder holder) {
        this.holder=holder;
        paint=new Paint();
        paint.setAntiAlias(true);
    }
    
    //ロック
    public void lock() {
        canvas=holder.lockCanvas();
    }
    
    //アンロック
    public void unlock() {
        holder.unlockCanvasAndPost(canvas);
    }
    
    //色の指定
    public void setColor(int color) {
        paint.setColor(color);
    }

    //フォントサイズの指定
    public void setFontSize(int fontSize) {
        paint.setTextSize(fontSize);
    }
    
    //文字幅の取得
    public int stringWidth(String string) {
        return (int)paint.measureText(string);
    }

    //矩形の描画
    public void fillRect(int x,int y,int w,int h) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(x,y,x+w,y+h),paint);
    }
    
    //ビットマップの描画
    public void drawBitmap(Bitmap bitmap,int x,int y) {
        canvas.drawBitmap(bitmap,x,y,null);
    }
    
    //文字列の描画
    public void drawString(String string,int x,int y) {
        canvas.drawText(string,x,y,paint);
    }
}
