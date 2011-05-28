package views
{
	import flash.media.Sound;
	import flash.net.URLRequest;
	
	import flashx.textLayout.debug.assert;

	public class SynthesisOfSpeech
	{
		/* 声色 */
		public static const FEMALE:String = "female01";
		public static const MALE:String = "male01";
		
		public function SpeechInJpn(text:String, vocalSound:String, useConvertion:Boolean):void
		{
			/* パラメータの確認 */
			if(text == null){
				throw new Error("textはnullではいけません。");
			}
			if(!(vocalSound == FEMALE || vocalSound == MALE)){
				throw new Error("vocalSoundにはMALEかFEMALE定数が設定されなくてはいけません。");
			}
			
			/* テキスト変換 */
			if(useConvertion){
				/* w+を 笑い に変換 */
				var wPattern:RegExp = /[wｗ]+/
				text = text.replace(wPattern, "笑い");
					
				/* おつを ちゅっちゅ に変換 */
				var otsuPattern:RegExp = /^[おつ,乙]+/
				text = text.replace(otsuPattern, "ちゅっちゅ")
			}
			
			/* 音声合成 */
			var req:URLRequest = new URLRequest("http://gimite.net/speech?format=mp3&speaker=" + vocalSound + "&text=" + text);
			var snd:Sound = new Sound(req);
			snd.play();
		}
	}
}