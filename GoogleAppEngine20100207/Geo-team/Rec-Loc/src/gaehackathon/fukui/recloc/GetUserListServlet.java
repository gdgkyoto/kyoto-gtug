package gaehackathon.fukui.recloc;

import gaehackathon.fukui.recloc.model.GpsRecord;
import gaehackathon.fukui.recloc.model.RecLocUser;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GetUserListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<RecLocUser> users = null;
		try {
			Query query = pm.newQuery(RecLocUser.class);
			//query.setOrdering("name");
			users = (List<RecLocUser>)query.execute();
		} finally {
			pm.close();
		}
		if (users == null) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			Document retDoc = dbf.newDocumentBuilder().getDOMImplementation().createDocument("", "users", null);
			Element userItems = retDoc.getDocumentElement();
			for (RecLocUser user : users) {
				Element userItem = retDoc.createElement("user");
				Element nameItem = retDoc.createElement("name");
				nameItem.appendChild(retDoc.createTextNode(user.getName()));
				userItem.appendChild(nameItem);
				userItems.appendChild(userItem);
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
	}
}
