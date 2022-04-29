package app.animation.animated;

import app.Config;
import app.Translate;
import app.animation.Animated;
import app.util.ImageLoader;

import java.awt.*;

public class AnimatedInfoPlayer extends Animated {

    private AnimatedPlayer player;
    private int latticeSize;
    private int cellSize;
    private int sizeBarLife;
    private int sizeBarRes;
    private int sizeBarSpeed;
    private Font font;
    private Image image;

    public AnimatedInfoPlayer(AnimatedPlayer player) {
        image = ImageLoader.loadImage("battle-tank").getImage();
        this.player = player;
    }

    @Override
    public void init() {
        latticeSize = Integer.parseInt(Config.get("LATTICE_SIZE"));
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
        setX(latticeSize * cellSize + 40);
        setY(50);
        setAngle(180);
        setZ(500);
        setWidth(cellSize - 4);
        setHeight(cellSize - 4);
        font = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void paint(Graphics2D g) {

        int x = getX() - getWidth() / 2;
        int y = getY() - getHeight() / 2;

        g.drawImage(image, getX() - getWidth() / 2, getY()- getHeight() / 2, getWidth(), getHeight(), null);

        g.rotate(-Math.toRadians(getAngle()), getX(), getY());
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString(Translate.get("GUI_LIFE"), x, y + getHeight() / 2 + 40);
        g.setColor(Color.red);
        g.fillRect(x, y + getHeight() / 2 + 45, 150, 5);
        g.setColor(Color.green);
        g.fillRect(x, y + getHeight() / 2 + 45, sizeBarLife, 5);

        g.setColor(Color.black);
        g.drawString(Translate.get("GUI_RESISTANCE"), x, y + getHeight() / 2 + 65);
        g.setColor(Color.red);
        g.fillRect(x, y + getHeight() / 2 + 70, 150, 5);
        g.setColor(Color.green);
        g.fillRect(x, y + getHeight() / 2 + 70, sizeBarRes, 5);

        g.setColor(Color.black);
        g.drawString(Translate.get("GUI_SPEED"), x, y + getHeight() / 2 + 90);
        g.setColor(Color.red);
        g.fillRect(x, y + getHeight() / 2 + 95, 150, 5);
        g.setColor(Color.green);
        g.fillRect(x, y + getHeight() / 2 + 95, sizeBarSpeed, 5);

        g.rotate(Math.toRadians(getAngle()), getX(), getY());

    }

    @Override
    public void animate() {
        sizeBarLife = player.getLife() * 150 / 100;
        sizeBarRes = player.getResistance() * 150 / 100;
        sizeBarSpeed = player.getSpeed() * 150 / 6;
    }

}
