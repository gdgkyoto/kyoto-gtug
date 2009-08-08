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
	var data = "lat=" + encodeURIComponent(lat) + "&lon=" + encodeURIComponent(lon);
	var req = getXMLHttpRequestObject();
	postData(req, path, data, updateUserLocationResultHandler);
}

function updateUserLocationResultHandler() {
//	if (theRequest.readyState == 4 && theRequest.status == 200) {
//		// Succeeded
//		alert("Succeeded");
//	} else {
//		// alert("Failed");
//	}
}
