package theWorld.AndroidHackathon.Kyoto;

import android.graphics.Bitmap;

public class Character {
	
	    //�C���[�W
	    public static Bitmap[] bmp=new Bitmap[3];

	    //�ϐ�
	    public int state;//(4)
	    public int life;
	    public int offence;
	    
	    
	    //�R���X�g���N�^
	    public character(int life) {
	        this.life=life;
	        this.state=1;
	    }
	    
	    
	    
	    //���
	    public void tick() {
	        state++;
	        if (state==19 || state==39) {
	            state=-MoguraView.rand(500);
	        }
	    }
	    
	    
	

}
