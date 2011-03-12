<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>


<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8">
	<title>天下一武道会</title>

     <link href="./style_battle.css" rel="stylesheet" type="text/css" />


     <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
	 <script type="text/javascript" src="http://cloud.github.com/downloads/heygrady/transform/jquery.transform-0.9.3.min.js"></script>
	 <script type="text/javascript" src="battle.js"></script>

</head>
<body>
    <div id="layer">
    <p></p>
    </div>
    <p id="result"></p>
	<div id="container">

		<div id="top-link"><a href="/hiy/">test</</a></div>

        <div class="player me">
            <dl>
                <dt><a href="#"><img src="cardImage?key=${f:h(myCard.key)}" alt="" /></a></dt>
                <dd><a href="#">${f:h(myCard.userName)}</a><br />
                勝率：60% 　○戦×勝△負</dd>
            </dl>
        </div>

        <div class="player enemy">
            <dl>
                <dt><a href="#"><img src="cardImage?key=${f:h(enemy.key)}" alt="" /></a></dt>
                <dd><a href="#">${f:h(enemy.userName)}</a><br />
                勝率：60% 　○戦×勝△負</dd>
            </dl>
        </div>

        <div id="hibana"><img src="./images/hibana.gif" alt="" /></div>

    </div>
    <input type="hiddden" id="me_power" name="me_power" value="${f:h(myCard.power)}" />
    <input type="hiddden" id="me_color" name="me_color" value="${f:h(myCard.color)}" />
    <input type="hiddden" id="enemy_power" name="enemy_power" value="${f:h(enemy.power)}" />
    <input type="hiddden" id="enemy_color" name="enemy_color" value="${f:h(enemy.color)}" />
</body>

</html>


