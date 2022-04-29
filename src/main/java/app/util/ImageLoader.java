package app.util;

import javax.swing.*;

public class ImageLoader {

    public static ImageIcon loadImage(String name) {
        return new ImageIcon(ClassLoader.getSystemResource("images/" + name + ".png"));
    }
}
