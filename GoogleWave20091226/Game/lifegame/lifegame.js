var width = 50;
var height = 50;

function   idx(x, y)
{
	if (x < 0) {x = width + (x % width);}
	else if (x >= width) { x %= width; }
	if (y < 0) {y = height + (y % height);}
	else if (y >= height) { y %= height; }
	return x + y * width;
}

function  parse()
{
	var ret;
	var elmts = document.getElementsByTagName('td');
	for (var i = 0; i< elmts.length; i++) {
		ret += "0";
	}
	_gel('state') = ret;
}

function plot_point(e) {
	//wave.getState().submitDelta({e.target}
	var state = wave.getState();
	alert(state);

	var cells = state.get("cellstate");
	var ary;

	alert(cells);
	ary = e.target.id.slice(5).split(',');
	alert(ary);

	//cells[idx(ary[0], ary[1])] = "1";

	state.submitDelta({'cellstate': cells});
}

function stateUpdated() {
	var state = wave.getState();
	var cells = state.get('cellstate');
	var list= document.getElementsByTagName('td');
	for (var i = 0; i< list.length; i++) {
		switch (cells[i]) {
		case "0":
			list[i].style.backGroundColor = "white";
			break;
		case "1":
			list[i].style.backGroundColor = "green";
			break;
		default:
			alert("something wrong");
		}
	}
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
			td.id = "point"+i+","+j;
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
	alert(state);
	//if (state && !state.get('cellstate')) {
		var s;
		for (var i = 0; i< width* height; i++) {
			s += "0";
		}
		//wave.getState().submitDelta({'cellstate': s});
	//}

	var list= document.getElementsByTagName('td');
    list[width+1].style.backgroundColor = "red";
}
gadgets.util.registerOnLoadHandler(init);
