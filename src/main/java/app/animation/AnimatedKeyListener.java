

package app.animation;

import java.awt.event.KeyEvent;

public interface AnimatedKeyListener {

    void animatedKeyPressed(Animated source, KeyEvent e);

    void animatedKeyReleased(Animated source, KeyEvent e);
}
