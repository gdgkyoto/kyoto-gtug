package org.gtug.kyoto.android.devilear.item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.gtug.kyoto.android.devilear.Item;
import org.gtug.kyoto.android.devilear.ItemsDbAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

public class SoundItem extends Item {
    
    private static String directory = "/sdcard/data/sound";
    
    static {
        File dir = new File(directory);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("SoundItem", "Failed to create the sound directory", new Exception("Failed to create the sound directory"));
            }
        }
    }
    
    public static SoundItem findById(Activity activity, long itemId) {
        Cursor cursor = mDbAdapter.fetchItem(itemId);
        activity.startManagingCursor(cursor);
        return cursorToItem(cursor);
    }
    
    private static SoundItem cursorToItem(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ROWID));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_TYPE));
        Long iconId = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ICON_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ICON_ID));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_DESCRIPTION));
        Long date = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_DATE));
        double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_LATITUDE));
        double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_LONGITUDE));

        SoundItem item = new SoundItem();
        item.setId(id);
        item.setType(type);
        item.setIconId(iconId);
        item.setName(name);
        item.setDescription(description);
        item.setDate(date);
        item.setLatitude(latitude);
        item.setLongitude(longitude);
        
        File propFile = makeSoundDataFile(id);
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(propFile));
            item.filePath = prop.getProperty("url");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return item;
    }

    private String filePath;
    
    public SoundItem() {
        
    }
    
    public SoundItem(Properties prop) {
        super(prop);
        filePath = prop.getProperty("url");
    }

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
        Properties prop = new Properties();
        File file = makeSoundDataFile(getId());
        prop.put("url", filePath);
        prop.save(new FileOutputStream(file), "Sound properties");
    }
    
    private static File makeSoundDataFile(long id) {
        return new File(directory, "" + id + ".properties");
    }
}
