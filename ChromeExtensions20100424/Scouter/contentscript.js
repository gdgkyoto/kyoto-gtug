// SVGの描画にRaphaelというライブラリを使用しています。
// Raphael Reference: http://raphaeljs.com/reference.html

function onResult(result) {
	if (result.error) {
		console.log(result.error);
		return;
	}
	var container = document.createElement("div");
	container.style.float = "right";
	container.className = "scouter";
	var tbd = document.getElementById("tbd");
	tbd.parentNode.insertBefore(container, tbd.nextSibling);

	showAsText(result, container);
	//showAsSvg(result, container);

	var mbEnd = document.getElementById("mbEnd");
	if (mbEnd) {
		container.appendChild(mbEnd);
	}
}

function showAsText(data, container) {
	var i, j;
	var html = "";
	for (i = 0; i < data.length; i++) {
		html += '<div class="engine">' + data[i].engine + ': '
			+ data[i].total + ' Hit!<ol>';
		for (j = 0; j < data[i].data.length; j++) {
			html += '<li><a href="'
				+ data[i].data[j].url + '">' + data[i].data[j].title
				+ "</a></li>";
		}
		html += "</ol></div>";
	}
	container.innerHTML = html;
}

function showAsSvg(data, container) {
	var paper = Raphael(container, 200, 400);
	paper.text(100, 20, "Search from " + data.length + " engines.");
	paper.text(100, 40, data[2].data[0].title);
	paper.text(100, 60, data[2].data[1].title);
	paper.text(100, 80, data[2].data[2].title);
}

// background.htmlにmessage passing
chrome.extension.sendRequest({
		'action': 'fetchSearchResult',
		'query': document.forms.gs.q.value
		}, onResult);
