var fontColor = "#5500AA";
var font = "bold 14px 'Book Antiqua,Century,ヒラギノ角ゴ Pro'";

function init(){
	checkLogo();	
}

function checkLogo () {
	var logo = {
		domain: "http://www.google.co.jp",
		url: "/intl/ja_jp/images/logo.gif"
	};
	
	$("#response").load("http://www.google.co.jp #logo", function(){
		var logoUrl = $('#logo').attr('src');
		var isChange = logoUrl != logo.url;

		if (isChange){
			drawRotateIcon();
		}else{
			drawIcon();
		}
	});
}

function drawIcon() {
	var canvas = document.getElementById('canvas');
	var ctx = canvas.getContext('2d');
	var gfx = document.getElementById('gfx');

	var g = "g";
	ctx.fillStyle = fontColor;
	ctx.font = font;
	ctx.textAlign = "left";
	ctx.textBaseline = "bottom";
	var textWidth = ctx.measureText(g).width;
	ctx.fillText(g, (canvas.width-textWidth)/2, 16);
	chrome.browserAction.setIcon({imageData:ctx.getImageData(0, 0, canvas.width,canvas.height)});
}

function drawRotateIcon() {
	var canvas = document.getElementById('canvas');
	var ctx = canvas.getContext('2d');
	var gfx = document.getElementById('gfx');

	var g = "g";
	ctx.fillStyle = fontColor;
	ctx.font = font;
	ctx.textAlign = "left";
	ctx.textBaseline = "bottom";
	ctx.translate(10, 10);
	var textWidth = ctx.measureText(g).width;
	var angle = 0;
	setInterval(function(){
		ctx.clearRect(-canvas.width/2,-canvas.height/2,canvas.width, canvas.height);

		ctx.rotate(angle * Math.PI / 180);
		ctx.fillText(g, -5, 6);
		angle = Math.abs(angle + 15*-1 - 360);
		chrome.browserAction.setIcon({imageData:ctx.getImageData(0, 0, canvas.width,canvas.height)});
	},50);
}

chrome.browserAction.onClicked.addListener(function(tab) {
	chrome.tabs.create({url: "http://www.google.co.jp/"}, function(tab) {
	});
});
