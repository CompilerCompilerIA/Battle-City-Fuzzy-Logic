package app.gui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public abstract class Controller implements ActionListener, ChangeListener, WindowListener, ItemListener {

    public abstract void action(Object source);

    @Override
    public void itemStateChanged(ItemEvent e) {
        action(e.getSource());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        action(e.getSource());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action(e.getSource());
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        action(e.getSource());
    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

}
