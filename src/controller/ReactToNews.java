package controller;

import model.World;
import util.FileReaderUtil;
import util.Tools;

import java.util.List;
import java.util.Random;

public class ReactToNews {

    private static final int CRITICAL_THRESHOLD_POPULATION = 2; // in billions
    private static final int CRITICAL_THRESHOLD_ENVIRONMENT = 20;
    private static final int CRITICAL_THRESHOLD_ECONOMY = 20;
    private static final int CRITICAL_THRESHOLD_SCIENCE = 20;

    private static final Random random = new Random();
    private static final FileReaderUtil fileReaderUtil = new FileReaderUtil();

    public static void reactToNews() {
        World world = World.getInstance();

        // Check if any variables are critical
        boolean isCritical = isCriticalState(world);

        // Select a variable to base the event on
        String selectedVariable = selectVariable(world, isCritical);

        // Determine if the event is positive or negative
        boolean isPositiveEvent = determineEventPositivity(world, selectedVariable);

        // Fetch the event description and its effects
        String eventDescription = fetchEvent(selectedVariable, isPositiveEvent);

        // Apply the event effects
        applyEventEffects(eventDescription);

        // Display the event
        System.out.println(eventDescription);

        // Increase the week counter
        World.nextWeek();

        // Return to the life phase (this could be a method in another class)
        startLifePhase();
    }

    private static boolean isCriticalState(World world) {
        return world.getPopulation() <= CRITICAL_THRESHOLD_POPULATION ||
                world.getEnvironment() <= CRITICAL_THRESHOLD_ENVIRONMENT ||
                world.getEconomy() <= CRITICAL_THRESHOLD_ECONOMY ||
                world.getScience() <= CRITICAL_THRESHOLD_SCIENCE;
    }

    private static String selectVariable(World world, boolean isCritical) {
        if (isCritical) {
            if (world.getPopulation() <= CRITICAL_THRESHOLD_POPULATION) return "population";
            if (world.getEnvironment() <= CRITICAL_THRESHOLD_ENVIRONMENT) return "environment";
            if (world.getEconomy() <= CRITICAL_THRESHOLD_ECONOMY) return "economy";
            if (world.getScience() <= CRITICAL_THRESHOLD_SCIENCE) return "science";
        }

        int randomPick = random.nextInt(4);
        switch (randomPick) {
            case 0:
                return "population";
            case 1:
                return "environment";
            case 2:
                return "economy";
            case 3:
                return "science";
            default:
                throw new IllegalStateException("Unexpected value: " + randomPick);
        }
    }

    private static boolean determineEventPositivity(World world, String selectedVariable) {
        double probability;
        switch (selectedVariable) {
            case "population":
                probability = world.getPopulationProbability();
                break;
            case "environment":
                probability = world.getEnvironmentProbability();
                break;
            case "economy":
                probability = world.getEconomyProbability();
                break;
            case "science":
                probability = world.getScienceProbability();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedVariable);
        }

        return random.nextDouble() < probability;
    }

    private static String fetchEvent(String selectedVariable, boolean isPositiveEvent) {
        String filename = (isPositiveEvent ? "positive_" : "negative_") + selectedVariable + ".txt";
        List<String> events = fileReaderUtil.readLinesFromFile("src/util/txt/" + filename);
        return events.get(random.nextInt(events.size()));
    }

    private static void applyEventEffects(String eventDescription) {
        String[] parts = eventDescription.split(";");
        for (int i = 1; i < parts.length; i++) {
            String[] effect = parts[i].split(":");
            String variable = effect[0];
            int change = Integer.parseInt(effect[1]);

            World world = World.getInstance();

            switch (variable) {
                case "population":
                    world.changePopulation(change);
                    break;
                case "environment":
                    world.changeEnvironment(change);
                    break;
                case "economy":
                    world.changeEconomy(change);
                    break;
                case "science":
                    world.changeScience(change);
                    break;
                case "growth":
                    world.changeGrowth(change);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + variable);
            }
        }
    }

    private static void startLifePhase() {
        // Placeholder for starting the life phase
        // This should call the method to initiate the life phase in the appropriate class
    }
}
