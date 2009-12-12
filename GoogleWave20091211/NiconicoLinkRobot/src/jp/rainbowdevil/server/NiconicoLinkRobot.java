package jp.rainbowdevil.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLStreamException;

import jp.rainbowdevil.data.NiconicoVideoItem;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;

public class NiconicoLinkRobot extends AbstractRobotServlet {
	
	/** ニコニコ動画のID抽出用正規表現 */
	private Pattern pattern = Pattern.compile("sm\\d+");

	/** ロギング */
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * イベント処理
	 */
	@Override
	public void processEvents(RobotMessageBundle bundle) {
		/*
		logger.warning("テストログですよ");
		
		logger.severe("severe");
		logger.warning("warning");
		logger.info("info");
		logger.config("config");
		logger.fine("fine");
		logger.finer("finer");
		logger.finest("finest");
		
		logger.warning("テストログここまでですよ");
		*/

		for (Event event: bundle.getEvents()) {
			
			// Blipが投稿された場合
			if (event.getType() == EventType.BLIP_SUBMITTED) {
                String submittedText = event.getBlip().getDocument().getText();
				try {
					// ロボットの応答生成
					String message = getMessage(submittedText);
					if( message != null ){
						logger.info("success! message=["+message+"]");
						// ロボットの応答を投稿
		                Blip blip = event.getBlip().createChild();
		                TextView textView = blip.getDocument();
						textView.appendMarkup(message);
						logger.info("finish.");
					}
				} catch (XMLStreamException e1) {
					logger.warning("XML Error. exception="+ e1.getClass().getName()+" message="+e1.getMessage());
				} catch (IOException e1) {
					logger.warning("IO Error. exception="+ e1.getClass().getName()+" message="+e1.getMessage());
				}
            }
		}
	}
	
	/**
	 * ロボットの応答生成
	 * 応答生成に失敗した場合はnullを返す。
	 * @param submittedText
	 * @return 応答文
	 * @throws XMLStreamException 
	 * @throws IOException 
	 */
	private String getMessage( String submittedText ) throws XMLStreamException, IOException{
		// 正規表現でニコニコ動画のIDを探す
		Matcher matcher = pattern.matcher(submittedText);
		
		// 見つかった場合
		if( matcher.find() ){
			String id = matcher.group(0);
			logger.warning("find id="+id);
			
			//ニコニコ動画APIのURL取得
			String url = getNiconicoApiUrl(id);
			
			// APIを使って動画の情報を検索する
			String xml = getXml(url);
			
			// 結果をパース
			NiconicoVideoParser parser = new NiconicoVideoParser();
			NiconicoVideoItem item = parser.parseXml(xml);
			if( item != null ){
				//ロボットの応答文を生成
				String robotResponse = getNiconicoVideoInfo(item); 
				return robotResponse;
			}else{
				return id+"は削除されているか、存在しませんorz";
			}
		}else{
			logger.warning("id not found. "+submittedText);
		}
		return null;
	}
	
	
	/**
	 * ロボットの応答内容の取得
	 * @param item ニコニコ動画の動画情報
	 * @return ロボットの応答内容
	 */
	private String getNiconicoVideoInfo( NiconicoVideoItem item ){
		StringBuilder sb = new StringBuilder();
		sb.append("<a href=\"");
		sb.append(item.getUrl());
		sb.append("\">");
		sb.append(item.getTitle());
		sb.append("</a><br />再生数:");
		sb.append(item.getViewCounter());
		sb.append("  コメント数:");
		sb.append(item.getCommentNum());
		sb.append(" マイリスト:");
		sb.append(item.getMylistCounter());
		sb.append("<br />");
		sb.append(item.getLastResBody());
		return sb.toString();
	}
	
	/**
	 * ニコニコ動画APIのURLを取得する。
	 * @param id ニコニコ動画の動画ID 例:sm12345
	 * @return RESTのURL
	 */
	private String getNiconicoApiUrl( String id ){
		return "http://ext.nicovideo.jp/api/getthumbinfo/" + id ;
	}
	
	/**
	 * 引数のURLからHTTPでテキストを取得する。
	 * @param urlstr 取得するURL
	 * @return HTTP GETの結果
	 * @throws IOException
	 */
	private String getXml( String urlstr ) throws IOException{
		URL url = new URL(urlstr);
		InputStream is = url.openConnection().getInputStream();
		BufferedReader br = new BufferedReader( new InputStreamReader( is , "UTF-8") ) ;
		StringBuilder sb = new StringBuilder();
		String line = null;
		while( ( line = br.readLine() ) != null ){
			sb.append(line);
			sb.append("\n");
		}
		return sb.toString();
	}
}
