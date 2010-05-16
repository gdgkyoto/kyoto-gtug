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