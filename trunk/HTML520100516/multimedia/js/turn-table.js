/*
 * TurnTableクラスの定義
 */

// コンストラクタ
function TurnTable (mp3Path, diskCanvas) {
    this.audio = new Audio();
    this.audio.src = mp3Path;
    this.audio.controls = false;

    this.scratch = new Audio();
    this.scratch.src = "mp3/sc.wav";
    this.scratch.loop = true;

    this.disk = new Disk(diskCanvas);

    this.isKeyDown = false;
    this.nowPlaying = false;
    this.point1;
    this.point2;
    this.p0 = {x:diskCanvas.width/2, y:diskCanvas.height/2};

    var _this = this;
}

// 再生
TurnTable.prototype.play = function() {
    if (!this.nowPlaying) {
	this.audio.play();
	this.disk.startRotation();
	
	this.nowPlaying = true;
    }
}

// 一時停止
TurnTable.prototype.pause = function() {
    if (this.nowPlaying) {
	this.audio.pause();
	this.disk.stopRotation();
	this.nowPlaying = false;
    }
}

// ボリュームの変更
TurnTable.prototype.setVolume = function (volume) {
    this.audio.volume = volume;
}
// ディスクのセット
TurnTable.prototype.setDisk = function (imagePath, mp3Path) {
    this.pause();
    this.disk.setDisk(imagePath);
    this.audio.src = mp3Path;
    this.audio.load();
}

// ディスク上でマウスボタンが押された時
TurnTable.prototype.keydown = function(e) {
    this.isKeyDown = true;
    var mouseX = e.offsetX;
    var mouseY = e.offsetY;
    this.point1 = {x:mouseX, y:mouseY};
    if (this.nowPlaying) {
	this.pause();
	this.nowPlaying = true;
    }
}

// ディスク上でマウスボタンが押されてからポインタが移動したとき
TurnTable.prototype.drag = function(e) {
    if (this.isKeyDown) {
	var mouseX = e.offsetX;
	var mouseY = e.offsetY;;	
	this.point2 = this.point1;
	this.point1 = {x:mouseX, y:mouseY}

	var angle = this.calcRad(this.point1, this.point2);
	this.audio.currentTime += angle/20;
	this.disk.rotate(angle);

    }
}

// ディスク上でマウスボタンが押されてからボタンが放たれたとき
TurnTable.prototype.keyup = function() {
    if(this.isKeyDown) {
	this.isKeyDown = false;
	if (this.nowPlaying) {
	    this.nowPlaying = false;
	    this.play();
	}
    }
}

// ベクトルの角度を計算（ラジアン）
TurnTable.prototype.calcRad  = function(p1, p2) {
    var vec1 = {x:p1.x - this.p0.x, y:p1.y - this.p0.y};
    var vec2 = {x:p2.x - this.p0.x, y:p2.y - this.p0.y};

    var innerProduct = vec1.x * vec2.x + vec1.y * vec2.y;
    var outerProduct = vec1.x * vec2.y - vec1.y * vec2.x;

    var s = -Math.atan2(outerProduct, innerProduct);
    var deg = (s/Math.PI)*180;
    
    return deg;
}
