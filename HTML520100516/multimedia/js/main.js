// 初期化
function init() {
    
    // ディスク表示用キャンバスの取得
    var leftDiskCanvas = document.getElementById("left_disk_canvas");
    var rightDiskCanvas = document.getElementById("right_disk_canvas");
    
    // 観客表示用キャンパスの取得
    var audienceCanvas = document.getElementById("audience_canvas");

    // ボリューム調整用つまみの取得
    var leftVolumeControl = document.getElementById("left_volume_control");
    var rightVolumeControl = document.getElementById("right_volume_control");
    
    // ミックスバランス調整用つまみの取得
    var mixBalanceControl = document.getElementById("mix_balance_control");

    // 各ボタンの取得
    var leftPlayButton = document.getElementById("left_play");
    var leftPauseButton = document.getElementById("left_pause");
    var rightPlayButton = document.getElementById("right_play");
    var rightPauseButton = document.getElementById("right_pause");

    // 観客の定義
    var audience = new Audience(audienceCanvas);

    // デバッグ用
    var leftVolumeValue = document.getElementById("left_volume_value");
    var rightVolumeValue = document.getElementById("right_volume_value");
    var balanceValue = document.getElementById("balance_value");
    var voltageValue = document.getElementById("voltage_value");
    var minVoltageValue = document.getElementById("min_voltage_value");
    setTimeout(function() {renewVoltage(audience);}, 1000);

    // ターンテーブルの定義
    var leftTurnTable = new TurnTable("mp3/1.mp3", leftDiskCanvas);
    var rightTurnTable = new TurnTable("mp3/3.mp3", rightDiskCanvas);
    
    // ディスクのセット
    leftTurnTable.setDisk("img/record_keion01_img.png");
    rightTurnTable.setDisk("img/record_keion01_img.png");

    // 各ボタンのイベントハンドラの設定
    leftPlayButton.onclick = function () {
	leftTurnTable.play();
	audience.changeVoltageByPlay();
    };
    rightPlayButton.onclick = function () {
	rightTurnTable.play();
	audience.changeVoltageByPlay();
    };
    leftPauseButton.onclick = function () {
	leftTurnTable.pause();
	audience.changeVoltageByPause();
    };
    rightPauseButton.onclick = function () {
	rightTurnTable.pause();
	audience.changeVoltageByPause();
    };
    
    leftVolumeControl.on = function () {
	leftVolumeChange();
	audience.changeVoltageByVolumeControl();
    }
    rightVolumeControl.onchange = function () {
	rightVolumeChange();
	audience.changeVoltageByVolumeControl();
    }
    mixBalanceControl.onchange = function () {
	leftVolumeChange();
	rightVolumeChange();
	audience.changeVoltageByBalanceControl();
    }

    leftDiskCanvas.onmousedown = function (e) {
	leftTurnTable.keydown(e);
    }
    leftDiskCanvas.onmousemove = function (e) {
	leftTurnTable.drag(e);
    }
    leftDiskCanvas.onmouseup = function () {
	leftTurnTable.keyup();
    }
    leftDiskCanvas.onmouseout = function () {
	leftTurnTable.keyup();
    }
    rightDiskCanvas.onmousedown = function (e) {
	rightTurnTable.keydown(e);
    }
    rightDiskCanvas.onmousemove = function (e) {
	rightTurnTable.drag(e);
    }
    rightDiskCanvas.onmouseup = function () {
	rightTurnTable.keyup();
    }
    rightDiskCanvas.onmouseout = function () {
	rightTurnTable.keyup();
    }
    
    function leftVolumeChange() {
	var volume = leftVolumeControl.value - 0;
	var balance = mixBalanceControl.value - 0;
	if (balance > 0) {
	    volume = volume * (1 - balance);
	}
	leftTurnTable.setVolume(volume)
	
	leftVolumeValue.innerHTML = leftVolumeControl.value;
	balanceValue.innerHTML = balance;
    }

    function rightVolumeChange() {
	var volume = rightVolumeControl.value - 0;
	var balance = mixBalanceControl.value - 0;
	if (balance < 0) {
	    volume = volume * (1 + balance);
	} 
	rightTurnTable.setVolume(volume);
		
	rightVolumeValue.innerHTML = rightVolumeControl.value;
	balanceValue.innerHTML = balance;
    }
    
    function renewVoltage(audience) {
	voltageValue.innerHTML = audience.voltage;
	minVoltageValue.innerHTML = audience.minVoltage;
	setTimeout(function() {renewVoltage(audience);}, 1000);
    }
}




