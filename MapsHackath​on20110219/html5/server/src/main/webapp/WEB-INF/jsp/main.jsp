<html>
<head>
<title>Welcome</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">
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
	});
</script>
</head>
<body>

<h2>People are waiting for our help!</h2>
<div id="map" style="width:100%;height:500px;background-color: gray;"></div>
<div>Today's help calls: 20</div>
<div id="help-list"></div>
<div id="sound"></div>

</body>
</html>