// SVGの描画にRaphaelというライブラリを使用しています。
// Raphael Reference: http://raphaeljs.com/reference.html
var SVG_NS = "http://www.w3.org/2000/svg";

function onResult(result) {
	var res = document.getElementById("res");
	var container = document.createElement("div");
	container.style.float = "right";
	res.parentNode.insertBefore(container, res);
	var paper = Raphael(container, 200, 400);
	paper.text(100, 20, "Search from " + result.length + " engines.");
}

function makeSvg(data) {
	var svg = Raphael(0, 0, 200, 400);
	return svg;
}

// background.htmlにmessage passing
chrome.extension.sendRequest({'action': 'fetchSearchResult', 'query': 'hoge'}, onResult);
