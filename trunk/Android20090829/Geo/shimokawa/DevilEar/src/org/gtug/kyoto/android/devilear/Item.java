package org.gtug.kyoto.android.devilear;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;


public class Item {
    
    private static ItemsDbAdapter mDbAdapter;
    protected static Context mContext;
    
    public static void openDb(Context context) {
        mContext = context;
        mDbAdapter = new ItemsDbAdapter(context);
        mDbAdapter.open();
    }
    
    public static void closeDb() {
        if (mDbAdapter != null) {
            mDbAdapter.close();
        }
    }
    
    public static List<Item> getAll(Activity activity) {
        List<Item> itemList = new ArrayList<Item>();

        Cursor itemsCursor = mDbAdapter.fetchAllItems();
        activity.startManagingCursor(itemsCursor);
        
        while (itemsCursor.moveToNext()) {
            Item item = cursorToItem(itemsCursor);
            itemList.add(item);
        }

        return itemList;
    }
    
    private static Item cursorToItem(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ROWID));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_TYPE));
        Long iconId = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ICON_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_ICON_ID));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_DESCRIPTION));
        Long date = cursor.getLong(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_DATE));
        double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_LATITUDE));
        double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(ItemsDbAdapter.KEY_LONGITUDE));

        Item item = new Item();
        item.setId(id);
        item.setType(type);
        item.setIconId(iconId);
        item.setName(name);
        item.setDescription(description);
        item.setDate(date);
        item.setLatitude(latitude);
        item.setLongitude(longitude);
        return item;
    }
    
    private Long id;
    private Long iconId;
    private String name;
    private String description;
    private String type;
    private Long date;
    private double latitude;
    private double longitude;
    
        
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the iconId
     */
    public Long getIconId() {
        return iconId;
    }

    /**
     * @param iconId the iconId to set
     */
    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the date
     */
    public Long getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public void setLocation(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }
    
    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public void save() throws IOException {
        if (id == null) {
            long newId = mDbAdapter.createItem(this);
            if (newId < -1) {
                throw new IOException("Failed to save");
            }
            id = newId;
        }
    }

}
