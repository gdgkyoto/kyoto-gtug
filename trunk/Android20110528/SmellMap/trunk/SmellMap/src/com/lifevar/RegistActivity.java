package com.lifevar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RegistActivity extends Activity {
    Fragrance frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        
        Intent i = getIntent();
        if(i!=null && i.getExtras()!=null){
            frag = (Fragrance)i.getParcelableExtra("fragrance");
            
            //初期設定
            TextView pram1 = (TextView)findViewById(R.id.param1);
            TextView pram2 = (TextView)findViewById(R.id.param2);
            TextView pram3 = (TextView)findViewById(R.id.param3);
            TextView pram4 = (TextView)findViewById(R.id.param4);
            TextView pram5 = (TextView)findViewById(R.id.param5);
            TextView pram6 = (TextView)findViewById(R.id.param6);
            TextView pram7 = (TextView)findViewById(R.id.param7);
            TextView pram8 = (TextView)findViewById(R.id.param8);
            pram1.setText(Integer.toString(frag.epara1));
            pram2.setText(Integer.toString(frag.epara2));
            pram3.setText(Integer.toString(frag.epara3));
            pram4.setText(Integer.toString(frag.epara4));
            pram5.setText(Integer.toString(frag.epara5));
            pram6.setText(Integer.toString(frag.epara6));
            pram7.setText(Integer.toString(frag.epara7));
            pram8.setText(Integer.toString(frag.epara8));
        }
        else{
            this.finish();
        }
    }

}
