//2点間の距離算出
function getDistance(longitudeFrom, latitudeFrom, longitudeTo, latitudeTo) {
	var from_x;	//Fromの経度
	var from_y;	//Fromの緯度
	var to_x;	//Toの経度
	var to_y;	//Toの緯度
	var deg;
	var distance; //2地点の距離
	from_x = longitudeFrom * Math.PI / 180;
	from_y = latitudeFrom * Math.PI / 180;
	to_x = longitudeTo * Math.PI / 180;
	to_y = latitudeTo * Math.PI / 180;
	deg = Math.sin(from_y) * Math.sin(to_y) + Math.cos(from_y) * Math.cos(to_y) * Math.cos(to_x-from_x);
	var distance = 6378140 * (Math.atan( -deg / Math.sqrt(-deg * deg + 	1)) + Math.PI / 2);
	return round(distance);
}

function round(value){
	value = value * 100;
	value =  Math.round(value);
	return value / 100;	// 単位はX.XXメートル
}
