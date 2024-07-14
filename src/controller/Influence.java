package controller;

import model.Player;
import model.World;
import util.FileReaderUtil;
import util.Tools;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Influence {

    public static boolean CalChance(int difficulty, double chance) {
        return (difficulty <= chance);
    }

    public static void TryInfluence(String var, String change) {

        Player player = Player.getInstance();
        World world = World.getInstance();

        Random random = new Random();
        FileReaderUtil fileReader = new FileReaderUtil();
        int sign = switch (change) {
            case "increased" -> 1;
            case "decreased" -> -1;
            default -> throw new IllegalStateException("Unexpected value: " + change);
        };
        int competence;
        int AISuspicionChange = switch(change) {
            case "increased" -> 5;
            case "decreased" -> 10;
            default -> throw new IllegalStateException("Unexpected value: " + change);
        };

        List<util.Influence> Influence = fileReader.readInfluenceFromFile(
                "src/util/txt/influence/"
                        + var
                        + "_"
                        + change
                        + ".txt");
        int randomIndex = random.nextInt(Influence.size());
        util.Influence selectedInfluence = Influence.get(randomIndex);

        world.nextWeek();
        System.out.println(selectedInfluence.getText());

        if (var.equals("suspicion") || var.equals("science")) {
            competence = player.getIntelligence();
        } else {
            competence = player.getPower();
        }

        if (CalChance(selectedInfluence.getDifficulty(), competence)) {

            AISuspicionChange = AISuspicionChange + random.nextInt(-10, 5);

            String AISuspicionChangeInText;
            if (var.equals("suspicion")) {
                AISuspicionChangeInText = "";
            } else if (AISuspicionChange < 0) {
                AISuspicionChangeInText = " and AI suspicion decreases by " + (- AISuspicionChange);
            } else {
                AISuspicionChangeInText = " and AI suspicion increased by " + AISuspicionChange;
            }

            System.out.println("SUCCESS, "
                    + var
                    + " "
                    + change
                    + " by "
                    + selectedInfluence.getValue()
                    + AISuspicionChangeInText
            );

            switch (var) {
                case "suspicion":
                    AISuspicionChange = 0;
                    world.changeAiSuspicion(selectedInfluence.getValue());
                    player.changeIntelligence(-10);
                    break;
                case "economy":
                    world.changeEconomy(sign * selectedInfluence.getValue());
                    player.changePower(-10);
                    break;
                case "science":
                    world.changeScience(sign * selectedInfluence.getValue());
                    player.changeIntelligence(-20);
                    break;
                case "population":
                    world.changeGrowth((double) (sign * selectedInfluence.getValue()) / 100);
                    player.changePower(-20);
                    break;
                case "environment":
                    world.changeEnvironment(sign * selectedInfluence.getValue());
                    player.changePower(-25);
                    break;
            }
            world.changeAiSuspicion(AISuspicionChange);
        } else {
            AISuspicionChange = AISuspicionChange + random.nextInt(0, 5);
            System.out.println("FAILED, AI suspicion increases by " + AISuspicionChange);
            world.changeAiSuspicion(AISuspicionChange);
        }
        Tools.showState(player,world);

        System.out.println("[1] Influence again [0] Return to Home");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            selectInfluence();
        } else {
            LifePhase.startLifePhase();
        }
    }

    public static void selectInfluence() {

        Player player = Player.getInstance();
        World world = World.getInstance();

        Tools.showState(player,world);

        System.out.println("""
                What do you want to influence?
                Beware influence economy, science, population growth and environment might increase suspicion!
                Moreover, the influence takes a week and costs your power.
                [1] Increase AI suspicion [requires 10 Intelligence]
                [2] Influence Economy [requires 10 Power]
                [3] Influence Science [requires 20 Intelligence]
                [4] Influence Population Growth [requires 20 Power]
                [5] Influence Environment [requires 25 Power]
                
                [0] Return to Home
                """);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice > 0 && choice < 6) {
            String variable = switch(choice) {
                case 1 -> "suspicion";
                case 2 -> "economy";
                case 3 -> "science";
                case 4 -> "population";
                case 5 -> "environment";
                default -> null;
            };

            if (variable.equals("suspicion")) {
                TryInfluence("suspicion", "increased");
            } else {
                System.out.println("[1] Increase "
                        + variable
                        + " [2] Decrease "
                        + variable
                        + " [0] Return");
                int howInfluence = scanner.nextInt();
                if (howInfluence == 1) {
                    TryInfluence(variable, "increased");
                } else if (howInfluence == 2) {
                    TryInfluence(variable, "decreased");
                } else {
                    selectInfluence();
                }
            }
        } else {
            LifePhase.startLifePhase();
        }
    }


    public static void main(String[] args) {
        Random random = new Random();
        World world = World.getInstance();
        Player player = Player.getInstance();

        player.setIntelligence(100);
        player.setPower(50);
        world.setAiSuspicion(random.nextInt(40,60));
        world.setEconomy(random.nextInt(40,60));
        world.setEnvironment(random.nextInt(40,60));
        world.setPopulation(random.nextInt(40,60));
        world.setScience(random.nextInt(40,60));
        selectInfluence();
    }
}
