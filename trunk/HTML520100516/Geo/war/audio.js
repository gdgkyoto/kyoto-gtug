function getAudio(distance){
	if(distance < 1000){
		return new Audio("../wav/data0.wav");
	}else if(distance < 2000){
		return new Audio("../wav/data1.wav");
	}else if(distance < 5000){
		return new Audio("../wav/data2.wav");
	}else if(distance < 10000){
		return new Audio("../wav/data3.wav");
	}else if(distance < 20000){
		return new Audio("../wav/data4.wav");
	}else if(distance < 50000){
		return new Audio("../wav/data5.wav");
	}else if(distance < 100000){
		return new Audio("../wav/data6.wav");
	}else{
		return new Audio("../wav/data7.wav");
	}
}