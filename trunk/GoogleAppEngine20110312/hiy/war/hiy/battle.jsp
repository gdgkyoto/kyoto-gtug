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
		<h2>自分</h2>
		<img src="cardImage?key=${f:h(myCard.key)}" width="100" />
		ユーザー : ${f:h(myCard.user)}
		パワー : ${f:h(myCard.power)}
	</p>

	<p>
		<h2>相手</h2>
		<img src="cardImage?key=${f:h(enemy.key)}" width="100" />
		ユーザー : ${f:h(enemy.user)}
		パワー : ${f:h(enemy.power)}
	</p>

	<p>
		<a href="./">天下一武道会 トップ</a>
	</p>
</body>
</html>
