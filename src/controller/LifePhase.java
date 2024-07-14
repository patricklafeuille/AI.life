package controller;

import model.Player;
import model.World;
import util.Tools;


/**
 * The LifePhase class is the home menu of this game.
 * The players can go to the three main parts of this game from here:
 * 1) Influence
 * 2) ReactToNews
 * 3) Training Phase where the Quizzes are.
 */

public class LifePhase {
    public static void startLifePhase() { // starting life phase homepage
        Player player = Player.getInstance();
        World world = World.getInstance();
        
        Tools.showState(player,world);

        // giving player the choice what to do
        System.out.println("""
                WHAT DO YOU WANT TO DO?
                [1] Influence the world
                [2] Read the news
                [3] Return to training
                """);

        int choice = Tools.onlyInt();
        switch (choice) {
            case 1:
                Tools.printDelayedEmptyLine();
                Influence.selectInfluence();
                break;
            case 2:
                Tools.printDelayedEmptyLine();
                ReadTheNews.reactToNews();
                break;
            case 3:
                TrainingPhase trainingPhase = new TrainingPhase(player);
                trainingPhase.startTrainingPhase();
                break;
        }
    }


    // MAIN FOR TESTING
    public static void main(String[] args) {
        startLifePhase();
    }
}
