/**
 * Position.longitude
 * Position.latitude
 */
function getPosition(func) {
	if(navigator.userAgent.indexOf('Firefox') != -1){
		getPositionFF(func);
	}else if(navigator.userAgent.indexOf('Chrome') != -1){
		getPositionChrome(func);
	}else{
		getPositionDemo(func);
	}
}

function getPositionFF(func){
	navigator.geolocation.getCurrentPosition(function(position){
		var value = {};
		value.latitude = position.coords.latitude;
		value.longitude = position.coords.longitude;
		func(value);
	});
}

var geo = null;
function getPositionChrome(func){
	if(geo === null){
		geo = google.gears.factory.create('beta.geolocation');
	}
	geo.getCurrentPosition(function(position){
		func(position);
	});
}

function getPositionDemo(func){
	if(index >= latitudes.length){
		index = 0;
	}
	var position = {};
	position.latitude = latitudes[index];
	position.longitude = longitudes[index];
	func(position);
	index ++;
}

var index = 0;
var latitudes=[34.996257, 34.996267, 34.996277, 34.996267];
var longitudes=[135.739085, 135.739095, 135.739085, 135.739075];