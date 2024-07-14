package controller;

import model.World;
import util.FileReaderUtil;
import util.Tools;

import java.util.List;
import java.util.Random;

public class ReactToNews {

    private static final int CRITICAL_POP = 2; // in billions
    private static final int CRITICAL_ENV = 20;
    private static final int CRITICAL_ECO = 20;
    private static final int CRITICAL_SCI = 20;

    private static final Random random = new Random();
    private static final FileReaderUtil fileReaderUtil = new FileReaderUtil();

    public static void reactToNews() {
        World world = World.getInstance();

        boolean isCritical = isCriticalState(world);

        String selectedVariable = randomEventType(world, isCritical);

        boolean isPositiveEvent = isPositiveEvent(world, selectedVariable);

        String eventDescription = fetchEvent(selectedVariable, isPositiveEvent);

        applyEventEffects(eventDescription);

        System.out.println(eventDescription);
        World.nextWeek();
        startLifePhase();
    }

    private static boolean isCriticalState(World world) {
        return world.getPopulation() <= CRITICAL_POP ||
                world.getEnvironment() <= CRITICAL_ENV ||
                world.getEconomy() <= CRITICAL_ECO ||
                world.getScience() <= CRITICAL_SCI;
    }

    private static String randomEventType(World world, boolean isCritical) {
        if (isCritical) {
            if (world.getPopulation() <= CRITICAL_POP) return "population";
            if (world.getEnvironment() <= CRITICAL_ENV) return "environment";
            if (world.getEconomy() <= CRITICAL_ECO) return "economy";
            if (world.getScience() <= CRITICAL_SCI) return "science";
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

    private static boolean isPositiveEvent(World world, String selectedVariable) {
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
        System.out.println("Fetching event...");
        Tools.showLoadingScreen("small");
        System.out.println("Event Type: " + selectedVariable);
        String filename = (isPositiveEvent ? "positive_" : "negative_") + selectedVariable + ".txt";
        List<String> events = fileReaderUtil.readLinesFromFile("src/util/txt/news/" + filename);
        if (events.isEmpty()) {
            throw new IllegalStateException("No events found in file: " + filename);
        }
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
                    System.out.println("Population changed by " + change + " billion");
                    break;
                case "environment":
                    world.changeEnvironment(change);
                    System.out.println("Environment changed by " + change);
                    break;
                case "economy":
                    world.changeEconomy(change);
                    System.out.println("Economy changed by " + change);
                    break;
                case "science":
                    world.changeScience(change);
                    System.out.println("Science changed by " + change);
                    break;
                case "growth":
                    world.changeGrowth(change);
                    System.out.println("Growth changed by " + change);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + variable);
            }
        }
    }

    private static void startLifePhase() {
        LifePhase.startLifePhase();
    }
}
