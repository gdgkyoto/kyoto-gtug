package views
{
	import flash.events.*;
	import flash.net.*;

	
	public class NiconamaConnector
	{
		/* BEGIN of 設定情報フィールド */
		private var hard_coded_post_key = ""; //POST_KEY
		private var use_hard_coded_post_key:Boolean = false; //ハードコーディングされたPOST_KEYを利用する
		private var is_premium = "0"; // プレミアムフラグ (プレミアム会員 1, 非プレミアム会員 0)
		/* END of 設定情報フィールド */
		
		private var POST_KEY_URL:String = "http://live.nicovideo.jp/api/getpostkey?block_no=0";
		
		private var socket:XMLSocket;
		private var postKey:String;
		private var authData:NiconamaAuthData;
		private var receiveCallback:Function;
		
		public static var niconamaConnector:NiconamaConnector = new NiconamaConnector();
		
		public function NiconamaConnector()
		{
			//NiconamaConnector.niconamaConnector = new NiconamaConnector();
		}
		
		public function setReceiveHandler(handler:Function):void{
			this.receiveCallback = handler;
		}
		
		/**
		 *  PostKeyを取得し、メンバ変数に設定する。
		 *  コメントの投稿時に必要
		 *   
		 */
		private function getPostKey(threadId:String):void
		{
			var request:URLRequest = new URLRequest(POST_KEY_URL);
			var loader:URLLoader = new URLLoader();
			request.method = "POST";
			request.contentType = "application/x-www-form-urlencoded";
			request.data = "thread=" + threadId;
			trace("POST_KEY_URL="+POST_KEY_URL+" thread="+threadId);
			loader.addEventListener(Event.COMPLETE, function (event:Event):void {
				var data:String = loader.data;
				data = data.replace("postkey=","");
				trace("PostKey:"+data+" thread="+threadId);
				
				if(use_hard_coded_post_key){
					trace("ハードコーディングされたポストキーを利用します : " + hard_coded_post_key);
					postKey = hard_coded_post_key;
				}else{
					trace("取得されたポストキー : " + data);
					postKey = data;
				}
				
				// 投稿テスト
				//sendComment("テスト");
			});
			loader.load(request);
		}
		
		/**
		 *  vposを取得する。
		 *  vposはコメント投稿時に必要になる。
		 */
		private function getVpos(openTime:Number){
			var iNow:int =  Math.floor((new Date).getTime() / 1000) ;
			trace("now="+iNow + " open="+openTime);
			var vpos:Number = iNow - openTime;
			return vpos * 100;
		}
		
		/**
		 *  コメントを送信する。
		 */
		public function sendComment(message:String){
			trace("sendComment1");
			var vpos:Number = getVpos(authData.openTime);
			trace("sendComment2");
			var data:String = "<chat thread=\""+authData.threadId+"\" ticket=\""+authData.commentTicket+"\" vpos=\""+vpos+"\" postkey=\""+postKey+"\" mail=\" 184\" user_id=\""+authData.loginUserId+"\" premium=\"" + is_premium + "\">"+message+"</chat>\0";
			trace("send:"+data);
			socket.send(data);
		}
		
		/**
		 * ニコ生のコメントサーバに接続する。
		 * 
		 * 接続した後は
		 * 
		 */
		public function connectCommentServer( authData:NiconamaAuthData){
			this.authData = authData;
			getPostKey(authData.threadId);
			trace("コメントサーバ接続");
			socket = new XMLSocket();
			socket.connect(authData.commentServerAddress, authData.commentServerPort);
			
			function connectHandler(event:Event):void {
				trace("接続成功");
				var command1:String = "<thread thread=\""+authData.threadId+"\" res_from=\"-200\" version=\"20061206\">\0"
				//var sendText:String = "sent text";
				socket.send ( command1 );
				//write( event.type + "\n" + sendText );
			}
			
			function ioErrorHandler(event:IOErrorEvent):void {
				trace("接続失敗");
				write( event.type );
			}
			
			function write( s ):void{
				trace( s );
				socket.close();
			}
			
			function receive( s ):void{
				trace( "Receive! "+s );
				if (s.data.search("<thread ") != -1){
					//trace("最初！"+s.data);
					var xml:XML = new XML(s.data);
					authData.commentTicket =  xml.@ticket;// .child("thread")[0].text();
					trace("commentTicket="+authData.commentTicket);
					
				}else{
					//trace("!!! "+s);
					receiveCallback(s.data);
				}
				
			}
			
			socket.addEventListener( Event.CONNECT, connectHandler );
			socket.addEventListener( DataEvent.DATA, receive );
			socket.addEventListener( IOErrorEvent.IO_ERROR, ioErrorHandler);
		}
	}
}