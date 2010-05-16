/*
 * レコードディスク回転用のWorker
 */

var angle;
var ctx;
var imgPath;

// メインスレッドからのメッセージを処理
onmessage = function(event) {
    if (event.shouldRotate) {
	ctx = event.canvas.getContext('2d');
	impPath = event.impPath;
	angle = 0;

	rotationLoop();
    } else {
	clearTimeout();
    }
}

// ディスク回転処理
function rotationLoop() {
    angle = (angle + 1) % 360;
    ctx.fillRect(0, 0, this.diskCanvas.width, this.diskCanvas.height);

    ctx.save();
    ctx.translate(this.diskCanvas.width/2, this.diskCanvas.height/2);
    ctx.rotate(angle/180*Math.PI);
    ctx.translate(-img.width/2, -img.height/2);
    ctx.drawImage(img, 0, 0);
    ctx.restore();
    setTimeout('rotationLoop()', 50);
}

