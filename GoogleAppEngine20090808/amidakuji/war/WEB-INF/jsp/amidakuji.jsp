<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>���݂�����</head>
<body>

	<p>
	���݂������̃��C���y�[�W�i�����j�B���̃y�[�W�͏��������Ă�����Ă�OK�ł��B
	</p>

	<table border="1">
		<tr><td>id</td><td><c:out value="${id}"/></td></tr>
		<tr><td>title</td><td><c:out value="${title}"/></td></tr>
		<tr><td>currentTime</td><td><c:out value="${currentTime}"/></td></tr>
		<tr><td>endTime</td><td><c:out value="${endTime}"/></td></tr>
		<tr><td>leftTime</td><td><c:out value="${leftTime}"/> (ms)</td></tr>
		<tr><td>finished</td><td><c:out value="${finished}"/></td></tr>
	</table>

</body>
</html>
