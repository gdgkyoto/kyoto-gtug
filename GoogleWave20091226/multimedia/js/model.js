/**
 * ソングデータの初期化を行う。
 * 
 * @param sampleNames
 * @return song object
 */
function initSong(sampleNames) {
  function getSong() {
    return {
      name : "NewSong",
      tempo : 180,
      patterns : []
    };
  }
  function getPattern() {
    return {
      stepLength : 16,
      instruments : []
    };
  }
  function getInstrument() {
    return {
      sample : "kick.mp3",
      steps : []
    };
  }
  function getStep() {
    return {
      velocity : 0,
      length : 0
    };
  }
  var song = getSong();
  song
  for ( var i = 0; i < 4; i++) {
    var pattern = getPattern();
    for ( var j = 0; j < sampleNames.length; j++) {
      var instrument = getInstrument();
      instrument.sample = sampleNames[j];
      for ( var k = 0; k < pattern.stepLength; k++) {
        var step = getStep();
        instrument.steps.push(step);
      }
      pattern.instruments.push(instrument);
    }
    song.patterns.push(pattern);
  }
  return song;
}

/**
 * プレイヤークラス。本アプリケーションのメインの部分。
 */
var player = {
  /** 再生中かどうか */
  isPlaying : false,
  /** 再生停止処理が通知されているかどうか */
  notifyStop : false,
  /**
   * 再生データを処理するオブジェクトの配列。<br>
   * この配列にpushされるオブジェクトは#process(steps)を実装する必要がある。
   */
  processors : [],
  /**
   * パターンの再生を行う。
   * 
   * @song 再生対象のソングオブジェクト
   * @patternNo 再生するパターンの番号(配列のインデックス)
   * @stepCount 再生処理をコールバックする時の位置を指定。プログラマがこの値を指定することはありません。
   */
  play : function(song, patternNo, stepCount) {
    if (!stepCount && this.isPlaying) {
      return;
    }
    var start = new Date();
    if (this.notifyStop) {
      this.isPlaying = false;
      this.notifyStop = false;
      return;
    }
    this.isPlaying = true;
    var sc = stepCount ? stepCount : 0;
    var pattern = song.patterns[patternNo];
    var steps = [];
    for ( var i = 0; i < pattern.instruments.length; i++) {
      var step = pattern.instruments[i].steps[sc % pattern.stepLength];
      step.sample = pattern.instruments[i].sample;
      steps.push(step);
    }
    for ( var i = 0; i < this.processors.length; i++) {
      this.processors[i].process(steps);
    }
    $(".sample" + (sc % pattern.stepLength)).css("background-color", "#faa");
    $(".sample" + ((sc - 1) % pattern.stepLength)).css("background-color",
        "#fff");
    var stepDelay = 1000 * 60 / song.tempo * (4 / pattern.stepLength);
    var end = new Date();
    setTimeout((function(sng, ptn, stp) {
      return function() {
        player.play(sng, ptn, stp);
      };
    })(song, patternNo, sc + 1), stepDelay - (end - start));
  },
  /**
   * ソング全体の再生を行う。
   * 
   * @song 再生対象のソングオブジェクト
   * @stepCount 再生処理をコールバックする時の位置を指定。プログラマがこの値を指定することはありません。
   */
  playSong : function(song, stepCount) {
    if (!stepCount && this.isPlaying) {
      return;
    }
    var start = new Date();
    if (this.notifyStop) {
      this.isPlaying = false;
      this.notifyStop = false;
      return;
    }
    this.isPlaying = true;
    var sc = stepCount ? stepCount : 0;

    var offset = 0;
    var patternNo = 0;
    var isLast = false;
    for ( var i = 0; i < song.patterns.length; i++) {
      if (sc - offset < song.patterns[i].stepLength) {
        patternNo = i;
        if (patternNo == song.patterns.length - 1
            && sc - offset === song.patterns[i].stepLength - 1) {
          isLast = true;
        }
        break;
      } else {
        offset += song.patterns[i].stepLength;
      }

    }
    var pattern = song.patterns[patternNo];

    var steps = [];
    for ( var i = 0; i < pattern.instruments.length; i++) {
      var step = pattern.instruments[i].steps[sc - offset];
      step.sample = pattern.instruments[i].sample;
      steps.push(step);
    }
    for ( var i = 0; i < this.processors.length; i++) {
      this.processors[i].process(steps);
    }
    if (isLast) {
      this.isPlaying = false;
      this.notifyStop = false;
      return;
    }
    var stepDelay = 1000 * 60 / song.tempo * (4 / pattern.stepLength);
    var end = new Date();
    setTimeout((function(sng, stp) {
      return function() {
        player.playSong(sng, stp);
      };
    })(song, sc + 1), stepDelay - (end - start));
  },
  /**
   * 再生を停止する。(正しくは再生停止の通知を行う)
   */
  stop : function() {
    if (this.isPlaying) {
      this.notifyStop = true;
    }
  }
};

