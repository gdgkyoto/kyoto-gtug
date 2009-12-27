var width = 60;
var height = 40;
var _default = "";
var totalmember = 0;

function   idx(x, y)
{
	x = parseInt(x); y = parseInt(y);
	if (x < 0) {x = width + (x % width);}
	else if (x >= width) { x %= width; }
	if (y < 0) {y = height + (y % height);}
	else if (y >= height) { y %= height; }
	return x + y * width;
}

function push(cells, c, x, y)
{
	var ix = idx(x,y);
	return cells.substr(0, ix) + c + cells.slice(ix+1);
}

function around_cell(cells, x, y)
{
	var direction = [
		[-1, -1], [0, -1], [+1, -1],
		[-1,  0],          [+1,  0],
		[-1, +1], [0, +1], [+1, +1]];
	var sum = {};
	for (var i = 0; i< direction.length; i++) {
		var d = direction[i];
		var v = cells[idx(x+d[0], y+d[1])];
		if (v != "0") sum[v] = sum[v] +1 || 1;
	}
	ary = [];
	for (var n in sum) { ary.push([n, sum[n]]) }
	ary.sort(function(a, b) {return a[1] < b[1]? 1 : -1});

	return ary;
}

function succ(cells)
{
	var outcells = cells;
	for (var y = 0; y< height; y++) {
		for (var x = 0; x< width; x++) {
			var center = cells[idx(x, y)];
			var around_h = around_cell(cells, x,y);
			
			if (center == "0") {
				if (around_h.length == 1) {
					if (around_h[0][1] == 3) {
						outcells = push(outcells, around_h[0][0], x, y);
					}
				} else if (around_h.length > 1 && around_h[0][1] > around_h[1][1]) {
					outcells = push(outcells, around_h[0][0], x, y);
				}
			} else {
				if (around_h.length == 0 || around_h.length == 1 &&
					!(around_h[0][1] == 2 || around_h[0][1] == 3)) {
						outcells = push(outcells, "0", x, y);
				} else if (around_h.length == 1 && center != around_h[0][0]) {
					outcells = push(outcells, around_h[0][0], x, y);
				} else if (around_h.length > 1 && around_h[0][1] > around_h[1][1]) {
					outcells = push(outcells, around_h[0][0], x, y);
				} else if (around_h.length > 1 && around_h[0][1] == around_h[1][1]) {
					outcells = push(outcells, "0", x, y);
				}
			}
		}
	}
	return outcells;
}

function  parse()
{
	var state = wave.getState();
	var cells = state.get("cellstate", _default);


	for (var i = 0; i< 1; i++)
	{
		cells = succ(cells);
		drawscene(cells);
	}
	state.submitDelta({'cellstate': cells});
}

function reset()
{
	var state = wave.getState();
	var cells = state.get("cellstate", _default);
	cells = _default;
	state.submitDelta({'cellstate': cells});
}

var color = {};
var label_color = [
	"white",
	"#0000bf", 
	"#00ff00", 
	"#ff0000", 
	"#202020", 
	"#00ffff", 
	"#a0a000", 
	"#f090f0", 
	"#00e0f0",
	"#f0f080",
	"#f080f0" 
];

function plot_point(e) {
	//wave.getState().submitDelta({e.target}
	var state = wave.getState();

	var cells = state.get("cellstate", _default);
	var ary;
	var mycolor = color[wave.getViewer().getId()];

	ary = e.target.id.slice(5).split(',');
	var atpoint = cells[idx(ary[0], ary[1])];
	if (atpoint == "0"){
		cells = push(cells, mycolor, ary[0], ary[1]);
	} else if (atpoint == mycolor) {
		cells = push(cells, "0", ary[0], ary[1]);
	}

	state.submitDelta({'cellstate': cells});
}

function drawscene(cells) {
	var list= document.getElementsByTagName('td');
	for (var i = 0; i< cells.length; i++) {
		list[i].style.backgroundColor = label_color[parseInt(cells[i])];
	}
	var part = wave.getParticipants();
	for (var j = 0; j< part.length; j++) {
		var mycolor = color[part[j].getId()];
		var ar = cells.match(new RegExp(mycolor, "g")) || [];
		_gel("life"+mycolor).innerHTML = ar.length; 
	}
}

function stateUpdated() {
	var state = wave.getState();
	var cells = state.get('cellstate', _default);
	drawscene(cells);
	_gel('cellstate').innerHTML = cells;
}

function partUpdated() {
    var part = wave.getParticipants();
	totalmember = part.length;
    if (totalmember==0) return;
    var col1='<tr><th>player</th>';
	var col2='<tr><th>score</th>';
	var col3='<tr><th>stock</th>';
	for (var i = 0; i< totalmember; i++) {
		var id = part[i].getId()
		color[id] = String(i+1);
		col1 +=  '<td style="text-align: center"><img src="' + part[i].getThumbnailUrl() + 
			'" width="35" height="35"/> <span style="background-color: '+label_color[i+1]+
			'; color: '+label_color[i+1]+ '">' + "oo</span></td>";
		col2 += '<td><span id="life' + color[id] + '"></span></td>';
		col3 += '<td><span id="rest' + color[id] + '"></span></td>';
	}
	col1+="</tr>";
	col2+="</tr>";
	col3+="</tr>";
    _gel('players').innerHTML = col1+col2+col3;
}

function init()
{
	var d= document;
	var tt = _gel("main");
	var table = d.createElement('table');
	for (var i = 0; i< height; i++) {
		var ht = d.createElement('tr');
		for (var j = 0; j< width; j++) {
			var td = d.createElement("td");
			td.id = "point"+j+","+i;
			td.style.backgroundColor = "white";
			ht.appendChild(td);
			td.addEventListener("click", plot_point, false );
		}
		table.appendChild(ht);
	}
	tt.appendChild(table);

	if (wave && wave.isInWaveContainer()) {
		wave.setStateCallback(stateUpdated);
		wave.setParticipantCallback(partUpdated);
	}

	_default = "";
	for (var i = 0; i< width* height; i++) {
		_default += "0";
	}
}
gadgets.util.registerOnLoadHandler(init);
