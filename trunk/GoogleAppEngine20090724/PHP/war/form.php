<html>
	<head>
		<title>フォームのデータを受け取る</title>
	</head>
	<body>
<?php
echo "<p>入力内容：".@$_POST["text1"];
?>
		<form method="POST" action="<?=$_SERVER["PHP_SELF"]?>">
			<table>
				<tr>
					<td><input type="text" name="text1"></td>
					<td><input type="submit" value="送信"></td>
				</tr>
			</table>
		</form>
	</body>
</html>