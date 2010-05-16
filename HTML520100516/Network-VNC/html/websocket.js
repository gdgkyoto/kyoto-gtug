//var imageRequestTimer = null;
var browser;
var remote;
//var webSocket;
var receiveIndexes = [];
var receiveData = {};
//function requestImage() {
//	sendMessage("image", 0, '');
//}
//function setImageRequestTimer(milliseconds) {
//	if (imageRequestTimer != null) {
//		clearTimeout(imageRequestTimer);
//	}
//	imageRequestTimer = setTimeout(requestImage, milliseconds);
//}
function onMouseMoveBrowser(eve) {	// TODO
	var x = eve.clientX / browser.clientWidth
			* (remote.offsetWidth - browser.clientWidth);
	var y = eve.clientY / browser.clientHeight
			* (remote.offsetHeight - browser.clientHeight);
	scrollTo(x, y);
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage( [ "mousemove", eve.pageX, eve.pageY ]);
}
function onContextMenuBrowser(eve) {
	eve.stopPropagation();
	eve.preventDefault();
}
function onMouseDownImage(eve) { // TODO
	var button = "";
	switch (eve.button) {
	case 0:
		button = "main";
		break;
	case 1:
		button = "wheel";
		break;
	case 2:
		button = "contextmenu";
		break;
	}
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage( [ "mousedown", button ]);
}
function onMouseUpImage(eve) { // TODO
	var button = "";
	switch (eve.button) {
	case 0:
		button = "main";
		break;
	case 1:
		button = "wheel";
		break;
	case 2:
		button = "contextmenu";
		break;
	}
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage( [ "mouseup", button ]);
}
function onMouseWheelImage(eve) {
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage( [ "mousewheel", eve.wheelDelta ]);
}
/*
 * function onKeyDownImage(eve){ console.log(eve.keyCode); //
 * sendMessage(["keydown"]); } function onKeyUpImage(eve){
 * console.log(eve.keyCode); // sendMessage(["keyup"]); } function
 * onKeyPressImage(eve){ console.log(eve.charCode); //
 * sendMessage(["keypress"]); }
 */
//function onOpenWebSocket() {
//	browser.addEventListener("mousemove", onMouseMoveBrowser, false);
//	remote.addEventListener("mousedown", onMouseDownImage, false);
//	remote.addEventListener("mouseup", onMouseUpImage, false);
//	remote.addEventListener("mousewheel", onMouseWheelImage, false);
//	// window.addEventListener("keydown",onKeyDownImage,false);
//	// window.addEventListener("keyup",onKeyUpImage,false);
//	// window.addEventListener("keypress",onKeyPressImage,false);
//	requestImage();
//}
function initial(eve) {
//	browser = document.documentElement;
//	remote = document.getElementById("remote");
//	var protocol = (location.protocol == "https:") ? "wss" : "ws";
//	var host = location.host;
//	webSocket = new WebSocket(protocol + "://" + host + "/ws/");
//	webSocket.addEventListener("open", onOpenWebSocket, false);
//	webSocket.addEventListener("close", onCloseWebSocket, false);
//	webSocket.addEventListener("message", onMessageWebSocket, false);
//	window.addEventListener("unload", onUnloadWindow, false);
	browser.addEventListener("contextmenu", onContextMenuBrowser, false);
	browser.addEventListener("contextmenu", onContextMenuBrowser, true);
}
//window.addEventListener("load", initial, false);