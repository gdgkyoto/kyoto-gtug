//2点間の距離算出
function getDistance(longitudeFrom, latitudeFrom, longitudeTo, latitudeTo) {
	alert("getDistance start");
	var from_x;	//A地点の経度（ラジアン）
	var from_y;	//A地点の緯度（ラジアン）
	var to_x;	//B地点の経度（ラジアン）
	var to_y;	//B地点の緯度（ラジアン）
	var deg;
	var distance; //2地点の距離
	from_x = longitudeFrom * Math.PI / 180;
	from_y = latitudeFrom * Math.PI / 180;
	to_x = longitudeTo * Math.PI / 180;
	to_y = latitudeTo * Math.PI / 180;
	deg = Math.sin(from_y) * Math.sin(to_y) + Math.cos(from_y) * Math.cos(to_y) * Math.cos(to_x-from_x);
	var distance = 6378140 * (Math.atan( -deg / Math.sqrt(-deg * deg + 1)) + Math.PI / 2);
	alert("return distance = " + distance + "getDistance end");
	return Math.round(distance);
}