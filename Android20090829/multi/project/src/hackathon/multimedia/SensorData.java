package hackathon.multimedia;

import android.util.Log;

public class SensorData {
    private float[] acceleration = new float[3];//加速度
    private float[] orientation = new float[3];//傾き
    private boolean flag;

    public SensorData() {
    	flag = false;
    }
    
    //加速度の指定
    public void setAcceleration(float[] acceleration) {
        this.acceleration = acceleration;

        Log.e("acceleration",
                String.valueOf(this.acceleration[0]) + ", " +
                String.valueOf(this.acceleration[1]) + ", " +
                String.valueOf(this.acceleration[2]));
    }
    
    //傾きの指定
    public void setOrientation(float[] orientation) {
        this.orientation = orientation;

        Log.e("orientation",
                String.valueOf(this.orientation[0]) + ", " +
                String.valueOf(this.orientation[1]) + ", " +
                String.valueOf(this.orientation[2]));
    }

    public float[] getAcceleration() {
    	return this.acceleration; 
    }

    public float[] getOrientation() {
    	return this.orientation; 
    }

    public void setFlag(boolean flag) {
    	this.flag = flag;
    }

    public boolean getFlag() {
    	return this.flag;
    }
}
