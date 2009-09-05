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
	    int center_1_x = width / 2 + rect.left;
	    int center_1_y = width / 2 + rect.top;
	    int center_2_x = player.width / 2 + player.rect.left;
	    int center_2_y = player.width / 2 + player.rect.top;
	    int diff_x = Math.abs(center_1_x - center_2_x);
	    int diff_y = Math.abs(center_1_y - center_2_y);
	    double diff = diff_x + diff_y;
	    if (diff <= width) {
	      if (diff_x > diff_y) {
	        if ((direction.dx > 0 && rect.left < player.rect.left)
	            || (direction.dx < 0 && rect.left > player.rect.left))
	          direction.dx = -1 * direction.dx;
	        if ((player.direction.getDx() > 0 && player.rect.left < rect.left)
	            || (player.direction.getDx() < 0 && player.rect.left > rect.left))
	        	player.direction.setDx(-1 * player.direction.getDx());
	      } else if (diff_x <= diff_y) {
	        if ((direction.dy > 0 && rect.top < player.rect.top)
	            || (direction.dy < 0 && rect.top > player.rect.top))
	          direction.dy = -1 * direction.dy;
	        if ((player.direction.getDy() > 0 && player.rect.top < rect.top)
	            || (player.direction.getDx() < 0 && player.rect.top > rect.top))
	        	player.direction.setDy(-1 * player.direction.getDy());
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
