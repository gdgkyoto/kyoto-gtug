package org.kyotogtug.vnc.events;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class ScreenCapturer extends Thread {

    private byte[] imageData;
    private static File file = new File("screen.jpg");

    private long interval = 500;

    public ScreenCapturer(long interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (true) {
                updateScreenData();
                Thread.sleep(interval);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized private void updateScreenData() throws IOException,
            AWTException {
        imageData = captureScreen();
    }

    synchronized private byte[] captureScreen() throws IOException, AWTException {
        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BufferedImage img = robot
                .createScreenCapture(new Rectangle(screenSize));
        ImageIO.write(img, "jpg", file);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        return bytes;
    }

    synchronized public byte[] getImageData() {
        return imageData;
    }
    
    synchronized public String getBase64ImageData() {
        return Base64.encodeBase64String(imageData);
    }

}
