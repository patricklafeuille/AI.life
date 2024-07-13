package controller;

import model.Player;

public class EndModule {
    public static void EndGame(String reason, Player player) {
        String text = switch (reason) {
            case "low environment" -> "Low environment...";
            case "high environment" -> "High environment...";
            case "low economy" -> "Low economy...";
            case "high economy" -> "High economy...";
            case "low population" -> "Human dies out...";
            case "low science" -> "Low science...";
            case "high science" -> "High science...";
            case "high suspicion" -> "Human shut off the AI...";
            default -> "Game ended";
        };

        String GoodEnd = switch (reason) {
            case "low environment" -> false;
            case "high environment" -> true;
            case "low economy" -> false;
            case "high economy" -> true;
            case "low population" -> false;
            case "low science" -> false;
            case "high science" -> true;
            case "high suspicion" -> false;
            default -> "Game ended";
        };

        System.out.println("----------------------------------------------");
        if (player.isObjective() == GoodEnd) {
            System.out.println("GAME OVER: VICTORY");
        } else {
            System.out.println("GAME OVER: FAILED");
        }
        System.out.println("----------------------------------------------");
        System.out.println(text);
        System.exit(999);
    }
}
