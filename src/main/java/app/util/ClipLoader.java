package app.util;

import javax.sound.sampled.*;

public class ClipLoader {

    public static synchronized Clip getClip(String name) {
        AudioInputStream soundIn;
        DataLine.Info info;
        try {
            soundIn = AudioSystem.getAudioInputStream(ClassLoader.getSystemResourceAsStream("sound/" + name + ".wav"));
            AudioFormat format = soundIn.getFormat();
            info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(soundIn);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
