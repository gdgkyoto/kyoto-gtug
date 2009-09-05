package org.gtug.kyoto.android.devilear.item;

import java.io.File;
import java.io.IOException;

import org.gtug.kyoto.android.devilear.Item;

import android.util.Log;

public class ImageItem extends Item {

    private static String directory = "/sdcard/data/" + SoundItem.class.getPackage() + "/image";
    
    static {
        File dir = new File(directory);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("ImageItem", "Failed to create the image directory", new Exception("Failed to create the image directory"));
            }
        }
    }
    
    private String filePath;

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public void save() throws IOException {
        super.save();
        File file = new File(directory, getId().toString());
    }
}
