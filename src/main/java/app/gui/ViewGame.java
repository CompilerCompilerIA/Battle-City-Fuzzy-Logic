package app.gui;

import app.animation.Animator;

public class ViewGame extends View {

    private Animator animator;

    @Override
    public void init() {
        setSize(800, 635);
        animator = new Animator();
        add(animator);
    }

    public Animator getAnimator() {
        return animator;
    }
}
