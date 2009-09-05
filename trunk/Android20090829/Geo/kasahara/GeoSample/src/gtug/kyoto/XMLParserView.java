package gtug.kyoto;

import java.io.StringReader;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Xml;
import android.view.View;

//XMLパーサー
public class XMLParserView extends View {
    //コンストラクタ
    public XMLParserView(Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);
    }
    
    //パース
    private ArrayList<String> parse(String text) {
        ArrayList<String> strs=new ArrayList<String>();
        String str;
        try {
            //パーサー
            final XmlPullParser parser=Xml.newPullParser();
            parser.setInput(new StringReader(text));

            while (true) {
                int type=parser.next();
                //ドキュメント開始
                if (type==XmlPullParser.START_DOCUMENT) {
                    str="type=START_DOCUMENT"+
                        ",text="+parser.getText()+
                        ",depth="+parser.getDepth();
                    strs.add(str);
                } 
                //タグ開始
                else if (type==XmlPullParser.START_TAG) {
                    str="type=START_TAG"+
                        ",text="+parser.getText()+
                        ",depth="+parser.getDepth();
                    strs.add(str);

                    //属性
                    str=" attribute,";
                    for (int i=0;i<parser.getAttributeCount();i++) {
                        str+=parser.getAttributeName(i)+"="+parser.getAttributeValue(i)+",";    
                        strs.add(str);
                    }
                } 
                //テキスト
                else if (type==XmlPullParser.TEXT) {
                    str="type=TEXT"+
                        ",text="+parser.getText()+
                        ",depth="+parser.getDepth();
                    strs.add(str);
                } 
                //タグ終了
                else if (type==XmlPullParser.END_TAG) {
                    str="type=END_TAG"+
                        ",text="+parser.getText()+
                        ",depth="+parser.getDepth();
                    strs.add(str);
                } 
                //ドキュメント終了
                else if (type==XmlPullParser.END_DOCUMENT) {
                    str="type=END_DOCUMENT"+
                        ",text="+parser.getText()+
                        ",depth="+parser.getDepth();
                    strs.add(str);
                    break;
                }                
            }
         } catch (Exception e) {
            android.util.Log.e("",e.toString());
        }        
        return strs;
    }

    public ArrayList<String> getXML(String str){
		return parse(str);
    }
    
    //描画
    /**
    @Override 
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();       
        paint.setAntiAlias(true);
        paint.setTextSize(16);
        ArrayList<String> strs=parse("<hoge name=\"test\">これはテストです</hoge>");
        for (int i=0;i<strs.size();i++) {
            canvas.drawText((String)strs.get(i),2,16+18*i,paint);
        }
    }
    */
}

