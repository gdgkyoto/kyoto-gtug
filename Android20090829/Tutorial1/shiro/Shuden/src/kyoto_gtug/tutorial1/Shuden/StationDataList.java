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
	
	/// GeoPointAPI���g���āC�ܓx�ƌo�x����C���̈ʒu���ӂ̉w�ꗗ�����ɍs���D
	public void updateStationDataList(double latitude, double longitude) throws Exception{
		
		// GeoPointAPI�̃��N�G�X�gURI
		URI geoPointAPIRequestURI;
		{
			// ���URI
			String base = "http://api.cirius.co.jp/v1/geopoint/";
			// �K�{�p�����[�^
			String format = "xml";
			String area = String.valueOf(latitude) + "," + String.valueOf(longitude);
			String api_key = "e4cb8f9fd1891ec90379302432d68d7e18cfcbd82da8139b252813748db7d60d";
			// �I�v�V�����p�����[�^
			String category = "2";
			
			// GeoPointAPI�̃��N�G�X�gURI�𐶐�����
			geoPointAPIRequestURI = new URI(base + format + "?area=" + area + "&api_key=" + api_key + "&category=" + category);
			
		}
		
		// URI�𐳋K��
		geoPointAPIRequestURI = geoPointAPIRequestURI.normalize();
		
		// URL�ɐڑ�����XML�t�@�C�����擾����D
		HttpURLConnection connection = (HttpURLConnection)geoPointAPIRequestURI.toURL().openConnection();
		connection.setRequestMethod("GET");
		
		// �T�[�o�[�ɐڑ�
		connection.connect();

	    // �p�[�X�����s����DOM���擾
		Document StationListXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());
		Element root = StationListXML.getDocumentElement();

		// �w�̐����擾
		int count = Integer.valueOf(root.getElementsByTagName("count").item(0).getFirstChild().getNodeValue());

		// �w�f�[�^�̔z������
		stationData = new StationData[count];

		// XML�t�@�C������w�̖��O�E�ܓx�E�o�x���擾����
		for(int i = 0; i <= count-1; i++){
			// �w��
			String display = root.getElementsByTagName("display").item(i).getFirstChild().getNodeValue();
			Log.d("DEBUG", display);
			// �ܓx
			Double lat = Double.valueOf(root.getElementsByTagName("lat").item(i).getFirstChild().getNodeValue());
			Log.d("DEBUG", String.valueOf(lat));
			// �o�x
			Double lon = Double.valueOf(root.getElementsByTagName("lon").item(i).getFirstChild().getNodeValue());
			Log.d("DEBUG", String.valueOf(lon));

			stationData[i] = new StationData(display, lat, lon);
		}
	}
}
