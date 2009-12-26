package org.kyotogtug.wavesensei.tutorial;

import hello.HelloLogger;

import java.util.logging.Logger;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.kyotogtug.wavesensei.tutorial.meta.TutorialStatusMeta;
import org.kyotogtug.wavesensei.tutorial.model.TutorialStatus;
import org.slim3.datastore.Datastore;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;

public class TutorialSensei {

    private static Logger logger = Logger.getLogger(HelloLogger.class.getSimpleName());

    public void execute(RobotMessageBundle bundle) {
        for (Event e : bundle.getEvents()) {
            if(e.getBlip().getCreator().endsWith("@appspot.com")) {
                continue;
            }
            String waveId = e.getWavelet().getWaveId();
            TutorialStatusMeta meta = TutorialStatusMeta.get();
            TutorialStatus status = Datastore.query(meta)
                    .filter(meta.waveId.equal(waveId))
                    .asSingle();
            if (status == null) {
                status = new TutorialStatus();
                status.setWaveId(waveId);
                status.setIsFirstQuestion(true);
            }
            // レベルに応じて処理
            Integer level = status.getLevel();
            if (level == null) {
                level = 1;
            }
            Boolean isFirstQuestion = status.getIsFirstQuestion();
            if (isFirstQuestion == null) {
                isFirstQuestion = true;
            }
            Datastore.put(status);
            logger.warning(ToStringBuilder.reflectionToString(status));

            String message = null;
            switch (level) {
                case 1 :
                    if (isFirstQuestion) {
                        message = "Blipを追加してみましょう。";
                        status.setIsFirstQuestion(false);
                    } else if (e.getType() == EventType.BLIP_SUBMITTED) {
                        message = "よく出来ました！";
                        status.setLevel(2);
                        status.setIsFirstQuestion(true);
                    }
                    break;
                case 2 :
                    if (isFirstQuestion) {
                        message = "Blipを削除してみましょう。";
                        status.setIsFirstQuestion(false);
                    } else if (e.getType() == EventType.BLIP_DELETED) {
                        message = "よく出来ました！";
                        status.setLevel(3);
                        status.setIsFirstQuestion(true);
                    }
                    break;
                case 3 :
                    if (isFirstQuestion) {
                        message = "Blipを編集してみましょう。";
                        status.setIsFirstQuestion(false);
                    } else if (e.getType() == EventType.DOCUMENT_CHANGED) {
                        message = "よく出来ました！";
                        status.setLevel(4);
                        status.setIsFirstQuestion(true);
                    }
                    break;
            }
            Datastore.put(status);
            if (message != null) {
                Blip blip = e.getBlip().createChild();
                TextView textView = blip.getDocument();
                textView.append(message);
            }
        }

    }
}