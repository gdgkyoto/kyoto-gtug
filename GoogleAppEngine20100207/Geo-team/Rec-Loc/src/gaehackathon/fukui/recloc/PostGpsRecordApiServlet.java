package gaehackathon.fukui.recloc;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gaehackathon.fukui.recloc.PMF;
import gaehackathon.fukui.recloc.model.GpsRecord;
import gaehackathon.fukui.recloc.model.RecLocUser;

public class PostGpsRecordApiServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		
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
			pm.makePersistent(record);
		} finally {
			pm.close();
		}
		resp.setContentType("text/plain");
		resp.getWriter().println("OK");
	}

}
