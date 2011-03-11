<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>天下一武道会</title>
<style type="text/css">
	body {
		background-image: url(images/tenga1.jpg);
		background-repeat: no-repeat;
		padding-left: 130px;
		height: 1477px;
	}

	div.cards {
		margin-top: 300px;
	}

	div.card {
		float: left;
		margin-left: 20px;
	}

	div.cardImage {
		width: 100px;
		height: 100px;
	}

	p {
		clear: left;
	}
</style>
</head>
<body>
	<div class="cards">
		<c:forEach var="e" items="${cards}">
			<div class="card">
				<a href="myCards?key=${f:h(e.key)}">
					<div class="cardImage">
						<img src="cardImage?key=${f:h(e.key)}" width="100" />
					</div>
					${f:h(e.userName)}
				</a>
			</div>
		</c:forEach>
		<br/>
	</div>

	<p>
		<br/>
		<a href="inputCard">カード追加</a>
	</p>
</body>
</html>
