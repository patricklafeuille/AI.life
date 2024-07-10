package model;

public class Society {

    private int aiFriendliness;
    private int aiSuspicion;

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
    }

    public void changeAiSuspicion(int n) {
        this.aiSuspicion = this.aiSuspicion + n;
    }
}
