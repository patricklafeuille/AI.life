package controller;

import model.Player;
import model.World;
import util.Tools;

import java.util.Scanner;

public class LifePhase {
    public static void startLifePhase() {
        Player player = Player.getInstance();
        World world = World.getInstance();
        
        Tools.showState(player,world);
        
        System.out.println("""
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

    public static void main(String[] args) {
        startLifePhase();
    }
}
