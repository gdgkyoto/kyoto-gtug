<!doctype html>
<html>
<head>
<title>Welcome</title>
<meta charset="UTF-8">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">

	var buzzer = new Audio("<%= request.getContextPath() %>/static/beep.mp3");
	buzzer.loop = true;
	
	// ページロード直後に呼ばれる
	$(document).ready(function() {
		$.getJSON("<%= request.getContextPath() %>/api/helps.json", function(json){
			var helpList = $("#help-list");
			for (var i = 0; i < json.length; i++) {
				var help = json[i];
				helpList.append("<p>" + help.id + ":" + help.message + ":(" + help.lat + "," + help.lng + ")</p>");
			}
		});
		
		var latlng = new google.maps.LatLng(34.995, 135.74);
		var options = {
			center: latlng,
			zoom: 15,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map($("#map")[0], options);

		var ws = new WebSocket("ws://www.smkw.jp:8080/savetheworld/ws/standby");
		//var ws = new WebSocket("ws://localhost:8080/ws/standby");
		ws.onopen = function(event) {
			var result = ws.send(JSON.stringify("hello"));
			if (result) {
				//alert("Wow!");
			}
		}
		ws.onclose = function(event) {
			alert("Close!");
		};
		ws.onmessage = function(event) {
			alert(event.data);
		}

		//buzzer.load();	// 音声ファイルをロードする
	});
	
	function toggleBuzzer() {
		if (buzzer.paused) {
			buzzer.play();
		} else {
			buzzer.pause();
		}
	}
</script>
</head>
<body>

<h2>People are waiting for our help!</h2>
<button onclick="toggleBuzzer();">Buzzer</button>
<div id="map" style="width:100%;height:500px;background-color: gray;"></div>
<div>Today's help calls: 20</div>
<div id="help-list"></div>
<div id="sound"></div>

<!-- 
<audio src="<%= request.getContextPath() %>/static/beep.mp3" autoplay autobuffer loop="loop" ></audio>
 -->
</body>
</html>