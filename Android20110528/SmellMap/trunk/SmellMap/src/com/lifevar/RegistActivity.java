package com.lifevar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class RegistActivity extends Activity implements OnClickListener {
    Fragrance frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        
        Intent i = getIntent();
        if(i!=null && i.getExtras()!=null){
            frag = (Fragrance)i.getParcelableExtra("fragrance");
            
            MessageFormat mf = new MessageFormat("{0,date,yyyy年MM月dd日 HH:mm}");
            //初期設定
            TextView date = (TextView)findViewById(R.id.textView2);
            TextView pram1 = (TextView)findViewById(R.id.param1);
            TextView pram2 = (TextView)findViewById(R.id.param2);
            TextView pram3 = (TextView)findViewById(R.id.param3);
            TextView pram4 = (TextView)findViewById(R.id.param4);
            TextView pram5 = (TextView)findViewById(R.id.param5);
            TextView pram6 = (TextView)findViewById(R.id.param6);
            TextView pram7 = (TextView)findViewById(R.id.param7);
            TextView pram8 = (TextView)findViewById(R.id.param8);
            Object[] objs = {frag.date};
            date.setText(mf.format(objs));
            pram1.setText(Integer.toString(frag.epara1));
            pram2.setText(Integer.toString(frag.epara2));
            pram3.setText(Integer.toString(frag.epara3));
            pram4.setText(Integer.toString(frag.epara4));
            pram5.setText(Integer.toString(frag.epara5));
            pram6.setText(Integer.toString(frag.epara6));
            pram7.setText(Integer.toString(frag.epara7));
            pram8.setText(Integer.toString(frag.epara8));
            
            Button btn = (Button)findViewById(R.id.button1);
            btn.setOnClickListener(this);
        }
        else{
            this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        SeekBar seek = (SeekBar)findViewById(R.id.seekBar1);
        frag.period = seek.getProgress();
        
        sendServe(frag);
        
        Intent i = new Intent(this, SmellMap.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("registdata", frag);
        startActivity(i);
    }
    
    private void sendServe(Fragrance frag){
        //ここでサーバーに登録
    	HttpClient objHttp = new DefaultHttpClient();
        try {  
            HttpPost objPost   = new HttpPost(getString(R.string.postUrl)); 
            List<NameValuePair> objValuePairs = new ArrayList<NameValuePair>(2);    
            objValuePairs.add(new BasicNameValuePair("mp.latitude", Double.toString(frag.latitude)));  
            objValuePairs.add(new BasicNameValuePair("mp.longitude", Double.toString(frag.longitude)));  
            objValuePairs.add(new BasicNameValuePair("mp.geoHash", ""/*Double.toString(frag.geoHash)*/));  //TODO: GEO_HASH
            objValuePairs.add(new BasicNameValuePair("mp.epara1", Integer.toString(frag.epara1)));  
            objValuePairs.add(new BasicNameValuePair("mp.epara2", Integer.toString(frag.epara2)));  
            objValuePairs.add(new BasicNameValuePair("mp.epara3", Integer.toString(frag.epara3)));  
            objValuePairs.add(new BasicNameValuePair("mp.epara4", Integer.toString(frag.epara4)));  
            objValuePairs.add(new BasicNameValuePair("mp.epara5", Integer.toString(frag.epara5)));  
            objValuePairs.add(new BasicNameValuePair("mp.epara6", Integer.toString(frag.epara6)));  
            objValuePairs.add(new BasicNameValuePair("mp.epara7", Integer.toString(frag.epara7)));  
            objValuePairs.add(new BasicNameValuePair("mp.epara8", Integer.toString(frag.epara8)));  
            objValuePairs.add(new BasicNameValuePair("mp.period", Integer.toString(frag.period)));  
            objPost.setEntity(new UrlEncodedFormEntity(objValuePairs, "UTF-8"));  
      
            objHttp.execute(objPost);  
        } catch (IOException e) {  
            return ;  
        }     
    }

}
