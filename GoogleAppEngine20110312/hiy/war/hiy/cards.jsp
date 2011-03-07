<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>天下一武道会</title>
</head>
<body>
	<p>天下一武道会</p>
	<p>
		<h1>対戦カード</h1>
		<c:forEach var="e" items="${cards}">
			<img src="cardImage?key=${f:h(e.key)}" width="100" />
			ユーザー : ${f:h(e.user)}
			パワー : ${f:h(e.power)}
			<hr />
		</c:forEach>
	</p>

	<p>
		<a href="inputCard">カード追加</a>
	</p>
</body>
</html>
