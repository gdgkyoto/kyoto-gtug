package theWorld.AndroidHackathon.Kyoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class selectRival extends Activity implements OnClickListener{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_rival);
        findViewById(R.id.button_serch).setOnClickListener(this);
        findViewById(R.id.button_ok).setOnClickListener(this);
        
	}
	public void onClick(View view){
	   	switch(view.getId() ){
	   
	   	case R.id.button_serch:
	   		//対戦相手検索の処理
	   		break;
	   		
	   	
		case R.id.button_ok:
			//３，２，１のカウントへ
			Intent intent = new Intent(this, StartWindow.class);
			startActivity(intent);
			finish();
			break;
	   	}
	   	
   	
	   	
	}
}
