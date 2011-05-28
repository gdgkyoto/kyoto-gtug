package theWorld.AndroidHackathon.Kyoto;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

//�O���t�B�b�N�X�N���X(
// TODO OpenGL�ɏ���������
public class Graphics {
    private SurfaceHolder holder;
    private Paint         paint;
    private Canvas        canvas;

    //�O���t�B�b�N�X
    public Graphics(SurfaceHolder holder) {
        this.holder=holder;
        paint=new Paint();
        paint.setAntiAlias(true);
    }
    
    //���b�N
    public void lock() {
        canvas=holder.lockCanvas();
    }
    
    //�A�����b�N
    public void unlock() {
        holder.unlockCanvasAndPost(canvas);
    }
    
    //�F�̎w��
    public void setColor(int color) {
        paint.setColor(color);
    }

    //�t�H���g�T�C�Y�̎w��
    public void setFontSize(int fontSize) {
        paint.setTextSize(fontSize);
    }
    
    //�������̎擾
    public int stringWidth(String string) {
        return (int)paint.measureText(string);
    }

    //��`�̕`��
    public void fillRect(int x,int y,int w,int h) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(x,y,x+w,y+h),paint);
    }
    
    //�r�b�g�}�b�v�̕`��
    public void drawBitmap(Bitmap bitmap,int x,int y) {
        canvas.drawBitmap(bitmap,x,y,null);
    }
    
    //������̕`��
    public void drawString(String string,int x,int y) {
        canvas.drawText(string,x,y,paint);
    }
}
