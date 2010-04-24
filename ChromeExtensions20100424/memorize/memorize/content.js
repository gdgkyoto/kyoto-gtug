$(document).ready(function() {
	//検索ボタンを押した時のイベントハンドラを登録
	$('input#send').bind("click", sendQuery);
});

/**
 * alcへ検索ワードを送信して結果表示を行う
 * @param event イベントオブジェクト
 */
function sendQuery(evnet) {
	var query = $('input#query').val();
	//todo 検索語がヒットしなかった時の対処
	$selected_tab = $('div#tabs-1');
	$selected_tab.load("http://api.smart.fm/items/matching/" + query);
}



