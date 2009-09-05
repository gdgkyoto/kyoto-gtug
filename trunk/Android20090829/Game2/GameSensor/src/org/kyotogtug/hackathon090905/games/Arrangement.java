package org.kyotogtug.hackathon090905.games;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;


public class Arrangement {
	private List<int[]> arranges = new ArrayList<int[]>(10);


  public Arrangement() {
	  int[] a ={1,1,1,1,0,0,0,1,1,1};
	  int[] b ={2,2,0,0,2,2,2,0,1,1};
	  int[] c ={0,0,1,1,1,1,0,1,1,0};
	  int[] d ={0,2,2,2,0,2,2,2,0,0};
	  int[] e ={0,2,0,2,2,2,2,0,2,0};
	  int[] f ={0,1,1,1,1,0,0,1,1,1};
	  int[] g ={1,1,0,0,1,0,0,0,1,1};
	  int[] h ={0,0,1,1,0,0,1,1,1,0};
	  int[] i ={1,0,1,1,1,1,0,1,0,1};
	  int[] j ={1,1,1,1,1,1,0,1,1,1};
	  arranges.add(a);
	  arranges.add(b);
	  arranges.add(c);
	  arranges.add(d);
	  arranges.add(e);
	  arranges.add(f);
	  arranges.add(g);
	  arranges.add(h);
	  arranges.add(i);
	  arranges.add(j);
  }

  public int[] getArrangement() {
	  Random random = new Random();
	  int[] arrange= arranges.get(random.nextInt(10));
	  return arrange;
  }
}