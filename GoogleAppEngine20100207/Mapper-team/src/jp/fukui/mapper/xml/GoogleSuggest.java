package jp.fukui.mapper.xml;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * GoogleサジェストAPIを呼ぶ
 * @author tanayama
 *
 */
public class GoogleSuggest {

	/**
	 * コンストラクタ
	 * @param keyword	Googleサジェストを行うキーワード
	 */
	public GoogleSuggest(String keyword){
		targetKeyword = keyword;		
	}

	/**
	 * キーワード
	 */
	private String targetKeyword = null;

	private static final Logger log = Logger.getLogger(GoogleSuggest.class.getName());

	/**
	 * GoogleSuggestAPIをコール
	 */
	public ArrayList<String> GetGoogleKeyword(){
		
		String encodedWord;
		ArrayList<String> resultList = null;
		try {
			
			// キーワードをエンコード
			encodedWord = URLEncoder.encode(targetKeyword, "sjis");
						
			// 読み込み
			InputStream in = new URL("http://google.com/complete/search?output=toolbar&q=" + encodedWord).openStream();
		

			// Googleサジェストキーワードリスト取得
			resultList= GetGoogleSuggest(in);
	
			
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
		return resultList;
	}
	
	
	/**
	 * InputStream(XML)からGoogleSuggestキーワードの一覧を取得する。
	 * @param inputStream
	 * @return
	 */
	private ArrayList<String> GetGoogleSuggest(InputStream inputStream) {
		
		ArrayList<String> resultList = null;
		
		try {
		      // SAXパーサーファクトリを生成
		      SAXParserFactory spfactory = SAXParserFactory.newInstance();
		      // SAXパーサーを生成
		      SAXParser parser = spfactory.newSAXParser();
		      
		      GoogleSuggestHandler handler = new GoogleSuggestHandler();
		      
		      // XMLファイルを指定されたデフォルトハンドラーで処理します
		      parser.parse(inputStream, handler);
		      
		      resultList = handler.getSuggestWordList();
		      
	    } catch (Exception e) {
			log.warning(e.getMessage());
	    }

	    return resultList;
	}
}
