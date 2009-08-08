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
<style type="text/css">
<!--
span.subject {
	font-weight:bold;
}
-->
</style>

</head>
<body>

<h1>This is Exam Word Page!!</h1>

<form action="/exam/saveResult">
<div>
	<span class="subject">
		Ｑ<%= request.getAttribute("index") %>．
	</span>
	<br/>
	&nbsp;&nbsp;<%= ((WordInfo)request.getAttribute("word")).getSpell() %>
</div>

<br/>
<br/>
<div class="anserArea">
	<span class="subject">
	ＡＮＳＷＥＲ．
	</span>
	<br/>
	<c:forEach var="m"  items="${word.meaninglist}" varStatus="meanIndex">
		&nbsp;&nbsp;${meanIndex.index+1}．&nbsp;${f:h(m.jptext)}<br/>
	</c:forEach>

	<br/>
	<br/>
	<span class="subject">
	評価☆
	</span>
	<div>
		&nbsp;&nbsp;<button onclick="document.getElementById('scoreId').value='3';submit();">&nbsp;覚えてた！&nbsp;</button>
		<button onclick="submit();" name="score" value="2">&nbsp;&nbsp;微妙。&nbsp;&nbsp;</button>
		<button onclick="submit();" name="score" value="1">(((( ；ﾟДﾟ))</button>
	</div>
</div>
<input type="hidden" name="index"   value="<%= request.getAttribute("index") %>">
<input type="hidden" name="wordKey" value="<%= ((WordInfo)request.getAttribute("word")).getKey() %>">
<input type="hidden" name="score"   id="scoreId">
</form>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">

$(function(){

});

</script>
</body>
</html>
