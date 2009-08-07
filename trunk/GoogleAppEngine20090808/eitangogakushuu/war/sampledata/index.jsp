<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>sampledata Index</title>
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
</tr>
</c:forEach>
</tbody>
</table>

<hr>

<form action="${f:url('create')}" method="post">
データを初期化します<br />
<input type="submit" value="Create sample" name="create" />
<input type="submit" value="Delete all" name="delete" />
</form>

<a href="${f:url('')}">${f:url('')}</a>
</body>
</html>
