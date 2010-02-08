var groupData = {};

function GroupMap() {
	this.map = initializeMap();

	updateGroupMap(this.map);
}

function initializeMap() {
	map = new GMap2(document.getElementById("map"));
	map.addControl(new GLargeMapControl());
	map.addControl(new GMapTypeControl());
	var center = new GLatLng(36.173357, 136.224976);
	map.setCenter(center, 9);
	
	groupData.bounds = map.getBounds();

	//表示変更時のリスナ登録（パン、ズーム時）
	GEvent.addListener(map, "moveend", function() {
		//更新必要性の判断
		if (map.getBounds().containsBounds(groupData.bounds)) {
			updateGroupMap(map);
		}
	});

	return map;
}

function updateGroupMap(map) {
	$.getJSON("/group/position/", {  }, function(json){

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

/*
function getGroupData(map) {
	var groupData = { };
	//get data from server
	//TODO 少し広めの領域でデータを取得する
	var mapBounds = map.getBounds();
	var zoomLevel = map.getZoom();
	groupData.bounds = mapBounds;  //取得時の領域を記憶

	//dummy
	var groups = [ { id: "id1", name: "FITEA", lat: 36.173357, lng: 136.224976 },
	               { id: "id2", name: "日本語名のグループ名", lat: 36.000000, lng: 136.000000 },
	               { id: "id3", name: "チームOther", lat: 35.350000, lng: 137.224976 }
		];
	//dummy tunagari data
	var relations = [ { group1_id: "id1", group2_id: "id2", tunagari: 3 },
	                  { group1_id: "id1", group2_id: "id3", tunagari: 10 },
	                  { group1_id: "id2", group2_id: "id3", tunagari: 1 },
		];
	groupData.groups = groups;
	groupData.relations = relations;

	var groupHashMap = new Array();
	for (var i = 0; i < groups.length; i++) {
		groupHashMap[groups[i].id] = groups[i];
	}
	groupData.groupHashMap = groupHashMap;
	return groupData;
}
*/

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
		var info = group.name + ":\n welcome";
		marker.openInfoWindow(document.createTextNode(info));
		onSelectedGroup(group);
	});
}

function addAllRelationLines(map, relations, groupHashMap) {
	for (var i = 0; i < relations.length; i++) {
		addRelationLine(map, relations[i], groupHashMap);
	}
}

function addRelationLine(map, relation, groupHashMap) {
//	var group1 = groupHashMap[relation.group1_id];
	var group1 = groupHashMap[relation[0]];
	var location1 = new GLatLng(group1.lat, group1.lng, false);
//	var group2 = groupHashMap[relation.group2_id];
	var group2 = groupHashMap[relation[1]];
	var location2 = new GLatLng(group2.lat, group2.lng, false);
//	var tunagari = relation.tunagari;
	var tunagari = relation[2] * 5;

	var polyline = new GPolyline([location1, location2], "#ff0000", tunagari, 1);
	map.addOverlay(polyline);
}

function onSelectedGroup(group) {
	var groupId = group.id;
	//グループ詳細情報をサーバから取得
	var groupDetails = getGroupDetails(groupId);

	//グループ詳細表示の更新
    var groupDetailsText = groupDetails.name + ", " + groupDetails.tags + ", " + groupDetails.url;
	document.getElementById("group_details").innerText = "選択されたグループ: " + groupDetailsText;

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
