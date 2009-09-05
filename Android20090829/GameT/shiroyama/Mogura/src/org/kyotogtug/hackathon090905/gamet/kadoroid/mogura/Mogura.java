package org.kyotogtug.hackathon090905.gamet.kadoroid.mogura;

import java.util.ArrayList;
import java.util.List;

import org.kyotogtug.hackathon090905.gamet.kadoroid.mogura.KaodroidGameUtil.Image;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

/** メインクラス */
public class Mogura extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MoguraView v = new MoguraView(getApplication(), this);
		setContentView(v);
	}
}

/** 画面再描画の仕組みを提供するクラス */
class RedrawHandler extends Handler {
	private View view;
	private int delayTime;
	private int frameRate;

	public RedrawHandler(View view, int frameRate) {
		this.view = view;
		this.frameRate = frameRate;
	}

	public void start() {
		this.delayTime = 1000 / frameRate;
		this.sendMessageDelayed(obtainMessage(0), delayTime);
	}

	public void stop() {
		delayTime = 0;
	}

	@Override
	public void handleMessage(Message msg) {
		view.invalidate();
		if (delayTime == 0)
			return; // stop
		sendMessageDelayed(obtainMessage(0), delayTime);
	}
}

/** 描画用のView */
class MoguraView extends View {
	public int dispX = 480;
	public int dispY = 640;
	private int frame = 0;
	private int score = 0;
	private int monsterSpeed = 4;
	private ArrayList<Monster> monsters;
	private Bitmap bmpMogura;
	private Bitmap bmpMoguraHit;
	private List<Image> imageList;

	public MoguraView(Context c, Activity a) {
		super(c);
		setFocusable(true);
		Resources r = c.getResources();

		if (KaodroidGameUtil.getInstance().roadGroup(a) == null) {
			bmpMogura = BitmapFactory.decodeResource(r, R.drawable.ikeda1);
			bmpMoguraHit = BitmapFactory.decodeResource(r, R.drawable.ikeda2);
		} else {
			imageList = KaodroidGameUtil.getInstance().roadGroup(a).getImages();
			int randomIndex = (int) Math.random() * (imageList.size() + 1);
			bmpMogura = imageList.get(randomIndex).getBitmap();
			bmpMoguraHit = bmpMogura; // 手抜き。本当なら bmpMoguraを加工して、ヒット時の画像を出す。
		}

		monsters = new ArrayList<Monster>();
		RedrawHandler handler = new RedrawHandler(this, 8);
		handler.start();
	}

	/** 画面サイズが変更されたときに呼び出されるメソッド */
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		dispX = w;
		dispY = h;
	}

	protected void onDraw(Canvas canvas) {
		// 画面を白で初期化
		canvas.drawColor(Color.WHITE);
		// モグラ(Monster)の描画
		Paint mpaint = new Paint();
		mpaint.setColor(Color.RED);
		mpaint.setStyle(Paint.Style.FILL);
		int i = 0;
		while (i < monsters.size()) {
			Monster p = monsters.get(i);
			if (p.anime > 0) {
				p.anime++;
				if (p.anime > 3) {
					monsters.remove(i);
					continue;
				}
				canvas.drawBitmap(bmpMoguraHit, p.x, p.y, null);
			} else {
				p.move();
				canvas.drawBitmap(bmpMogura, p.x, p.y, null);
			}
			i++;
		}
		// 新しいモグラを作るかどうか
		if (frame % 20 == 0) {
			if (monsters.size() < 50) {
				monsters.add(new Monster(this, monsterSpeed));
				monsterSpeed += 2;
			}
		}
		// Message の描画
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		String msg = "Score:" + score + ", Mogura:" + (monsters.size())
				+ ", Frame:" + (frame++);
		canvas.drawText(msg, 2, 30, paint);
	}

	/** タッチイベントの処理 */
	public boolean onTouchEvent(MotionEvent event) {
		int tx = (int) event.getX();
		int ty = (int) event.getY();
		// モグラにあたったかどうか判定
		int i = 0;
		while (i < monsters.size()) {
			Monster p = monsters.get(i);
			if (p.hitTest(tx, ty)) {
				p.anime++;
				score++;
			}
			i++;
		}
		return true;
	}
}

/** モグラクラスの定義 */
class Monster {
	public int x;
	public int y;
	public int w = 76;
	public int h = 100;
	protected int speed;
	protected int dirX;
	protected int dirY;
	protected MoguraView view;
	protected int anime = 0;

	public Monster(MoguraView view, int speed) {
		this.view = view;
		this.speed = speed;
		dirX = (int) Math.round(Math.random() * 3 - 1);
		dirY = (int) Math.round(Math.random() * 3 - 1);
		x = (int) Math.floor(Math.random() * view.dispX);
		y = (int) Math.floor(Math.random() * view.dispY);
	}

	public void move() {
		x += speed * dirX; // 単純に移動
		y += speed * dirY;
		x = Math.min(view.dispX, Math.max(0, x));
		y = Math.min(view.dispY, Math.max(0, y));
		if (Math.random() > 0.7) { // 動く向きを変換する
			dirX = (int) Math.round(Math.random() * 3 - 1);
			dirY = (int) Math.round(Math.random() * 3 - 1);
		}
	}

	/** 当たり判定 */
	public boolean hitTest(int tx, int ty) {
		return (x <= tx && tx < (x + w) && y <= ty && ty < (ty + h));
	}

	public Rect getRect() {
		return new Rect(x, y, x + w, y + h);
	}
}