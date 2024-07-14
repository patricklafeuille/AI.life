package controller;

import model.Player;
import model.World;
import util.FileReaderUtil;
import util.Tools;

import java.util.List;
import java.util.Random;

/**
 * The Influence class is a part of the life phase, 
 * where the player can influence the world state
 * This is one of the most important part of this game, 
 * since influencing the world is the only way you can win or lose.
 */

public class Influence {

    public static boolean CalChance(int difficulty, double chance) { // returns if the task is successful
        Random random = new Random();
        return ((difficulty + random.nextInt(0, 20)) <= chance); // the plus random number increases the difficulty
    }

    public static void TryInfluence(String var, String change) { // after selected the variable to influence, here is the main process of influencing

        Player player = Player.getInstance();
        World world = World.getInstance();

        Random random = new Random();
        FileReaderUtil fileReader = new FileReaderUtil();
        
        int sign = switch (change) { // determines whether the change will be positive or negative.
            case "increased" -> 1;
            case "decreased" -> -1;
            default -> throw new IllegalStateException("Unexpected value: " + change);
        };
        
        int AISuspicionChange = switch(change) { // determines the base value for AI suspicion
            case "increased" -> 5;
            case "decreased" -> 5;
            default -> throw new IllegalStateException("Unexpected value: " + change);
        };

        List<util.Influence> Influence = fileReader.readInfluenceFromFile( // randomly select one task from the text file.
                "src/util/txt/influence/"
                        + var
                        + "_"
                        + change
                        + ".txt");
        int randomIndex = random.nextInt(Influence.size());
        util.Influence selectedInfluence = Influence.get(randomIndex);

        World.nextWeek(); // Next week
        
        System.out.println(selectedInfluence.getText()); // show the text of the influence task

        int competence; // whether intelligence or power is required
        if (var.equals("suspicion") || var.equals("science")) {
            competence = player.getIntelligence();
        } else {
            competence = player.getPower();
        }

        if (CalChance(selectedInfluence.getDifficulty(), competence)) { // Case SUCCESS

            AISuspicionChange += random.nextInt(10, 20); // Increase suspicion randomly

            // print out the changes after the task
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

            // making changes after the task is successful
            switch (var) {
                case "suspicion":
                    AISuspicionChange = 0;
                    world.changeAiSuspicion(-selectedInfluence.getValue());
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
        } else { // Case FAILED
            AISuspicionChange = AISuspicionChange + random.nextInt(0, 5);
            System.out.println("FAILED, AI suspicion increases by " + AISuspicionChange);
            world.changeAiSuspicion(AISuspicionChange);
        }
        Tools.showState(player,world);

        System.out.println("[1] Influence again [0] Return to Home");

        int choice = Tools.onlyInt();

        if (choice == 1) {
            selectInfluence();
        } else {
            LifePhase.startLifePhase();
        }
    }

    public static void selectInfluence() { // The player can select which variable to influence

        Player player = Player.getInstance();
        World world = World.getInstance();

        Tools.showState(player,world);

        System.out.println("""
                What do you want to influence?
                Beware influence economy, science, population growth and environment might increase suspicion!
                Moreover, the influence takes a week and costs your power.
                [1] Decrease AI suspicion [requires 10 Intelligence]
                [2] Influence Economy [requires 10 Power]
                [3] Influence Science [requires 20 Intelligence]
                [4] Influence Population Growth [requires 20 Power]
                [5] Influence Environment [requires 25 Power]
                
                [0] Return to Home
                """);

        int choice = Tools.onlyInt();

        if (choice > 0 && choice < 6) {
            String variable = switch(choice) {
                case 1 -> "suspicion";
                case 2 -> "economy";
                case 3 -> "science";
                case 4 -> "population";
                case 5 -> "environment";
                default -> null;
            };
            
            boolean reachRequirement = switch(choice) { // which requirement the task need to reach and if it reaches it
                case 1 -> (player.getIntelligence() >= 10);
                case 2 -> (player.getPower() >= 10);
                case 3 -> (player.getIntelligence() >= 20);
                case 4 -> (player.getPower() >= 20);
                case 5 -> (player.getPower() >= 25);
                default -> false;
            };
            
            if (reachRequirement) {
                if (variable.equals("suspicion")) {
                    TryInfluence("suspicion", "decreased");
                } else {
                    System.out.println("[1] Increase "
                            + variable
                            + " [2] Decrease "
                            + variable
                            + " [0] Return");
                    int howInfluence = Tools.onlyInt();
                    if (howInfluence == 1) {
                        TryInfluence(variable, "increased");
                    } else if (howInfluence == 2) {
                        TryInfluence(variable, "decreased");
                    } else {
                        selectInfluence();
                    }
                }
            } else {
                System.out.println("You do not reach the requirement.");
                LifePhase.startLifePhase();
            }
            
        } else {
            LifePhase.startLifePhase();
        }
    }


    // MAIN FOR TESTING INFLUENCE
    public static void main(String[] args) {
        Random random = new Random();
        World world = World.getInstance();
        Player player = Player.getInstance();

        player.setIntelligence(100);
        player.setPower(50);
        world.setAiSuspicion(random.nextInt(40,60));
        world.setEconomy(random.nextInt(40,60));
        world.setEnvironment(random.nextInt(40,60));
        world.setPopulation(random.nextInt(5,8));
        world.setScience(random.nextInt(40,60));
        selectInfluence();
    }
}
