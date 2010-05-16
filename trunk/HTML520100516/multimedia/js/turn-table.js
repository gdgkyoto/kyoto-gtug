/*
 * TurnTableクラスの定義
 */

// コンストラクタ
function TurnTable (mp3Path, diskCanvas) {
    this.audio = new Audio();
    this.audio.src = mp3Path;
    this.audio.controls = false;

    this.audio.controls = 0.5;

    this.disk = new Disk(diskCanvas);
}

// 再生
TurnTable.prototype.play = function() {
    this.audio.play();
    this.disk.startRotation();
}

// 一時停止
TurnTable.prototype.pause = function() {
    this.audio.pause();
    this.disk.stopRotation();
}

// ボリュームの変更
TurnTable.prototype.setVolume = function (volume) {
    this.audio.volume = volume;
}
// ディスクのセット
TurnTable.prototype.setDisk = function (imagePath) {
    this.disk.setDisk(imagePath);
}


