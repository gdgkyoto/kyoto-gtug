package jp.fukui.mapper.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * GoogleSuggest����擾����XML���p�[�X����N���X
 * @author tanahizu
 *
 */
public class GoogleSuggestHandler extends DefaultHandler {

	private ArrayList<String> suggestWordList = null;

	/**
	 * GoogleSuggest�L�[���[�h���X�g�擾
	 * @return
	 */
	public ArrayList<String> getSuggestWordList() {
		return suggestWordList;
	}

	/**
	 * �v�f�̊J�n�^�O�ǂݍ��ݎ�
	 */
	public void startElement(String uri,
			String localName,
			String qName,
			Attributes attributes) {

		if ("suggestion".equals(qName)) {
			int length = attributes.getLength();
			if (length > 0) {
				if (suggestWordList == null) {
					suggestWordList = new ArrayList<String>();
				}
				suggestWordList.add(attributes.getValue(0));
			}

		}
	}


}
