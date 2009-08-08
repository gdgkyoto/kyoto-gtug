<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<%@page import="com.appspot.eitan.model.WordInfo"%><html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>translate Translate</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>

<h1>Result of Translattion</h1>
<div>
	<%= ((WordInfo)request.getAttribute("wordInfo")).getSpell() %>
</div>

<c:forEach var="m"  items="${word.meaninglist}">
	${f:h(m.jptext)}
</c:forEach>

<a href="${f:url('translate')}">${f:url('translate')}</a>
</body>
</html>
