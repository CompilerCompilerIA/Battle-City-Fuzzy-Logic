package app.animation.animated;

import app.Config;
import app.animation.Animated;
import app.animation.AnimatedKeyListener;
import app.flc.FLCLoader;
import app.util.ImageLoader;
import net.sourceforge.jFuzzyLogic.FIS;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class AnimatedPlayer extends Animated implements AnimatedKeyListener {

    public static int DIR_UP = 1;
    public static int DIR_RIGHT = 2;
    public static int DIR_DOWN = 3;
    public static int DIR_LEFT = 4;
    public int direction;
    private int speed;
    private final int latticeSize;
    private final int cellSize;
    private Rectangle rectangle;
    private boolean move;
    private final Image image;
    private int life;
    private int resistance;
    private int attack;
    private final Random rand;
    private final FIS fis;

    public AnimatedPlayer(int row, int column) {
        setColumn(column);
        setRow(row);
        latticeSize = Integer.parseInt(Config.get("LATTICE_SIZE"));
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
        addAnimatedKeyListener(this);
        image = ImageLoader.loadImage("battle-tank").getImage();

        rand = new Random();
        resistance = 61 + rand.nextInt(40);
        attack = 61 + rand.nextInt(40);
        life = 100;
        fis = FIS.load(FLCLoader.load(), true);
        calculateSpeed();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void calculateSpeed() {
        fis.setVariable("Vida", life);
        fis.evaluate();
        speed = (int) fis.getVariable("Velocidad").defuzzify();
    }

    public void calculateDamage(int attack) {
        fis.setVariable("Vida", life);
        fis.setVariable("Ataque", attack);
        fis.setVariable("Resistencia", resistance);
        fis.evaluate();
        life -= fis.getVariable("Damage").defuzzify();
        if (life <= 0) {
            removeAnimated();
            AnimatedGameOver animatedGameOver = new AnimatedGameOver(false);
            getAnimator().addAnimated(animatedGameOver);
        }
    }

    @Override
    public void init() {
        setWidth(cellSize - 4);
        setHeight(cellSize - 4);
        setX(getColumn() * cellSize + getWidth() / 2);
        setY(getRow() * cellSize + getHeight() / 2);
        setZ(100);
        speed = 6;
        rectangle = new Rectangle();
        setDirection(DIR_RIGHT);
    }

    @Override
    public Shape getShape() {
        rectangle.setFrame(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        return rectangle;
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(image, getX() - getWidth() / 2, getY()- getHeight() / 2, getWidth(), getHeight(), null);
    }

    public int intersect(int x, int y, int w, int h, int direction) {
        for (int i = 0; i < getAnimator().getAnimateds().size(); i++) {
            Animated animated = getAnimator().getAnimateds().get(i);
            if (animated == this || animated.getShape() == null || animated instanceof AnimatedBullet)
                continue;

            if (direction == DIR_UP) {
                for (int j = 0; j <= speed; j++) {
                    if (animated.getShape().intersects(x, y - j - 1, w, h))
                        return j;
                }
            } else if (direction == DIR_DOWN) {
                for (int j = 0; j <= speed; j++) {
                    if (animated.getShape().intersects(x, y + j + 1, w, h))
                        return j;
                }
            } else if (direction == DIR_LEFT) {
                for (int j = 0; j <= speed; j++) {
                    if (animated.getShape().intersects(x - j - 1, y, w, h))
                        return j;
                }

            } else if (direction == DIR_RIGHT) {
                for (int j = 0; j <= speed; j++) {
                    if (animated.getShape().intersects(x + j + 1, y, w, h))
                        return j;
                }
            }

        }
        return speed;
    }

    @Override
    public void animate() {
        int speed = 0;
        if (direction == DIR_UP) {
            setAngle(90);
            speed = intersect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), 1, direction);
            if (move && getY() - getHeight() / 2 > 0)
                setY(getY() - speed);

        } else if (direction == DIR_DOWN) {
            setAngle(270);
            speed = intersect(getX() - getWidth() / 2, getY() + getHeight() / 2, getWidth(), 1, direction);
            if (move && getY() + getHeight() / 2 < latticeSize * cellSize)
                setY(getY() + speed);

        } else if (direction == DIR_LEFT) {
            setAngle(180);
            speed = intersect(getX() - getWidth() / 2, getY() - getHeight() / 2, 1, getHeight(), direction);
            if (move && getX() - getWidth() / 2 > 0)
                setX(getX() - speed);

        } else if (direction == DIR_RIGHT) {
            setAngle(0);
            speed = intersect(getX() + getWidth() / 2, getY() - getHeight() / 2, 1, getHeight(), direction);
            if (move && getX() + getWidth() / 2 < latticeSize * cellSize)
                setX(getX() + speed);
        }

        int enemys = 0;
        for (int i = 0; i < getAnimator().getAnimateds().size(); i++) {
            Animated animated = getAnimator().getAnimateds().get(i);
            if (animated instanceof AnimatedEnemy)
                enemys++;
        }

        if (enemys == 0) {
            AnimatedGameOver animatedGameOver = new AnimatedGameOver(true);
            getAnimator().addAnimated(animatedGameOver);
        }

    }

    @Override
    public void animatedKeyPressed(Animated source, KeyEvent e) {

        AnimatedPlayer animated = (AnimatedPlayer) source;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            animated.setDirection(AnimatedPlayer.DIR_UP);
            move = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            animated.setDirection(AnimatedPlayer.DIR_DOWN);
            move = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            animated.setDirection(AnimatedPlayer.DIR_LEFT);
            move = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            animated.setDirection(AnimatedPlayer.DIR_RIGHT);
            move = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            fire();
        }

    }

    public void fire() {
        AnimatedBullet animatedBullet = new AnimatedBullet(this);
        animatedBullet.setDirection(getDirection());
        int space = 10;
        if (direction == DIR_DOWN) {
            animatedBullet.setY(getY() + getHeight() / 2 + space);
            animatedBullet.setX(getX());
        } else if (direction == DIR_UP) {
            animatedBullet.setY(getY() - getHeight() / 2 - space);
            animatedBullet.setX(getX());
        } else if (direction == DIR_LEFT) {
            animatedBullet.setY(getY());
            animatedBullet.setX(getX() - getWidth() / 2 - space);
        } else if (direction == DIR_RIGHT) {
            animatedBullet.setY(getY());
            animatedBullet.setX(getX() + getWidth() / 2 + space);
        }
        animatedBullet.setAttack(attack);
        getAnimator().addAnimated(animatedBullet);
    }

    @Override
    public void animatedKeyReleased(Animated source, KeyEvent e) {
        move = false;
    }

}
