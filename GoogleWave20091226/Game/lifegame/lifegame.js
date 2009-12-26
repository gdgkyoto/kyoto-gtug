var width = 8;
var height = 8;
var _default = "";

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

function around(cells, x, y)
{
	return 
		cells[idx(x-1, y-1)] != "0"? 1: 0 +
		cells[idx(x  , y-1)] != "0"? 1: 0 +
		cells[idx(x+1, y-1)] != "0"? 1: 0 +
		cells[idx(x-1, y  )] != "0"? 1: 0 +
		cells[idx(x+1, y  )] != "0"? 1: 0 +
		cells[idx(x-1, y+1)] != "0"? 1: 0 +
		cells[idx(x  , y+1)] != "0"? 1: 0 +
		cells[idx(x+1, y+1)] != "0"? 1: 0;
}

function succ(cells)
{
	for (var y = 0; y< height; y++) {
		for (var x = 0; x< width; x++) {
			var center = cells[idx(x, y)];
			var around_h = around(cells, x,y);
			if (center == "0") {
				if (around_h == 3) {
					cells = push(cells, "1", x, y);
				}
			} else {
				if (!(around_h == 2 || around_h == 3)) {
					cells = push(cells, "0", x, y);
				}
			}
		}
	}
	return cells;
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

function plot_point(e) {
	//wave.getState().submitDelta({e.target}
	var state = wave.getState();

	var cells = state.get("cellstate", _default);
	var ary;

	ary = e.target.id.slice(5).split(',');

	cells = push(cells, "1", ary[0], ary[1]);

	state.submitDelta({'cellstate': cells});
}

function drawscene(cells) {
	var list= document.getElementsByTagName('td');
	for (var i = 0; i< cells.length; i++) {
		if (cells[i] == "0") {
			list[i].style.backgroundColor = "white";
		} else if (cells[i] == "1") {
			list[i].style.backgroundColor = "green";
		} else {
			alert(cells[i]);
			break;
		}
	}
}

function stateUpdated() {
	var state = wave.getState();
	var cells = state.get('cellstate', _default);
	drawscene(cells);
	_gel('cellstate').innerHTML = cells;
}

function partUpdated() {
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
