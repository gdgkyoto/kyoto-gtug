function onResult(result) {
	alert('hoge');
}

// background.html��message passing
chrome.extension.sendRequest({'action': 'fecthSearchResult', 'query': 'hoge'}, onResult);
