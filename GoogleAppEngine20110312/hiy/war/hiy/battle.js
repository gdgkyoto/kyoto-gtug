// JavaScript Document

var timerId
var seconds = 3;

function startGatagata() {
    timerId = setInterval( "displayGatagata()", 50 );
}

var gatagataCount = 0;
var val = '1'

function displayGatagata() {
	$('#hibana').css({
		display: 'block'
	});
	if(0 == gatagataCount % 2) {
		$('.player').css({
			'-webkit-transform':'rotate(' + val + 'deg)'
			, '-moz-transform':'rotate(' + val + 'deg)'
		});
	} else {
		$('.player').css({
			'-webkit-transform':'rotate(-' + val + 'deg)'
			, '-moz-transform':'rotate(-' + val + 'deg)'
		});
	}
	++gatagataCount;
	if(60 < gatagataCount) {
		clearInterval(timerId);
		$('.player').css({
			'-webkit-transform':'rotate(0deg)'
			, '-moz-transform':'rotate(0deg)'
		});
		var win = meWin($('#me_power').val(), $('#me_color').val(), $('#enemy_power').val(), $('#enemy_color').val());
		console.log(win);
		if(win) {
			displayResult('.me', 'lose');
		} else {
			displayResult('.enemy', 'win');
		}
	}
}

function meWin(mePower, meColor, enemyPower, enemyColor) {
	/*
	console.log(mePower + ',' + meColor + ',' +  enemyPower + ',' + enemyColor);
	if(!(meColor == enemyColor || ('O' == enemyColor && 'O' == meColor))) {
		if(('R' == meColor && 'B' == enemyColor) || ('G' == meColor && 'R' == enemyColor) || ('B' == meColor && 'G' == enemyColor)) {
			enemyPower = 2 * enemyPower;
		}
		if(('R' == meColor && 'G' == enemyPower) || ('G' == meColor && 'B' == enemyPower) || ('B' == meColor && 'R' == enemyPower)) {
			meColor = 2 * meColor;
		}
	}
	*/
	if(Number(mePower) < Number(enemyPower)) {
		return true;
	}
	return false;
}

function startCountDown(){
    timerId = setInterval( "displayCountDown()", 1000 );
}

function displayCountDown() {
	$('#layer p').text(seconds);
	--seconds;
	if(0 > seconds) {
		clearInterval(timerId);
		$('#layer, #layer p').css({
			'background-color':'rgba(255, 255, 255, 1)'
			, 'z-index': '0'
			, 'top':'-9999'
			, 'margin-top' : '-9999px'
			, 'width':'1px'
			, 'height': '1px'});
		startGatagata();
	}
}

function displayWin() {
	$('#result').text('Win');
	var left = Math.floor(($(window).width() - $("#result").width()) / 2);
    var top  = Math.floor(($(window).height() - $("#result").height()) / 2);
	$('#result').css({
		display:'block'
		,'top' : top
		,left : left
		,scale : [4.0, 4.0]
	});
	$('#result').animate( {
		scale: [0.7, 0.7]
	}, 400);
	$('#result').animate( {
		scale: [1.5, 1.5]
	}, 400);
	$('#result').animate( {
		scale: [1.0, 1.0]
	}, 400);
}

function displayLose() {
	$('#result').text('Lose');
	var left = Math.floor(($(window).width() - $("#result").width()) / 2);
    var top  = Math.floor(($(window).height() - $("#result").height()) / 2);
	$('#result').css({
		display:'block'
		,'top' : '-300px'
		,left : left
		,color : '#808080'
		, '-webkit-transform': 'scale(1)'
	});
	$('#result').animate( {
		top: top + 50
	}, 500);
	$('#result').animate( {
		top: top - 30
	}, 500);
	$('#result').animate( {
		top: top
	}, 500);
}

function displayResult(id, result){
	$(id).css({
		'position' : 'absolute'
	});
	var positionX = '';
	if('.me' == id) {
		positionX = '999px';
	} else {
		positionX = '-999px';
	}
	$(id).animate({
		translate:[positionX, '-999px']
		, rotate : '540deg'
	},500);
	if('win' == result) {
		$(id).queue(displayWin);
	} else {
		$(id).queue(displayLose);
	}
}

$(function(){
	displayCountDown();
	startCountDown();
});

