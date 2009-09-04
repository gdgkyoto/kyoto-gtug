package kgtug.android.geolocation;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;
import android.content.Context;

import java.util.ArrayList;

public class ImageViewAdapter extends BaseAdapter {
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private Context ctx;
    private ArrayList<Row> coll;

    public ImageViewAdapter(Context context, ArrayList<Row> list) {
    	ctx = context;
    	coll = list;
    }

    public int getCount() {
    	return coll.size();
    }

    public Object getItem(int position) {
    	return coll.get(position);
    }

    public long getItemId(int position) {
    	return position;
    }

	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layout = new LinearLayout(ctx);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		Row rw = (Row)coll.get(position);
		ImageView iv = new ImageView(ctx);
		iv.setImageResource(rw.getDwId());
		iv.setPadding(0, 2, 5, 0);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(WC, WC);
		layout.addView(iv, param);
		TextView txv = new TextView(ctx);
		txv.setText(rw.getText());
		txv.setTextColor(Color.WHITE);
		txv.setTextSize(26);
		LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(WC, WC);
		param2.leftMargin = 5;
		param2.topMargin = 13;
		layout.addView(txv, param2);
		return layout;
	}
}