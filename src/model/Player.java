package model;

public class Player {

    private String name;
    private boolean objective; // Objective of the AI (true means good)
    private boolean dataset;
    private int intelligence;
    private int power;

    public String getName() {
        return name;
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

    public void setDataset(boolean dataset) {
        this.dataset = dataset;
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

    public boolean isDataset() {
        return dataset;
    }

    public void changeIntelligence(int n) {
        this.intelligence = this.intelligence + n;
    }

    public void changePower(int n) {
        this.power = this.power + n;
    }

    public void printState() {
        System.out.println(
                "| Intelligence: "
                        + this.intelligence
                        + " | Power: "
                        + this.power
                        + " |"
        );
    }
}
