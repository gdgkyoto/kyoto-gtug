package org.kyotogtug.wavesensei;

import org.kyotogtug.wavesensei.tw.TwitterSensei;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.RobotMessageBundle;

@SuppressWarnings("serial")
public class WaveSenseiServlet extends AbstractRobotServlet {
	//Blipが作られた時にデータを保存する
	public void processEvents(RobotMessageBundle bundle) {
		TwitterSensei twsensei = new TwitterSensei();
		twsensei.processEvents(bundle);
	}
}
