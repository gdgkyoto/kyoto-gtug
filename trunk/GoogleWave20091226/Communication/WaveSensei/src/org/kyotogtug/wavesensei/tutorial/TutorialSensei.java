package org.kyotogtug.wavesensei.tutorial;

import hello.HelloLogger;

import java.util.logging.Logger;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.kyotogtug.wavesensei.WaveSenseiRobot;
import org.kyotogtug.wavesensei.tutorial.meta.TutorialStatusMeta;
import org.kyotogtug.wavesensei.tutorial.model.TutorialStatus;
import org.kyotogtug.wavesensei.util.WaveUtil;
import org.slim3.datastore.Datastore;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;

public class TutorialSensei {

    private static Logger logger = Logger.getLogger(HelloLogger.class.getSimpleName());

    public void execute(RobotMessageBundle bundle) {
        for (Event e : bundle.getEvents()) {
            try {
                if (e.getBlip() != null && e.getBlip().getCreator() != null
                        && e.getBlip().getCreator().endsWith("@appspot.com")) {
                    continue;
                }
            } catch (Exception ex) {}
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
                        message = "はじめまして。ひとりで遊びにきたの？\n"
                            + "Google Wave ははじめてかしら。\n" 
                            + "まずは Blip を追加してみましょうか。\n"
                            + "Blip とはひとつひとつの発言の枠のことよ。\n"
                            + "このメッセージが表示されているのも一つの Blip。\n"
                            + "Blip を追加するには、別の Blip の下端にマウスポイントをあわせて青枠がでたらクリックするの。";
                        appendChildBlip(e.getBlip(), message);
                        status.setIsFirstQuestion(false);
                    } else if (e.getType() == EventType.BLIP_SUBMITTED) {
                        if (WaveUtil.getGadget(e.getBlip(),
                                               WaveSenseiRobot.SENSEI_GADGET_URL) == null) {
                            message = "よく出来ました！追加できたようね。\n"
                                    + "次は Blip を削除してみましょう。\n"
                                    + "Blip を削除するには、Blip の右端にある逆三角のプルダウンから Delete を選ぶの";
                            appendChildBlip(e.getBlip(), message);
                            status.setLevel(2);
                            //                            message = "次は Blip を削除してみましょう。";
                            //                            appendChildBlip(e.getBlip(), message);
                            //status.setIsFirstQuestion(true);
                        }
                    }
                    break;
                case 2 :
                    if (isFirstQuestion) {
                        message = "Blipを削除してみましょう。";
                        appendChildBlip(e.getBlip(), message);
                        status.setIsFirstQuestion(false);
                    } else if (e.getType() == EventType.BLIP_DELETED) {
                        message = "よく出来ました！削除できたようね。\n"
                            + "そろそろお友達を誘ってみたらどうかしら。";
                        appendChildBlip(e.getWavelet(), message);
                        status.setLevel(3);
                        //                        status.setIsFirstQuestion(true);
                    }
                    break;
                case 3 :
                    if (isFirstQuestion) {
                        message = "Blipを編集してみましょう。";
                        appendChildBlip(e.getBlip(), message);
                        status.setIsFirstQuestion(false);
                    } else if (e.getType() == EventType.WAVELET_PARTICIPANTS_CHANGED
                            && e.getAddedParticipants().size() > 0) {
                        message = "よく出来ました！。\n" 
                            + "あなたはもう一人ぼっちじゃないわ。\n"
                            + "もう私に教えられることはないわ。\n"
                            + "あとわからないことがあれば「ものしり先生」にきいてみるといいわ。\n"
                            + "ものしり先生に答えられないことはないの。";                        
                        appendChildBlip(e.getWavelet(), message);
                        status.setLevel(4);
                        status.setIsFirstQuestion(true);
                    }
                    break;
            }
            Datastore.put(status);
            if (message != null) {}
        }

    }

    private void appendChildBlip(Blip blip, String message) {
        Blip newBlip = blip.createChild();
        TextView textView = newBlip.getDocument();
        textView.append(message);
    }

    private void appendChildBlip(Wavelet wavelet, String message) {
        Blip newBlip = wavelet.appendBlip();
        TextView textView = newBlip.getDocument();
        textView.append(message);
    }
}