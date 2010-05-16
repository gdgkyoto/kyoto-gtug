/**
 * Position.longitude
 * Position.latitude
 */
function getPosition() {
	var value;
	navigator.geolocation.getCurrentPosition(function(position){
		value.latitude = position.coords.latitude;
		value.longitude = position.coords.longitude;
	});
	return value;
}

function getPosition2(){
	var geo = google.gears.factory.create('beta.geolocation');
	var value;
	geo.getCurrentPosition(function(position){
		value = position;	
	});
	return value;
}