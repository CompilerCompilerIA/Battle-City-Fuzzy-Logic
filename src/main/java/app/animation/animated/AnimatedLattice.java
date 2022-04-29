package app.animation.animated;

import app.Config;
import app.animation.Animated;

import java.awt.*;

public class AnimatedLattice extends Animated {

    private Color color;
    private int size;
    private int cellSize;

    @Override
    public void init() {
        size = Integer.parseInt(Config.get("LATTICE_SIZE"));
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
        color = new Color(30, 30, 30);
        //color = new Color(173, 217, 43);
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(color);
        for (int i = 0; i <= size; i++) {
            g.drawLine(0, i * cellSize, size * cellSize, i * cellSize);
            g.drawLine(i * cellSize, 0, i * cellSize, size * cellSize);
        }
    }

    @Override
    public void animate() {

    }

}
