function initFileUpload() {
	console.log('initFileUp');
	var dest = document.getElementById("text");
	dest.addEventListener("dragover", onDragOver, false);
	dest.addEventListener("drop", onDrop, false);
}
function onDragOver(ev) {
	ev.preventDefault();
	var dt = ev.dataTransfer;
	return false;
}

function onDrop(ev) {
	console.log('onDrop');
	// ev.stopPropagation();
	var dt = ev.dataTransfer;
	var files = dt.files;
	ev.preventDefault();
	if (files.length > 0) {
		var file = files[0];
		var reader = new FileReader();
		reader.onerror = function() {
			alert('read error');
		};
		reader.onloadend = function() {
			var content = reader.result;
			sendMessage('FILE_UPLOAD', 1, content);
			alert('uploaded');
		};
		reader.readAsBinaryString(file);
	} else {
		alert('no file');
	}
	return false;
}