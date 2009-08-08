/**
 * 
 */
package com.appspot.eitan.search.parsers;


/**
 * 検索結果パースストラテジオブジェクトのファクトリ。
 * <pre>
 * DOMパーサが遅かったら、SAXとかに切り替えるために使う。
 * </pre>
 * @author kazu
 */
public class ParsingStrategyFactory {
	
	public static ParsingStrategy getStrategy() {
		return new DOMStrategy();
	}
	
	// インスタンス不要
	private ParsingStrategyFactory() {}
}
