package controller;

import model.Player;
import model.World;
import util.FileReaderUtil;
import util.Tools;

import java.util.List;
import java.util.Random;

/**
 * <p> Welcome to React to News!
 *  * Basic overview (don't want to comment on everything):
 *  {@link ReadTheNews}
 *  * First created some finals for the thresholds that are supposed to ensure that certain types of events
 *  * happen if the world state is in a dire place somehow. Example: If population is very low, there should
 *  * be events related to world population.
 *
 *  {@link #isCriticalState(World)}
 *  * Here, I first checked whether there are any critical variables and based on that an event type is picked
 *  in {@link #randomEventType(World, boolean)}.
 *  * (IntelliJ told me I can put the switch within the return, p. smart).
 *
 *  {@link #isPositiveEvent(World, String)}
 *  * Then, based on the current state, it is determined whether the event should be positive or negative.
 *
 *  Now, the {@link #fetchEvent(String, boolean)} is relatively similar to what I did in the Quiz class,
 *  it reads a random event from the (through the above methods specified) text file.
 *
 *  {@link #applyEventEffects(String)}
 *  This is the crux of this class. Basically, it first separates the text via the ; separator, as before.
 *  But then I wanted to be able to change multiple variables at once, so we have a for-loop that starts from
 *  index 1 (since index 0 is the event description) and then splits the effect into variable
 *  and change using the : separator.
 *  Based on the variable, the change is applied to the world state. Tada! :)
 *
 *  The main method is just for testing purposes.
 *  </p>
 *
 */

public class ReadTheNews {

    private static final int CRITICAL_POP = 2; // in billions
    private static final int CRITICAL_ENV = 20;
    private static final int CRITICAL_ECO = 20;
    private static final int CRITICAL_SCI = 20;

    private static final Random random = new Random();
    private static final FileReaderUtil fileReaderUtil = new FileReaderUtil();

    public static void reactToNews() { // basic method to call from other classes

        System.out.println("""
                        ----------------------------------------------
                        Welcome to the News Stand!
                        Let's see what we have in store for you today.
                        """);
        World world = World.getInstance();

        boolean isCritical = isCriticalState(world);

        String selectedVariable = randomEventType(world, isCritical);

        boolean isPositiveEvent = isPositiveEvent(world, selectedVariable);

        String eventDescription = fetchEvent(selectedVariable, isPositiveEvent);

        Tools.showMessageScreen("Scanning headlines", "small");
        Tools.printDelayedEmptyLine();

        System.out.println("----------------------------------------------");
        System.out.println("BREAKING NEWS: ");
        System.out.println(separateEventDescription(eventDescription));
        System.out.println("----------------------------------------------");

        Tools.pause(3000);
        Tools.printDelayedEmptyLine();

        applyEventEffects(eventDescription);

        Tools.pause(2000);
        Tools.printDelayedEmptyLine();

        World.nextWeek();
        startLifePhase();
    }

    private static boolean isCriticalState(World world) { // has a variable reached a critical point?
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
        return switch (randomPick) {
            case 0 -> "population";
            case 1 -> "environment";
            case 2 -> "economy";
            case 3 -> "science";
            default -> throw new IllegalStateException("Unexpected value: " + randomPick);
        };
    }

    private static boolean isPositiveEvent(World world, String selectedVariable) {
        double probability = switch (selectedVariable) {
            case "population" -> world.getPopulationProbability();
            case "environment" -> world.getEnvironmentProbability();
            case "economy" -> world.getEconomyProbability();
            case "science" -> world.getScienceProbability();
            default -> throw new IllegalStateException("Unexpected value: " + selectedVariable);
        };

        return random.nextDouble() < probability;
    }

    private static String fetchEvent(String selectedVariable, boolean isPositiveEvent) {
        System.out.println("Fetching event...");
        Tools.pause(3000);
        Tools.printDelayedEmptyLine();
        System.out.println("Event Type: " + selectedVariable);
        String filename = (isPositiveEvent ? "positive_" : "negative_") + selectedVariable + ".txt";
        List<String> events = fileReaderUtil.readLinesFromFile("src/util/txt/news/" + filename);
        if (events.isEmpty()) {
            throw new IllegalStateException("No events found in file: " + filename);
        }
        return events.get(random.nextInt(events.size()));
    }

    private static String separateEventDescription(String eventDescription) {
        String[] parts = eventDescription.split(";");
        return parts[0];
    }

    private static void applyEventEffects(String eventDescription) {
        String[] parts = eventDescription.split(";");
        for (int i = 1; i < parts.length; i++) {
            String[] effect = parts[i].split(":");
            String variable = effect[0];
            int change = Integer.parseInt(effect[1]);

            World world = World.getInstance();

            System.out.println(variable + " changed by: " + change);

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
        LifePhase.startLifePhase();
    }


    // for testing purposes
    public static void main(String[] args) {
        Player.getInstance().setIntelligence(100);
        Player.getInstance().setPower(50);
        World.getInstance().setAiSuspicion(random.nextInt(40,60));
        World.getInstance().setEconomy(random.nextInt(40,60));
        World.getInstance().setAiSuspicion(random.nextInt(40,60));
        World.getInstance().setPopulation(random.nextInt(5,8));
        World.getInstance().setScience(random.nextInt(40,60));
        World.getInstance().setEnvironment(random.nextInt(40,60));

        reactToNews();
    }
}
