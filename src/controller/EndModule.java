package controller;

import model.Player;
import model.World;
import util.Tools;

public class EndModule {
    public static void EndGame(String reason) {

        Player player = Player.getInstance();
        World world = World.getInstance();

        String text = switch (reason) {
            case "low environment" -> "Low environment: The earth became entirely uninhabitable. Gradually, humans die out. What a poor ending.";
            case "high environment" -> "High environment: All environmental crises are resolved.\n " +
                    "The earth will have a stable period of at least a thousand years.";
            case "low economy" -> "Low economy: Extreme economic crisis. \n All nations at war. ANARCHY! \n Humans die out..";
            case "high economy" -> "High economy: Due to magnificent economic up-turn, everyone has become rich and poverty is entirely solved.";
            case "low population" -> "Huh? Nobody to procreate? Humans die out...";
            case "high population" -> "OVERPOPULATION. Too much waste and carbon dioxide. \n Humans die out...";
            case "low science" -> "Low science: Humans return to barbaric state. \n Even a small pest was enough to wipe them out. \n Ooga Wooga!";
            case "high science" -> "High science: Science has came to an era where ANYTHING is possible. \n Humans can solve everything ⭐︎.";
            case "high suspicion" -> "High Suspicion: Humans have started a global rebellion and killed all AI - sadly, that includes you.";
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
            System.out.println("Aww, shucks. Seems you didn't quite make it, " + Player.getName() + "...");
        } else {
            System.out.println("GAME OVER: VICTORY");
            System.out.println("Hey, " + Player.getName() + "! That's pretty impressive!");
        }
        System.out.println("----------------------------------------------");
        System.out.println(text); // printing the reason of the game over.
        Tools.showState(player, world);
        System.exit(999); // END OF THE GAME
    }
}
