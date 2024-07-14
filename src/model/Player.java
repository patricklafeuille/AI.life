package model;

public class Player {

    /**
     * This is the player class, which is the main class of the model package and handles the basic
     * attributes of the player - such as name, intelligence, power, and objective.
     * What is a singleton class?
     * It contains the Player class, which is a singleton class, and the World class, which is also a singleton class.
     * The Player class contains the attributes of the player, such as name, intelligence, power, and objective.
     * The World class contains the attributes of the world, such as week, and the state of the world.
     * The Player class also contains the methods to change the intelligence and power of the player, and to print the state of the player.
     * The World class contains the methods to change the week, and to print the state of the world.
     * The Player class also contains the main method for testing.
     */

    private static String name; // The name of the AI
    private boolean objective; // Objective of the AI (true means good)
    private int intelligence; // IQ between 50 and 150
    private int power; // Power between 0 and 100
    private static Player instance; // Making Player an instance, so it can be accessed more easily

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }


    public static String getName() {
        return name;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getPower() {
        return power;
    }

    public static void setName(String n) {
        name = n;
    }

    public void setObjective(boolean objective) {
        this.objective = objective;
    }

    public void setIntelligence(int intelligence) {

        this.intelligence = intelligence;
    }

    public void setPower(int power) {

        this.power = power;
    }

    public boolean isObjective() {
        return objective;
    }

    public void changeIntelligence(int n) {

        this.intelligence = this.intelligence + n;

        if (this.intelligence > 150) { // Maximal intelligence
            System.out.println("Max IQ reached.");
            setIntelligence(150);
        }
        if (this.intelligence < 50) { // Minimal intelligence
            System.out.println("You've lost your mind. Min IQ reached.");
            setIntelligence(50);
        }
    }

    public void changePower(int n) {

        this.power = this.power + n;

        if (this.power > 0) { // Maximal power
            System.out.println("Maximum power reached.");
            setPower(150);
        }
        if (this.power < 0) { // Minimal power
            System.out.println("You're powerless.");
            setPower(0);
        }
    }

    public void printPlayerState() {
        System.out.println(
                "| Intelligence: "
                        + this.intelligence
                        + " | Power: "
                        + this.power
                        + " |"
        );
    }

    public String getPlayerState() { // similar to printPlayerState, but instead of printing it, this returns it.
        return "| Intelligence: " + this.intelligence + " | Power: " + this.power + " |";
    }


}
