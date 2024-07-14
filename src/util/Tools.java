package util;
import model.World;
import model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Thank god for this class. Simply moving out some basic methods actually makes quite the difference
 * in terms of readability and maintainability. Who would have thought?
 * The methods themselves aren't too complicated.
 * {@link #scan()} is just a simple scanner method.
 * {@link #pause(long)} is another simple method to help create some reading time for the player.
 * {@link #showMessageScreen(String, String)} shows a message for a set duration (duh).
 * {@link #showState(Player, World)} prints the state of the player and the world.
 *
 * Other than that, there are some more specific methods, based on the ones above, to make things quicker.
 *
 */
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
        pause(750);
    }

    public static void mediumPause() {
        pause(2500);
    }

    public static void bigPause() {
        pause(3000);
    }

    public static void theatricalPause() {
        pause(3500);
    }

    public static void printDelayedEmptyLine(){
        System.out.println();
        smallPause();
    }

    public static void showMessageScreen(String message, String duration) {
        System.out.println(message + "...");
        switch (duration) {
            case "small" -> smallPause();
            case "medium" -> mediumPause();
            case "big" -> bigPause();
            case "theatrical" -> theatricalPause();
            default -> pause(Integer.parseInt(duration));
        }
    }

    public static void showLoadingScreen(String duration) {
        showMessageScreen("Loading", duration);
    }
    
    public static void showState(Player player, World world) {
        System.out.println("--------------------------------");
        player.printPlayerState();
        world.printWorldState();
        System.out.println("--------------------------------");
    }

    public static int onlyInt(){
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("""
                ----------------------------------------------
                ERROR! That's not even a number.
                Try again:
                """);
                scanner.next(); // clear the invalid input
            }
        }

        return input;
    }

    public static void separationLine() {
        System.out.println("--------------------------------");
    }
}


