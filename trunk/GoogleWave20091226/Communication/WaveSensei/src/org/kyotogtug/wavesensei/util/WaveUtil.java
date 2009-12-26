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
        try {
            for (Gadget g : blip.getDocument().getGadgetView().getGadgets()) {
                if (g.getUrl().equals(url)) {
                    return g;
                }
            }
        } catch (Throwable t) {}
        return null;
    }

}
