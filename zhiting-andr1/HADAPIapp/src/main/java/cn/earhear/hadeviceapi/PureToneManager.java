package cn.earhear.hadeviceapi;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import cn.earhear.hadevicelib.constant.FittingAlgorithm;
import cn.earhear.hadevicelib.constant.SideSelect;

/**
 * Created by ice88 on 2018-5-30.
 */

public class PureToneManager {
    private Context context;
    private AudioTrack audioTrack;
    private int vol, max_vol;
    private final int duration = 2; // seconds
    private int sampleRate = 44100;

    public PureToneManager(Context context) {
        this.context = context;
        /*
        AudioManager am= (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if (am != null)
            vol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            */
    }

    public void prepare_gen_tone() {
        AudioManager am= (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if (am == null)
            return;
        max_vol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        vol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (max_vol/2 == vol)
            return;
        am.setStreamVolume(AudioManager.STREAM_MUSIC, max_vol/2, AudioManager.FLAG_SHOW_UI);
    }

    public void start(int side, int freq, int dB) {
        stop();
        if (dB < FittingAlgorithm.MIN_HL_VAL || dB > FittingAlgorithm.MAX_HL_VAL)
            return;
        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate, AudioFormat.CHANNEL_OUT_DEFAULT,
                AudioFormat.ENCODING_PCM_16BIT, sampleRate*duration, AudioTrack.MODE_STREAM);
        if (side == SideSelect.S_UNILATERAL_LEFT)
            audioTrack.setStereoVolume(1.0f, 0);
        else
            audioTrack.setStereoVolume(0, 1.0f);
        audioTrack.write(calcSinArray(freq, dB), 0, sampleRate*duration);
        audioTrack.play();
        audioTrack.flush();
    }


    public void restore_tone() {
        stop();
        AudioManager am= (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        if (am == null)
            return;
        if (vol != max_vol/2)
            am.setStreamVolume(AudioManager.STREAM_MUSIC, vol, AudioManager.FLAG_SHOW_UI);
    }

    private void stop() {
        if (audioTrack != null) {
            audioTrack.stop();
            audioTrack.release();
            audioTrack = null;
        }
    }

    private short[] calcSinArray(int freq, int dB) {
        int totLen = sampleRate*duration;
        short[] wave = new short[totLen];
        short[] wave_car = new short[totLen];
        double amp = updateAmplitude(freq, dB);

        for (int i = 0; i < totLen; i++) {
            wave[i] = (short) (amp * Math.sin(2 * Math.PI * i * freq / sampleRate));
            //wave_car[i] = (short) Math.sin(2 * Math.PI * i / sampleRate);
        }

        /*
        for (int i=0; i<totLen; i++) {
            int add = 0;
            for (int j=0; j<i; j++) {
                add = wave[j]*wave_car[j];
            }
            wave[i] = wave[i];
        }*/

        return wave;
    }

    private double updateAmplitude(int freq, int dB) {
        double temp = 0;
        switch (freq) { // GB/T 16402-1996
            case 125: temp = 28; break;
            case 250: temp = 17.5; break;
            case 500: temp = 9.5; break;
            case 750: temp = 6; break;
            case 1000: temp = 5.5; break;
            case 1500: temp = 9.5; break;
            case 2000: temp = 11.5; break;
            case 3000: temp = 13; break;
            case 4000: temp = 15; break;
            case 6000: temp = 16; break;
            case 8000: temp = 15.5; break;
        }
        return Math.pow(10, (dB + temp) / 20);
    }
}
