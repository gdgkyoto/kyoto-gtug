package kyoto_gtug.tutorial1.Shuden;

import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import android.util.Log;

public class StationDataList {
	private StationData[] stationData;
	
	public StationData[] getStationData(){
		return this.stationData;
	}
	
	/// GeoPointAPIを使って，緯度と経度から，その位置周辺の駅一覧を取りに行く．
	public void updateStationDataList(double latitude, double longitude) throws Exception{
		
		// GeoPointAPIのリクエストURI
		URI geoPointAPIRequestURI;
		{
			// 基底URI
			String base = "http://api.cirius.co.jp/v1/geopoint/";
			// 必須パラメータ
			String format = "xml";
			String area = String.valueOf(latitude) + "," + String.valueOf(longitude);
			String api_key = "e4cb8f9fd1891ec90379302432d68d7e18cfcbd82da8139b252813748db7d60d";
			// オプションパラメータ
			String category = "2";
			
			// GeoPointAPIのリクエストURIを生成する
			geoPointAPIRequestURI = new URI(base + format + "?area=" + area + "&api_key=" + api_key + "&category=" + category);
			
		}
		
		// URIを正規化
		geoPointAPIRequestURI = geoPointAPIRequestURI.normalize();
		
		// URLに接続してXMLファイルを取得する．
		HttpURLConnection connection = (HttpURLConnection)geoPointAPIRequestURI.toURL().openConnection();
		connection.setRequestMethod("GET");
		
		// サーバーに接続
		connection.connect();

	    // パースを実行してDOMを取得
		Document StationListXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());
		Element root = StationListXML.getDocumentElement();

		// 駅の数を取得
		int count = Integer.valueOf(root.getElementsByTagName("count").item(0).getFirstChild().getNodeValue());

		// 駅データの配列を作る
		stationData = new StationData[count];

		// XMLファイルから駅の名前・緯度・経度を取得する
		for(int i = 0; i <= count-1; i++){
			// 駅名
			String display = root.getElementsByTagName("display").item(i).getFirstChild().getNodeValue();
			Log.d("DEBUG", display);
			// 緯度
			Double lat = Double.valueOf(root.getElementsByTagName("lat").item(i).getFirstChild().getNodeValue());
			Log.d("DEBUG", String.valueOf(lat));
			// 経度
			Double lon = Double.valueOf(root.getElementsByTagName("lon").item(i).getFirstChild().getNodeValue());
			Log.d("DEBUG", String.valueOf(lon));

			stationData[i] = new StationData(display, lat, lon);
		}
	}
}
