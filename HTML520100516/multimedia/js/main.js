var songs = [
    {mp3: "music_01_dstm.mp3", img: "record_01_dstm.png"},
    {mp3: "music_02_onemoretime.mp3", img: "record_02_onemoretime.png"},
    {mp3: "music_03_loveforever.mp3", img: "record_03_loveforever.png"},
    {mp3: "music_04_thriller.mp3", img: "record_04_thriller.png"},
    {mp3: "music_05_mip.mp3", img: "record_05_mip.png"},
    {mp3: "music_06_juju.mp3", img: "record_06_juju.png"},
    {mp3: "music_07_keion0op.mp3", img: "record_07_keion0op.png"},
    {mp3: "music_08_doragonquest.mp3", img: "record_08_doragonquest.png"},
    {mp3: "music_09_hatsune.mp3", img: "record_09_hatsune.png"},
    {mp3: "music_10_yodobashi.mp3", img: "record_10_yodobashi.png"},
    {mp3: "music_11_iichiko.mp3", img: "record_11_iichiko.png"},
    {mp3: "music_12_persona.mp3", img: "record_12_persona.png"},
    {mp3: "music_13_bakemono.mp3", img: "record_13_bakemono.png"},
    {mp3: "music_14_keioned.mp3", img: "record_14_keioned.png"}
];


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
    
    // プルダウンメニューの取得
    var rightSelect = document.getElementsByName("right_select")[0];
    var leftSelect = document.getElementsByName("left_select")[0];
    
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
    
    for (var i = 0; i < songs.length; i++ ) {
	rightSelect.options[i] = new Option(songs[i].mp3, i);
	leftSelect.options[i] = new Option(songs[i].mp3, i);
    }

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




