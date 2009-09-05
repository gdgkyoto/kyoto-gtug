package org.kyotogtug.hackathon090905.games;

import java.net.ContentHandler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.lang.Math;

import org.kyotogtug.hackathon090905.games.R;

public class Player {
  protected int width;
  protected int height;
  private Rect viewRect;
  Drawable drawable;
  Drawable r1;
  Drawable r2;
  Drawable r3;
  Drawable l1;
  Drawable l2;
  Drawable l3;
  Drawable jump;
  
  Rect rect;
  Rect oldRect;
  protected Direction direction;
  private int status=0;

  private int preRoll =0;

  public Player(Drawable drawable, Rect viewRect, int x, int y, Context context) {
    this.drawable = drawable;
    this.width = drawable.getIntrinsicWidth();
    this.height = drawable.getIntrinsicHeight();
    this.oldRect = this.rect = new Rect(0, 0, width, height);
    this.viewRect = viewRect;
    r1 = context.getResources().getDrawable(R.drawable.player_r01);
    r2 = context.getResources().getDrawable(R.drawable.player_r02);
    r3 = context.getResources().getDrawable(R.drawable.player_r03);
    l1 = context.getResources().getDrawable(R.drawable.player_l01);
    l2 = context.getResources().getDrawable(R.drawable.player_l01);
    l3 = context.getResources().getDrawable(R.drawable.player_l01);
    jump = context.getResources().getDrawable(R.drawable.player_jump);
    rect.offset(x, y);
    direction = new Direction(0, 0);
  }

  public void move(int x, int y) {
	final int gravity = 1;	// 重力
	this.oldRect = this.rect;
	//横方向
	direction.dx = x;
	//縦方向
	//重力
	if (rect.bottom < viewRect.bottom) {
		if(direction.dy < 6) direction.dy = direction.dy + gravity;
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
    
    if(Math.abs(direction.dy) > 2){
    	status = 7;
    }else if(direction.dx > 0){
    	switch(status){
    	case 1:
    		status = 2;
    		break;
    	case 2:
    		status = 3;
    		break;
    	default:
    		status = 1;
    		break;
    	}
    }else if(direction.dx < 0){
    	switch(status){
    	case -1:
    		status = -2;
    		break;
    	case -2:
    		status = -3;
    		break;
    	default:
    		status = -1;
    		break;
    	}    	
    }else{
    	status = 0;
    }
    
    
  }

  public void draw(Canvas canvas) {
	  switch(status){
	  	case 7:
	  		jump.setBounds(rect);
	  	    jump.draw(canvas);
	  		break;
	  	case -1:
	  		l1.setBounds(rect);
	  		l1.draw(canvas);
	  		break;	    	
	  	case -2:
	  		l2.setBounds(rect);
	  		l2.draw(canvas);
	  		break;	    	
	  	case -3:
	  		r3.setBounds(rect);
	  		r3.draw(canvas);
	  		break;	    	
	  	case 1:
	  		r1.setBounds(rect);
	  		r1.draw(canvas);
	  		break;	    	
	  	case 2:
	  		r2.setBounds(rect);
	  		r2.draw(canvas);
	  		break;	    	
	  	case 3:
	  		r3.setBounds(rect);
	  		r3.draw(canvas);
	  		break;	    	
	  default:
	  		drawable.setBounds(rect);
	  	    drawable.draw(canvas);
	  		break;
	  }
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
