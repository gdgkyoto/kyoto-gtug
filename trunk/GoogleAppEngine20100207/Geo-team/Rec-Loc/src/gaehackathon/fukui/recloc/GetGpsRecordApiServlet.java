package gaehackathon.fukui.recloc;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
		List<GpsRecord> recs = null;
		try {
			Query query = pm.newQuery(GpsRecord.class);
			query.setFilter("ownerName == replOwnerName");
			query.setOrdering("postGpsDate");
			query.setRange(startPoint, (startPoint + maxPoints));
			query.declareParameters("String replOwnerName");
			recs = (List<GpsRecord>)query.execute(user.getName());
		} finally {
			pm.close();
		}
		if (recs == null) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
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
		//*
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			Document retDoc = dbf.newDocumentBuilder().getDOMImplementation().createDocument("", "items", null);
			Element pointItems = retDoc.getDocumentElement();
			//Element pointItems = retDoc.createElement("items");
			for (GpsRecord rec : recs) {
				Element point = retDoc.createElement("item");
				Element lat = retDoc.createElement("lat");
				lat.appendChild(retDoc.createTextNode(Double.toString(rec.getLatitude())));
				point.appendChild(lat);
				Element lng = retDoc.createElement("lng");
				lng.appendChild(retDoc.createTextNode(Double.toString(rec.getLongitude())));
				point.appendChild(lng);
				pointItems.appendChild(point);
			}
			//root.appendChild(pointItems);
			
			resp.setContentType("application/xml");
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			DOMSource source = new DOMSource(retDoc);
			StreamResult result = new StreamResult(resp.getWriter());
			transformer.transform(source, result);		

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		/*/
		StringBuilder sb = new StringBuilder();
		for (GpsRecord rec : recs) {
			sb.append(rec.getGpsDate().getTime()).append('\t');
			sb.append(rec.getLongitude()).append('\t');
			sb.append(rec.getLatitude()).append("\r\n");
		}
		resp.setContentType("text/plain");
		resp.getWriter().println(sb.toString());
		//*/
	}

}
