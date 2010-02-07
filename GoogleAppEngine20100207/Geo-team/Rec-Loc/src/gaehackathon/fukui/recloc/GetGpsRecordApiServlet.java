package gaehackathon.fukui.recloc;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gaehackathon.fukui.recloc.model.GpsRecord;
import gaehackathon.fukui.recloc.model.RecLocUser;

public class GetGpsRecordApiServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName = req.getParameter("user");
		RecLocUser user = new RecLocUser(userName);
		
		int startPoint = 0;
		int maxPoints = 100;
		try {
			String str = req.getParameter("st");
			if (str != null) {
				startPoint = Integer.parseInt(str);
			}
			str = req.getParameter("max");
			if (str != null) {
				maxPoints = Integer.parseInt(str);
			}
		} catch (NumberFormatException nfe) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(GpsRecord.class);
		query.setFilter("ownerName == replOwnerName");
		query.setOrdering("postGpsDate");
		query.setRange(startPoint, (startPoint + maxPoints));
		query.declareParameters("String replOwnerName");
		List<GpsRecord> recs = (List<GpsRecord>)query.execute(user.getName());
		
		/*
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select from ").append(GpsRecord.class.getName());
		queryStr.append(" where ownerName == replOwnerName"
				+ " order by postGpsDate desc");
		queryStr.append(" range ").append(startPoint).append(',').append(maxPoints);
		queryStr.append(" parameters String replOwnerName");
		System.out.println("query = " + queryStr.toString());
		List<GpsRecord> recs = (List<GpsRecord>)pm.newQuery(queryStr.toString()).execute(user.getName());
		*/
		
		//Document retDoc = new javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		//Element pointElement = retDoc.createElement("Point");
		StringBuilder sb = new StringBuilder();
		for (GpsRecord rec : recs) {
			sb.append(rec.getGpsDate().getTime()).append('\t');
			sb.append(rec.getLongitude()).append('\t');
			sb.append(rec.getLatitude()).append("\r\n");
		}
		resp.setContentType("text/plain");
		resp.getWriter().println(sb.toString());
	}

}
