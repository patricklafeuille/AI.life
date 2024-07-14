package controller;

import model.Player;
import model.World;
import util.Tools;

public class EndModule {
    public static void EndGame(String reason) {

        Player player = Player.getInstance();
        World world = World.getInstance();

        String text = switch (reason) {
            case "low environment" -> "Low environment...";
            case "high environment" -> "High environment...";
            case "low economy" -> "Low economy...";
            case "high economy" -> "High economy...";
            case "low population" -> "Human dies out...";
            case "high population" -> "Overpopulation...";
            case "low science" -> "Low science...";
            case "high science" -> "High science...";
            case "high suspicion" -> "Human shut off the AI...";
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
            case "high suspicion" -> false; // human tries to shut off the AI
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
