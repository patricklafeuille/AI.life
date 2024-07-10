package model;

public class World {

    private int week;
    private long population;
    private double growth;
    private int environment;
    private int economy;
    private int science;

    public int getWeek() {
        return week;
    }

    public long getPopulation() {
        return population;
    }

    public double getGrowth() {
        return growth;
    }

    public int getEnvironment() {
        return environment;
    }

    public int getEconomy() {
        return economy;
    }

    public int getScience() {
        return science;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public void setEnvironment(int environment) {
        this.environment = environment;
    }

    public void setEconomy(int economy) {
        this.economy = economy;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public void nextWeek() {
        this.week++;
    }

    public void changePopulation(int n) {
        this.population = this.population + n;
    }

    public void changeEnvironment(int n) {
        this.environment = this.environment + n;
    }

    public void changeEconomy(int n) {
        this.economy = this.economy + n;
    }

    public void changeScience(int n) {
        this.science = this.science + n;
    }

    public void growPopulation() {
        population = Math.round(population + (population * growth));
    }

    public void printState() {
        System.out.println(
                "| pop. "
                        + this.population
                        + " | env. "
                        + this.environment
                        + " | eco. "
                        + this.economy
                        + " | sci. "
                        + this.science
                        + " |"
        );
    }
}
