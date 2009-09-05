package phone.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * 電話帳画面のActivity
 * 
 * 
 * 引数:  
 *   なし。
 *
 * 戻り値: 
 *   以下の処理で戻り値を戻す。
 *   Intent.putExtra(PARAM,value);
 *   setResult(RESULT_OK, intent);
 *   finish(); 
 * RotaryDial.PARAM_DIAL_PERSON_NAME    String 電話をかける相手の名前
 * RotaryDial.PARAM_DIAL_PERSON_NUMBER  String 電話をかける相手の番号
 * RotaryDial.PARAM_MOVE_TO_DIAL_MODE   int    ダイヤル画面に遷移するか 0=遷移する 1=遷移しない
 * 
 * @author KENJI
 *
 */
public class ContactListActivity extends Activity {
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactlist);

		listView = (ListView) findViewById(R.id.ListView01);

		String[] projection = new String[] { android.provider.BaseColumns._ID,
				android.provider.Contacts.PeopleColumns.NAME ,android.provider.Contacts.PhonesColumns.NUMBER};
		// managedQuery(android.provider.Contacts.Phones.CONTENT_URI,
		// projection, selection, selectionArgs, sortOrder)
		Cursor cur = managedQuery(android.provider.Contacts.Phones.CONTENT_URI,
				projection, // 取得するカラムを指定
				null, null, // WHERE句にあたる部分。全部必要なのでnull
				android.provider.Contacts.PeopleColumns.NAME + " ASC"); // Order
																		// by

		String name;

		final List<String> list = new ArrayList<String>();

		// データ取得用のインデックスを取得する
		int nameColumn = cur
				.getColumnIndex(android.provider.Contacts.PeopleColumns.NAME);
		int numberColumn = cur
				.getColumnIndex(android.provider.Contacts.PhonesColumns.NUMBER);
		// データ取得開始
		while (cur.moveToNext()) {
			// データ取得
			name = cur.getString(nameColumn);

			// 表示用の配列に追加
			// items.add(name);
			Log.d("phone", name);
			Log.d("phone", cur.getString(numberColumn));
			list.add(name + ":" + cur.getString(numberColumn));
		}
		for(int i=0; i<30; i++){
			list.add("");
		}
		
		ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.row,list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String text = list.get(arg2);
				if(text != null && !text.equals("")){
					String[] split = text.split(":");
					String name = split[0];
					String number = split[1];
					selectNumber(name, number);
				}
			}
		});
	}
	
	private void selectNumber( String name , String number ){
		Log.d("phone","selectNumber name="+name+" number="+number);
		Intent intent = new Intent();
		intent.putExtra(RotaryDial.PARAM_DIAL_PERSON_NAME, name);
		intent.putExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER,number);
		intent.putExtra(RotaryDial.PARAM_MOVE_TO_DIAL_MODE, 1);
		setResult(RESULT_OK, intent);
		finish();
	}
}
