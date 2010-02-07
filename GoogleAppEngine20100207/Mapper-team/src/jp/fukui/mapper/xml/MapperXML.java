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

import jp.fukui.mapper.MapperDB.MapperDB;
import jp.fukui.mapper.MapperData.MapperData;

/**
 * XML�쐬�N���X
 * @author tanayama
 *
 */
public class MapperXML {
	
	private static final Logger log = Logger.getLogger(MapperXML.class.getName());

	/**
	 * �R���X�g���N�^
	 */
	public MapperXML(String keyword){
		targetKeyword = keyword;
	}

	/**
	 * �L�[���[�h
	 */
	private String targetKeyword = null;

	/**
	 * XML�쐬
	 * @return
	 */
	public String CreateXML(){

		// DB�ɓo�^����Ă���L�[���[�h�����擾
		ArrayList<MapperData> userKeywordList = GetData(targetKeyword);

		// Google Suggest Keyword���X�g�擾
		GoogleSuggest googleSuggestAPI = new GoogleSuggest(targetKeyword);
		ArrayList<String> googleKeywordList = googleSuggestAPI.GetGoogleKeyword();

		String result = "";

		try {

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();

			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = domFactory.newDocumentBuilder();
			Document document = docbuilder.newDocument();

			// �e�v�f(MainKeyword)
			Element root = document.createElement("MainKeyword");
			document.appendChild(root);

			// Keyword�v�f��ǉ�
			Element mainKeyword = document.createElement("keyword");
			Text text = document.createTextNode(targetKeyword);
			mainKeyword.appendChild(text);			
			root.appendChild(mainKeyword);

			// GoogleSuggest�L�[���[�h��ݒ�
			this.SetGoogleSuggestKeyword(googleKeywordList, root, document);

			// DB����擾�����L�[���[�h��ݒ�
			this.SetUserKeyword(userKeywordList, root, document);

			// DOM�I�u�W�F�N�g�𕶎���Ƃ��ďo��
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer();

			// �G���R�[�h��UTF-8�Ɏw��
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(new DOMSource(document), new StreamResult(outStream));

			result = new String(outStream.toByteArray());

		} catch (Exception e) {
			log.warning(e.getMessage());
		}


		return result;
	}

	/**
	 * DB�������f�[�^��ԋp����
	 * @param keyword�@
	 * @return
	 */
	private ArrayList<MapperData> GetData(String keyword) {

		ArrayList<MapperData> resultList = new ArrayList<MapperData>();

		// DB����擾
		MapperDB db = new MapperDB();
		
		resultList = db.Get(keyword);

		return resultList;
	}

	/**
	 * GoogleSuggest�Ŏ擾�����L�[���[�h��XML�֐ݒ�
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
	 * ���[�U�[���o�^�����L�[���[�h����XML�֐ݒ�
	 * @param dataList			�L�[���[�h���
	 * @param rootElement		MainKeyword�G�������g
	 * @param document			Document
	 */
	private void SetUserKeyword(ArrayList<MapperData> dataList, Element rootElement, Document document) {
		Element userRootElement = document.createElement("SubKeyword");
		rootElement.appendChild(userRootElement);

		if (dataList != null) {
			
			HashMap<String, Element> elementMap = new HashMap<String, Element>();
			
			for (MapperData data : dataList) {
				// Type���P�̏ꍇ
				if (data.GetType() == 1) {
					// MainKeyword�̂��߁A���̓ǂݍ���
					continue;
				}

				// XML����
				if (data.GetType() == 2) {
					
					Element element = document.createElement("keyword");
					Text text = document.createTextNode(data.GetPropaty());
					element.appendChild(text);
					userRootElement.appendChild(element);

					// ��Q�v�f��HashMap�Ɋi�[
					elementMap.put(data.GetPropaty(), element);
					
				}
				else if(data.GetType() == 3) {
					
					// �e�̗v�f���擾
					if (elementMap.containsKey(data.GetPropaty())) {
						
						// �e�̗v�f�擾
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
