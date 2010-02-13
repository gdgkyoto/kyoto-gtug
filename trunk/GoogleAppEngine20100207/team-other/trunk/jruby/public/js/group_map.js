var groupData = {};

function GroupMap() {
	this.map = initializeMap();

	updateGroupMap(this.map);
}

function initializeMap() {
	var map = new GMap2(document.getElementById("map"));
	map.addControl(new GLargeMapControl());
	map.addControl(new GMapTypeControl());
	var center = new GLatLng(36.173357, 136.224976);
	map.setCenter(center, 9);
	
	groupData.bounds = map.getBounds();

	//表示変更時のリスナ登録（パン、ズーム時）
	GEvent.addListener(map, "moveend", function() {
		//更新必要性の判断
//		if (map.getBounds().containsBounds(groupData.bounds)) {
			updateGroupMap(map);
//		}
	});

	return map;
}

function updateGroupMap(map) {
  var bounds = map.getBounds(); 
  var southWest = bounds.getSouthWest(); 
  var northEast = bounds.getNorthEast(); 

	var params = {keido1:southWest.lng(),
	ido1:southWest.lat(),
	keido2:northEast.lng(),
	ido2:northEast.lat()}
	//サーバサイドで少し大きめに取得してますがちょっと不十分です。

	$.getJSON("/group/position/json/", params , function(json){

			var groups = json[0];
			var relations = json[1];

			var groupHashMap = new Array();
			for (var i = 0; i < groups.length; i++) {
				groupHashMap[groups[i].id] = groups[i];
			}

			var mapBounds = map.getBounds(); //TODO 大きめの領域に
			groupData.bounds = mapBounds;  //取得時の領域を記憶

			//clear all markers and lines
			map.clearOverlays();

			//add new marker and lines
			addAllGroupMarkers(map, groups);
			addAllRelationLines(map, relations, groupHashMap);

		});
}

function addAllGroupMarkers(map, groups) {
	for (var i = 0; i < groups.length; i++) {
		addGroupMarker(map, groups[i]);
	}
}

function addGroupMarker(map, group) {
	var newIcon = MapIconMaker.createFlatIcon( { shape: "roundrect", width: 100, height: 32, label: group.name, primaryColor: "#00ff00" });
	var location = new GLatLng(group.lat, group.lng, false);
	var marker = new GMarker(location, { icon: newIcon });
	map.addOverlay(marker);

	GEvent.addListener(marker, 'click', function() {
		//	         var info = "id = " + group.id + ", name = " + group.name + ",\n lat = " + group.lat + ", lng = " + group.lng;
//取得した情報ベースで表示しよう
//		var info = group.name + ":\n welcome";
//		marker.openInfoWindow(document.createTextNode(info));
		onSelectedGroup(group,marker);
	});
}

function addAllRelationLines(map, relations, groupHashMap) {
	for (var i = 0; i < relations.length; i++) {
		addRelationLine(map, relations[i], groupHashMap);
	}
}

function addRelationLine(map, relation, groupHashMap) {
	var group1 = groupHashMap[relation.group1_id];
	var location1 = new GLatLng(group1.lat, group1.lng, false);
	var group2 = groupHashMap[relation.group2_id];
	var location2 = new GLatLng(group2.lat, group2.lng, false);
	var tunagari = relation.tunagari;

	var polyline = new GPolyline([location1, location2], "#ff0000", tunagari, 1);
	map.addOverlay(polyline);
}

function onSelectedGroup(group,marker) {
	var groupId = group.id;
	//グループ詳細情報をサーバから取得
	//var groupDetails = getGroupDetails(groupId);

	$.getJSON("/group/information/json/"+groupId, {} , function(json){
	//グループ詳細表示の更新
		var groupDetails = json

		//マーカー作成
		var info = groupDetails.name + ":\n "+groupDetails.description;
		marker.openInfoWindow(document.createTextNode(info));

		//div要素変更(TODO?)
		var groupDetailsText = groupDetails.name + ", " + groupDetails.tags + ", " + groupDetails.url;
//		document.getElementById("group_details").innerText = "選択されたグループ: " + groupDetailsText;
		$('#group_details').text("選択されたグループ: " + groupDetailsText);
	});

	//タイムライン取得テスト(前田)	//nagise/hokuriku
	getTimeLine("ts0604","team-other");
	
	//twitterつぶやきの更新
	//TODO
/*	var second = false;
	$.timer(1000, function(timer) {
		if (!second) {
			document.getElementById("timeline").innerText = "つぶやき１";
			second = true;
			timer.reset(5000);
		} else {
			document.getElementById("timeline").innerText = "つぶやき１<br>つぶやき２<br>";
			timer.stop();
		}
	});
*/
}

function getGroupDetails(groupId) {
	var groupDetails = [
	               { id: "id1", name: "FITEA", description: "こんなグループです", tags: "GAE, RIA, Java, Python, Ruby", url: "http://www.www.www"},
	               { id: "id2", name: "日本語名のグループ名", description: "こんなグループです", tags: "GAE, RIA, Java, Python, Ruby", url: "http://www.www.www"},
	               { id: "id3", name: "チームOther", description: "こんなグループです", tags: "GAE, RIA, Java, Python, Ruby", url: "http://www.www.www"}
		];
	var groupDetailHashMap = new Array();
	for (var i = 0; i < groupDetails.length; i++) {
		groupDetailHashMap[groupDetails[i].id] = groupDetails[i];
	}
	
	return groupDetailHashMap[groupId];
}

function initialize() {
	var groupMap = new GroupMap();
}

// body の onload の代わりに jquery のを使うようにする。By H
// 後々リファクタリングする!!
/**
$(document).ready(function(){
	initialize();
});
**/

//タイムライン表示
function getTimeLine(screenName,listName,page) {
	getTimeLineSub(screenName,listName,page);
}

//タイムライン表示
function getTimeLine(screenName,listName) {
	getTimeLineSub(screenName,listName,1);
}

function getTimeLineSub(screenName,listName,page){
	$.getJSON("/devutil/twitter_get_list_timeline/" + screenName + "/" + listName + "/" + page + "/", {} , function(json){
		var timeLine = "";
		$(json).each(function(i, tubuyaki) {
			timeLine += tubuyaki.screen_name;
			timeLine += "  ";
			timeLine += tubuyaki.text;
			timeLine += "\n";
        });
		
//		document.getElementById("group_timeline").innerText = timeLine;	//とりあえずどこかに表示してみる
			$('#group_timeline').text(timeLine);
	});
}

