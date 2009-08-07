<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>user Index</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>




<table>
<thead>
<tr><th>メールアドレス</th></tr>
</thead>
<tbody>
<c:forEach var="e" items="${userList}">
<tr>
<td>${f:h(e.email)}</td>
<c:set var="addWordUrl" value="addword?key=${e.key}&version=${e.version}"/>
<td><a href="${f:url(addWordUrl)}" onclick="return confirm('add word OK?')">Add Word</a></td>
<c:set var="deleteUrl" value="delete?key=${e.key}&version=${e.version}"/>
<td><a href="${f:url(deleteUrl)}" onclick="return confirm('delete OK?')">Delete</a></td>
</tr>
</c:forEach>
</tbody>
</table>
<hr>
<form action="${f:url('insert')}" method="post">
メールアドレス<br />
<input type="text" ${f:text("email")} class="${f:errorClass('email', 'error')}"/>${f:h(errors.email)}<br />
<input type="submit" value="Insert"/>
</form>

<a href="${f:url('')}">${f:url('')}</a>
</body>
</html>
