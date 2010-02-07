package jp.fukui.mapper.xml;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import jp.fukui.mapper.MapperData.MapperData;

/**
 * XML作成クラス
 * @author tanayama
 *
 */
public class MapperXML {
	
	private static final Logger log = Logger.getLogger(MapperXML.class.getName());

	/**
	 * コンストラクタ
	 */
	public MapperXML(String keyword){
		targetKeyword = keyword;
	}

	/**
	 * キーワード
	 */
	private String targetKeyword = null;

	/**
	 * XML作成
	 * @return
	 */
	public String CreateXML(){

		// DBに登録されているキーワード情報を取得
		ArrayList<MapperData> userKeywordList = GetData(targetKeyword);

		// Google Suggest Keywordリスト取得
		GoogleSuggest googleSuggestAPI = new GoogleSuggest(targetKeyword);
		ArrayList<String> googleKeywordList = googleSuggestAPI.GetGoogleKeyword();

		String result = "";

		try {

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();

			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = domFactory.newDocumentBuilder();
			Document document = docbuilder.newDocument();

			// 親要素(MainKeyword)
			Element root = document.createElement("MainKeyword");
			document.appendChild(root);

			// Keyword要素を追加
			Element mainKeyword = document.createElement("keyword");
			Text text = document.createTextNode(targetKeyword);
			mainKeyword.appendChild(text);			
			root.appendChild(mainKeyword);

			// GoogleSuggestキーワードを設定
			this.SetGoogleSuggestKeyword(googleKeywordList, root, document);

			// DBから取得したキーワードを設定
			this.SetUserKeyword(userKeywordList, root, document);

			// DOMオブジェクトを文字列として出力
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer();

			// エンコードをUTF-8に指定
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(new DOMSource(document), new StreamResult(outStream));

			result = new String(outStream.toByteArray());

		} catch (Exception e) {
			log.warning(e.getMessage());
		}


		return result;
	}

	/**
	 * DB検索しデータを返却する
	 * @param keyword　
	 * @return
	 */
	private ArrayList<MapperData> GetData(String keyword) {

		ArrayList<MapperData> resultList = new ArrayList<MapperData>();

		// DBから取得
		//		MapperDB dbAccess = new MapperDB();

		MapperData data = new MapperData();
		data.SetKeyword("Google");
		data.SetType(1l);
		resultList.add(data);

		data = new MapperData();
		data.SetKeyword("Google");
		data.SetType(2l);
		data.SetPropaty("What Service?");
		resultList.add(data);

		data = new MapperData();
		data.SetKeyword("Google");
		data.SetType(3l);
		data.SetPropaty("What Service?");
		data.SetParameter("search");
		resultList.add(data);

		data = new MapperData();
		data.SetKeyword("Google");
		data.SetType(3l);
		data.SetPropaty("What Service?");
		data.SetParameter("gmail");
		resultList.add(data);

		return resultList;
	}

	/**
	 * GoogleSuggestで取得したキーワードをXMLへ設定
	 * @param keywordList
	 * @param rootElement
	 * @param document
	 */
	private void SetGoogleSuggestKeyword(ArrayList<String> keywordList, Element rootElement, Document document) {

		Element googleElement = document.createElement("GoogleSuggestKeyword");
		rootElement.appendChild(googleElement);

		if (keywordList != null) {
			for (String keyword : keywordList) {
				Element element = document.createElement("keyword");
				Text text = document.createTextNode(keyword);
				element.appendChild(text);
				googleElement.appendChild(element);
			}
		}

	}

	/**
	 * ユーザーが登録したキーワード情報をXMLへ設定
	 * @param dataList			キーワード情報
	 * @param rootElement		MainKeywordエレメント
	 * @param document			Document
	 */
	private void SetUserKeyword(ArrayList<MapperData> dataList, Element rootElement, Document document) {
		Element userRootElement = document.createElement("SubKeyword");
		rootElement.appendChild(userRootElement);

		if (dataList != null) {
			
			HashMap<String, Element> elementMap = new HashMap<String, Element>();
			
			for (MapperData data : dataList) {
				// Typeが１の場合
				if (data.GetType() == 1) {
					// MainKeywordのため、次の読み込み
					continue;
				}

				// XML生成
				if (data.GetType() == 2) {
					
					Element element = document.createElement("keyword");
					Text text = document.createTextNode(data.GetPropaty());
					element.appendChild(text);
					userRootElement.appendChild(element);

					// 第２要素をHashMapに格納
					elementMap.put(data.GetPropaty(), element);
					
				}
				else if(data.GetType() == 3) {
					
					// 親の要素を取得
					if (elementMap.containsKey(data.GetPropaty())) {
						
						// 親の要素取得
						Element subKeywordElement = document.createElement("SubKeyword");
						userRootElement.appendChild(subKeywordElement);
						
						
						Element element = document.createElement("keyword");
						Text text = document.createTextNode(data.GetParameter());
						element.appendChild(text);
						subKeywordElement.appendChild(element);
						
					}
					
				}
				else {
					continue;
				}

			}
		}

	}
}
