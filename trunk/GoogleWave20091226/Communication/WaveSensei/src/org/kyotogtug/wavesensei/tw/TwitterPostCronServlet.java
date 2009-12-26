package org.kyotogtug.wavesensei.tw;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kyotogtug.wavesensei.tw.meta.TwPostMeta;
import org.kyotogtug.wavesensei.tw.model.TwPost;
import org.kyotogtug.wavesensei.util.XMPPHandler;
import org.slim3.datastore.Datastore;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@SuppressWarnings("serial")
public class TwitterPostCronServlet extends HttpServlet {

  private static Logger logger =
    Logger.getLogger(TwitterPostCronServlet.class.getSimpleName());

  private static final String LOG_NOTIFY_TO = "bufferings@gmail.com";

  static {
    logger.addHandler(new XMPPHandler(LOG_NOTIFY_TO));
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (req.getParameter("test") != null) {
      for (int i = 0; i < 5; i++) {
        TwPost twPost = new TwPost();
        twPost.setContent("かきくけこ" + i);
        twPost.setWaveId("SampleWaveId");
        twPost.setPostBlipId("SampleBlipId" + i);
        twPost.setCreated(new Date());
        Datastore.put(twPost);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
      }
      return;
    }

    logger.info("\r\nTwitterPostCronServlet********************************");

    // ついったーへ未送信のBlip情報を取得
    TwPostMeta m = TwPostMeta.get();
    List<TwPost> twPosts =
      Datastore.query(m).filter(m.postTwitId.equal(null)).sortInMemory(
        m.created.asc).asList();
    if (twPosts.size() == 0) {
      logger.info("ポストはありません\r\n");
      return;
    }

    Twitter twitter = new Twitter("WaveSensei", "ajsIj793jDds6");
    for (TwPost twPost : twPosts) {
      logger.info("Before TwPost:" + twPost.toString());
      try {
        // ついったーへ送信
        Status status = twitter.updateStatus(twPost.getContent());
        String postTwitId = Long.toString(status.getId());

        // データストアへ保存
        twPost.setPostTwitId(postTwitId);
        Datastore.put(twPost);

        logger.info("After TwPost:" + twPost.toString());
      } catch (TwitterException e) {
        logger.warning("PostToTwitter Failed: " + e.getMessage());
      } catch (RuntimeException e2) {
        logger.warning(e2.getMessage());
      }
    }
  }
}
