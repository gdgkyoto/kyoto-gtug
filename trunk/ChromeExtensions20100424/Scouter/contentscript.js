// The library named Raphael is used to draw about SVG. 
// Raphael Reference: http://raphaeljs.com/reference.html

function onResult(result) {
	if (result.error) {
		console.log(result.error);
		return;
	}
	var container = document.createElement("div");
	container.style.float = "right";
	container.className = "scouter";
	var rhs = document.getElementById("rhs");
	if (!rhs) {
		rhs = document.createElement("div");
		rhs.style.position = "absolute";
		rhs.style.width = "264px";
		rhs.style.right = "0px";
		rhs.style.top = "0px";
		var center_col = document.getElementById("center_col");
		center_col.style.minWidth = "0px";
		center_col.parentNode.appendChild(rhs);
	}
	rhs.appendChild(container);

	showAsText(result, container);
	//showAsSvg(result, container);
	showPageRank('div.engine > ol > li > a');

	var mbEnd = document.getElementById("mbEnd");
	if (mbEnd) {
		container.appendChild(mbEnd);
	}
}
function numberFormat(n) {
	if (isNaN(n))	return "";
	
	const step = 3;
	num = "" + n;
	var to = num.length;
	var splited = [];
	do{
		var from = (0 < to-step) ? to-step : 0;
		splited.push(num.slice(from, to));
		to = to - step;
	}while (0 < to);
	return splited.reverse().join(',');
}

function showAsText(data, container) {
	var i, j;
	var html = "";
	for (i = 0; i < data.length; i++) {
		icon = '<img class="icon" align="center" src="' + chrome.extension.getURL("images/" + data[i].icon) + '">';
		link = '<a class="engine-title" href="' + data[i].url + '">' + icon + data[i].engine + '</a>';
		html += '<div class="engine">' + link + '<span class="hit">'
			+ numberFormat(data[i].total) + ' Hit!</span>' + '<ol>';
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

function showPageRank(selector) {
	var links = document.querySelectorAll(selector);
	for (var i=0; i<links.length; i++) {
		var link = links[i];
		chrome.extension.sendRequest({
			'action': 'fetchPageRank',
			'url': link.href,
			},
			function(link){
				return function(rank){
					var span = document.createElement('span');
					var klass, text;
					if (rank < 0) {
						klass = 'baserank no-pagerank';
						text = '?';
					} else {
						klass = 'baserank pagerank' + rank;
						text = "" + rank;
					}
					span.setAttribute('class', klass);
					span.innerText = text;
					link.parentNode.insertBefore(span);
				}
			}(link));
	}
}

// background.htmlにmessage passing
chrome.extension.sendRequest({
		'action': 'fetchSearchResult',
		'query': document.forms.gs.q.value
		}, onResult);

chrome.extension.sendRequest({
	'action': 'isPageRankDisplayed'
	},
	function(response){
		console.log(response);
		if (response.value != 'true')
			return;
		showPageRank('li.g.w0 > h3 > a');
	}
);
