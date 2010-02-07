<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.ArrayList" %>

<html>
  <body>

	<form method="post" action="/setKeyword">
		<p>新規キーワード<br>
		<input type="text" name="setKeyword" size="50" value="">
		<input type="submit" value="登録"></p>
	</form>
	<%
	 	ArrayList<String> keywordList =  (ArrayList<String>)request.getAttribute("keywordList");
	
		if(keywordList == null)
		{
			keywordList = new ArrayList<String>();
		}
	
		for (String keyword : keywordList) {
	%>
	
	<%= keyword %><br>
	
	<%
		}
	%>
	
  </body>
</html>
