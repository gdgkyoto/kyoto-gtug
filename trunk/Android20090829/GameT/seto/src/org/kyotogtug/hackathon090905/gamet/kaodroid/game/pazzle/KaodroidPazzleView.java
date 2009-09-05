package org.kyotogtug.hackathon090905.gamet.kaodroid.game.pazzle;
import android.app.Activity;
import android.os.Bundle;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;


public class KaodroidPazzleView extends View {
	private Random rand = new Random();
	private Bitmap image;
	private int[] data = new int[25];
	
	public KaodroidPazzleView(Context context)
	{
		super(context);
		setBackgroundColor(Color.WHITE);
		
		for (int i=0; i<25; i++)
		{
			data[i] = i;
		}
//		loadPicture(0);
		
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	
	Activity hoge = null;
	
	public void setActivity(Activity act)
	{
		hoge = act;
	}
	
	
	int loadPicture(int id)
	{
		Context context = getContext();
		Resources r = context.getResources();
		int resID = R.drawable.android;
//		image = BitmapFactory.decodeResource(r, resID);
//		image = KaodroidGameUtil.getInstance().roadGroup(hoge).getImages().get(id).getBitmap();
		image = KaodroidGameUtil.getInstance().roadGroup(hoge).getImages().get(id).getBitmap();
		image = Bitmap.createScaledBitmap(image, 300, 300, false);
		int images_num = KaodroidGameUtil.getInstance().roadGroup(hoge).getImages().size();
		
		int[] DIR = {
				KeyEvent.KEYCODE_DPAD_UP,
				KeyEvent.KEYCODE_DPAD_DOWN,
				KeyEvent.KEYCODE_DPAD_LEFT,
				KeyEvent.KEYCODE_DPAD_RIGHT,
		};
		for (int i=99; i>=0; i--)
		{
			movePiece(DIR[(rand.nextInt()>>>1)%4]);
		}
		invalidate();
		
		return images_num;
	}
	
	private void movePiece(int key)
	{
		int freeIdx = 0;
		for ( ; freeIdx<25; freeIdx++ )
		{
			if (data[freeIdx] == 24)
			{
				break;
			}
		}
		
		if (key == KeyEvent.KEYCODE_DPAD_UP && freeIdx/5 < 4)
		{
			data[freeIdx] = data[freeIdx+5];
			data[freeIdx+5] = 24;
		}
		else if (key == KeyEvent.KEYCODE_DPAD_DOWN && freeIdx/5 > 0)
		{
			data[freeIdx] = data[freeIdx-5];
			data[freeIdx-5] = 24;
		}
		else if (key == KeyEvent.KEYCODE_DPAD_LEFT && freeIdx%5 < 4)
		{
			data[freeIdx] = data[freeIdx+1];
			data[freeIdx+1] = 24;
		}
		else if (key == KeyEvent.KEYCODE_DPAD_RIGHT && freeIdx%5 > 0)
		{
			data[freeIdx] = data[freeIdx-1];
			data[freeIdx-1] = 24;
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		boolean complete = true;
		for (int i=0; i<25; i++)
		{
			if (data[i] != i)
			{
				complete = false;
			}
		}
		
		Paint paint = new Paint();

		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.argb(255, 0, 0, 0));
		canvas.drawRect(
				new Rect(0, 0, getWidth(), getHeight()), paint
		);

		int bx = (getWidth()-300)/2;
		int by = (getHeight()-300)/2;

		paint.setColor(Color.argb(255, 100, 100, 100));
		canvas.drawRect(
				new Rect(bx, by, bx+300, by+300), paint
		);
		
		for (int i=0; i<25; i++)
		{
			int sx = data[i] % 5;
			int sy = data[i] / 5;
			int dx = i % 5;
			int dy = i / 5;
			if (complete || data[i]!=24)
			{
				canvas.drawBitmap(image,
						new Rect(60*sx, 60*sy, 60*sx+60, 60*sy+60),
						new Rect(bx+60*dx, by+60*dy, bx+60*dx+60, by+60*dy+60),
				null);
			}
			if (!complete)
			{
				paint.setColor(Color.argb(255, 255, 255, 255));
				paint.setStyle(Paint.Style.STROKE);
				canvas.drawRect(new Rect(bx+60*dx, by+60*dy, bx+60*dx+60, by+60*dy+60), paint);
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() != MotionEvent.ACTION_DOWN)
		{
			return true;
		}
		int touchX = (int)(event.getX()-(getWidth()-300)/2)/60;
		int touchY = (int)(event.getY()-(getHeight()-300)/2)/60;
		if (touchX < 0 || 5 <= touchX) return true;
		if (touchY < 0 || 5 <= touchY) return true;
		
		int freeX = 0;
		int freeY = 0;
		for (int i=0; i<25; i++)
		{
			if (data[i] == 24)
			{
				freeX = i % 5;
				freeY = i / 5;
				break;
			}
		}
		
		if (touchX == freeX)
		{
			if (freeY < touchY)
			{
				for (int i=freeY; i<touchY; i++)
				{
					movePiece(KeyEvent.KEYCODE_DPAD_UP);
				}
			}
			else if (freeY > touchY)
			{
				for (int i=freeY; i>touchY; i--)
				{
					movePiece(KeyEvent.KEYCODE_DPAD_DOWN);
				}	
			}
		}
		else if (touchY == freeY)
		{
			if (freeX < touchX)
			{
				for (int i=freeX; i<touchX; i++)
				{
					movePiece(KeyEvent.KEYCODE_DPAD_LEFT);
				}
			}
			else if (freeX > touchX)
			{
				for (int i=freeX; i>touchX; i--)
				{
					movePiece(KeyEvent.KEYCODE_DPAD_RIGHT);
				}	
			}
		}
		
		invalidate();
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		movePiece(keyCode);
		invalidate();
		return super.onKeyDown(keyCode, event);
	}
};
