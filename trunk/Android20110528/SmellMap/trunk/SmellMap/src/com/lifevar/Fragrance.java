package com.lifevar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Fragrance implements Parcelable {
    Date date;
    double latitude;
    double longitude;
    String geohash;
    int epara1;
    int epara2;
    int epara3;
    int epara4;
    int epara5;
    int epara6;
    int epara7;
    int epara8;
    int period;
    
    public Fragrance(){
        
    }
    
    public Fragrance(Parcel par){
        date = new Date(par.readLong());
        latitude = par.readDouble();
        longitude = par.readDouble();
        geohash = par.readString();
        epara1 = par.readInt();
        epara2 = par.readInt();
        epara3 = par.readInt();
        epara4 = par.readInt();
        epara5 = par.readInt();
        epara6 = par.readInt();
        epara7 = par.readInt();
        epara8 = par.readInt();
        period = par.readInt();
    }
    
    public Fragrance(JSONObject o){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(o.getString("createdby"));
            latitude = o.getDouble("");
            longitude = o.getDouble("");
            epara1 = o.getInt("");
            epara2 = o.getInt("");
            epara3 = o.getInt("");
            epara4 = o.getInt("");
            epara5 = o.getInt("");
            epara6 = o.getInt("");
            epara7 = o.getInt("");
            epara8 = o.getInt("");
            period = o.getInt("");
        } catch (Exception e) {
            Logger.e("JSONパース処理", e);
        }
    }
    
    public static final Parcelable.Creator CREATOR
        = new Parcelable.Creator() {
        public Fragrance createFromParcel(Parcel in) {
            return new Fragrance(in);
        }
        
        public Fragrance[] newArray(int size) {
            return new Fragrance[size];
        }
    };
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date.getTime());
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(geohash);
        dest.writeInt(epara1);
        dest.writeInt(epara2);
        dest.writeInt(epara3);
        dest.writeInt(epara4);
        dest.writeInt(epara5);
        dest.writeInt(epara6);
        dest.writeInt(epara7);
        dest.writeInt(epara8);
        dest.writeInt(period);
    }
}
