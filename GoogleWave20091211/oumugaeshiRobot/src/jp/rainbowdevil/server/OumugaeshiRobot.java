package jp.rainbowdevil.server;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;

/**
 * オウム返しロボット
 * @author Kenji
 *
 */
public class OumugaeshiRobot extends AbstractRobotServlet {
	public void processEvents(RobotMessageBundle bundle) {
		for (Event e : bundle.getEvents()) {
			// イベントタイプがBlip投稿だった場合
			if (e.getType() == EventType.BLIP_SUBMITTED) {
				// 投稿されたメッセージを取得
				String submittedText = e.getBlip().getDocument().getText();
				// Blip作成
				Blip blip = e.getBlip().createChild();
				TextView textView = blip.getDocument();
				String message = "echo '" + submittedText + "'"; // メッセージを投稿
				
				// おまけ ユーザの発言に付け加え
				e.getBlip().getDocument().append("、と言うことにしたいのですね。");
				
				// メッセージ投稿
				textView.append(message);
			}
		}
	}
}
