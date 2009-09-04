package kgtug.android.geolocation;

public class Row {
	private String text;
	private int resid;
	Row(String tx, int id){  text = tx;  resid = id; }
	public int getDwId() {  return resid;   }
	public String getText() {  return text;     }
	public boolean isSelectable() {  return true;  }
}