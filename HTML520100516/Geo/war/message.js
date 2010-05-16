function getMessage(distance){
	if(distance < 1000){
		return "まだ歩き始めたばかりです。頑張ってください。";
	}else if(distance < 2000){
		return "1km地点を超えました。まだまだこれからです。";
	}else if(distance < 5000){
		return "2km地点を超えました。いい運動になったのでは？";
	}else if(distance < 10000){
		return "5km地点を超えました。冷たいお茶でも飲みましょうか。";
	}else if(distance < 20000){
		return "10km地点を超えました。少し休憩しませんか？";
	}else if(distance < 50000){
		return "20km地点を超えました。いい運動になりましたね！";
	}else if(distance < 100000){
		return "50km地点を超えました。素晴らしい走行距離です!";
	}else{
		return "100km地点を超えました!今日はもう休んでください！";
	}
}