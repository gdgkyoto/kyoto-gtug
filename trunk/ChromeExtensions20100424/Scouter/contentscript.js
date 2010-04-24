function onResult(result) {
	alert(result.length + "種類の検索エンジンで検索したよ！");
}

// background.htmlにmessage passing
chrome.extension.sendRequest({'action': 'fetchSearchResult', 'query': 'hoge'}, onResult);
