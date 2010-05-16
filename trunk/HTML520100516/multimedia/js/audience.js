/*
 * Audienceクラスの定義
 */


function Audience(audienceCanvas) {
    this.audienceCanvas = audienceCanvas;

    this.personImages = [
	new Image(),
	new Image(),
	new Image(),
	new Image(),
	new Image()
    ];

    this.displayPerson();

    this.voltage = 0;
    this.minVoltage = 0;

    var _this = this;
    setTimeout(function () {_this.voltageDown();}, 250);
}

Audience.prototype.displayPerson = function () {
    for(var i = 0; i < this.personImages.length; i++) {
	this.personImages[i].src = "img/dancer_0" + (i+1) + "_img.png?" + new Date().getTime();
    }
    var ctx = this.audienceCanvas.getContext('2d');

    var img = this.personImages[0];
    var _this = this;
    img.onload = function () {
	for (var i=0; i<5; i++) {
	    ctx.drawImage(img, 141*i, 0);
	    _this.schedule(i);
	}
    }
}

Audience.prototype.schedule = function (num) {
    var interval;
    if (50 > this.voltage && this.voltage >= 0) { // 8秒前後で画像変更
	interval = Math.random() * 8 * 0.4 + 8 * 0.8;
    } else if (100 > this.voltage && this.voltage >= 50) { // 4秒前後で画像変更
	interval = Math.random() * 4 * 0.4 + 4 * 0.8;
    } else if (150 > this.voltage && this.voltage >= 50) { // 2秒前後で画像変更
	interval = Math.random() * 2 * 0.4 + 2 * 0.8;
    } else if (200 > this.voltage && this.voltage >= 50) { // 1秒前後で画像変更
	interval = Math.random() * 1 * 0.4 + 1 * 0.8;
    } else if (250 > this.voltage && this.voltage >= 50) { // 0.5秒前後で画像変更
	interval = Math.random() * 0.5 * 0.4 + 0.5 * 0.8;
    } else if (this.voltage >= 250) { // 0.25秒前後で画像変更
	interval = Math.random() * 0.25 * 0.4 + 0.25 * 0.8;
    }

    var _this = this;
    setTimeout(function () {_this.dance(num);}, interval * 1000 );
}

Audience.prototype.dance = function (num) {
    var ctx = this.audienceCanvas.getContext('2d');
    ctx.fillStyle = 'rgba(255, 255, 255, 1)';
    ctx.fillStyle = 'rgba(255, 255, 255, 0)';
    ctx.fillRect(num*141, 0, 141, 200);

    ctx.save();
    var img = this.personImages[Math.floor(Math.random()*4)];
    ctx.drawImage(img, 141*num, 0);
    ctx.restore();
    this.schedule(num);
}

Audience.prototype.changeVoltageByPlay = function () {
    this.minVoltage += 50;
    this.voltage += 50;
}

Audience.prototype.changeVoltageByPause = function () {
    this.minVoltage -= 50;
    this.voltage = this.voltage > 50 ? this.voltage - 50 : 0;
}

Audience.prototype.changeVoltageByVolumeControl = function () {
    this.voltage += 2;
}

Audience.prototype.changeVoltageByBalanceControl = function () {
    this.voltage += 4;
}

Audience.prototype.voltageDown = function () {
    if(this.voltage > this.minVoltage) {
	this.voltage--;
    }
    var _this = this;
    setTimeout(function () {_this.voltageDown();}, 250);
}
