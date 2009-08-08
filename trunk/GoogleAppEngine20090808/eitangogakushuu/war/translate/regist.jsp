<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<%@page import="com.appspot.eitan.util.AuthenticationUtil" %>
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
	<jsp:include page="/header.jsp">
	    <jsp:param name="title" value="Index"/>
	</jsp:include>
	<jsp:include page="/menu.jsp" />

<h1>登録しました</h1>
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

メモ：<br />
<%=(wordInfo.getMemo() != null)? wordInfo.getMemo().getValue(): ""%>

	<jsp:include page="/footer.jsp" />
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
$(function(){
  $('li span:eq(0)').addClass('selected');

  if(<%= AuthenticationUtil.instance().isLogin() %>) {
    $('li span:gt(0)').mouseover(function(){ $(this).addClass('hover'); }).
                     mouseout(function(){ $(this).removeClass('hover'); });
  } else {
    $('li:gt(0)').css('visibility', 'hidden');
  }
});
</script>
</body>
</html>
