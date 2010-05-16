function onMousePress(eve) {
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage( 'MOUSE_PRESS', 0, getMouseInfo(eve, this).join(','));
}

function onMouseRelease(eve) {
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage( 'MOUSE_RELEASE', 0, getMouseInfo(eve, this).join(','));
}
function onMouseMoveScreen(eve) {
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage("CURSOR_MOVE", 0, getMousePos(eve, this).join(","));
}

function getMouseInfo(event, target){
	var info = getMousePos(event, target);
	info[2] = event.button;
	return info;
}

function getMousePos(event, target){
	var moveX = event.pageX - target.x;
	var moveY = event.pageY - target.y;
	return new Array(moveX, moveY);
}