package org.gtug.kyoto.android.devilear.item;

import java.io.File;
import java.io.IOException;

import org.gtug.kyoto.android.devilear.Item;
import org.gtug.kyoto.android.devilear.ItemsDbAdapter;

import android.util.Log;

public class SoundItem extends Item {
    
    private static String directory = "/sdcard/data/" + SoundItem.class.getPackage() + "/sound";
    
    static {
        File dir = new File(directory);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("SoundItem", "Failed to create the sound directory", new Exception("Failed to create the sound directory"));
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
    }
}
