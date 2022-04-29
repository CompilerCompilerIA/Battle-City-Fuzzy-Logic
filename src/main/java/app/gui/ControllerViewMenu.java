package app.gui;

import app.flc.FLCLoader;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

import javax.swing.*;

public class ControllerViewMenu extends Controller {

    private ViewMenu viewMenu;

    public ControllerViewMenu() {
        viewMenu = new ViewMenu();
        viewMenu.setController(this);
    }

    @Override
    public void action(Object source) {
        if (source.equals(viewMenu))
            exit();
        else if (source.equals(viewMenu.getBtnExit()))
            exit();
        else if (source.equals(viewMenu.getBtnStart()))
            start();
        else if (source.equals(viewMenu.getBtnMembershipFunctions()))
            membershipFunctions();
        else if (source.equals(viewMenu.getBtnCalculateDamage()))
            calculateDamage();
        else if (source.equals(viewMenu.getBtnCalculateSpeed()))
            calculateSpeed();
    }

    public void calculateDamage() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControllerViewCalculateDamage();
                viewMenu.dispose();
            }
        });
    }

    public void calculateSpeed() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControllerViewCalculateSpeed();
                viewMenu.dispose();
            }
        });
    }

    public void membershipFunctions() {
        FIS fis = FIS.load(FLCLoader.load(), true);
        JFuzzyChart.get().chart(fis);
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControllerViewGame();
                viewMenu.dispose();
            }
        });
    }

    public void exit() {
        System.exit(0);
    }



}
