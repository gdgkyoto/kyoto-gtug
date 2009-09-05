package org.gtug.kyoto.android.devilear;

import org.gtug.kyoto.android.devilear.item.TextItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextBrowser extends Activity {

    TextView mTitleView;
    TextView mContentView;
    
    Long mItemId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_browser);
        
        mTitleView = (TextView)findViewById(R.id.TextView01);
        mContentView = (TextView)findViewById(R.id.TextView02);
        
        Button closeButton = (Button)findViewById(R.id.Button01);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish activity
                setResult(RESULT_OK);
                finish();
            }
        });
        
        mItemId = savedInstanceState != null ? savedInstanceState.getLong(ItemsDbAdapter.KEY_ROWID) : null;
        if (mItemId == null) {
            Bundle extras = getIntent().getExtras();
            mItemId = extras != null ? extras.getLong(ItemsDbAdapter.KEY_ROWID) : null;
        }
        
        populateFields();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    private void populateFields() {
        if (mItemId != null) {
            TextItem item = TextItem.findById(this, mItemId);
            mTitleView.setText(item.getName());
            mContentView.setText(item.getText());
        }
    }
}
