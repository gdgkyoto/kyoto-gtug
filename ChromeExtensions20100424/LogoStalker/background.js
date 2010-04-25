(function(w){
	var logoStalker = {
		isChange: false
	};
	
	logoStalker.setttings = {
		canvas: 'canvas',
		normal: {
			moji: 'g',
			color: '#5500AA',
			font: "bold 14px 'Book Antiqua,Century,ヒラギノ角ゴ Pro'"			
		},
		rotate: {
			moji: 'g',
			color: '#5500AA',
			font: "bold 14px 'Book Antiqua,Century,ヒラギノ角ゴ Pro'"		
		}
	}
	
	logoStalker.checkLogo = function(){
		var logo = {
			domain: "http://www.google.co.jp",
			url: "/intl/ja_jp/images/logo.gif"
		};
		var that = this;

		return function(callback) {
			$("#response").load("http://www.google.co.jp #logo", function(){
				var logoUrl = $('#logo').attr('src');
				that.isChange = logoUrl != logo.url;

				callback();
			});			
		}
	}();
	
	logoStalker.drawIcon = function(){
		if (this.isChange){
			this.drawRotateIcon();
		}else{
			this.drawNormalIcon();
		}
	}
	
	logoStalker.drawNormalIcon = function(){
		var config = this.setttings.normal;
		var canvas = document.getElementById(this.setttings.canvas);
		var ctx = canvas.getContext('2d');
		var g = config.moji;
		var textWidth = ctx.measureText(g).width;

		ctx.clearRect(0, 0, canvas.width, canvas.height);
		ctx.fillStyle = config.color;
		ctx.font = config.font;
		ctx.textAlign = "left";
		ctx.textBaseline = "bottom";
		ctx.fillText(g, (canvas.width-textWidth)/2, 16);
		chrome.browserAction.setIcon({imageData:ctx.getImageData(0, 0, canvas.width,canvas.height)});
	}
	
	logoStalker.drawRotateIcon = function(){
		var config = this.setttings.rotate;
		var canvas = document.getElementById(this.setttings.canvas);
		var ctx = canvas.getContext('2d');
		var g = config.moji;
		var textWidth = ctx.measureText(g).width;

		ctx.fillStyle = config.color;
		ctx.font = config.font;
		ctx.textAlign = "left";
		ctx.textBaseline = "bottom";
		ctx.translate(10, 10);

		setInterval(function(){
			var angle = 0;
			return function(){
				ctx.clearRect(-canvas.width/2,-canvas.height/2,canvas.width, canvas.height);

				ctx.rotate(angle * Math.PI / 180);
				ctx.fillText(g, -5, 6);
				angle = Math.abs(angle + 15*-1 - 360);
				chrome.browserAction.setIcon({imageData:ctx.getImageData(0, 0, canvas.width,canvas.height)});				
			}
		}(),50);		
	}
	
	w.logoStalker = logoStalker;
}(window));

$(function(){
	logoStalker.checkLogo(function(){
		logoStalker.drawIcon();
	});	
})

chrome.browserAction.onClicked.addListener(function(tab) {
	chrome.tabs.create({url: "http://www.google.co.jp"}, function(tab) {
	});
});
