package main;

import model.Player;
import model.Society;
import model.World;

public class Main {

    public static void main(String[] args) {

        // INITIALIZATION
        Player player = new Player();

        //Title at the beginning
        System.out.println("""
                AI.life
                a text-based simulator of the future
                A Programming Project by Kane Friedenhagen and Patrick Ye
                [1] Start [0] Quit""");

        while(true) {
            util.Tools.scan();
            String answer = util.Tools.getAns();

            if (answer.equals("0")) {
                System.exit(0);
            } else if (answer.equals("1")) {
                controller.StartModule.showIntroduction();
                player.setName(controller.StartModule.ChooseName());
                player.setObjective(controller.StartModule.ChooseObjective());
                player.setDataset(controller.StartModule.ChooseDataset());
                controller.StartModule.StartWith();
            } else {
                System.out.println("Invalid input. Please enter 1 to start training phase or 2 to start life phase.");
            }
        }
    }
}