/**
 * 音を鳴らすためのProcessor
 */
var soundProcessor = {
  process : function(steps) {
    for ( var i = 0; i < steps.length; i++) {
      var step = steps[i];
      if (step.velocity == 0) {
        continue;
      }
      soundManager.play(step.sample, {
        volume : step.velocity
      });
    }
  }
};

/**
 * 画面エフェクトを処理するためのProcessor
 */
var visualProcessor = {
  process : function(steps) {
    // ここが三井さんに担当してもらう個所。
  }
};

player.processors.push(soundProcessor);
player.processors.push(visualProcessor);

var song = null;

function stateUpdated() {
  // $("#value").text(wave.getState().get("count",0));
}

function init() {
  alert("init1");
  if (wave && wave.isInWaveContainer()) {
    wave.setStateCallback(stateUpdated);
  }
  alert("init2");
  song = wave.getState().get("song", initSong(SAMPLES));
  alert("init3");

  $("#tempo").slider( {
    min : 60,
    max : 300,
    value : 120,
    step : 1,
    animate : true,
    change : function(event, ui) {
      $("#tempo_disp").text(ui.value + "bpm");
      song.tempo = ui.value;
      wave.getState().submitDelta( {
        'song' : song
      });
    },
    slide : function(event, ui) {
      $("#tempo_disp").text(ui.value + "bpm");
      song.tempo = ui.value;
      wave.getState().submitDelta( {
        'song' : song
      });
    }
  });
  $("#tempo").slider('value', 120);
  $("#tempo-l").click(function() {
    $("#tempo-i").toggle(300);
  });

  $("#play").click(function() {
    player.play(song, 0);
  });
  $("#playSong").click(function() {
    player.playSong(song);
  });
  $("#stop").click(function() {
    player.stop();
  });
  createUI(song);

  var canvas = document.getElementsByTagName('tempcanvas')[0];
  var p = Processing(canvas);
  p.rect(10, 10, 70, 70);
}

function createUI(song) {
  var instruments = song.patterns[0].instruments;
  for ( var i = 0; i < instruments.length; i++) {
    var inst = instruments[i];
    var pLine = $("<div>");
    var gLine = $("<div>");
    var bLine = $("<div>");
    var dLine = $("<div>");
    for ( var j = 0; j < inst.steps.length; j++) {
      var step = inst.steps[j];
      var img = $("<image>");
      img.attr("value", step.value);
      img.attr("numOfInst", i);
      img.attr("numOfStep", j);
      if (step.value == 30) {
        img.attr("src", SERVER_PATH + "image/sound-weak.png");
      } else if (step.value == 60) {
        img.attr("src", SERVER_PATH + "image/sound-normal.png");
      } else if (step.value == 90) {
        img.attr("src", SERVER_PATH + "image/sound-strong.png");
      } else {
        img.attr("src", SERVER_PATH + "image/sound-none.png");
      }
      img.addClass("sample" + j);
      img.attr("step", j);
      img.click(function() {
        var img = $(this);
        if (img.attr("value") == 30) {
          img.attr("value", 60);
          img.attr("src", SERVER_PATH + "image/sound-normal.png");
        } else if (img.attr("value") == 60) {
          img.attr("value", 90);
          img.attr("src", SERVER_PATH + "image/sound-strong.png");
        } else if (img.attr("value") == 90) {
          img.attr("value", 0);
          img.attr("src", SERVER_PATH + "image/sound-none.png");
        } else {
          img.attr("value", 30);
          img.attr("src", SERVER_PATH + "image/sound-weak.png");
        }
        song.patterns[0].instruments[img.attr("numOfInst")].steps[img
            .attr("numOfStep")].velocity = img.attr("value");
        wave.getState().submitDelta( {
          'song' : song
        });
      });

      if (i < 8) {
        pLine.append(img);
      } else if (i < 16) {
        gLine.append(img);
      } else if (i < 24) {
        bLine.append(img);
      } else {
        dLine.append(img);
      }
    }
    $("#piano").append(pLine);
    $("#guitar").append(gLine);
    $("#bass").append(bLine);
    $("#drum").append(dLine);
  }
  $("#p-icon").click(function() {
    $(".icon").hide(300);
    $("#piano").show(300);
  });
  $("#g-icon").click(function() {
    $(".icon").hide(300);
    $("#guitar").show(300);
  });
  $("#b-icon").click(function() {
    $(".icon").hide(300);
    $("#bass").show(300);
  });
  $("#d-icon").click(function() {
    $(".icon").hide(300);
    $("#drum").show(300);
  });
}
