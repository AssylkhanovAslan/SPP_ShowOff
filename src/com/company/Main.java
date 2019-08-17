package com.company;

import java.awt.event.KeyListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Welcome to the BookShare made by Assylkhanov Aslan");
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter command (quit to exit):");
            String input = keyboard.next();
            if (input == null) {
                continue;
            }
            if (input.equals("quit")) {
                exit = true;
                continue;
            }
            System.out.println("Input is = " + input);
        }
        keyboard.close();

    }
}
