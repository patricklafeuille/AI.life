package model;

import static controller.EndModule.EndGame;

public class Society {

    private int aiFriendliness;
    private int aiSuspicion;
    private static Society instance;

    public static Society getInstance() {
        if (instance == null) {
            instance = new Society();
        }
        return instance;
    }

    public int getAiFriendliness() {
        return aiFriendliness;
    }

    public int getAiSuspicion() {
        return aiSuspicion;
    }

    public void setAiFriendliness(int aiFriendliness) {
        this.aiFriendliness = aiFriendliness;
    }

    public void setAiSuspicion(int aiSuspicion) {
        this.aiSuspicion = aiSuspicion;
    }

    public void changeAiFriendliness(int n) {
        this.aiFriendliness = this.aiFriendliness + n;

        if (this.aiFriendliness > 100) {
            setAiFriendliness(100);
        }
        if (this.aiFriendliness < 0) {
            setAiFriendliness(0);
        }
    }

    public void changeAiSuspicion(int n) {
        this.aiSuspicion = this.aiSuspicion + n;

        if (this.aiSuspicion > 100) {
            setAiSuspicion(100);
            EndGame("high suspicion");
        }
        if (this.aiSuspicion < 0) {
            setAiSuspicion(0);
        }
    }

    public void printSocietyState() {
        System.out.println(
                "| friendliness. "
                        + this.aiFriendliness
                        + " | suspicion. "
                        + this.aiSuspicion
                        + " |"
        );}
}
