package main;

import model.Player;
import model.World;
import java.util.Random;

// START THE ENTIRE GAME HERE!

public class Main {
    
    public static void main(String[] args) {

        // initialising the varibles.
        Random random = new Random();
        Player player = Player.getInstance();
        World world = World.getInstance();

        // setting player state to default
        player.setIntelligence(100);
        player.setPower(50);

        // setting world state randomly
        world.setAiSuspicion(random.nextInt(40,60));
        world.setEconomy(random.nextInt(40,60));
        world.setEnvironment(random.nextInt(40,60));
        world.setPopulation(random.nextInt(5,8));
        world.setScience(random.nextInt(40,60));

        // displaying title at the beginning
        System.out.println("""
                ----------------------------------------------
                AI.life v0.1
                a text-based simulator of the future
                A Programming Project by Kane Friedenhagen and Patrick Ye
                
                Do you want to enter the game?
                [1] Enter [0] Quit
                (please only enter integers!)
                ----------------------------------------------""");

        while(true) { // detecting if anything is entered
            util.Tools.scan();
            String answer = util.Tools.getAns();

            if (answer.equals("0")) {
                System.exit(0);
            } else if (answer.equals("1")) {
                controller.StartModule.showIntroduction(); // showing the introduction
                player.setName(controller.StartModule.chooseName()); // choose a name
                player.setObjective(controller.StartModule.chooseObjective()); // choose the objective
                controller.StartModule.tutorialChoice(); // choose to read tutorial
                controller.StartModule.startWith(); // start the game with either training phase or life phase
            } else {
                System.out.println("Invalid input. Please enter 1 to enter the game or 0 to quit");
            }
        }
    }
}
