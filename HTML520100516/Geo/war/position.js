/**
 * Position.longitude
 * Position.latitude
 */
function getPosition(func) {
	if(navigator.userAgent.indexOf('Firefox') != -1){
		getPositionFF(func);
	}else if(navigator.userAgent.indexOf('Chrome') != -1){
		getPositionChrome(func);
	}else if(navigator.userAgent.indexOf('Android') != -1){
		getPositionChrome(func);
	}else{
		getPositionDemo(func);
	}
}

function getPositionFF(func){
	navigator.geolocation.getCurrentPosition(func);
}

var geo = null;
function getPositionChrome(func){
	if(geo === null){
		geo = google.gears.factory.create('beta.geolocation');
	}
	geo.getCurrentPosition(func);
}

function getPositionDemo(func){
	if(index >= latitudes.length){
		index = 0;
	}
	var position = {};
	position.timestamp = new Date().getTime();
	position.coords = {};
	position.coords.latitude = latitudes[index];
	position.coords.longitude = longitudes[index];
	func(position);
	index ++;
}

var index = 0;
var latitudes=[34.985706, 34.995973, 35.010315, 35.037305, 35.088591, 35.037305, 35.010315, 34.995973];
var longitudes=[135.757885, 135.759258, 135.75943, 135.75943, 135.707417, 135.75943, 135.75943, 135.759258];