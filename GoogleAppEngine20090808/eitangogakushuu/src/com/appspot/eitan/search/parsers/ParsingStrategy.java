/**
 * 
 */
package com.appspot.eitan.search.parsers;

import java.io.InputStream;
import java.util.List;

import com.appspot.eitan.search.Meaning;


/**
 * 翻訳検索結果XMLをパースするストラテジーのインターフェース。
 * @author kazu
 */
public interface ParsingStrategy {
	
	/**
	 * 翻訳検索結果をパースする。
	 * @param xml 翻訳検索結果のXMLストリーム。
	 * @return 翻訳検索結果のリスト。検索に失敗した場合、空のリストを返す。
	 */
	List<Meaning> parse(InputStream xml);
}
