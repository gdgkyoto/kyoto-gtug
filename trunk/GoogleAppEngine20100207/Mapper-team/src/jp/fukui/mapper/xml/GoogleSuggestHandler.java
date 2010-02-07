package jp.fukui.mapper.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * GoogleSuggestから取得したXMLをパースするクラス
 * @author tanahizu
 *
 */
public class GoogleSuggestHandler extends DefaultHandler {

	private ArrayList<String> suggestWordList = null;

	/**
	 * GoogleSuggestキーワードリスト取得
	 * @return
	 */
	public ArrayList<String> getSuggestWordList() {
		return suggestWordList;
	}

	/**
	 * 要素の開始タグ読み込み時
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
