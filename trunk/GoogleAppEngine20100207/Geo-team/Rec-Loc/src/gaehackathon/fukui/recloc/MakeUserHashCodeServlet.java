package gaehackathon.fukui.recloc;

import gaehackathon.fukui.recloc.model.GpsRecord;
import gaehackathon.fukui.recloc.model.RecLocHashCode;
import gaehackathon.fukui.recloc.model.RecLocUser;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MakeUserHashCodeServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String dispName = req.getParameter("dispname");
		if (user == null) {
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
			return;
		} else if (dispName == null || dispName.trim().length() == 0) {
			resp.sendRedirect("/makehash.html");
		}
		RecLocHashCode code = new RecLocHashCode(user.getNickname());
		// •Û‘¶
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(RecLocUser.class);
			query.setFilter("name == replUserName");
			query.declareParameters("String replUserName");
			List<RecLocUser> users = (List<RecLocUser>)query.execute(user.getNickname());
			if (users.size() == 0) {
				pm.makePersistent(code);
				RecLocUser rlUser = new RecLocUser(user.getNickname(), code.getHashCode());
				pm.makePersistent(rlUser);
			}
		} finally {
			pm.close();
		}
		StringBuilder html = new StringBuilder();
		html.append("<html><body>User <b>").append(user.getNickname());
		html.append("</b><br>Your hash code is <b>");
		html.append(code.getHashCode());
		html.append("</b>.<br></body></html>");
		resp.getWriter().println(html.toString());
	}
}
