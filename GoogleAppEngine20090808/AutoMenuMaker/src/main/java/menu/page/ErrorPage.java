package menu.page;

import menu.util.ExceptionUtils;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorPage extends WebPage {
	private static final Logger log = LoggerFactory.getLogger(ErrorPage.class);

	public ErrorPage(RuntimeException e) {
		log.error("エラーが発生しました", e);

		String trace = ExceptionUtils.getStringStackTrace(e);
		MultiLineLabel description = new MultiLineLabel("description", new Model<String>(trace));
		add(description);
	}

}
