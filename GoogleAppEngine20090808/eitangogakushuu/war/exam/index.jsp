<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<%@page import="com.appspot.eitan.util.AuthenticationUtil" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><fmt:message key="menu.exam"/></title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
	<jsp:include page="/header.jsp">
	    <jsp:param name="title" value="Index"/>
	</jsp:include>
	<jsp:include page="/menu.jsp" />
	<div id="body">
		<h1>this is Exam page!</h1>
		
		<form method="post" action="${f:url('/exam/selectExamWord')}">
			<table>
				<caption>検索条件</caption>
				<tr>
					<th>検索回数</th>
					<td>
						<input type="text" ${f:text("searchCount")} class="${f:errorClass('searchCount', 'error')}"/>回以上 ${f:h(errors.searchCount)}
					</td>
				</tr>
				<tr>
					<th>状態</th>
					<td>
						<input type="checkbox" ${f:checkbox("status")} class="${f:errorClass('wordCount', 'error')}" />
						<input type="checkbox" ${f:checkbox("status")} class="${f:errorClass('wordCount', 'error')}" />
						<input type="checkbox" ${f:checkbox("status")} class="${f:errorClass('wordCount', 'error')}" />${f:h(errors.status)}
					</td>
				</tr>
				<tr>
					<th>試験する単語数</th>
					<td><input type="text" ${f:text("wordCount")} class="${f:errorClass('wordCount', 'error')}"/>${f:h(errors.wordCount)}</td>
				</tr>
			</table>
			
					<input type="submit" id="start" value="START!">
		</form>

		<button id="btn">hanaoka</button>

	</div>
	<jsp:include page="/footer.jsp" />

<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">

$(function(){
  $('li span:eq(2)').addClass('selected');

  if(<%= AuthenticationUtil.instance().isLogin() %>) {
    $('li span:not(2)').mouseover(function(){ $(this).addClass('hover'); }).
                     mouseout(function(){ $(this).removeClass('hover'); });
  } else {
    $('li:gt(0)').css('visibility', 'hidden');
  }

  $('#btn').click(function(){
      window.open('/exam/selectExamWord', 'mywindow1', 'width=400, height=300, menubar=no, toolbar=no, scrollbars=yes');
  });

});

</script>
</body>
</html>