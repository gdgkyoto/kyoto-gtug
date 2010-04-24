var o = window.localStorage;

//ページロード時の処理
window.addEventListener("load", function(){

    // 後日、検索結果表示後の処理に変更
    var b = document.getElementById("send");
    b.addEventListener("click", setHistory, false);

    var c = document.getElementById("clear_history");
    c.addEventListener("click", clearHistory, false);

    }, false);


//　履歴データをセット
function setHistory(){

  var k = document.getElementById("word_query");
  var key = k.value; 
  
  var data_set = { count: 0,
    test_flag: true,
    wordtype: "",
    mean: "",
    created: null,
    upadted: null
  };

  // localStorageからJSON文字列を取得
  var value_t = o.getItem(key);
  var value = data_set;
  if(value_t){
    // JSON文字列をJavaScriptのオブジェクトに変換
    value = JSON.parse(value_t);
    value.count++;
    value.updated = new Date();
    console.log(value.updated);
  }
  else{
    value.count++;

    // 後日変更
    value.mean = "";
    value.wordtype = "";
    value.created = new Date();
    value.updated = value.created;
    console.log(value.updated);
 
  }
  ////////////////////////////
  // JSON文字列に変換
  ///////////////////////////
  // Dateオブジェクトの型が崩れてしまうので注意が必要
  // <例>
  //  Fri Apr 23 2010 23:47:34 GMT+0900 (JST)
  //  >> "2010-04-23T14:47:34.270Z"
  //
  value_t = JSON.stringify(value);
  console.log(value_t);

  // 念のために値をチェック
  if( key == "" || value_t == ""){  return; }

  o.setItem(key, value_t);
  showHistory();
}



function showHistory(){

  // 初期化
  $('div#history_result').empty();

  for(var i=0; i<o.length; i++){

    var key = o.key(i);

    var value_t = o.getItem(key);
    // JSON文字列をJavaScriptのオブジェクトに変換
    var value = JSON.parse(value_t);

//    console.log(value);

    ///////////////////////////// 
    // 検索履歴の例
    ////////////////////////////
    //
    //<div class="card">
    //   <div class="card_main">
    //       <input type="checkbox" name="" value="1">
    //       <div class="word">word</div>
    //       <div class="search_count">検索回数：2回</div>
    //       <h3 class="wordtype">名詞</h3>
    //       <p class="mean">単語</p>
    //   </div>
    // 
    //   <div class="card_footer">
    //     <li class="created">4/22/2010</li>
    //     <li class="updated">4/23/2010</li>
    //   </div>
    //</div>
    //

    var str = '';
    str += '<div class="card">';

    str += '<div class="card_main">';
    str += '<div class="card_head">';
    str += '<input type="checkbox" name="' + key +'_flag'  + '" value="1">';
    str += '<div class="word">'+ key +'</div>';
    str += '<div class="search_count">検索回数：'+ value.count +'回</div>';
    str += '</div>';

    str += '<div class="card_body">';
    str += '<ul>';
    //    str += '<li class="wordtype">' + value.wordtype +'</li>';
    //    str += '<li class="mean">' + value.mean +'</li>';
    str += '<li class="wordtype">' + "名詞" +'</li>';
    str += '<li class="mean">' + "※ 意味を挿入する。" +'</li>';
    str += '</ul>';
    str += '</div>';
    str += '</div>';

    str +='<div class="card_footer">';
    //    str += '<li class="created">' + created.getYear() + '年' + created.getMonth()+ 1 + '月' + created.getDate() + '日 ' + created.getHours() + ':' + created.getMinuites() + '</li>';
    str += '<div class="created">' + '作成日時:' + value.created + '</div>';
    str += '<div class="updated">' + '更新日時:' + value.updated + '</div>';
    str += '<img src="img/trash.png" class="trash_box" title="Delete" onclick=""></img>';
    str += '<div class="delete_confirm">この履歴を削除しますか？ : <a href="javascript:Composer.confirmDestory();">Yes</a>　<a href="javascript:Composer.denyDestroy();">No</a> </div>';
    str += '</div>';
    
    str+= '</div>';

    $('div#history_result').append(str); 
  }
}



function clearHistory(){
  o.clear();
  showHistory();
}


function deleteRecord(){



}

//function showDeleteConfirm(node){
//  $(".delete_confirm").hide();
//  $(".delete_confirm.destroy, node").show();
//  this.
//}
//

