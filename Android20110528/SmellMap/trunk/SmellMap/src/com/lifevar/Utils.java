package com.lifevar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {

    public List<Fragrance> getFragrance(double x1, double y1, double x2, double y2, int zoom) throws Exception{
        BufferedReader in = null;
        ArrayList<Fragrance> ret = new ArrayList<Fragrance>();
        try {
            // URLクラスのインスタンスを生成
            //http://lifevar.com/g/map/get?x1=35.40896342520019&y1=136.7590570449829&x2=35.404643575791816&y2=136.76277995109558&zoom=17
            StringBuilder u = new StringBuilder();
            u.append("http://lifevar.com/g/map/get?");
            u.append("x1=").append(x1);
            u.append("&y1=").append(y1);
            u.append("&x2=").append(x2);
            u.append("&y2=").append(y2);
            u.append("&zoom=").append(zoom);
            URL url = new URL(u.toString());

            // 接続します
            URLConnection con = url.openConnection();

            // 入力ストリームを取得
            in = new BufferedReader(new InputStreamReader(con.getInputStream(),
                    "UTF-8"));

            // 一行ずつ読み込みます
            StringBuffer json = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                // 表示します
                json.append(line);
            }
            
            //Logger.d(u.toString());
            //Logger.d(json.toString());
            if(json.equals("error")!=true){
	            JSONArray root = new JSONArray(json.toString());
	            for(int i=0; i<root.length(); i++){
	                JSONObject o = root.getJSONObject(i);
	                Fragrance f = new Fragrance(o);
	                ret.add(f);
	            }
            }
            return ret;
        } finally {
            // 入力ストリームを閉じます
            if (in != null){
                in.close();
            }
        }
    }
    
    
}
