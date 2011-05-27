package jp.co.spookies.android.fsktest;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;

public class FSKTest extends Activity {
    private static final int W = 255;
    private static final int fL = 600;
    private static final int fH = 1200;
    private static final int F = 8000;
    private static final int bps = 1000;
    private static final double dL = Math.PI * 2 * fL / F;
    private static final double dH = Math.PI * 2 * fH / F;
    private static final byte[] wavL = { 0, 89, 127, 89, 0, -90, -128, -90 };
    private static final byte[] wavH = { 0, 127, 0, -128, 0, 127, 0, -128};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        int t = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, F, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_8BIT, F * 1 * t, AudioTrack.MODE_STREAM);
        track.play();
        short[] wav = new short[F * t];
        for (int i = 0; i < wav.length; i++) {
            wav[i] = (short) (Math.floor(Math.sin(i * dH) * W / 2));
        }
        track.write(wav, 0, wav.length);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < wav.length; i++) {
            wav[i] = (short) (Math.floor(Math.sin(i * dL) * W / 2));
        }
        track.write(wav, 0, wav.length);
    }
}