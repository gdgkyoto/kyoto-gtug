package org.kyotogtug.hackathon090905.games;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class Block {
	  private int width;
	  private int height;
	  private Rect viewRect;
	  Drawable drawable;
	  Rect rect;
	  Direction direction;

	  public Block(Drawable drawable, Rect viewRect, int x, int y) {
	    this.drawable = drawable;
	    this.width = drawable.getIntrinsicWidth();
	    this.height = drawable.getIntrinsicHeight();
	    this.rect = new Rect(0, 0, width, height);
	    this.viewRect = viewRect;
	    rect.offset(x, y);
	    Random random = new Random();
	    direction = new Direction(random.nextInt(5) + 1, random.nextInt(5) + 1);
	  }

	  public void move() {
	    rect.offset(direction.dx, direction.dy);
	    if (rect.left < 0) {
	      direction.dx = -direction.dx;
	      rect.left = 0;
	      rect.right = rect.left + width;
	    } else if (rect.top < 0) {
	      direction.dy = -direction.dy;
	      rect.top = 0;
	      rect.bottom = rect.top + height;
	    } else if (rect.bottom > viewRect.bottom) {
	      direction.dy = -direction.dy;
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

	  public void changeDirectionIfEncountered(Player player) {
	    int center_1_x = rect.centerX();
	    int center_1_y = rect.centerY();
	    int center_2_x = player.rect.centerX();
	    int center_2_y = player.rect.centerY();
	    int diff_x = Math.abs(center_1_x - center_2_x);
	    int diff_y = Math.abs(center_1_y - center_2_y);
	    
	    if (diff_x <= (width + player.width)/2 && diff_y <= (height + player.height)/2) {
			  if(player.oldRect.bottom < rect.top ){
				  player.rect.offsetTo(player.rect.left, rect.top-player.height-1);
				  player.direction.setDy(0);
			  }
			  else if(player.oldRect.top > rect.bottom){
				  player.rect.offsetTo(player.rect.left, rect.bottom+1);
				  player.direction.setDy(0);
			  }
			  else if(player.oldRect.left > rect.right){
				  player.rect.offsetTo(rect.right+1, player.rect.top);
			  }
			  else if(player.oldRect.right < rect.left){
				  player.rect.offsetTo(rect.left-player.width-1, player.rect.top);
			  }
	    }
		  
	  }

	  class Direction {
	    private int dx;
	    private int dy;

	    private Direction(int dx, int dy) {
	      this.dx = dx;
	      this.dy = dy;
	    }
	  }
	}
