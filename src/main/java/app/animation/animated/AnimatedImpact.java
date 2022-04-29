package app.animation.animated;

import app.Config;
import app.animation.Animated;
import app.util.ClipLoader;
import javax.sound.sampled.Clip;
import java.awt.*;

public class AnimatedImpact extends Animated {

    @Override
    public void init() {
        setWidth(0);

        if (Config.get("SOUND_EFFECTS").equals("OFF"))
            return;

        Thread soundThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Clip sound = ClipLoader.getClip("explosion");
                    if (sound != null)
                        sound.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        soundThread.start();
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.RED);
        g.drawOval(getX() - getWidth() / 2, getY() - getWidth() / 2, getWidth(), getWidth());
    }

    @Override
    public void animate() {
        setWidth(getWidth() + 10);
        if (getWidth() > 40)
            removeAnimated();
    }

}
