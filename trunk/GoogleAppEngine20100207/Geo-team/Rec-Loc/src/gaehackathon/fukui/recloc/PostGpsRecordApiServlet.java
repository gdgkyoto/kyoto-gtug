package gaehackathon.fukui.recloc;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gaehackathon.fukui.recloc.PMF;
import gaehackathon.fukui.recloc.model.GpsRecord;
import gaehackathon.fukui.recloc.model.RecLocUser;

public class PostGpsRecordApiServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
		String userCode = req.getParameter("code");
		GpsRecord record = null;
		try {
			String userName = req.getParameter("user");
			RecLocUser user = new RecLocUser(userName);
			double lat = Double.parseDouble(req.getParameter("lat"));
			double lng = Double.parseDouble(req.getParameter("lng"));
			Date gpsDate = new Date(Long.parseLong(req.getParameter("dt")));
			record = new GpsRecord(user, lat, lng, gpsDate); 
		} catch (NumberFormatException nfe) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		// •Û‘¶
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(RecLocUser.class);
			query.setFilter("hashCode == rplHashCode");
			query.declareParameters("String rplHashCode");
			List<RecLocUser> users = (List<RecLocUser>)query.execute(userCode);
			if (users.size() != 1) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			pm.makePersistent(record);
		} finally {
			pm.close();
		}
		resp.setContentType("text/plain");
		resp.getWriter().println("OK");
	}

}
