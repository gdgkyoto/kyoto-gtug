package com.google.ktygtug.droid2.tecchan;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RakutenList extends ListActivity {
    public final static String LOG_TAG = "tecchan";
    public String SERCH_URL ="http://api.rakuten.co.jp/rws/3.0/rest?developerId=4df5f07ce018f3aaa11285248e05ba94&operation=KeywordHotelSearch&version=2009-10-20&keyword=%E5%93%81%E5%B7%9D%E3%82%B7%E3%83%BC%E3%82%B5%E3%82%A4%E3%83%89";


    ImageButton btn01 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(Activity.RESULT_CANCELED);
        GetEtaTask task = new GetEtaTask();
        task.execute(1);


//        setContentView(R.layout.rakutenlistview);


    }
	/*
	 * (非 Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
    protected void onListItemClick(ListView listView, View v, int position,
            long id) {

		super.onListItemClick(listView, v, position, id);
        // 選択されたアイテムを取得します
		Intent intent = new Intent(this,Rakuten.class);
		startActivity(intent);


	}

	static class ViewHolder {
	     TextView hotelname;
	     TextView address1;
	     TextView address2;
	     ImageView image;
	}
	public class ListAdapter extends ArrayAdapter<ListData> {

		private ArrayList<ListData> items;
		private LayoutInflater inflater;
		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<ListData> items) {
			super(context, textViewResourceId, items);
			this.items = items;
			this.inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		/*
		 * (非 Javadoc)
		 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = convertView;
			ViewHolder holder;
			if (view == null) {
				// 受け取ったビューがnullなら新しくビューを生成
				view = inflater.inflate(R.layout.rakutenrow, null);
				// 背景画像をセットする
//				view.setBackgroundResource(R.drawable.back);

				TextView hotelname = (TextView) view.findViewById(R.id.toptext);
				hotelname.setTypeface(Typeface.DEFAULT_BOLD);

				TextView address1 = (TextView) view.findViewById(R.id.bottomtext1);
				TextView address2 = (TextView) view.findViewById(R.id.bottomtext2);
//				ImageView imageView = (ImageView)view.findViewById(R.id.icon);


				holder = new ViewHolder();
				holder.hotelname = hotelname;
				holder.address1 = address1;
				holder.address2 = address2;
//				holder.image = imageView;
		        view.setTag(holder);

			} else {
		        holder = (ViewHolder)view.getTag();
		    }

			// 表示すべきデータの取得
			ListData item = items.get(position);
		    holder.hotelname.setText(item.getHotelname());
		    holder.address1.setText(item.getAddress1());
		    holder.address2.setText(item.getAddress2());
//		    holder.image.setImageResource(item.getImageId());
		    return view;

		}
	}

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private class GetEtaTask extends AsyncTask<Integer, Integer, Boolean>{
        ProgressDialog mProgDlg = null;
    	// 表示するデータのリスト
    	private ArrayList<ListData> mlist = null;
    	// listAdapter
    	private ListAdapter adapter = null;

//    	private String UrlData = null;
    	private String UrlData ="http://api.rakuten.co.jp/rws/3.0/rest?developerId=4df5f07ce018f3aaa11285248e05ba94&operation=KeywordHotelSearch&version=2009-10-20&keyword=%E5%93%81%E5%B7%9D%E3%82%B7%E3%83%BC%E3%82%B5%E3%82%A4%E3%83%89";
//    	private String URLDATA = null;
        //コンストラクタ
        public GetEtaTask(){

        }


//       public void execute(String string) {
 //       	this.URLDATA=string;
//		}

		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressDialog dlg = new ProgressDialog(RakutenList.this);
            dlg.setTitle("通信中");
            dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dlg.setMessage("通信しています");
            dlg.setIndeterminate(false);
            dlg.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    cancel(true);
                    dialog.dismiss();
                }
            });
            dlg.setCancelable(true);
            mProgDlg = dlg;
            mProgDlg.show();
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
 // ここで上のhttp通信を呼び出す
            try{
        		this.mlist = new ArrayList<ListData>();

                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet();
                get.setURI(new URI(this.UrlData)); // uriに "http://hoge.hoge./"とかをセット
                HttpResponse res = client.execute(get);
                InputStream in = res.getEntity().getContent();

                Log.d("xml",res.toString());
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(in,"UTF-8");
                int eventType = parser.getEventType();

                this.mlist = new ArrayList<ListData>();

                ListData currentItem = null;

                String tag = "";
                currentItem = new ListData();

                while(eventType != XmlPullParser.END_DOCUMENT){
//                    Log.d("xml", String.valueOf(eventType));
                    switch(eventType){
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if("hotelName".equals(tag)){      //      <hotelName> をさがす
                            currentItem.setHotelname(parser.nextText());
//                        	hotelname = parser.nextText();
                            Log.d("xml",currentItem.getHotelname().toString());
                        }else if("address1".equals(tag)){
                            currentItem.setAddress1(parser.nextText());
                            Log.d("xml",currentItem.getAddress1().toString());

                        }else if("address2".equals(tag)){
                            currentItem.setAddress2(parser.nextText());
                            Log.d("xml",currentItem.getAddress2().toString());
                        }else if("hotelThumbnailUrl".equals(tag)){
                        	//ToDo 画像取得する
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if (tag.equals("hotelBasicInfo")) {
                        	this.mlist.add(currentItem);
                        }
                        break;
                    }
                    eventType = parser.next();

                }
            }catch(Exception ex){
            	Log.d("ex",ex.toString());
//                mAppBean.writeException("TrackService#connETA() exception: ",ex);
//            	onCancelled();
            	return false;
            }

//            for(ListData dat: this.mlist){
//            	Log.d("",dat.toString());
//            }
 // ここで上のhttp通信を呼び出す
            if(isCancelled()) return false;
            return true;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(mProgDlg != null) mProgDlg.dismiss();
            if(!isCancelled()){
                if(result){
                    // 通信成功
            		adapter = new ListAdapter(RakutenList.this, R.layout.rakutenrow, mlist);
            		setListAdapter(adapter);

                	//画面表示
                }else{

//                	AndroTool.showAlertDialog(RakutenList.this,"通信エラー","サーバーとの通信に失敗しました");
//                	Toast.makeText(, "テキスト", Toast.LENGTH_LONG).show();

                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

}
