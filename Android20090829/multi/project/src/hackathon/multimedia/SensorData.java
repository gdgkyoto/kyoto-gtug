package hackathon.multimedia;

public class SensorData {
    private float[] acceleration = new float[3];//加速度
    private float[] orientation = new float[3];//傾き
    private boolean flag = false;
    
    //加速度の指定
    public void setAcceleration(float[] acceleration) {
        this.acceleration = acceleration;
    }
    
    //傾きの指定
    public void setOrientation(float[] orientation) {
        this.orientation = orientation;
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
