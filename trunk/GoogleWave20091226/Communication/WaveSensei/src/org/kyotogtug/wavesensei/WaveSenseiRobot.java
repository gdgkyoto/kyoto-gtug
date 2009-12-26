package org.kyotogtug.wavesensei;

import java.util.logging.Logger;

import org.kyotogtug.wavesensei.meta.CommonStatusMeta;
import org.kyotogtug.wavesensei.model.CommonStatus;
import org.kyotogtug.wavesensei.tutorial.TutorialSensei;
import org.slim3.datastore.Datastore;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.Gadget;
import com.google.wave.api.RobotMessageBundle;

public class WaveSenseiRobot extends AbstractRobotServlet {

    private Logger log = Logger.getLogger(this.getClass().getName());
    public static final String DEFAULT_SENSEI_TYPE = "-1";
    public static final String SENSEI_GADGET_URL = "http://hosting.gmodules.com/ig/gadgets/file/100410660575232591215/ChooseWS.xml";

    @Override
    public void processEvents(RobotMessageBundle bundle) {
        String gSensei = sensei(bundle);
        log.warning("SenseiType: " + gSensei);
        if (gSensei.equals("1")) {
            // ついった先生
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
           Gadget g = getSenseiGadget(e.getBlip());
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
     * Blip から先生選択ガジェットを取得する
     * なければ null
     * @param blip
     * @return
     */
    private Gadget getSenseiGadget(Blip blip) {
        if(blip.getDocument() == null) return null;
        if(blip.getDocument().getGadgetView() == null) return null;
        if(blip.getDocument().getGadgetView().getGadgets() == null) return null;
        for(Gadget g : blip.getDocument().getGadgetView().getGadgets()) {
            if(g.getUrl().equals(SENSEI_GADGET_URL)) {
                return g;
            }
        }
        return null;
    }

}
