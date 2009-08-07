<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<%@page import="com.appspot.eitan.util.AuthenticationUtil" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><fmt:message key="menu.translate"/></title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
	<jsp:include page="/header.jsp">
	    <jsp:param name="title" value="Index"/>
	</jsp:include>
	<jsp:include page="/menu.jsp" />
	<div id="body">
		<h1>this is translate page!</h1>
	</div>
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
