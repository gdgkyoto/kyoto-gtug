package org.kyotogtug.wavesensei;

import java.util.logging.Logger;

import org.kyotogtug.wavesensei.meta.CommonStatusMeta;
import org.kyotogtug.wavesensei.model.CommonStatus;
import org.kyotogtug.wavesensei.tutorial.TutorialSensei;
import org.kyotogtug.wavesensei.tw.TwitterSensei;
import org.kyotogtug.wavesensei.util.WaveUtil;
import org.kyotogtug.wavesensei.util.XMPPHandler;
import org.slim3.datastore.Datastore;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.Gadget;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;

@SuppressWarnings("serial")
public class WaveSenseiRobot extends AbstractRobotServlet {

    private static Logger log = Logger.getLogger(WaveSenseiRobot.class.getName());
    public static final String DEFAULT_SENSEI_TYPE = "-1";
    public static final String SENSEI_GADGET_URL = "http://hosting.gmodules.com/ig/gadgets/file/100410660575232591215/ChooseWS.xml";
    private static final String GADGET_RELOAD_FIELD = "Reload";
    private static final String LOG_NOTIFY_TO = "bufferings@gmail.com";

    static {
      log.addHandler(new XMPPHandler(LOG_NOTIFY_TO));
    }

    @Override
    public void processEvents(RobotMessageBundle bundle) {
        addGadget(bundle);
      
        String gSensei = sensei(bundle);
//        log.warning("SenseiType: " + gSensei);
        if (gSensei.equals("1")) {
            // ついった先生
            boolean timerEvent = isTimerEvent(bundle);
            if(timerEvent){
              log.warning("タイマーイベントです");
              new TwitterSensei().getTwPeplyData(bundle);
            }
            new TwitterSensei().processEvents(bundle);
        } else if (gSensei.equals("0")) {
            // ちゅーと先生
            new TutorialSensei().execute(bundle);
        } else {
            // なにもしない
        }
    }

    /**
     * Wave 別の状態オブジェクトを取得する。
     * なければ新規作成する。
     * @param bundle
     * @return
     */
    private CommonStatus getStatus(RobotMessageBundle bundle) {
        String waveId = bundle.getWavelet().getWaveId();
        CommonStatusMeta e = CommonStatusMeta.get();
        CommonStatus status = Datastore.query(e)
                .filter(e.waveId.equal(waveId))
                .asSingle();
        if (status == null) {
            status = new CommonStatus();
            status.setWaveId(waveId);
            status.setTeacherType(DEFAULT_SENSEI_TYPE);
            saveStatus(status);
        }
        return status;
    }

    private void saveStatus(CommonStatus status) {
        Datastore.put(status);
    }

    /**
     * イベントと永続化情報から、現在の先生タイプを返す。
     * 先生変更を検知したらその情報を保存しておく
     * @param bundle
     * @return 先生タイプ
     */
    public String sensei(RobotMessageBundle bundle) {
        CommonStatus status = getStatus(bundle);
        String cSenseiType = status.getTeacherType();
        String gSenseiType = null;
        for (Event e : bundle.getEvents()) {
           Gadget g = WaveUtil.getGadget(e.getBlip(), SENSEI_GADGET_URL);
           if(g == null) continue;
           gSenseiType = g.getProperty("SenseiType");
        }
        if(gSenseiType == null) {
            return cSenseiType;
        }
        if(!gSenseiType.equals(cSenseiType)){
            status.setTeacherType(gSenseiType);
            Datastore.put(status);
        }
        return gSenseiType;
    }
      
    /**
     * ロボットが追加された場合、ルートブリップにガジェットを追加します。
     * @param robotMessageBundle
     */
    public void addGadget(RobotMessageBundle robotMessageBundle) {
      Wavelet wavelet = robotMessageBundle.getWavelet();
      Blip rootBlip = wavelet.getRootBlip();
      TextView textView = rootBlip.getDocument();

      if (robotMessageBundle.wasSelfAdded()) {
        rootBlip.getDocument().setAuthor("ウェーブ先生より");
        if (textView.getGadgetView().getGadget(SENSEI_GADGET_URL) == null) {
          Gadget gadget = new Gadget(SENSEI_GADGET_URL);
          textView.getGadgetView().append(gadget);
          gadget.setField("id", wavelet.getWaveId());
        }
      }
    }
    
    /**
     * ガジェットのタイマーイベントかどうかを判別します。
     * @param robotMessageBundle
     * @return
     */
    public boolean isTimerEvent(RobotMessageBundle robotMessageBundle){
      Wavelet wavelet = robotMessageBundle.getWavelet();
      boolean toCheck = false;
      for (Event e : robotMessageBundle.getEvents()) {
        if(e.getType() == EventType.BLIP_SUBMITTED){
          toCheck = true;
          break;
        }
      }
      if(!toCheck){
        return false;
      }
      
      Gadget gadget =WaveUtil.getGadget(wavelet.getRootBlip(), SENSEI_GADGET_URL);
      String gadgetReloadProp = gadget.getField(GADGET_RELOAD_FIELD);
      if(gadgetReloadProp == null){
        return false;
      }
      
      CommonStatus status = getStatus(robotMessageBundle);
      if(status.getReload() == null ||  !status.equals(gadgetReloadProp)){
        status.setReload(gadgetReloadProp);
        Datastore.put(status);
        return true;
      }
      
      return false;
    }
    
}
