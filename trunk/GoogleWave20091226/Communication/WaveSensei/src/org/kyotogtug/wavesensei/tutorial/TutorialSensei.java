package org.kyotogtug.wavesensei.tutorial;

import org.kyotogtug.wavesensei.tutorial.meta.TutorialStatusMeta;
import org.kyotogtug.wavesensei.tutorial.model.TutorialStatus;
import org.slim3.datastore.Datastore;

import com.google.wave.api.Event;

public class TutorialSensei {

    public void execute(Event event) {

        String waveId = event.getWavelet().getWaveId();
        TutorialStatusMeta e = TutorialStatusMeta.get();
        TutorialStatus status = Datastore.query(e).filter(e.waveId.equal(waveId)).asSingle();
        if(status == null) {
            status = new TutorialStatus();
        }
        
        // レベルに応じて処理
        
    }

}