function onKeyPress(event){
	console.log(event.keyCode);
	sendMessage('KEY_PRESS', 0, event.keyCode);
}
function onKeyRelease(event){
	console.log(event.keyCode);
	sendMessage('KEY_RELEASE', 0, event.keyCode);
}
