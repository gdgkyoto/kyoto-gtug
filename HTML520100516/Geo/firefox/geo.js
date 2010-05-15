function currentPositionRun() {
	alert("currentPositionRun start");
	var position = navigator.geolocation.getCurrentPosition(onSuccess)//, onError,
//	{
//		enableHighAccuracy: true;
//		maximumAge: 0;
	//	});
	alert("currentPositionRun end");
	return position;
}

function callback(position) {
	lat = position.coords.latitude
	lng = position.coords.longitude;
	var src = 'http://maps.google.com/staticmap?center=' + lat + ',' + lng
			+ '&zoom=14&size=400x300&markers=' + lat + ',' + lng;
	var img = $('<img/>').attr('src', src).appendTo('#here');
}

function onSuccess(position) {
	alert("緯度  :" + position.coords.latitude + " 経度 ：" + position.coords.longitude);
}

function onError(error) {
	if (error === error.TIMEOUT) {
		alert("現在利用可能な位置情報が存在しません。");
	} else {
		alert("エラーが発生しました。");
	}
}