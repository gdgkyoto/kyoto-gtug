<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<%@page import="com.appspot.eitan.model.WordInfo"%><html>
<%@page import="com.appspot.eitan.model.UserInfo"%><html>
<%@page import="com.appspot.eitan.model.beans.RefInfo"%><html>
<%@page import="com.google.appengine.api.datastore.Text"%><html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>translate Translate</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>

<h1>Result of Translattion</h1>
<div>
<%
	UserInfo userInfo = (UserInfo)request.getAttribute("userInfo");
	WordInfo wordInfo = (WordInfo)request.getAttribute("wordInfo");
	RefInfo refInfo = (RefInfo)request.getAttribute("refInfo");
%>


	<%= wordInfo.getSpell() %> 参照回数: <%= refInfo.getRefCount() %>
</div>

<c:forEach var="m"  items="${wordInfo.meaninglist}">
	${f:h(m.jptext)}<br />
</c:forEach>

<hr>

<form action="${f:url('regist')}" method="post">
メモ：<br />
<textarea name="memo" cols="60" rows="5"><%=(wordInfo.getMemo() != null)? wordInfo.getMemo().getValue(): ""%></textarea><br />
<input type="hidden" name="wordinfokey" value="<%=wordInfo.getKey()%>" />
<input type="submit" value="OK"/>
</form>
</body>
</html>
