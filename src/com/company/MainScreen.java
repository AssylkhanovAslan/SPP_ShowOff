package com.company;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainScreen extends Applet implements KeyListener {

    private static final String SCORE_FORMAT = "Score: %d";

    private Rectangle rect;
    private Rectangle snake;
    private Rectangle target;
    private Label score;
    private int scoreValue = 0;

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
            snake.y -= 10;
            repaint();
            evaluateCurrentSituation();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.y += 10;
            repaint();
            evaluateCurrentSituation();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.x -= 10;
            repaint();
            evaluateCurrentSituation();
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.x += 10;
            repaint();
            evaluateCurrentSituation();
            return;
        }
    }

    private void evaluateCurrentSituation() {
        if (snake.x != target.x || snake.y != target.y) {
            return;
        }

        scoreValue++;
        updateScore();
        putTargetInRandomPlace();
        repaint();
    }

    private void updateScore() {
        String scoreText = String.format(SCORE_FORMAT, scoreValue);
        score.setText(scoreText);
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
