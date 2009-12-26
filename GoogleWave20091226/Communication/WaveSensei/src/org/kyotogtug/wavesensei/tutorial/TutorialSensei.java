package org.kyotogtug.wavesensei.tutorial;

import org.kyotogtug.wavesensei.tutorial.meta.TutorialStatusMeta;
import org.kyotogtug.wavesensei.tutorial.model.TutorialStatus;
import org.slim3.datastore.Datastore;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;

public class TutorialSensei {

	public void execute(RobotMessageBundle bundle) {
		for (Event e : bundle.getEvents()) {
			String waveId = e.getWavelet().getWaveId();
			TutorialStatusMeta meta = TutorialStatusMeta.get();
			TutorialStatus status = Datastore.query(meta).filter(
					meta.waveId.equal(waveId)).asSingle();
			if (status == null) {
				status = new TutorialStatus();
			}

			// レベルに応じて処理
			Integer level = status.getLevel();
			if (level == null) {
				level = 1;
			}
			Boolean isFirstQuestion = status.getIsFirstQuestion();
			if (isFirstQuestion == null) {
				isFirstQuestion = false;
			}

			Blip blip = e.getBlip().createChild();
			TextView textView = blip.getDocument();
			String message = "";
			switch (level) {
			case 1:
				if (isFirstQuestion) {
					message = "Blipを追加してみましょう。";
				} else if (e.getType() == EventType.BLIP_SUBMITTED) {
					message = "よく出来ました！";
					status.setLevel(2);
					status.setIsFirstQuestion(true);
					Datastore.put(status);
				}
				break;
			case 2:
				break;
			case 3:
				break;
			}
			textView.append(message);
		}

	}
}