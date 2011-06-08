package theWorld.AndroidHackathon.Kyoto;

import android.app.Activity;
import android.os.Bundle;

public class BattleMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle_main);
	}

	
	/**
	 * 終了時イベント。
	 * 
	 */
	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

}
