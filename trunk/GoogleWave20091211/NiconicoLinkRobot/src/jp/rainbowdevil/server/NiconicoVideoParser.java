package jp.rainbowdevil.server;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

import jp.rainbowdevil.data.NiconicoVideoItem;


/**
 * ニコニコ動画のAPIの結果をパースするパーサ
 * @author Kenji
 *
 */
public class NiconicoVideoParser {
	
	public static final String STATUS_FAIL = "fail";
	
	/**
	 * 動画情報のXMLをパースする
	 * @param xml 
	 * @return ニコニコ動画の動画情報
	 * @throws XMLStreamException
	 */
	public NiconicoVideoItem parseXml( String xml ) throws XMLStreamException{
		
		XMLInputFactory f = XMLInputFactory.newInstance();
		XMLEventReader r = f.createXMLEventReader( new StringReader(xml));

		NiconicoVideoItem item = null ;
		while( r.hasNext() ){
			XMLEvent event = r.nextEvent();
			
			// 検索に成功したかどうかチェック
			if( event.isStartElement() && event.asStartElement().getName().toString().equals("nicovideo_thumb_response")){
				Attribute attrStatus = event.asStartElement().getAttributeByName(new QName("status"));
				if( attrStatus != null ){
					String status = attrStatus.getValue();
					
					// 検索に失敗した場合はnullを返す
					if( STATUS_FAIL.equals(status) ) {
						return null;
					}
				}
			}
			 
			if( event.isStartElement() && event.asStartElement().getName().toString().equals("thumb") ){
				item = new NiconicoVideoItem();
			}
			
			// タイトル
			if( item != null && isStartCharacter( "title" ,event ,r )){
				item.setTitle(getCharacter(r));
			}
			
			// URL
			if( item != null && isStartCharacter( "watch_url" ,event ,r )){
				item.setUrl(getCharacter(r));
			}
			
			// サムネイル
			if( item != null && isStartCharacter( "thumbnail_url" ,event  ,r)){
				item.setThumbnailUrl(getCharacter(r));
			}
			
			// 動画ID 
			if( item != null && isStartCharacter( "video_id" ,event ,r )){
				item.setId(getCharacter(r));
			}
			
			// 再生数 
			if( item != null && isStartCharacter( "view_counter" ,event ,r )){
				item.setViewCounter(getCharacter(r));
			}
			
			// コメント数 
			if( item != null && isStartCharacter( "comment_num" ,event ,r )){
				item.setCommentNum(getCharacter(r));
			}
			
			// マイリスト数
			if( item != null && isStartCharacter( "mylist_counter" ,event ,r )){
				item.setMylistCounter(getCharacter(r));
			}
			
			// 最後に投稿されたコメント
			if( item != null && isStartCharacter( "last_res_body" ,event ,r )){
				item.setLastResBody(getCharacter(r));
			}

		}

		return item;
	}
	
	/**
	 * イベントが指定のタグ名の開始タグかどうかを取得する。
	 * @param tagName タグ名
	 * @param e XMLEvent
	 * @param r 
	 * @return 指定のタグ名の開始タグかどうか
	 * @throws XMLStreamException
	 */
	boolean isStartCharacter( String tagName , XMLEvent e , XMLEventReader r ) throws XMLStreamException{
		if( e.isStartElement() && e.asStartElement().getName().toString().equals(tagName) && r.peek().isCharacters() ){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 現在のElementがStartElementの場合、そのタグのコンテンツを取得する
	 * @param r XMLEventReader
	 * @return 次のEventのValue
	 * @throws XMLStreamException 
	 */
	private String getCharacter( XMLEventReader r) throws XMLStreamException{
		if( r.peek().isCharacters() ){
			return r.nextEvent().asCharacters().getData();
		}else{
			return "";
		}
	}
	
	

}
