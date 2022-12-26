package com.company;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MainScreen game = new MainScreen();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.add(game);
        frame.pack();
        frame.setBounds(0, 0, 500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        game.init();
    }
}
