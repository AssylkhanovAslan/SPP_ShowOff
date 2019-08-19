package com.company;

import java.awt.*;
import java.util.Random;

public class Target extends Rectangle {

    public Target() {
        super(0, 30, 10, 10);
    }

    public void putTargetInRandomPlace(Snake snake) {
        int targetX = snake.getHead().x;
        int targetY = snake.getHead().y;

        boolean shoulSearchForPlace = true;
        while (shoulSearchForPlace) {
            targetX = new Random().nextInt(46) + 3;
            targetY = new Random().nextInt(46) + 3;
            targetX *= 10;
            targetY *= 10;
            x = targetX;
            y = targetY;
            if (intersects(snake.getHead())) {
                continue;
            }
            boolean intersects = false;
            for (Rectangle tailItem : snake.getTail()) {
                if (intersects(tailItem)) {
                    intersects = true;
                    break;
                }
            }
            shoulSearchForPlace = intersects;
        }
        x = targetX;
        y = targetY;
    }

}
