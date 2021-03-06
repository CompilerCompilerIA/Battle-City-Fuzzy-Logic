package app.gui;

import app.Config;
import app.Translate;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ViewCalculateSpeed extends View {

    private JSpinner spiLife;
    private JLabel lblLife;
    private JButton btnCalculate;
    private JButton btnClose;

    @Override
    public void init() {
        setSize(300, 150);
        setLayout(new MigLayout("insets 20"));
        lblLife = new JLabel();
        spiLife = new JSpinner();
        spiLife.setModel(new SpinnerNumberModel(0, 0, Integer.parseInt(Config.get("MAX_LIFE_VALUE")), 1));


        btnCalculate = new JButton();
        btnClose = new JButton();

        add(lblLife, "width 50%");
        add(spiLife, "width 50%, wrap 20");
        add(btnCalculate, "grow, height 30");
        add(btnClose, "grow, height 30, wrap");

        addButtonToAction(btnCalculate);
        addButtonToAction(btnClose);
    }

    @Override
    public void translate() {
        btnClose.setText(Translate.get("GUI_CLOSE"));
        btnCalculate.setText(Translate.get("GUI_CALCULATE"));
        lblLife.setText(Translate.get("GUI_LIFE"));
    }

    public JSpinner getSpiLife() {
        return spiLife;
    }

    public JButton getBtnCalculate() {
        return btnCalculate;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

}
