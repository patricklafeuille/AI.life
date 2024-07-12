package main;

import model.Player;
import model.Society;
import model.World;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        // INITIALIZATION
        Player player = new Player();
        Society society = new Society();
        World world = new World();

        player.setIntelligence(random.nextInt(50,150));
        player.setPower(random.nextInt(0, 100));
        society.setAiFriendliness(random.nextInt(0,100));
        society.setAiSuspicion(random.nextInt(0, 100));
        world.setEconomy(random.nextInt(0, 100));
        world.setEnvironment(random.nextInt(0, 100));
        world.setPopulation(random.nextInt(0, 100));
        world.setScience(random.nextInt(0, 100));

        //Title at the beginning
        System.out.println("""
                ----------------------------------------------
                AI.life
                a text-based simulator of the future
                A Programming Project by Kane Friedenhagen and Patrick Ye
                Do you want to enter the game?
                [1] Enter [0] Quit
                ----------------------------------------------""");

        while(true) {
            util.Tools.scan();
            String answer = util.Tools.getAns();

            if (answer.equals("0")) {
                System.exit(0);
            } else if (answer.equals("1")) {
                controller.StartModule.showIntroduction();
                player.setName(controller.StartModule.chooseName());
                player.setObjective(controller.StartModule.chooseObjective());
                controller.StartModule.tutorialChoice();
                player.setDataset(controller.StartModule.chooseDataset());
                controller.StartModule.startWith(player);
            } else {
                System.out.println("Invalid input. Please enter 1 to enter the game or 0 to quit");
            }
        }
    }
}
