function getMessage(distance){
	if(distance < 1000){
		return "まだ歩き始めたばかりです。";
	}else if(distance < 5000){
		return "今、1km以上歩いています。";
	}else if(distance < 10000){
		return "もう5kmを超えました。";
	}else if(distance < 20000){
		return "現在10km超えです。";
	}else if(distance < 500000){
		return "なんと50km超えました!";
	}else if(distance < 1000000){
		return "100kmも歩いています!";
	}else{
		return "100km以上も歩いていますよ～";
	}
}