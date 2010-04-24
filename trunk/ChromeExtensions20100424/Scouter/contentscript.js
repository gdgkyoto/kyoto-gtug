// SVGの描画にRaphaelというライブラリを使用しています。
// Raphael Reference: http://raphaeljs.com/reference.html
var SVG_NS = "http://www.w3.org/2000/svg";

function onResult(result) {
	if (result.error) {
		console.log(result.error);
		return;
	}
	var res = document.getElementById("res");
	var container = document.createElement("div");
	container.style.float = "right";
	res.parentNode.insertBefore(container, res);
	var paper = Raphael(container, 200, 400);
	paper.text(100, 20, "Search from " + result.length + " engines.");
	paper.text(100, 40, result[2].data[0].title);
	paper.text(100, 60, result[2].data[1].title);
	paper.text(100, 80, result[2].data[2].title);
}

function makeSvg(data) {
	var svg = Raphael(0, 0, 200, 400);
	return svg;
}

// background.htmlにmessage passing
chrome.extension.sendRequest({
		'action': 'fetchSearchResult',
		'query': document.forms.gs.q.value
		}, onResult);

