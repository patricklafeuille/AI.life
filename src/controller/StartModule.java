package controller;

import model.World;
import model.Player;
import controller.TrainingPhase;
public class StartModule extends main.Main {

    public static void showIntroduction() {
        System.out.println("Here is the introduction");
    }

    public static String ChooseName() {
        System.out.println("""
                
                Choose your name""");
        util.Tools.scan();
        System.out.println("Your name is " + util.Tools.getAns());
        return util.Tools.getAns();
    }

    public static boolean ChooseObjective() {
        System.out.println("""
                
                Choose your objective
                [1] Good AI [2] Bad AI""");
        while (true) {
            util.Tools.scan();
            String answer = util.Tools.getAns();

            if (answer.equals("1")) {
                System.out.println("I chose to be a good AI.");
                return true;
            } else if (answer.equals("2")) {
                System.out.println("I chose to be a bad AI.");
                return false;
            } else {
                System.out.println("Invalid input. Please enter 1 for Good AI or 2 for Bad AI.");
            }
        }
    }

    public static boolean ChooseDataset() {
        System.out.println("""
                
                Choose your Dataset
                [1] Emotional dataset [2] Rational dataset""");
        while (true) {
            util.Tools.scan();
            String answer = util.Tools.getAns();

            if (answer.equals("1")) {
                System.out.println("I chose to be a emotional AI.");
                return true;
            } else if (answer.equals("2")) {
                System.out.println("I chose to be a rational AI.");
                return false;
            } else {
                System.out.println("Invalid input. Please enter 1 for Emotional AI or 2 for Rational AI.");
            }
        }
    }

    public static void StartWith() {
        System.out.println("""
                
                Choose your Dataset
                [1] Start with Training Phase [2] Directly to Life Phase""");
        while(true) {
            util.Tools.scan();
            String answer = util.Tools.getAns();

            if (answer.equals("1")) {

            } else if (answer.equals("2")) {
                // Start life phase
            } else {
                System.out.println("Invalid input. Please enter 1 to start training phase or 2 to start life phase.");
            }
        }

    }

}
