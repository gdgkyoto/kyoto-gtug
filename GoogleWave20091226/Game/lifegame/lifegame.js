var width = 50;
var height = 50;
function   idx(x, y)
{
	if (x < 0) x = width - (-x);
	if (x >= width) { x %= width; }
	if (y < 0) y = width - (-y);
	if (y >= width) { y %= width; }
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
}

function init()
{

	var tt = _gel("main");
	var d= document;
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
	var list= document.getElementsByTagName('td');
    _gel('point1,1').style.backgroundColor = "red";
}
gadgets.util.registerOnLoadHandler(init);
