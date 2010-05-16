function onFileDownloadResponse(host, path) {
	url = "http://"+ host + path.replace("/html", "");
	
	//window.location.href = url;
	window.open(url);
}

function fileDownloadRequest() {
	sendMessage('FILE_DOWNLOAD_REQUEST', 0, 'dummy');
}
