package org.kyotogtug.vnc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.jetty.websocket.WebSocket.Outbound;
import org.kyotogtug.vnc.events.Event;
import org.kyotogtug.vnc.events.FileEvent;
import org.kyotogtug.vnc.events.ImageEvent;
import org.kyotogtug.vnc.events.ImageRequestEvent;
import org.kyotogtug.vnc.events.KeyEvent;
import org.kyotogtug.vnc.events.MousePressEvent;
import org.kyotogtug.vnc.events.MouseReleaseEvent;
import org.kyotogtug.vnc.events.MouseMoveEvent;
import org.kyotogtug.vnc.events.ScreenCapturer;

public class EventProcessor {
    
    static private Robot robot;
    static private ScreenCapturer capturer;
    
    /** get log instance */
	private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
			.getLog(EventProcessor.class);
    
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        startCapturing();
    }
    
    public static void startCapturing() {
        if (capturer == null) {
            capturer = new ScreenCapturer(100);
        }
        if (!capturer.isAlive()) {
            capturer.start();
        }
    }
    
    private Outbound outbound;
    
    public void setOutbound(Outbound outbound) {
        this.outbound = outbound;
    }
    
    
	public void handleEvent(Event event ){
	    if (event instanceof FileEvent) {
	        //FileEvent ev = (FileEvent)event;
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
        } else if (event instanceof ImageRequestEvent) {
            // 
            if (capturer.isAlive()) {
                sendScreenImage();
            }
	    } else if (event instanceof KeyEvent) {
	        KeyEvent ev = (KeyEvent)event;
	        Integer keyCode = Integer.parseInt(ev.getData());
	        robot.keyPress(keyCode);
	    } else if (event instanceof MouseReleaseEvent) {
	        MouseReleaseEvent e = (MouseReleaseEvent)event;
	        robot.mouseMove(e.getX(), e.getY());
	        robot.mouseRelease(toRobotButtonId(e.getButton()));
	    } else if (event instanceof MousePressEvent ){
	    	MousePressEvent e = (MousePressEvent)event;
	        robot.mouseMove(e.getX(), e.getY());
	        robot.mousePress(toRobotButtonId(e.getButton()));
	    } else if (event instanceof MouseMoveEvent) {
	        MouseMoveEvent ev = (MouseMoveEvent)event;
	        robot.mouseMove(ev.getX(), ev.getY());
	    }
	}
	

    private int toRobotButtonId(int button) {
		switch( button ){
		case 0: // 左クリック 
			log.debug("左クリック");
			return InputEvent.BUTTON1_MASK;
		case 1: // ホイールクリック
			log.debug("ホイールクリック");
			return InputEvent.BUTTON2_MASK;
		case 2: // 右クリック
			log.debug("右クリック");
			return InputEvent.BUTTON3_MASK;
		default:
			log.warn("未知のボタンを受信しました。マウスボタン="+button);
			break;
		}
		return -1;
    }


    private void sendScreenImage() {
        String base64Image = capturer.getBase64ImageData();
        String data = String.format("IMAGE|0|%d|%s", new Date().getTime(), base64Image);
        try {
            outbound.sendMessage(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
