package org.kyotogtug.wavesensei.util;

import com.google.wave.api.Blip;
import com.google.wave.api.Gadget;

public class WaveUtil {

    /**
     * Blip から先生選択ガジェットを取得する
     * なければ null
     * @param blip
     * @return
     */
    public static Gadget getGadget(Blip blip, String url) {
        if(blip.getDocument() == null) return null;
        if(blip.getDocument().getGadgetView() == null) return null;
        if(blip.getDocument().getGadgetView().getGadgets() == null) return null;
        for(Gadget g : blip.getDocument().getGadgetView().getGadgets()) {
            if(g.getUrl().equals(url)) {
                return g;
            }
        }
        return null;
    }

}
