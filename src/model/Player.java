package model;

public class Player {

    private String name;
    private boolean objective; // Objective of the AI (true means good)
    private boolean dataset; // Dataset of the AI (true means emotional)
    private int intelligence; // IQ between 50 and 150
    private int intelligenceIn100 = intelligence - 50;
    private int power; // Power between 0 and 100
    private static Player instance;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getIntelligenceIn100() {
        return intelligenceIn100;
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

        if (this.intelligence > 150) {
            setIntelligence(150);
        }
        if (this.intelligence < 50) {
            setIntelligence(50);
        }
    }

    public void changePower(int n) {

        this.power = this.power + n;

        if (this.power > 150) {
            setPower(150);
        }
        if (this.power < 50) {
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

    public String getPlayerState() {
        return "| Intelligence: " + this.intelligence + " | Power: " + this.power + " |";
    }


}
