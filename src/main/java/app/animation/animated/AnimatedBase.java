package app.animation.animated;

import app.Config;
import app.animation.Animated;
import app.util.ImageLoader;

import java.awt.*;

public class AnimatedBase extends Animated {

    private final int cellSize;
    private final Image image;
    private final Rectangle rectangle;
    private final Image image2;
    private boolean destroy;

    public AnimatedBase(int row, int column) {
        setColumn(column);
        setRow(row);
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
        image = ImageLoader.loadImage("base").getImage();
        image2 = ImageLoader.loadImage("base_destroyed").getImage();
        rectangle = new Rectangle();
        destroy = false;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
    public void init() {
        setWidth(cellSize);
        setHeight(cellSize);
        setX(getColumn() * cellSize);
        setY(getRow() * cellSize);
    }

    @Override
    public Shape getShape() {
        rectangle.setFrame(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }

    @Override
    public void paint(Graphics2D g) {
        if (destroy)
            g.drawImage(image2, getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);

    }

    @Override
    public void animate() {

    }

}
