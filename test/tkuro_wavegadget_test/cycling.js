function init() {
    if (wave && wave.isInWaveContainer()) {
	wave.setStateCallback(stateUpdated);
	wave.setParticipantCallback(partInfo);
	var c = document.getElementById("can");
	c.addEventListener("mousedown", function () {
		alert(wave.getState().get("speed", '50'));
	}, false);
	setTimeout("draw()", 100);
    }
}

function partInfo()
{
    var part = wave.getParticipants();
    if (part.length==0) return;
    var all ='';
    for (var i = 0; i< part.length; i++) {
	all +=  '<img src="' + part[i].getThumbnailUrl() + 
		'" width="50" height="50"/>' + part[i].getDisplayName() + "<br>";
    }
    _gel('who').innerHTML = all;
}

function change_value(diff) { 
    var speed = parseInt(wave.getState().get("speed", '50'));
    speed += diff;
    wave.getState().submitDelta({'speed': speed });
}

inc = function() {change_value(1);}
dec = function() {change_value(-1);}

var r = 0;

function inc() {
    var value = parseInt(wave.getState().get('speed', '50')) + 1;
    wave.getState().submitDelta({'speed': value });
}
function stateUpdated()
{
    var value1 = parseInt(wave.getState().get("speed", '50'));
    speed = value1;
    _gel("speed").innerHTML = value1+1;
}

function draw() {
    var c = document.getElementById("can");
    var ctx = c.getContext("2d");
    ctx.globalAlpha = 0.5;
    ctx.clearRect(0, 0, 150, 150);

    ctx.beginPath();
    ctx.fillStyle='rgb(228, 100, 162)';
    ctx.arc(60, 60, 40, r+1.0, 3.14+r, false);
    ctx.fill();

    ctx.beginPath();
    ctx.fillStyle='rgb(128, 250, 162)';
    ctx.arc(40, 60, 40, r*1.1+2.0, 4.14+r*1.1, false);
    ctx.fill();

    ctx.beginPath();
    ctx.fillStyle='rgb(38, 100, 162)';
    ctx.arc(50, 40, 40, r*1.3+3.0, 5.14+r*1.3, false);
    ctx.fill();

    r += 0.1;
    setTimeout("draw()", speed);
}
