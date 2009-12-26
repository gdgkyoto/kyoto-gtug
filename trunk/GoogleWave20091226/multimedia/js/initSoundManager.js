/* SoundManager2の設定用のコード */
var soundManager = new SoundManager(null, 'sm2');
soundManager.url = SERVER_PATH + "/swf/soundmanager2_flash9_xdomain.swf";
soundManager.flashVersion = (window.location.toString().match(/#flash8/i) ? 8
    : 9);
if (soundManager.flashVersion != 8) {
  soundManager.useHighPerformance = true;
  soundManager.useFastPolling = true;
}
soundManager.debugMode = false;
soundManager.consoleOnly = false;

/* ここにサンプルを追加していってください。 */
var SAMPLES = [ "P-1", "P-2", "P-3", "P-4", "P-5", "P-6", "P-7", "P-8", "G-1",
    "G-2", "G-3", "G-4", "G-5", "G-6", "G-7", "G-8", "B-1", "B-2", "B-3",
    "B-4", "B-5", "B-6", "B-7", "B-8", "clash", "hh-o", "hh-c", "snare", "kick" ];

soundManager.onload = function() {

  for ( var i = 0; i < SAMPLES.length; i++) {
    soundManager.createSound( {
      multiShot : true,
      id : SAMPLES[i],
      url : SERVER_PATH + "sample/" + SAMPLES[i] + ".mp3"
    });
  }
}
