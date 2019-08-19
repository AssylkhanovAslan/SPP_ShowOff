package com.company;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class Snake {

    private Rectangle head;
    private List<Rectangle> tail;
    private int direction = VK_RIGHT;
    private int size = 0;

    public Snake() {
        head = new Rectangle(10, 30, 10, 10);
        tail = new LinkedList<>();
        tail.add(new Rectangle(0, 30, 10, 10));
    }

    public void moveSnake() {
        for (int i = 0; i < tail.size(); i++) {
            Rectangle tailElem = tail.get(i);
            if (i == tail.size() - 1) {
                tailElem.x = head.x;
                tailElem.y = head.y;
            } else {
                Rectangle tailElemInFront = tail.get(i + 1);
                tailElem.x = tailElemInFront.x;
                tailElem.y = tailElemInFront.y;
            }
        }

        if (direction == VK_UP) {
            head.y -= 10;
            teleportHeadIfNeeded();
            return;
        }
        if (direction == VK_DOWN) {
            head.y += 10;
            teleportHeadIfNeeded();
            return;
        }
        if (direction == VK_LEFT) {
            head.x -= 10;
            teleportHeadIfNeeded();
            return;
        }
        if (direction == VK_RIGHT) {
            head.x += 10;
            teleportHeadIfNeeded();
            return;
        }
    }

    public Boolean eatsTarget(Rectangle target) {
        if (!head.intersects(target)) {
            return false;
        }
        tail.add(new Rectangle(head.x, head.y, 10, 10));
        return true;
    }

    public Rectangle getHead() {
        return head;
    }

    public List<Rectangle> getTail() {
        return tail;
    }

    public void setDirection(int directionKey) {
        if (directionKey == VK_UP && direction == VK_DOWN) {
            return;
        }
        if (directionKey == VK_DOWN && direction == VK_UP) {
            return;
        }
        if (directionKey == VK_LEFT && direction == VK_RIGHT) {
            return;
        }
        if (directionKey == VK_RIGHT && direction == VK_LEFT) {
            return;
        }
        direction = directionKey;
    }

    public void teleportHeadIfNeeded() {
        if (head.x < 0) {
            head.x = 490;
            return;
        }
        if (head.x > 490) {
            head.x = 0;
            return;
        }
        if (head.y < 30) {
            head.y = 490;
            return;
        }
        if (head.y > 490) {
            head.y = 30;
            return;
        }

    }
}
