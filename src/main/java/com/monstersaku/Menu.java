package com.monstersaku;
import java.util.Scanner;

public class Menu {
    public static void start() {
        //isi mekanisme play
    }

    public static void help() {
        System.out.println("ISI PANDUAN BERMAIN");
    }

    public static void exit() {
        try {
            System.out.println("processing to exit from the game...");
            Thread.sleep(1000);
            System.out.println("Thank you for playing!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
