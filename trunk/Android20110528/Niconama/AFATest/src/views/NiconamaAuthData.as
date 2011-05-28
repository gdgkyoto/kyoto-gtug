package views
{
	/**
	 *  ニコ生の接続情報を保持するクラス
	 */
	public class NiconamaAuthData
	{
		public function NiconamaAuthData()
		{
		}
		
		/**
		 * gatplayerstatusの結果XMLをパースし、メンバ変数に設定する。
		 * 
		 */
		public function parseGetPlayerStatusResult(xmlData:String):void
		{
			var xml:XML = new XML(xmlData);
			
			threadId = xml.child("ms")[0].child("thread")[0].text();
			commentServerAddress = xml.child("ms")[0].child("addr")[0].text();
			commentServerPort = parseInt(xml.child("ms")[0].child("port")[0].text());
			openTime = Number( parseInt(xml.child("stream")[0].child("open_time")[0].text()));
			loginUserId = xml.child("user")[0].child("user_id")[0].text();
		}
		
		public var loginUserId:String;
		public var openTime:int;
		public var threadId:String;
		public var commentTicket:String;
		public var commentServerAddress:String;
		public var commentServerPort:int;
		public var postKey:String;
	}
}