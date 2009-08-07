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

ユーザー情報
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

<form action="${f:url('reset')}" method="post">
サンプルデータ初期化<br />
<input type="hidden" name="clean" value="0" />
<input type="submit" value="Reset" />
</form>

<form action="${f:url('reset')}" method="post">
全データ削除<br />
<input type="hidden" name="clean" value="1" />
<input type="submit" value="Clean" />
</form>
</body>
</html>
