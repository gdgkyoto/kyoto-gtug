/**
 * Position.longitude
 * Position.latitude
 */
function getPosition(func) {
	var value;
	navigator.geolocation.getCurrentPosition(function(position){
		value.latitude = position.coords.latitude;
		value.longitude = position.coords.longitude;
		func(value);
	});
}

function getPosition2(func){
	var geo = google.gears.factory.create('beta.geolocation');
	geo.getCurrentPosition(function(position){
		func(position);
	});
}

function getPosition3(func){
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
var latitudes=[34.996257, 34.996258, 34.996259, 34.996258];
var longitudes=[135.739085, 135.739086, 135.739085, 135.739084];