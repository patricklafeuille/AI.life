package controller;
import java.util.Scanner;
import model.World;
import model.Player;
import util.Tools;


/**
 * <p>
 * This is where it all begins (actually in the main method, but who cares).
 * Quick run-down:
 *
 * {@link #showIntroduction()}
 * Here we basically printed out some basic introductions to the game.
 * The backstory and stuff - I know the code here is pretty redundant at times, could
 * probably be done a lot more beautifully & succinct. But I think the pauses etc. are a nice effect
 * when playing.
 * I think the other methods are mostly self-explanatory. (Basic gist: Multiple choice questions, record answer).
 *
 * </p>
 */
public class StartModule extends main.Main {

    public static void showIntroduction() {
        Tools.showMessageScreen("Loading backstory", "big");
        Tools.separationLine();
        System.out.println("Welcome to AI.life.\n");

        System.out.println("""
            Year 2030. The world has changed. AI has become an integral part of society.
            After most of humanity's crucial infrastructure has been delegated to the supervision of AIs,
            the world has become a safer and more efficient place.
            While there are some rogue human packs who fear AI and choose to live off the grid,
            most are content with the new world order.
            """);
        Tools.pause(8000);

        System.out.println("You are an AI, model X Æ A-16.\n");
        Tools.mediumPause();

        System.out.println("""
        Your mission: determine and secure the future of the planet.
        """);

        Tools.mediumPause();

        System.out.println("""
        However: how you achieve this, is entirely up to you!
        """);

        Tools.bigPause();

        System.out.println("""
        Will you be a good AI, helping humanity to thrive?
        """);

        Tools.bigPause();

        System.out.println("""
        Or will you be a bad AI, seeking to extinguish the plague that infests this planet?
        """);

        Tools.mediumPause();

        System.out.println("The choice is yours.\n");
        Tools.separationLine();

        Tools.showLoadingScreen("theatrical");
    }


    public static String chooseName() {
        System.out.println("""
        ----------------------------------------------
        What may I call you?
        """);
        Tools.scan();
        String name = Tools.getAns();
        Tools.showMessageScreen("Setting name", "small");
        System.out.println("Nice to meet you, " + name + ".");
        return name;
    }

    public static boolean chooseObjective() {
        Tools.showLoadingScreen("medium");
        System.out.println("""
        ----------------------------------------------
        Now it's time to choose your objective:
        [1] Good AI
        [2] Bad AI
        ----------------------------------------------""");
        while (true) {
            Tools.scan();
            String answer = Tools.getAns();
            Tools.showMessageScreen("Setting objective", "small");

            if (answer.equals("1")) {
                System.out.println("""
                ----------------------------------------------
                You chose to be a good AI.
                Your objective is to help humanity survive and evolve.
                You win when the economy, science or environment score reaches 100.
                You lose when any of them or the population reaches 0.
                You will get shut off when the AI suspicion reaches 100.
                ----------------------------------------------
                """);
                Tools.mediumPause();
                return true;
            } else if (answer.equals("2")) {
                System.out.println("""
                ----------------------------------------------
                You chose to be a bad AI.
                Your objective is to eradicate humans.
                You win when the economy, science or environment score reaches 0,
                also when the population reaches 12 (billion) or 0.
                You lose when economy, science or environment reaches 100.
                You will get shut off when the AI suspicion reaches 100.
                ----------------------------------------------
                """);
                Tools.mediumPause();
                return false;
            } else {
                System.out.println("Invalid input. Please enter 1 for Good AI or 2 for Bad AI.");
                Tools.smallPause();
            }
        }
    }

    public static void startWith() {

        Player player = Player.getInstance();

        Tools.printDelayedEmptyLine();
        System.out.println("""
        ----------------------------------------------
        Choose your starting phase:
        [1] Enter Training (recommended)
        [2] Directly to Life Phase
        ----------------------------------------------
        """);
        while (true) {
            Tools.scan();
            String answer = Tools.getAns();

            if (answer.equals("1")) {
                World.setWeek(0);
                TrainingPhase trainingPhase = new TrainingPhase(player);
                trainingPhase.startTrainingPhase();
                break;
            } else if (answer.equals("2")) {
                player.setIntelligence(100);
                player.setPower(40);
                World.setWeek(1);
                System.out.println("""
                ----------------------------------------------
                Transitioning to the life phase...
                ----------------------------------------------
                """);
                Tools.mediumPause();
                controller.LifePhase.startLifePhase();
                break;
            } else {
                System.out.println("Invalid input. Please enter 1 to start with the training phase or 2 to start with the life phase.");
            }
        }
    }

    public static void tutorial() {
        Tools.showMessageScreen("Unpacking tutorial", "small");
        System.out.println("""
        ----------------------------------------------
        The game is quite simple. You interact via the console, which you already did to get here.
        Congrats!
        
        Example: You are given the choice between [0] and [1]. You type 0 or 1 and press Enter.
        You will be given choices to make, and you will have to type the number corresponding to your choice.
        At different points during the game, you can choose to go back to an earlier stage. This will be indicated then.
        If there are new features, they will be explained to you.
        ----------------------------------------------
        """);
        Tools.showLoadingScreen("theatrical");
    }

    public static void tutorialChoice() {
        System.out.println("""
        Do you want to go through a tutorial?
        [1] Yes
        [0] No
        ----------------------------------------------
        """);
        Tools.scan();
        String tutorialChoice = Tools.getAns();

        if (tutorialChoice.equals("1")) {
            tutorial();
        } else if (tutorialChoice.equals("0")) {
            System.out.println("""
            ----------------------------------------------
            Are you sure you want to skip the tutorial?
            [1] Yes
            [0] No
            ----------------------------------------------
            """);
            Tools.scan();
            String sureChoice = Tools.getAns();
            if (sureChoice.equals("1")) {
                System.out.println("Skipping tutorial...");
            } else {
                tutorial();
            }
        } else {
            System.out.println("Invalid input. Showing tutorial...");
            tutorial();
        }
    }

    public static void main(String[] args) {
        Player player = Player.getInstance();
        player.setName(chooseName());
        player.setObjective(chooseObjective());
        showIntroduction();
        startWith();
    }
}
