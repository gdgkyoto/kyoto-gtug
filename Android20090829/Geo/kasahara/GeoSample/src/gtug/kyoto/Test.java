
package gtug.kyoto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Test extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        TextView tv = new TextView(this);
        String str = null;
        
        try{
            URL url = new URL("http://doodle.st/k/api2.php?lat=35.003760&lon=135.769300&tp=1&ap=doodle&ref=demosite.com&format=x&add=1");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            InputStream in = http.getInputStream();
            byte b[] = new byte[1024];
            in.read(b);
            in.close();
            http.disconnect();
            str = new String(b);
            tv.setText(str);
        }catch(Exception e){
            tv.setText(e.toString());
        }
        
        
        XMLParserView xml = new XMLParserView(this);
        ArrayList<String> list=xml.getXML(str);
        for (int i=0;i<list.size();i++) {
        	tv.setText((String)list.get(i));
        }
        
        setContentView(tv);
        

    }


}