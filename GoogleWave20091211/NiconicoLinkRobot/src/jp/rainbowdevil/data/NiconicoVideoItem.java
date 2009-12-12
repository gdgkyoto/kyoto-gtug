package jp.rainbowdevil.data;


/**
 * ニコニコ動画の動画一つの情報を表すクラス
 * @author Kenji
 *
 */
public class NiconicoVideoItem {
	
	/** 動画のID sm12345 */
	private String id;
	
	/** 動画のタイトル */
	private String title;
	
	/** 動画のURL */
	private String url;
	
	/** サムネイル */
	private String thumbnailUrl;
	
	/** 再生数 */
	private String viewCounter;
	
	/** コメント数 */
	private String commentNum;
	
	/** マイリスト数 */
	private String mylistCounter;
	
	/** 最近投稿されたコメント */
	private String lastResBody;

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getViewCounter() {
		return viewCounter;
	}

	public void setViewCounter(String viewCounter) {
		this.viewCounter = viewCounter;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getMylistCounter() {
		return mylistCounter;
	}

	public void setMylistCounter(String mylistCounter) {
		this.mylistCounter = mylistCounter;
	}

	public String getLastResBody() {
		return lastResBody;
	}

	public void setLastResBody(String lastResBody) {
		this.lastResBody = lastResBody;
	}

}
