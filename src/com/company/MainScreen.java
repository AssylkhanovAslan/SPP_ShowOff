package com.company;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainScreen extends Applet implements KeyListener {

    private Rectangle rect;
    private Rectangle snake;
    private Rectangle target;
    private Label score;

    @Override
    public void init() {
        super.init();
        addKeyListener(this);
        setSize(500, 500);
        setName("Snake");
        setFocusable(true);
        requestFocusInWindow();

        snake = new Rectangle(0, 30, 10, 10);
        score = new Label("Start moving the keys");
        add(score);

        putTargetInRandomPlace();
    }

    @Override
    public void paint(Graphics g) {
        setSize(500, 500);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(snake);
        graphics2D.fill(target);

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            score.setText("UP");
            Timber.e("UP");
            snake.y -= 10;
            repaint();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            score.setText("DOWN");
            Timber.e("DOWN");
            snake.y += 10;
            repaint();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            score.setText("VK_LEFT");
            Timber.e("LEFT");
            snake.x -= 10;
            repaint();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            score.setText("VK_RIGHT");
            Timber.e("RIGHT");
            snake.x += 10;
            repaint();
            return;
        }
    }

    private void putTargetInRandomPlace() {
        int targetX = snake.x;
        int targetY = snake.y;
        while (targetX == snake.x && targetY == snake.y) {
            targetX = new Random().nextInt(46) + 3;
            targetY = new Random().nextInt(46) + 3;
            targetX *= 10;
            targetY *= 10;
            System.out.println(targetX);
            System.out.println(targetY);
        }
        if (target == null) {
            target = new Rectangle(targetX, targetY, 10, 10);
        }
        target.x = targetX;
        target.y = targetY;
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
