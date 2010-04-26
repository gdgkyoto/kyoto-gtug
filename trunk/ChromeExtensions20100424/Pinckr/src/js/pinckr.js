var Pinckr = {
	search: function(keyword) {
		keyword = keyword.trim();
		$.get(
		    "http://api.flickr.com/services/rest/",
		    {
			    "method":"flickr.photos.search",
			    "api_key":"90485e931f687a9b9c2a66bf58a3861a",
			    "text":keyword,
			    "safe_search":2,
			    "content_type":1,
			    "sort":"relevance",
			    "per_page":20
		    }, 
		    Pinckr.show)
	},
		
	show: function(searchResult) {
		var photos = searchResult.getElementsByTagName("photo");
		
		//Pinckr.refreshList();
		
		if (photos.length > 0) {
			$("#logo_image").remove();
			console.log("写真の取得数:" + photos.length);
		}
		
		for (var i = 0, photo; photo = photos[i]; i++) {
			var img = document.createElement("img");
			img.src = Pinckr.createImageURL(photo);
			var li = document.createElement("li");
			li.appendChild(img);
			$(".filmstrip").append(li);
		}
		
		Pinckr.setupGallery();
	},
	
	refreshList: function() {
		//$(".panel").remove();
		$(".filmstrip").empty();
		$(".filmstrip").add('<li id="logo_image"><img src="icon_128.png" /></li>');
	},

	// See: http://www.flickr.com/services/api/misc.urls.html
	createImageURL: function(data) {
	  return "http://farm" + data.getAttribute("farm") +
	      ".static.flickr.com/" + data.getAttribute("server") +
	      "/" + data.getAttribute("id") +
	      "_" + data.getAttribute("secret") +
	      ".jpg";
	},
	
	setupGallery: function() {
		$("#gallery").galleryView({
			panel_width: 480,
			panel_height: 320,
			frame_width: 64,
			frame_height: 64,
			transition_speed: 1200,
			background_color: "#222",
			border: "none",
			easing: "easeInOutBack",
			pause_on_hover: true,
			nav_theme: "custom",
			overlay_height: 52,
			panel_scale: "nocrop",
			filmstrip_position: "right",
			overlay_position: "right"
		});
	},
	
	loadBijin: function() {
		var now = new Date();
		var hh = now.getHours();
		var mm = now.getMinutes()
		
		var startMin = hh * 60 + mm;
		var stopMin = startMin + 60;
		
		$("#logo_image").remove();
		
		for (var i = 0; i < 60; i++) {
			var current = startMin + i;
			var hh = parseInt(current / 60);
			var mm = current % 60;
			if (hh < 10) { hh = "0" + hh; }
			if (mm < 10) { mm = "0" + mm; }

			var img = document.createElement("img");
			img.src = "http://f.air-life.net/crx/bijin/images/" + hh + mm + ".jpg";
			var li = document.createElement("li");
			li.appendChild(img);
			$(".filmstrip").append(li);
		}
	}
};


$(function(){
	Pinckr.loadBijin();
	// 入力フォームサポート
	$.updnWatermark.attachAll();

	// TODO searchable check
	// $("#search_form").css("display", "none");
	
	Pinckr.setupGallery();
	
	$("#search_button").click(function() {
		var keyword = $("#search").val();
		console.log("検索キーワード:" + keyword);
		
		Pinckr.search(keyword)
	});
	
	//search([key1,key2,key3])
});