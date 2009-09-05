package org.gtug.kyoto.android.devilear;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemFoundDialog extends Dialog {
    
    private Item mItem; 

    public ItemFoundDialog(final DevilEar activity) {
        super(activity);
        setContentView(R.layout.item_found_dialog);
        setTitle("You've got an item!");
        TextView text = (TextView) findViewById(R.id.text);
        text.setText("");
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.text_icon);
        
        View v = findViewById(R.id.image);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });
        
        Button button = (Button)findViewById(R.id.button);
        button.setText("open");
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
                if (mItem != null) {
                    activity.startItemViewActivity(mItem);
                }
            }
        });

        setOwnerActivity(activity);
    }
    
    public void configure(Item item) {
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(item.getDescription());
        ImageView image = (ImageView)findViewById(R.id.image);
        if (item.getType().equals("text")) {
            image.setImageResource(R.drawable.text_icon);
        } else if (item.getType().equals("sound")) {
            image.setImageResource(R.drawable.sound_icon);
            Log.d("ItemFoundDialog", "Not impleented");
        } else if (item.getType().equals("image")) {
            Log.d("ItemFoundDialog", "Not impleented");
        }
        mItem = item;
    }

}
