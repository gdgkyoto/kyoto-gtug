<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<%@page import="com.appspot.eitan.util.AuthenticationUtil" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@page import="com.appspot.eitan.model.WordInfo"%><html>
<%@page import="com.appspot.eitan.model.UserInfo"%><html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><fmt:message key="menu.mylist"/></title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
	<jsp:include page="/header.jsp">
	    <jsp:param name="title" value="Index"/>
	</jsp:include>
	<jsp:include page="/menu.jsp" />
	
	<div id="body">
	
<%
	UserInfo userInfo = (UserInfo)request.getAttribute("userInfo");
	WordInfo wordInfo = (WordInfo)request.getAttribute("wordInfo");
%>
	<table>
	<tr><td>単語</td><td>参照回数</td><td>全利用者参照回数</td></tr>
		<c:forEach var="w"  items="${worddispinfolist}">
			<tr><td>${f:h(w.spell)}</td><td>${f:h(w.count)}</td><td>${f:h(w.publicCount)}</td></tr>
		</c:forEach>
	</table>
	</div>
	<jsp:include page="/footer.jsp" />
	
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">

$(function(){
  $('li span:eq(1)').addClass('selected');

  if(<%= AuthenticationUtil.instance().isLogin() %>) {
    $('li span:not(1)').mouseover(function(){ $(this).addClass('hover'); }).
                     mouseout(function(){ $(this).removeClass('hover'); });
  } else {
    $('li:gt(0)').css('visibility', 'hidden');
  }
});

</script>
</body>
</html>
