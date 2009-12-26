package org.kyotogtug.wavesensei.tw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kyotogtug.wavesensei.tw.meta.TwPostMeta;
import org.kyotogtug.wavesensei.tw.model.TwPost;
import org.kyotogtug.wavesensei.tw.model.TwReply;
import org.kyotogtug.wavesensei.tw.model.TwSysProp;
import org.kyotogtug.wavesensei.util.XMPPHandler;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

@SuppressWarnings("serial")
public class TwitterReplyCronServlet extends HttpServlet {

  private static Logger logger =
    Logger.getLogger(TwitterReplyCronServlet.class.getSimpleName());

  private static final String LOG_NOTIFY_TO = "bufferings@gmail.com";

  static {
    logger.addHandler(new XMPPHandler(LOG_NOTIFY_TO));
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    logger.info("\r\nTwitterReplyCronServlet********************************");

    TwSysProp twSysProp = null;
    Long sinceId = null;
    Key twSysPropKey = TwSysProp.createKey();
    try {
      twSysProp = Datastore.get(TwSysProp.class, twSysPropKey);
      sinceId = twSysProp.getSinceId();
    } catch (EntityNotFoundRuntimeException e) {
      twSysProp = new TwSysProp();
      twSysProp.setKey(twSysPropKey);
    }

    Twitter twitter = new Twitter("WaveSensei", "ajsIj793jDds6");
    List<Status> statuses = new ArrayList<Status>();
    try {
      if (sinceId == null) {
        statuses = twitter.getMentions();
      } else {
        Paging paging = new Paging();
        paging.setSinceId(sinceId);
        statuses = twitter.getMentions(paging);
      }
    } catch (TwitterException te) {
      logger.warning("リプライの取得に失敗しました: " + te.getMessage());
      return;
    }

    if (statuses.size() == 0) {
      logger.info("リプライはありません\r\n");
      return;
    }

    for (int i = statuses.size() - 1; i >= 0; i--) {
      Status status = statuses.get(i);
      long inReplyToStatusId = status.getInReplyToStatusId();
      if (inReplyToStatusId == -1) {
        logger.info("リプライではありません[" + status.getText() + "]\r\n");
        twSysProp.setSinceId(status.getId());
        Datastore.put(twSysProp);
        logger.info("SinceId更新[id=" + twSysProp.getSinceId() + "]\r\n");
        continue;
      }

      long statusId = status.getId();

      Key key = Datastore.createKey(TwReply.class, statusId);
      Transaction tx = Datastore.beginTransaction();
      try {
        Datastore.get(tx, key);
        logger.info("既に存在します[" + status.getText() + "]\r\n");
        tx.rollback();
        twSysProp.setSinceId(status.getId());
        Datastore.put(twSysProp);
        logger.info("SinceId更新[id=" + twSysProp.getSinceId() + "]\r\n");
        continue;
      } catch (EntityNotFoundRuntimeException e) {
        // 存在しない場合処理を続ける
      }

      // 対応するポストを取得
      TwPostMeta m = TwPostMeta.get();
      List<TwPost> twPosts =
        Datastore.query(m).filter(
          m.postTwitId.equal(Long.toString(inReplyToStatusId))).asList();
      if (twPosts.size() == 0) {
        logger.info("対応するポストがありません[" + status.getText() + "]\r\n");
        tx.rollback();
        twSysProp.setSinceId(status.getId());
        Datastore.put(twSysProp);
        logger.info("SinceId更新[id=" + twSysProp.getSinceId() + "]\r\n");
        continue;
      }
      TwPost twPost = twPosts.get(0);

      TwReply twReply = new TwReply();
      twReply.setKey(key);
      twReply.setWaveId(twPost.getWaveId());
      twReply.setPostBlipId(twPost.getPostBlipId());
      twReply.setReplyTwitId(Long.toString(statusId));
      twReply.setUserProfileImageUrl(status
        .getUser()
        .getProfileImageURL()
        .toExternalForm());
      twReply.setUserScreenName(status.getUser().getScreenName());
      twReply.setInreplyToStatusId(Long.toString(inReplyToStatusId));
      twReply.setText(status.getText());
      twReply.setCreated(status.getCreatedAt());
      Datastore.put(tx, twReply);

      Datastore.commit(tx);
      logger.info("コミット成功\r\n");

      twSysProp.setSinceId(status.getId());
      Datastore.put(twSysProp);
      logger.info("システム更新成功[id=" + twSysProp.getSinceId() + "]\r\n");
    }

  }
}
