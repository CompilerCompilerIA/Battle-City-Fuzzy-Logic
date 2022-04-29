package app;

import app.gui.ControllerViewMenu;
import javax.swing.*;

public class Main {

    private static void loadFeatures() {
        try {
            Config.load();
            Translate.load();
        } catch (Exception e) {
            System.out.println("Error detectado" + e);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        loadFeatures();
        SwingUtilities.invokeLater(ControllerViewMenu::new);
    }

}
