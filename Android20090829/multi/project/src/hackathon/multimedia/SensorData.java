package hackathon.multimedia;

public class SensorData {
    private float[] acceleration = new float[3];//�����x
    private float[] orientation = new float[3];//�X��
    private boolean flag = false;
    
    //�����x�̎w��
    public void setAcceleration(float[] acceleration) {
        this.acceleration = acceleration;
    }
    
    //�X���̎w��
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
