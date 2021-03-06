package org.kyotogtug.hackathon090905.games.kitagawaSample;

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

  public Player(Drawable drawable, Rect viewRect, int x, int y) {
    this.drawable = drawable;
    this.width = drawable.getIntrinsicWidth() / 2;
    this.height = drawable.getIntrinsicHeight() / 2;
    this.rect = new Rect(0, 0, width, height);
    this.viewRect = viewRect;
    rect.offset(x, y);
    direction = new Direction(0, 0);
  }

  public void move() {
	if (rect.bottom < viewRect.bottom) {
		direction.dy = direction.dy+1;
	}

	rect.offset(direction.dx, direction.dy);
    if (rect.left < 0) {
    	direction.dx = -direction.dx;
    	rect.left = 0;
    	rect.right = rect.left + width;
    } else if (rect.top < 0) {
    	//ここには画面スクロールの処理が必要か？
    	direction.dy = -direction.dy;
    	rect.top = 0;
    	rect.bottom = rect.top + height;
    } else if (rect.bottom > viewRect.bottom) {
      direction.dy = 0;
      rect.bottom = viewRect.bottom;
      rect.top = rect.bottom - height;
    } else if (rect.right > viewRect.right) {
      direction.dx = -direction.dx;
      rect.right = viewRect.right;
      rect.left = rect.right - width;
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
