package org.kyotogtug.wavesensei.tw;

import hello.HelloLogger;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.kyotogtug.wavesensei.tw.meta.TwReplyMeta;
import org.kyotogtug.wavesensei.tw.model.TwPost;
import org.kyotogtug.wavesensei.tw.model.TwReply;
import org.kyotogtug.wavesensei.util.XMPPHandler;
import org.slim3.datastore.Datastore;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;

public class TwitterSensei {
	private static Logger logger = Logger.getLogger(TwitterSensei.class
			.getSimpleName());

	private static final String LOG_NOTIFY_TO = "bufferings@gmail.com";

	static {
	  logger.addHandler(new XMPPHandler(LOG_NOTIFY_TO));
  }


	/**
	 * ユーザーが投稿したBlipの内容をストアするメソッド
	 * 
	 * @param bundle
	 */
	public void processEvents(RobotMessageBundle bundle) {
		for (Event e : bundle.getEvents()) {
			// イベントタイプがBlip投稿だった場合
			if (e.getType() == EventType.BLIP_SUBMITTED) {
				logger.warning("find new Blip Submitted.");
				// 投稿されたメッセージを取得
				String submittedText = e.getBlip().getDocument().getText();
				// メッセージが140文字以上だったとき
				if(submittedText.length() == 0){
				  return;
				}else if (submittedText.length() > 140) {
					makeWarningBlip(e);
				} else { // データをストア
					TwPost twpost = new TwPost();
					Date now = new Date(System.currentTimeMillis());
					twpost.setContent(submittedText);
					twpost.setCreator(e.getBlip().getDocument().getAuthor());
					twpost.setPostBlipId(e.getBlip().getBlipId());
					twpost.setPostTwitId(null); // twitIDはnullをセット
					twpost.setWaveId(e.getWavelet().getWaveId());
					twpost.setCreated(now);
					// データをGoogleAppに保存
					Datastore.put(twpost);
	        logger.warning("put 成功.");
				}
			}
		}
	}

	/**
	 * ユーザーの投稿が140文字以上であれば叱るBlipを作成するメソッド
	 * 
	 * @param e
	 */
	public void makeWarningBlip(Event e) {
		// 140文字超えた時のお叱りBlip作成
		Blip blip = e.getBlip().createChild();
		TextView textView = blip.getDocument();
		String message = "ちょっとメッセージが長いです＞＜ Twitterに投げるから140文字に収まるように発言し直して下さい。";
		// メッセージを投稿
		textView.append(message);
	}

	/**
	 * 自由用途のBlip作成用
	 * 
	 * @param e
	 * @param twpost
	 */
	public void makePostBlip(Event e, TwPost twpost) {
		Blip blip = e.getBlip().createChild();
		TextView textView = blip.getDocument();
		String message = twpost.getContent();
		// メッセージを投稿
		textView.append(message);
	}

	public String makePostBlip(Blip parent, Event e, String message) {
	  
		Blip blip = parent.createChild();
		TextView textView = blip.getDocument();
		// メッセージを投稿
		textView.append(message);
		return blip.getBlipId();
	}

	/**
	 * 4の処理
	 * 
	 * @param bundle
	 */
	public void getTwPeplyData(RobotMessageBundle bundle) {
		Event e = bundle.getEvents().get(0);

	    // Blipへ未送信のTwitter情報を取得
	    TwReplyMeta m = TwReplyMeta.get();
	    List<TwReply> twReplys =
	      Datastore.query(m).filter(m.replyBlipId.equal(null),m.waveId.equal(e.getWavelet().getWaveId())).sortInMemory(
	        m.created.asc).asList();
	    if (twReplys.size() == 0) {
	      logger.info("ポストはありません\r\n");
	      return;
	    }
	    for (TwReply tr : twReplys) {
	      
	      Blip blip = find(tr.getPostBlipId(), bundle.getWavelet().getRootBlip());
	      String blipID = "-1";
	      if(blip != null){
	        blipID = makePostBlip(blip, e, tr.getText());
	      }
			tr.setReplyBlipId(blipID);
			Datastore.put(tr);
	    }
	    
	}
	
	public Blip find(String id, Blip parent){
	  List<Blip> blips = parent.getChildren();
	  for(Blip blip: blips){
	    if(blip.getBlipId().equals(id)){
	      return blip;
	    }
	    Blip ret = find(id, blip);
	    if(ret != null){
	      return ret;
	    }
	  }
	  return null;
	}
}
