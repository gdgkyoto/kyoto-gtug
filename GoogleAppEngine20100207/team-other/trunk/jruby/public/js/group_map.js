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
	map.setCenter(center, 8);
	
	groupData.bounds = map.getBounds();

	//表示変更時のリスナ登録（パン、ズーム時）
	GEvent.addListener(map, "moveend", function() {
		//TODO 更新必要性の判断
//		if (!map.getBounds().containsBounds(groupData.bounds)) {
			updateGroupMap(map);
//		}
	});

	return map;
}

function updateGroupMap(map) {
	groupData = getGroupData(map);
	//clear all markers and lines
	map.clearOverlays();

	//add new marker and lines
	addAllGroupMarkers(map, groupData.groups);
	addAllRelationLines(map, groupData.relations, groupData.groupHashMap);
}

function getGroupData(map) {
	var groupData = { };
	//get data from server
	//TODO 少し広めの領域でデータを取得する
	var mapBounds = map.getBounds();
	var zoomLevel = map.getZoom();
	groupData.bounds = mapBounds;  //取得時の領域を記憶
	///////////TODO

	//dummy
	var groups = [ { id: "id1", name: "FITEA", lat: 36.173357, lng: 136.224976 },
	               { id: "id2", name: "日本語名のグループ名", lat: 36.000000, lng: 136.000000 },
	               { id: "id3", name: "LONG NAME", lat: 35.350000, lng: 137.224976 }
		];
	//dummy tunagari data
	var relations = [ { group1_id: "id1", group2_id: "id2", tunagari: 3 },
	                  { group1_id: "id1", group2_id: "id3", tunagari: 10 },
	                  { group1_id: "id2", group2_id: "id3", tunagari: 1 },
		];
	var groupHashMap = new Array();
	for (var i = 0; i < groups.length; i++) {
		groupHashMap[groups[i].id] = groups[i];
	}

	groupData.groups = groups;
	groupData.groupHashMap = groupHashMap;
	groupData.relations = relations;
	return groupData;
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
	var group1 = groupHashMap[relation.group1_id];
	var location1 = new GLatLng(group1.lat, group1.lng, false);
	var group2 = groupHashMap[relation.group2_id];
	var location2 = new GLatLng(group2.lat, group2.lng, false);

	var polyline = new GPolyline([location1, location2], "#ff0000", relation.tunagari);
	map.addOverlay(polyline);
}

function onSelectedGroup(group) {
	//	    alert("onSelectedGroup: " + "group.id = " + group.id + ", group.name = " + group.name);

	var groupId = group.id;
	//TODO グループ詳細情報をサーバから取得

	var groupDetails = {
			id: "id1",
			name: "FITEA",
			description: "こんなグループです",
			tags: "GAE, RIA, Java, Python, Ruby",
			url: "http://www.www.www"
		};

	//グループ詳細表示の更新
	//TODO
	document.getElementById("group_details").innerText = "Selected Group: " + group.name;

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

function initialize() {
	var groupMap = new GroupMap();
}
