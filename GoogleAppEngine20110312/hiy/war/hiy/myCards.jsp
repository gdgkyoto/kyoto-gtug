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
	<h1>天下一武道会</h1>

	<p>
		<h2>対戦相手</h2>
		<img src="cardImage?key=${f:h(enemy.key)}" width="100" />
		ユーザー : ${f:h(enemy.user)}
		パワー : ${f:h(enemy.power)}
	</p>

	<p>
		<h2>自分</h2>
		<c:forEach var="e" items="${myCards}">
			<a href="battle?key=${f:h(e.key)}&enemy=${f:h(enemy.key)}">
				<img src="cardImage?key=${f:h(e.key)}" width="100" />
				パワー : ${f:h(e.power)}
			</a>
			<hr />
		</c:forEach>
	</p>
</body>
</html>
