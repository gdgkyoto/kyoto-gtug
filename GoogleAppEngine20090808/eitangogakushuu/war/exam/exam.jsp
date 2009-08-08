<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<%@page import="com.appspot.eitan.util.AuthenticationUtil" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>


<%@page import="com.appspot.eitan.model.WordInfo"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><fmt:message key="menu.translate"/></title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>

<h1>This is Exam Word Page!!</h1>
<div>
	<%= ((WordInfo)request.getAttribute("word")).getSpell() %>
</div>

<c:forEach var="m"  items="${word.meaninglist}">
	${f:h(m.jptext)}
</c:forEach>

<div>
	<%= request.getAttribute("index") %>
</div>

<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">

$(function(){

});

</script>
</body>
</html>
