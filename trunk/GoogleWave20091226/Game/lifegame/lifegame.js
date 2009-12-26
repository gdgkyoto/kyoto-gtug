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
function init()
{

	var tt = _gel("main");
	var d= document;
	var table = d.createElement('table');
	for (var i = 0; i< height; i++) {
		var ht = d.createElement('tr');
		for (var j = 0; j< width; j++) {
			var td = d.createElement("td");
			td.style.backgroundColor = "white";
			ht.appendChild(td);
			td.addEventListener("click", function(e) {e.target.style.backgroundColor="green"; }, false );
		}
		table.appendChild(ht);
	}
	tt.appendChild(table);
	var list= document.getElementsByTagName('td');

	list[0].style.backgroundColor="red";
	list[6].style.backgroundColor="red";
}
gadgets.util.registerOnLoadHandler(init);
