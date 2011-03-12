<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8">
	<title>天下一武道会</title>

     <link href="./style_select.css" rel="stylesheet" type="text/css" />


     <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
	 <script type="text/javascript" src="./jquery.infieldlabel.min.js"></script>
	 <script type="text/javascript" src="http://gadgetparty.jp/js/main.js"></script>

</head>
<body>
	<div id="container">
        <div id="header">
            <div class="inner"><a href="./"><img src="./images/logo.png" alt="" /></a></div>
        </div>

        <div id="contents">

            <div id="stage">
                <p class="catch"><img src="./images/catch_mycards.gif" alt="" /></p>
                <div id="gadget_wrap">
                    <div id="slide-back"><a><img src="./images/arw_left.gif" alt="" /></a></div>
                    <div id="field">
                        <div id="slide-inner">
                  			<c:forEach var="e" items="${myCards}">
								<div class="card">
                         		   <div class="player">
                             		   <dl>
                             		       <dt><a href="battle?key=${f:h(e.key)}&enemy=${f:h(enemy.key)}"><img src="cardImage?key=${f:h(e.key)}" alt="" width="150"/></a></dt>
                             		       <dd><a href="battle?key=${f:h(e.key)}&enemy=${f:h(enemy.key)}">${f:h(e.userName)}</a><br />
                            		        勝率：60% 　○戦×勝△負</dd>
                           		     </dl>
                           		     <p><a href="battle?key=${f:h(e.key)}&enemy=${f:h(enemy.key)}"><img src="./images/b_battle.gif" alt="" /></a></p>
                        		    </div>
								</div>
							</c:forEach>
                        </div>
                    </div>
                    <div id="slide-next"><a><img src="./images/arw_right.gif" alt="" /></a></div>
                </div>
            </div>

            <div id="wrapper">
                <div id="enemy">
                	<p class="catch"><img src="./images/catch_enemy.gif" alt="" /></p>
                    	<div class="player">
                   			<dl>
                         		<dt><img src="cardImage?key=${f:h(enemy.key)}" alt="" width="150"/></dt>
                         		<dd>${f:h(enemy.userName)}<br />
                        		勝率：60% 　○戦×勝△負</dd>
                       		</dl>
                    	</div>
                    <p class="btn"><a href="./"><img src="./images/b_back.gif" alt="" /></a></p>
                </div>
                <div id="about">
                    <dl>
                        <dt><img src="./images/t_about.gif" alt="" /></dt>
                        <dd>天下一武道会はKINECTを使って、カメハメ波の完成度を競い合うWebサービスです。勝敗の要素はポージング以外にもあるかも？<br>
                    最強のカメハメ波をキメてアメリカの大会でニューヨーカーのド肝を抜いて抜いてヌキまくろう！</dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>


    <div id="footer">
    	<div class="inner">
        	<div class="left">
            	<p>★ チーム #hiy</p>
                <div>
                	<dl>
                    	<dt><a href="#"><img src="./images/icon_nakanishi.jpg" alt="" /></a></dt>
                        <dd><a href="#">@hIDDEN_xv</a><br>
                        Nakanishi Takayuki</dd>
                    </dl>
                    <dl>
                    	<dt><a href="#"><img src="./images/icon_tobita.jpg" alt="" /></a></dt>
                        <dd><a href="#">@tobitobita</a><br>
                        Manabe Kaorinofan</dd>
                    </dl>
                    <dl>
                    	<dt><a href="#"><img src="./images/icon_nishino.jpg" alt="" /></a></dt>
                        <dd><a href="#">@idkqh7</a><br>
                        Nishino Takuya</dd>
                    </dl>
                    <dl>
                    	<dt><a href="#"><img src="./images/icon_sasaki.jpg" alt="" /></a></dt>
                        <dd><a href="#">@shirokuro331</a><br>
                        Sasaki Toshiaki</dd>
                    </dl>
                </div>
                <ul>
                	<li><a href="#">TOP</a> |</li>
                    <li> <a href="#">遊び方</a> |</li>
                    <li> <a href="#">twitter</a> |</li>
                    <li> <a href="#">利用規約</a> |</li>
                    <li> <a href="#">お問い合わせ</a></li>
                </ul>

            </div>
            <div class="right"><img src="./images/twitter.jpg" alt="" /></div>
        </div>
        <p id="copyright">Copyright (c) #hiy</p>
    </div>



</body>

</html>


