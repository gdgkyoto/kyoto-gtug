function onResult(result) {
	alert('hoge');
}

// background.html‚Émessage passing
chrome.extension.sendRequest({'action': 'fecthSearchResult', 'query': 'hoge'}, onResult);
