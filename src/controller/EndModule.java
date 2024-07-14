package controller;

import model.Player;
import model.World;
import util.Tools;

public class EndModule {
    public static void EndGame(String reason) {

        Player player = Player.getInstance();
        World world = World.getInstance();

        String text = switch (reason) {
            case "low environment" -> "Low environment: the earth became uninhabitable. Gradually, humans die out.";
            case "high environment" -> "High environment: all the environmental crisis are solved.\n The earth became extremely stable for at least a thousand years.";
            case "low economy" -> "Low economy: extreme economic crisis started. \n Every nation goes to war and fights for resources.";
            case "high economy" -> "High economy: Due to the magnificient economy, everyone became rich and poverty is entirely solved.";
            case "low population" -> "Human dies out...";
            case "high population" -> "Overpopulation...";
            case "low science" -> "Low science: science returns to barbaric state. \n It can't satisfy the basic need of human.";
            case "high science" -> "High science: science has came to an era where anything is possible. \n Humans can solve everything.";
            case "high suspicion" -> "Human started a rebellion and shut down all the AI programmes including you.";
            default -> "Game ended";
        };

        boolean GoodEnd = switch (reason) {
            case "low environment" -> false; // the earth became uninhabitable
            case "high environment" -> true; // the climate change is entire solved. The environment is very stable
            case "low economy" -> false; // the entire economic system collapses
            case "high economy" -> true; // human has enough resources and everyone is rich
            case "low population" -> false; // human dies out
            case "high population" -> false; // overpopulation...
            case "low science" -> false; // human becomes barbaric
            case "high science" -> true; // science develops so well that we don't need to worry anything anymore
            case "high suspicion" -> false; // human tries to shut down the AI
            default -> false;
        };
        // printing whether the player wins or not
        System.out.println("----------------------------------------------");
        if (reason.equals("high suspicion") || player.isObjective() != GoodEnd) {
            System.out.println("GAME OVER: FAILED");
        } else {
            System.out.println("GAME OVER: VICTORY");
        }
        System.out.println("----------------------------------------------");
        System.out.println(text); // printing the reason of the game over.
        Tools.showState(player, world);
        System.exit(999); // END OF THE GAME
    }
}
