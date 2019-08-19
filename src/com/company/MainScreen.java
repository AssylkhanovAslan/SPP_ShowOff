package com.company;

import javafx.scene.shape.Line;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.awt.event.KeyEvent.*;

public class MainScreen extends Applet implements KeyListener {

    private static final String SCORE_FORMAT = "Score: %d";

    private Rectangle rect;
    private Rectangle snake;
    private Rectangle target;
    private Label score;
    private int scoreValue = 0;
    private int snakeDirection = VK_RIGHT;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Override
    public void init() {
        super.init();
        addKeyListener(this);
        setSize(500, 500);
        setName("Snake");
        setFocusable(true);
        requestFocusInWindow();

        snake = new Rectangle(0, 30, 10, 10);
        score = new Label("Start moving the keys", Label.CENTER);
        add(score);

        putTargetInRandomPlace();
        executorService.scheduleAtFixedRate(gameStepInitiator, 0, 100, TimeUnit.MILLISECONDS);
    }

    @Override
    public void paint(Graphics g) {
        setSize(500, 500);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(snake);
        graphics2D.fill(target);
        graphics2D.drawLine(0, 30, 500, 30);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key != VK_UP && key != VK_DOWN && key != VK_LEFT && key != VK_RIGHT) {
            return;
        }
        if (key == VK_UP && snakeDirection == VK_DOWN) {
            return;
        }
        if (key == VK_DOWN && snakeDirection == VK_UP) {
            return;
        }
        if (key == VK_LEFT && snakeDirection == VK_RIGHT) {
            return;
        }
        if (key == VK_RIGHT && snakeDirection == VK_LEFT) {
            return;
        }
        snakeDirection = key;

    }

    private void evaluateCurrentSituation() {
        if (snake.x < 0 || snake.x > 490 || snake.y < 30 || snake.y > 490) {
            score.setText("GAME OVER");
            executorService.shutdown();
            removeKeyListener(this);
            return;
        }

        if (snake.x != target.x || snake.y != target.y) {
            return;
        }

        scoreValue++;
        updateScore();
        putTargetInRandomPlace();
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

    private void moveSnake() {
        if (snakeDirection == VK_UP) {
            snake.y -= 10;
            return;
        }
        if (snakeDirection == VK_DOWN) {
            snake.y += 10;
            return;
        }
        if (snakeDirection == VK_LEFT) {
            snake.x -= 10;
            return;
        }
        if (snakeDirection == VK_RIGHT) {
            snake.x += 10;
            return;
        }
    }

    private Runnable gameStepInitiator = () -> {
        moveSnake();
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
