package app.gui;

import app.Translate;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ViewMenu extends View {

    private JButton btnStart;
    private JButton btnExit;
    private JButton btnMembershipFunctions;
    private JButton btnCalculateDamage;
    private JButton btnCalculateSpeed;

    @Override
    public void init() {
        setSize(400, 350);
        setLayout(new BorderLayout());

        JPanel pnlCenter = new JPanel(new MigLayout("insets 30"));
        add(pnlCenter, BorderLayout.CENTER);

        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);

        btnStart = new JButton();
        btnStart.setFont(buttonFont);

        btnStart = new JButton();
        btnStart.setFont(buttonFont);

        btnStart = new JButton();
        btnStart.setFont(buttonFont);

        btnMembershipFunctions = new JButton();
        btnMembershipFunctions.setFont(buttonFont);

        btnCalculateDamage = new JButton();
        btnCalculateDamage.setFont(buttonFont);

        btnCalculateSpeed = new JButton();
        btnCalculateSpeed.setFont(buttonFont);

        btnExit = new JButton();
        btnExit.setFont(buttonFont);

        String buttonLayout = "width 100%, height 40, wrap 10";
        pnlCenter.add(btnStart, buttonLayout);
        pnlCenter.add(btnMembershipFunctions, buttonLayout);
        pnlCenter.add(btnCalculateDamage, buttonLayout);
        pnlCenter.add(btnCalculateSpeed, buttonLayout);
        pnlCenter.add(btnExit, buttonLayout);

        addButtonToAction(btnStart);
        addButtonToAction(btnExit);
        addButtonToAction(btnMembershipFunctions);
        addButtonToAction(btnCalculateDamage);
        addButtonToAction(btnCalculateSpeed);
    }

    public JButton getBtnCalculateSpeed() {
        return btnCalculateSpeed;
    }

    @Override
    public void translate() {
        btnStart.setText(Translate.get("GUI_START"));
        btnExit.setText(Translate.get("GUI_EXIT"));
        btnMembershipFunctions.setText(Translate.get("GUI_MEMBERSHIPFUNCTIONS"));
        btnCalculateDamage.setText(Translate.get("GUI_CALCULATEDAMAGE"));
        btnCalculateSpeed.setText(Translate.get("GUI_CALCULATESPEED"));
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JButton getBtnMembershipFunctions() {
        return btnMembershipFunctions;
    }

    public JButton getBtnCalculateDamage() {
        return btnCalculateDamage;
    }

}
