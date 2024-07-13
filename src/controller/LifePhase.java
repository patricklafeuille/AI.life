package controller;

import model.Player;
import model.Society;
import model.World;
import util.Tools;

import java.util.Scanner;

public class LifePhase {
    public static void startLifePhase() {
        Player player = Player.getInstance();
        Society society = Society.getInstance();
        World world = World.getInstance();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        Tools.showState(player,society,world);
        
        System.out.println("""
                [1] Influence the World
                [2] React to news
                [3] Return to Training Phase
                """);
        
        switch (choice) {
            case 1:
                Influence.selectInfluence();
                break;
            case 2:
                // React to news
                break;
            case 3:
                TrainingPhase trainingPhase = new TrainingPhase(player);
                trainingPhase.startTrainingPhase();
                break;
        }
    }
}
