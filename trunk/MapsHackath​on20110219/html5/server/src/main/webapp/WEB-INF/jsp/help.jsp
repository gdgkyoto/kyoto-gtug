<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>Help!</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//alert("hello");
	});

	function buttonPressed() {
		var msg = $("#message").attr("value");
		$.post('<%= request.getContextPath() %>/api/help',
			{message:msg, lat:34.9, lng:134.9},
			function (data, textStatus) {
				alert("Please wait a minute!");
			}
		);
	}

</script>
</head>
<body>
<div>
<spring:message code="help.message" text="Do you need help?" />
<form action="help" method="post">
<div id="help-button" style="height:300px;">
<img alt="button" src="<%= request.getContextPath() %>/images/button.png" onclick="buttonPressed();">
</div>
<div>
<input id="message" type="text" name="message" value="Help!">
<input id="lat" type="hidden" name="lat" value="35" />
<input id="lng" type="hidden" name="lng" value="135" />
</div>
</form>
</div>
</body>
</html>