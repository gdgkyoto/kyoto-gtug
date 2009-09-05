package org.kyotogtug.hackathon090905.games;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Player {
  protected int width;
  protected int height;
  private Rect viewRect;
  Drawable drawable;
  Rect rect;
  protected Direction direction;

  private int preRoll =0;

  public Player(Drawable drawable, Rect viewRect, int x, int y) {
    this.drawable = drawable;
    this.width = drawable.getIntrinsicWidth();
    this.height = drawable.getIntrinsicHeight();
    this.rect = new Rect(0, 0, width, height);
    this.viewRect = viewRect;
    rect.offset(x, y);
    direction = new Direction(0, 0);
  }

  public void move(int x, int y) {
	final int gravity = 1;	// 重力
	
	//横方向
	direction.dx = x;
	//縦方向
	//重力
	if (rect.bottom < viewRect.bottom) {
		direction.dy = direction.dy + gravity;
	}
	//ジャンプ
	if (direction.dy == 0) {
		if(y > 10){
			direction.dy = -10 * 2;
		}else
		if (y>3) {
			direction.dy = -y*2;
		}
	}
	
	rect.offset(direction.dx, direction.dy);
    if (rect.left < 0) {
    	direction.dx = 0;
        rect.offsetTo(viewRect.left, rect.top);
    }
    if (rect.top < 0) {
    	//ここには画面スクロールの処理が必要か？
    	direction.dy = 0;
        rect.offsetTo(rect.left, viewRect.top);
    }
    if (rect.bottom > viewRect.bottom) {
      direction.dy = 0;
      rect.offsetTo(rect.left, viewRect.bottom-height);
    }
    if (rect.right > viewRect.right) {
      direction.dx = 0;
      rect.offsetTo(viewRect.right-width, rect.top);
    }
  }

  public void draw(Canvas canvas) {
	drawable.setBounds(rect);
    drawable.draw(canvas);
  }

  class Direction {
	private int dx;
	private int dy;

    private Direction(int dx, int dy) {
      this.dx = dx;
      this.dy = dy;
    }
    protected int getDx(){
    	return dx;
    }
    protected void setDx(int dx){
    	this.dx = dx;
    }
    protected int getDy(){
    	return dy;
    }
    protected void setDy(int dy){
    	this.dy = dy;
    }
  }
}
