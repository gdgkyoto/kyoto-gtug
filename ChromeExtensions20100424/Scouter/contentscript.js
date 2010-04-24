function onResult(result) {
	alert('hoge');
}

// background.htmlにmessage passing
chrome.extension.sendRequest({'action': 'fecthSearchResult', 'query': 'hoge'}, onResult);
