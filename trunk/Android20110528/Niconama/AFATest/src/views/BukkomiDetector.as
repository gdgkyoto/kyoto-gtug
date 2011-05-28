package views
{
	import flash.events.AccelerometerEvent;
	import flash.geom.Vector3D;
	import flash.sensors.Accelerometer;

	/* ぶっこみ検出器 */
	public class BukkomiDetector
	{
		/* ぶっこみ検出のための閾値 */
		public static const threshold:Number = 1.6;
		
		private var lastBukkomiedTime:Number = 0;
		private var accelerotmeter:Accelerometer = new Accelerometer();
		
		/* ぶっこまれたコールバック(引数なし) */
		public var bukkomaretaCallback:Function;
		
		/* ぶっこみ検出器の作成 */
		public function BukkomiDetector(callback:Function)
		{
			trace("Initialized Bukkomi Detector");
			
			bukkomaretaCallback = callback;
			accelerotmeter.addEventListener(AccelerometerEvent.UPDATE, OnAccUpdate);	
		}
		
		/* 加速度が更新されたら呼び出す */
		public function OnAccUpdate(event:AccelerometerEvent):void
		{
			var accVector:Vector3D = new Vector3D(event.accelerationX,event.accelerationY,event.accelerationZ);
			
			trace("Acc Vector Size is " + accVector.length);
			
			if((accVector.length > threshold) && ((lastBukkomiedTime + 2500) < (new Date()).getTime())){
				/* 大きさが指定値より大きい場合にコメントを投稿 */
				bukkomaretaCallback();
				
				/* ご検出防止のため、その後5秒間は投稿しない */
				lastBukkomiedTime = (new Date()).getTime();
			}
		}
	}
}