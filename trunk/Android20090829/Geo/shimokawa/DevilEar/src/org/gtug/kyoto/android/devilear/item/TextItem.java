package org.gtug.kyoto.android.devilear.item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.gtug.kyoto.android.devilear.Item;

import android.content.Context;
import android.util.Log;

public class TextItem extends Item {

    //private static String directory = "/data/data/" + SoundItem.class.getPackage() + "/text";
    private static String directory = "/sdcard/data/" + SoundItem.class.getPackage().getName() + "/text";
    
    static {
        File dir = new File(directory);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("TextItem", "Failed to create the text directory", new Exception("Failed to create the text directory"));
            }
        }
    }
    
    private String mText;
    
    public TextItem() {
        setType("text");
    }
    
    /**
     * @return the text
     */
    public String getText() {
        return mText;
    }
    /**
     * @param text the text to set
     */
    public void setText(String text) {
        mText = text;
    }
    
    @Override
    public void save() throws IOException {
        if (mText == null) {
            return;
        }
        super.save();
        
        File file = new File(directory, getId().toString() + ".txt");
        
        FileOutputStream fout = null;
        BufferedWriter writer = null;
        try {
            //fout = mContext.openFileOutput(file.getPath(), Context.MODE_WORLD_READABLE);
            fout = new FileOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(fout));
            writer.write(mText);
            writer.flush();
        } finally {
            if (writer != null) writer.close();
            if (fout != null) fout.close();
        }
    }
    
    
}
