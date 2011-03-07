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
		<form action="upload" method="post" enctype="multipart/form-data">
			ユーザー : <input type="text" name="userID" value="${f:h(userID)}">
			<input type="text" name="userName" value="${f:h(userName)}"> <br/>
			パワー : <input type="text" name="power" value="100"/> <br/>
			画像ファイル : <input type="file" name="image" /><br />
			色 :
			<select name="color">
				<option value="R">赤</option>
				<option value="G">緑</option>
				<option value="B">青</option>
				<option value="O">オレンジ</option>
			</select> <br/>
			<input type="submit" value="送信"/><br/>
		</form>
	</p>

	<p>
		<a href="./">天下一武道会 トップ</a>
	</p>
</body>
</html>
