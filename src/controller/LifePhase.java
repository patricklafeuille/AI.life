package controller;

import model.Player;
import model.World;
import util.Tools;

import java.util.Scanner;

public class LifePhase {
    public static void startLifePhase() { // starting life phase homepage
        Player player = Player.getInstance();
        World world = World.getInstance();
        
        Tools.showState(player,world);

        // giving player the choice what to do
        System.out.println("""
                WHAT DO YOU WANT TO DO?
                [1] Influence the World
                [2] React to news
                [3] Return to Training Phase
                """);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Influence.selectInfluence();
                break;
            case 2:
                ReactToNews.reactToNews();
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
