package org.gtug.kyoto.android.devilear.item;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.gtug.kyoto.android.devilear.Item;
import org.gtug.kyoto.android.devilear.ItemsDbAdapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class TextItem extends Item {

    //private static String directory = "/data/data/" + SoundItem.class.getPackage() + "/text";
    //private static String directory = "/sdcard/data/" + SoundItem.class.getPackage().getName() + "/text";
    private static String directory = "/sdcard/data/text";
    
    static {
        File dir = new File(directory);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("TextItem", "Failed to create the text directory", new Exception("Failed to create the text directory"));
            }
        }
    }
    
    public static TextItem findById(Activity activity, long itemId) {
        Cursor cursor = mDbAdapter.fetchItem(itemId);
        activity.startManagingCursor(cursor);
        return cursorToItem(cursor);
    }
    
    private static TextItem cursorToItem(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ROWID));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_TYPE));
        Long iconId = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ICON_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ICON_ID));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_DESCRIPTION));
        Long date = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_DATE));
        double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_LATITUDE));
        double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_LONGITUDE));

        TextItem item = new TextItem();
        item.setId(id);
        item.setType(type);
        item.setIconId(iconId);
        item.setName(name);
        item.setDescription(description);
        item.setDate(date);
        item.setLatitude(latitude);
        item.setLongitude(longitude);
        
        File file = new File(directory, "" + id + ".txt");
        FileInputStream fin = null;
        BufferedReader reader = null;
        
        try {
            fin = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(fin));
            String line = null;
            StringBuffer buf = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buf.append(line);
            }
            item.setText(buf.toString());
            return item;
        } catch (FileNotFoundException e) {
            Log.e("TextItem", "No Text Data!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("TextItem", e.getMessage());
        }
        
        return null;
    }

    
    private String mText;
    
    public TextItem() {
        setType("text");
    }
    
    public TextItem(Properties prop) {
        super(prop);
        setType("text");
        mText = prop.getProperty("text");
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
