//
//
//

function getXMLHttpRequestObject() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			return new ActiveXObject("Microsoft.MLHTTP");
		}
	}
	return null;
}

function post_message(requestObj, msg, place, callback) {
	requestObj.open('POST', '/messages.json');
	data = "text=" + encodeURIComponent(msg);
	if (place != null) {
		data += "&latitude=" + encodeURIComponent(place[0]);
		data += "&longitude=" + encodeURIComponent(place[1]);
	}
	requestObj.onreadystatechange = callback;

	requestObj.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	requestObj.send(data);
}

function get_messages(requestObj, area, callback) {
	var path = '/messages.json';

	if (area) {
		path += "?lat=" + area.center.latitude + "&lon="
				+ area.center.longitude + "&rad=" + area.radius;
	}

	requestObj.open('GET', path);
	callback.request = requestObj;
	requestObj.onreadystatechange = callback;
	requestObj.send(null);
}

function postData(request, path, data, callback) {
	request.open('POST', path);
	request.onreadystatechange = callback;
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send(data);
}

function updateUserLocation(userId, lat, lon, callback) {
	var path = "/users/" + userId;
	var data = "lat=" + encodeURIComponent(lat) + "&lon="
			+ encodeURIComponent(lon);
	var req = getXMLHttpRequestObject();
	postData(req, path, data, updateUserLocationResultHandler);
}

function updateUserLocationResultHandler() {
	// if (theRequest.readyState == 4 && theRequest.status == 200) {
	// // Succeeded
	// alert("Succeeded");
	// } else {
	// // alert("Failed");
	// }
}

var TweetMarker = function(latlng, message) {
	this.latlng = latlng;
	this.message = message;
}

TweetMarker.prototype = new GOverlay();

TweetMarker.prototype = {
	initialize : function(map) {
		var div = document.createElement("div");
		div.style.border = "1px solid #ddd";
		div.style.background = "#ffffff";
		div.style.position = "absolute";
		div.style["-border-radius"] = "8px";
		div.style["-moz-border-radius"] = "8px";		
		div.style["padding"] = "4px";
		
		map.getPane(G_MAP_MAP_PANE).appendChild(div);
		this.map = map;
		this.div = div;
	},
	remove : function(map) {
		if(this.div){
			this.div.parentNode.removeChild(this.div);
		}
	},
	copy : function() {
		return new TweetMarker(this.latlng,this.message);
	},
	redraw : function(force) {
		if(!force) return;
		
		j$div = jQuery(this.div);
		j$div.hide();
				
		var center = this.map.fromLatLngToDivPixel(this.latlng);		
		var msg = this.message.text;
		this.div.style.width = msg.length * 20 + "px";
		this.div.style.height = "20px";
		this.div.style.left = (center.x - msg.length * 12) + "px";
		this.div.style.top = center.y + "px";
		this.div.innerHTML = msg;		
		
		j$div.fadeIn();
	}
}
