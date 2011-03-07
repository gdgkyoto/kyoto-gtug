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
		<form action="upload" method="post" enctype="multipart/form-data">
			ユーザー : <input type="text" name="user"/> <br/>
			パワー : <input type="text" name="power" value="100"/> <br/>
			画像ファイル : <input type="file" name="image" /><br />
			<input type="submit" value="送信"/><br/>
		</form>
		<a href="./">キャンセル</a>
	</p>
</body>
</html>
