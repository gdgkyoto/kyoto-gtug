/**
 * 
 */
package com.appspot.eitan.search;

/**
 * SmartFmItemSearcher#search()での検索オプション。
 * @author kazu
 */
public class SearchOption {
	
	private int perPage = 0;
	private int pageOffset = 0;
	
	/**
	 * 結果を何件取得するか。
	 * @param value
	 */
	public void setPerPage(int value) {
		if (value < 0)
			throw new IllegalArgumentException("0以上であること");
		perPage = value;
	}
	
	public int getPerPage() {
		return perPage;
	}
	
	/**
	 * 何ページあとの結果を取得するか。
	 * @param value
	 */
	public void setPageOffset(int value) {
		if (value < 0)
			throw new IllegalArgumentException("0以上であること");
		pageOffset = value;
	}
	
	public int getPageOffset() {
		return pageOffset;
	}
	
	public String toURLFragment() {
		StringBuilder builder = new StringBuilder();
		if (perPage > 0)
			builder.append("?per_page=" + perPage);
		if (pageOffset > 0) {
			appendConnectionChar(builder);
			builder.append("page=" + pageOffset);
		}
		return builder.toString();
	}
	
	private void appendConnectionChar(StringBuilder builder) {
		if (builder.length() == 0)
			builder.append("?");
		else
			builder.append("&");
	}
}
