function onMousePress(eve) {
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage( 'MOUSE_PRESS', 0, getMouseInfo(eve).join(','));
}

function onMouseRelease(eve) {
	eve.stopPropagation();
	eve.preventDefault();
	sendMessage( 'MOUSE_RELEASE', 0, getMouseInfo(eve).join(','));
}

function getMouseInfo(event){
	return [123, 456, event.button];
}