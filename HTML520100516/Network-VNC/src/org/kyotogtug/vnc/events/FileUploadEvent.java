package org.kyotogtug.vnc.events;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class FileUploadEvent extends Event {
	
	/** ファイルの内容のbase64 */
	private String base64data;
	private String filename;

	public String getBase64data() {
		return base64data;
	}

	public void setBase64data(String base64data) {
		this.base64data = base64data;
	}

    public void setFileName(String filename) {
        this.filename = filename;
    }
	
    public String getFileName() {
        return filename;
    }

    public void save(String fileName) {
        if (base64data == null) {
            return;
        }
        byte[] data = Base64.decodeBase64(base64data);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(fileName);
            fout.write(data);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
