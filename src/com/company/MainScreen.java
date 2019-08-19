package com.company;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.awt.event.KeyEvent.*;

public class MainScreen extends Applet implements KeyListener {

    private static final String SCORE_FORMAT = "Score: %d";

    private Snake snake;
    private Target target;
    private Label score;
    private int scoreValue = 0;
    private ScheduledExecutorService executorService;

    @Override
    public void init() {
        super.init();
        addKeyListener(this);
        setSize(500, 500);
        setName("Snake");
        setFocusable(true);
        requestFocusInWindow();
        target = new Target();
        snake = new Snake();
        score = new Label("Start moving the keys", Label.CENTER);
        add(score);


        target.putTargetInRandomPlace(snake);
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(gameStepInitiator, 0, 100, TimeUnit.MILLISECONDS);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(0, 30, 500, 30);
        graphics2D.fill(snake.getHead());
        for (Rectangle item : snake.getTail()) {
            graphics2D.fill(item);
        }


        graphics2D.setColor(Color.RED);
        graphics2D.fill(target);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key != VK_UP && key != VK_DOWN && key != VK_LEFT && key != VK_RIGHT) {
            return;
        }
        snake.setDirection(key);
    }

    private void evaluateCurrentSituation() {
        Rectangle snakeHead = snake.getHead();
        for (Rectangle tailItem : snake.getTail()) {
            if (tailItem.intersects(snakeHead)) {
                score.setText("GAME OVER");
                executorService.shutdown();
                removeKeyListener(this);
            }
        }

        if (snake.eatsTarget(target)) {
            scoreValue++;
            updateScore();
            target.putTargetInRandomPlace(snake);
        }
    }

    private void updateScore() {
        String scoreText = String.format(SCORE_FORMAT, scoreValue);
        score.setText(scoreText);
    }

    private Runnable gameStepInitiator = () -> {
        snake.moveSnake();
        evaluateCurrentSituation();
        repaint();
    };

    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
