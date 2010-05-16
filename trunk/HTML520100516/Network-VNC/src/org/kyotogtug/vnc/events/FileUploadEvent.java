package org.kyotogtug.vnc.events;

public class FileUploadEvent extends Event {
	
	/** ファイルの内容のbase64 */
	private String base64data;

	public String getBase64data() {
		return base64data;
	}

	public void setBase64data(String base64data) {
		this.base64data = base64data;
	}
	
	

}
