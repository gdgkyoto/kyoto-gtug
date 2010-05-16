function onMouseMoveScreen(eve) {
	eve.stopPropagation();
	eve.preventDefault();
	
	var moveX = eve.pageX - this.x;
	var moveY = eve.pageY - this.y;
	var arr = new Array(moveX, moveY);
	
	sendMessage("CURSOR_MOVE", 0, arr.join(","));
}
