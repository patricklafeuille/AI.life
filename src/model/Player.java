package model;

public class Player {

    private String name; // The name of the AI
    private boolean objective; // Objective of the AI (true means good)
    private int intelligence; // IQ between 50 and 150
    private int power; // Power between 0 and 100
    private static Player instance; // Making Player an instance so it can be accessed more easily

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getPower() {
        return power;
    }

    public void setName(String name) {
        this.name = name;
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
            setIntelligence(150);
        }
        if (this.intelligence < 50) { // Minimal intelligence
            setIntelligence(50);
        }
    }

    public void changePower(int n) {

        this.power = this.power + n;

        if (this.power > 150) { // Maximal power
            setPower(150);
        }
        if (this.power < 50) { // Minimal power
            setPower(50);
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
