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

public class ContactListActivity extends Activity {
	
	private Button backButton;
	private EditText numberEditText;
	private EditText nameEditText;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactlist);

		numberEditText = (EditText) findViewById(R.id.contactlist_number);
		nameEditText = (EditText) findViewById(R.id.contactlist_name);

		backButton = (Button) findViewById(R.id.contactlist_back_button);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectNumber(nameEditText.getText().toString(), numberEditText.getText().toString());
			}
		});

		listView = (ListView) findViewById(R.id.ListView01);

		String[] projection = new String[] { android.provider.BaseColumns._ID,
				android.provider.Contacts.PeopleColumns.NAME ,android.provider.Contacts.PhonesColumns.NUMBER};
		// managedQuery(android.provider.Contacts.Phones.CONTENT_URI,
		// projection, selection, selectionArgs, sortOrder)
		Cursor cur = managedQuery(android.provider.Contacts.Phones.CONTENT_URI,
				projection, // �擾����J�������w��
				null, null, // WHERE��ɂ����镔���B�S���K�v�Ȃ̂�null
				android.provider.Contacts.PeopleColumns.NAME + " ASC"); // Order
																		// by

		String name;

		final List<String> list = new ArrayList<String>();

		// �f�[�^�擾�p�̃C���f�b�N�X���擾����
		int nameColumn = cur
				.getColumnIndex(android.provider.Contacts.PeopleColumns.NAME);
		int numberColumn = cur
				.getColumnIndex(android.provider.Contacts.PhonesColumns.NUMBER);
		// �f�[�^�擾�J�n
		while (cur.moveToNext()) {
			// �f�[�^�擾
			name = cur.getString(nameColumn);

			// �\���p�̔z��ɒǉ�
			// items.add(name);
			Log.d("phone", name);
			Log.d("phone", cur.getString(numberColumn));
			list.add(name + ":" + cur.getString(numberColumn));
		}
		ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.row,list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String text = list.get(arg2);
				String[] split = text.split(":");
				String name = split[0];
				String number = split[1];
				selectNumber(name, number);
			}
		});
	}
	
	private void selectNumber( String name , String number ){
		Intent intent = new Intent();
		intent.putExtra(RotaryDial.PARAM_DIAL_PERSON_NAME, name);
		intent.putExtra(RotaryDial.PARAM_DIAL_PERSON_NUMBER,number);
		intent.putExtra(RotaryDial.PARAM_MOVE_TO_DIAL_MODE, 1);
		setResult(RESULT_OK, intent);
		finish();
	}
}
