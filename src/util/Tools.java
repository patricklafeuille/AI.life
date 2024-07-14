package util;
import model.World;
import model.Player;

import java.util.Scanner;

public class Tools {

    static String ans = "";

    public static void scan() {
        Scanner scanner = new Scanner(System.in);
        ans = scanner.nextLine();
    }

    public static String getAns() {
        return ans;
    }

    public static void printWithDelay(String text, long delayInMillis) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            System.out.println(line);
            try {
                Thread.sleep(delayInMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Printing interrupted.");
            }
        }
    }


    public static void printWithBigDelay(String text) {
        printWithDelay(text, 110);
    }

    public static void printWithSmallDelay(String text) {
        printWithDelay(text, 50);
    }

    public static void printWithMediumDelay(String text) {
        printWithDelay(text, 75);
    }

    public static void pause(long delayInMillis) {
        try {
            Thread.sleep(delayInMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Pause interrupted.");
        }
    }

    public static void smallPause() {
        pause(150);
    }

    public static void mediumPause() {
        pause(300);
    }

    public static void bigPause() {
        pause(400);
    }

    public static void theatricPause() {
        pause(450);
    }

    public static void printDelayedEmptyLine(){
        System.out.println();
        smallPause();
    }


    public static void showLoadingScreen(String text) {
        System.out.println("Loading...");
        switch (text) {
            case "small" -> smallPause();
            case "medium" -> mediumPause();
            case "big" -> bigPause();
            default -> theatricPause();
        }
    }
    
    public static void showState(Player player, World world) {
        System.out.println("--------------------------------");
        player.printPlayerState();
        world.printWorldState();
        System.out.println("--------------------------------");
    }
    
    public static void separationLine() {
        System.out.println("--------------------------------");
    }
}


