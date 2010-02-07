package twkazemap.cron.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twkazemap.cron.service.TwitterCollectingService;

@SuppressWarnings("serial")
public class TwitterCollectingServlert extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(TwitterCollectingServlert.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOG.info("start.");
		new TwitterCollectingService().collect();
		LOG.info("end.");
	}
}
