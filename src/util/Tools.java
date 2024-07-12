package util;
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
        printWithDelay(text, 1100);
    }

    public static void printWithSmallDelay(String text) {
        printWithDelay(text, 500);
    }

    public static void printWithMediumDelay(String text) {
        printWithDelay(text, 750);
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
        pause(1500);
    }

    public static void mediumPause() {
        pause(3000);
    }

    public static void bigPause() {
        pause(4000);
    }

    public static void theatricPause() {
        pause(4500);
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
}


