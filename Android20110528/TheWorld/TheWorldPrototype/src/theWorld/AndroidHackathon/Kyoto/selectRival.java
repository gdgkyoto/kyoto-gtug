package theWorld.AndroidHackathon.Kyoto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class selectRival extends Activity implements OnClickListener{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_rival);
        findViewById(R.id.button_serch).setOnClickListener(this);
	}
	public void onClick(View view){
	   	switch(view.getId() ){
	   	//スタンドを選択
	   	case R.id.button_serch:
	   		//対戦相手検索の処理
	   	}
	   	
	}
}