package org.kyotogtug.vnc;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.kyotogtug.vnc.events.Event;
import org.kyotogtug.vnc.events.FileEvent;
import org.kyotogtug.vnc.events.ImageEvent;
import org.kyotogtug.vnc.events.KeyEvent;
import org.kyotogtug.vnc.events.MouseMoveEvent;

public class EventProcessor {
    
    static private Robot robot;
    
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
	public void handleEvent(Event event ){
	    if (event instanceof FileEvent) {
	        if (event.getEventType().equals("FILE_DOWNLOAD")) {
	            
	        } else if (event.getEventType().equals("FILE_UPLOAD")) {
	            byte[] data = Base64.decodeBase64(event.getData());
	            FileOutputStream fout = null;
	            try {
                    fout = new FileOutputStream("");
                    fout.write(data);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
	        }
	    } else if (event instanceof ImageEvent) {
	        // Respond screen image?
	    } else if (event instanceof KeyEvent) {
	        Integer keyCode = Integer.parseInt(event.getData());
	        robot.keyPress(keyCode);
	    } else if (event instanceof MouseMoveEvent) {
	        String[] params = event.getData().split(",");
	        int x = Integer.parseInt(params[0]);
	        int y = Integer.parseInt(params[1]);
	        //int buttonId = Integer.parseInt(params[2]);
	        robot.mouseMove(x, y);
	    }
	}

}
