(function(){

	document.W = {
		
	}
	
	W.getRecentHelps(callback) {
		$.getJSON("/api/helps.json" function(json))
	}
	
	
}())
