/* 
 * ディスククラスの定義
 */

// コンストラクタ
function Disk(diskCanvas) {
    this.diskCanvas = diskCanvas;
    
    this.imagePath = '';
    this.img = new Image();
    this.angle = 0;
    
}

// ディスクセット
Disk.prototype.setDisk = function (imagePath) {
    this.angle = 0;
	
    var ctx = this.diskCanvas.getContext('2d');
    this.img.src = imagePath + "?" + new Date().getTime();
    var img = this.img;
    img.onload = function () {
	ctx.drawImage(img, 0, 0);
    }
}

// ディスク回転処理
Disk.prototype.startRotation = function() {
    this.angle = (this.angle + 1) % 360;
   
    var ctx = this.diskCanvas.getContext('2d');

    ctx.fillStyle = 'rgba(255,255,255,1)';
    ctx.fillStyle = 'rgba(255,255,255,0)';
    ctx.fillRect(0, 0, this.diskCanvas.width, this.diskCanvas.height);

    ctx.save();
    ctx.translate(this.diskCanvas.width/2, this.diskCanvas.height/2);
    ctx.rotate(this.angle/180*Math.PI);
    ctx.translate(-this.img.width/2, -this.img.height/2);
    ctx.drawImage(this.img, 0, 0);
    ctx.restore();

    var _this = this;
    this.timerId = setTimeout(function () {_this.startRotation();}, 50);
}

// ディスク停止処理
Disk.prototype.stopRotation = function() {
    if (this.timerId != null) {
	clearTimeout(this.timerId);
    }
}

Disk.prototype.rotate = function(diff) {

    this.angle = (this.angle + diff) % 360;
    
    var ctx = this.diskCanvas.getContext('2d');
    ctx.fillStyle = 'rgba(255,255,255,1)';
    ctx.fillStyle = 'rgba(255,255,255,0)';
    ctx.fillRect(0, 0, this.diskCanvas.width, this.diskCanvas.height);

    ctx.save();
    ctx.translate(this.diskCanvas.width/2, this.diskCanvas.height/2);
    ctx.rotate(this.angle/180*Math.PI);
    ctx.translate(-this.img.width/2, -this.img.height/2);
    ctx.drawImage(this.img, 0, 0);
    ctx.restore();

}

