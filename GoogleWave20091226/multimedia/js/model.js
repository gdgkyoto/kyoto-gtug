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
   * @patternNo 再生するパターンの番号(配列のインデックス)
   * @stepCount 再生処理をコールバックする時の位置を指定。プログラマがこの値を指定することはありません。
   */
  play : function(patternNo, stepCount) {
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
    setTimeout((function(ptn, stp) {
      return function() {
        player.play(ptn, stp);
      };
    })(patternNo, sc + 1), stepDelay - (end - start));
  },
  /**
   * ソング全体の再生を行う。
   * 
   * @stepCount 再生処理をコールバックする時の位置を指定。プログラマがこの値を指定することはありません。
   */
  playSong : function(stepCount) {
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
    setTimeout((function(stp) {
      return function() {
        player.playSong(stp);
      };
    })(sc + 1), stepDelay - (end - start));
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
        volume : (step.velocity / 2)
      });
    }
  }
};

/**
 * 画面エフェクトを処理するためのProcessor
 */
var canvas = null;
var p1r = 0, p1rb = 0, p1g = 0, p1gb = 0, p1b = 0, p1bb = 0, p2r = 0, p2rb = 0, p2g = 0, p2gb = 0, p2b = 0, p2bb = 0;
function rect(c, x, y, w, h) {
  c.beginPath();
  c.rect(Math.round(x), Math.round(y), Math.round(w), Math.round(h));
  c.closePath();
}
var visualProcessor = {
  process : function(steps) {
    // ここが三井さんに担当してもらう個所。
    if (!canvas || !canvas.getContext) {
      return false;
    }
    /* 2Dコンテキスト */
    var ctx = canvas.getContext('2d');

    ctx.clearRect(0,0,320,175);
    /* 四角を描く */
    if (steps.name === 'P-1') {

    }
            p1r = 100;
      p1g = 0;
      p1b = 0;

    ctx.fillStyle = 'rgba(' + p1r + ',' + p1g + ',' + p1b + ', 0.5)';
    rect(ctx, 8, 20, 52, 155);
    ctx.fill();

    if (steps.name === 'P-2') {
    }
    ctx.fillStyle = 'rgba(192, 80, 77, 0.5)'; // 赤
    rect(ctx, 72, 20, 52, 155);
    ctx.fill();

    if (steps.name === 'P-3') {
    }
    if (steps.name === 'P-4') {
    }
    if (steps.name === 'P-5') {
    }
    if (steps.name === 'P-6') {
    }
    if (steps.name === 'P-7') {
    }
    if (steps.name === 'P-8') {
    }

  }
};

player.processors.push(soundProcessor);
player.processors.push(visualProcessor);

var song = null;
var selectPtn = 0;

function stateUpdated() {
  song = JSON.parse(wave.getState().get("song",
      JSON.stringify(initSong(SAMPLES))));
  createUI(song);
}

function init() {
  alert("初期化時のやんごとなき理由により、人呼吸置きましてからダイアログを閉じてください。");// どうやらロードのタイミングがおかしいのでアドホックに
  if (wave && wave.isInWaveContainer()) {
    wave.setStateCallback(stateUpdated);
  }
  song = JSON.parse(wave.getState().get("song",
      JSON.stringify(initSong(SAMPLES))));

  for ( var i = 0; i < song.patterns.length; i++) {
    var ptn = $("<div>");
    ptn.css("float", "left");
    ptn.text("Pattern" + i + "|");
    ptn.attr("patternNo", i);
    ptn.addClass("ptns");
    ptn.click(function() {
      $(".ptns").css("background-color", "#fff");
      $(this).css("background-color", "#afa");
      selectPtn = $(this).attr("patternNo");
      createUI(song);
    });
    $("#patterns").append(ptn);
  }

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
        'song' : JSON.stringify(song)
      });
    },
    slide : function(event, ui) {
      $("#tempo_disp").text(ui.value + "bpm");
      song.tempo = ui.value;
      wave.getState().submitDelta( {
        'song' : JSON.stringify(song)
      });
    }
  });
  $("#tempo").slider('value', 120);
  $("#tempo-l").click(function() {
    $("#tempo-i").toggle(300);
  });

  $("#play").click(function() {
    player.play(selectPtn);
  });
  $("#playSong").click(function() {
    player.playSong();
  });
  $("#stop").click(function() {
    player.stop();
  });
  $("#init").click(function() {
    wave.getState().submitDelta( {
      "song" : JSON.stringify(initSong(SAMPLES))
    });
  });
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
  $("#e-icon").click(function() {
    $(".icon").hide(300);
    $("#effect").show(300);
  });

  createUI(song);

  canvas = document.getElementById('tempcanvas');
}

function createUI(song) {
  $("#piano").empty();
  $("#guitar").empty();
  $("#bass").empty();
  $("#drum").empty();
  var instruments = song.patterns[selectPtn].instruments;
  for ( var i = 0; i < instruments.length; i++) {
    var inst = instruments[i];
    var pLine = $("<div>");
    var gLine = $("<div>");
    var bLine = $("<div>");
    var dLine = $("<div>");
    for ( var j = 0; j < inst.steps.length; j++) {
      var step = inst.steps[j];
      var img = $("<image>");
      img.attr("velocity", step.velocity);
      img.attr("numOfInst", i);
      img.attr("numOfStep", j);
      if (step.velocity == 30) {
        img.attr("src", SERVER_PATH + "image/sound-weak.png");
      } else if (step.velocity == 60) {
        img.attr("src", SERVER_PATH + "image/sound-normal.png");
      } else if (step.velocity == 90) {
        img.attr("src", SERVER_PATH + "image/sound-strong.png");
      } else {
        img.attr("src", SERVER_PATH + "image/sound-none.png");
      }
      img.addClass("sample" + j);
      img.attr("step", j);
      img.click(function() {
        var img = $(this);
        if (img.attr("velocity") == 30) {
          img.attr("velocity", 60);
          img.attr("src", SERVER_PATH + "image/sound-normal.png");
        } else if (img.attr("velocity") == 60) {
          img.attr("velocity", 90);
          img.attr("src", SERVER_PATH + "image/sound-strong.png");
        } else if (img.attr("velocity") == 90) {
          img.attr("velocity", 0);
          img.attr("src", SERVER_PATH + "image/sound-none.png");
        } else {
          img.attr("velocity", 30);
          img.attr("src", SERVER_PATH + "image/sound-weak.png");
        }
        song.patterns[selectPtn].instruments[img.attr("numOfInst")].steps[img
            .attr("numOfStep")].velocity = img.attr("velocity");
        wave.getState().submitDelta( {
          "song" : JSON.stringify(song)
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
}
