var width = 60;
var height = 40;
var _default = "";
var totalmember = 0;

function   idx(x, y)
{
	x = parseInt(x);
	y = parseInt(y);
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
		[-1, -1], [0, -1], [1, -1],
		[-1,  0],          [1,  0],
		[-1, +1], [0, +1], [1, +1]];
	var sum = {};
	for (var d in direction) {
		var v = cells[idx(d[0], d[1])];
		sum[v] = sum[v] +1 || 1;
	}
	ary = [];
	for (var n in sum) { ary.push([n, sum[n]]) }
	ary.sort(function(a, b) {return a[1] < b[1]? 1 : -1})

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
				} else if (around_h > 1 && around_h[0][1] > around_h[1][1]) {
					outcells = push(outcells, around_h[0][0], x, y);
				}
			} else {
				if (around_h.length == 1) { 
					if (!(around_h == 2 || around_h == 3)) {
						outcells = push(outcells, "0", x, y);
					}
				} else if (around_h > 1 && around_h[0][1] > around_h[1][1]) {
					outcells = push(outcells, around_h[0][0], x, y);
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
var label_color = {
	"#0000ff", 
	"#00ff00", 
	"#ff0000", 
	"#202020", 
	"#00ffff", 
	"#a0a000", 
	"#f090f0", 
	"#00f0f0" 
};

function plot_point(e) {
	//wave.getState().submitDelta({e.target}
	var state = wave.getState();

	var cells = state.get("cellstate", _default);
	var ary;
	var mycolor = color[wave.getViewer().getDisplayName()];

	ary = e.target.id.slice(5).split(',');
	var atpoint = cells[idx(ary[0], ary[1])];
	if (atpoint == "0"){
		cells = push(cells, color[wave.getViewer().getId()], ary[0], ary[1]);
	} else if ({
		cells = push(cells, "0", ary[0], ary[1]);
	}

	state.submitDelta({'cellstate': cells});
}

function drawscene(cells) {
	var list= document.getElementsByTagName('td');
	for (var i = 0; i< cells.length; i++) {
		list[i].style.backgroundColor = label_color[parseInt(cells[i])];
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
    if (part.length==0) return;
    var all ='';
	for (var i = 0; i< part.length; i++) {
		color[part[i].getId()] = String(i+1);
		all +=  '<img src="' + part[i].getThumbnailUrl() + 
			'" width="25" height="25"/> <div style="color: '+label_color[i+1]+
			'">' + part[i].getDisplayName() + "</div>";
	}
    _gel('who').innerHTML = all;
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

    wave.setStateCallback(stateUpdated);
    wave.setParticipantCallback(partUpdated);

	var state = wave.getState();
	//if (state && !state.get('cellstate')) {
	_default = "";
	for (var i = 0; i< width* height; i++) {
		_default += "0";
	}
	//wave.getState().submitDelta({'cellstate': s});

	var list= document.getElementsByTagName('td');
    list[width+1].style.backgroundColor = "red";
}
gadgets.util.registerOnLoadHandler(init);
