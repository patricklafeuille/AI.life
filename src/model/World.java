package model;

import static controller.EndModule.EndGame;

public class World {

    private static int week; // week since the beginning of the game
    private double population; //in billions, 0 to 12
    private double human_growth_rate; // -10 to 10
    private int environment; // 1 to 100
    private int economy; // 1 to 100
    private int science; // 1 to 100
    private int aiSuspicion; // How much the society finds the AI suspicious, and how much they want to shut the AI down: 1 to 100
    private static World instance; // making World an instance so it can be accessed more easily.

    // Method to calculate probabilities
    public double getPopulationProbability() {
        return population / 12.0;
    }

    public double getEnvironmentProbability() {
        return environment / 100.0;
    }

    public double getEconomyProbability() {
        return economy / 100.0;
    }

    public double getScienceProbability() {
        return science / 100.0;
    }

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public static void nextWeek() {
        week++;
        System.out.println("----------------------------------------------\nWEEK " + week);
        getInstance().changePopulation(getInstance().getGrowth() / 10);
    }

    public static int getWeek() {
        return week;
    }

    public int getAiSuspicion() {
        return aiSuspicion;
    }

    public void setAiSuspicion(int aiSuspicion) {
        this.aiSuspicion = aiSuspicion;
    }

    public double getPopulation() {
        return population;
    }

    public double getGrowth() {
        return human_growth_rate;
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

    public static void setWeek(int w) {
        week = w;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public void setGrowth(double growth) {
        this.human_growth_rate = growth;
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

    public void changePopulation(double n) {
        this.population += n;

        if (this.population < 0) { // minimal population
            setPopulation(0);
            EndGame("low population"); // triggering EndModule: GAME OVER
        } else if (this.population > 12) { // maximal population
            setPopulation(12);
            EndGame("high population"); // triggering EndModule: GAME OVER
        }
    }

    public void changeEnvironment(int n) {
        this.environment = this.environment + n;

        if (this.environment > 100) { // maximal environment
            setEnvironment(100);
            EndGame("high environment"); // triggering EndModule: GAME OVER
        }
        if (this.environment < 0) { // minimal environment
            setEnvironment(0);
            EndGame("low environment"); // triggering EndModule: GAME OVER
        }
    }

    public void changeEconomy(int n) {
        this.economy = this.economy + n;

        if (this.economy > 100) { // maximal economy
            setEconomy(100);
            EndGame("high economy"); // triggering EndModule: GAME OVER
        }
        if (this.economy < 0) { // minimal economy
            setEconomy(0);
            EndGame("low economy"); // triggering EndModule: GAME OVER
        }
    }

    public void changeScience(int n) {
        this.science = this.science + n;

        if (this.science > 100) { // maximal science
            setScience(100);
            EndGame("high science"); // triggering EndModule: GAME OVER
        }
        if (this.science < 0) { // minimal science
            setScience(0);
            EndGame("low science"); // triggering EndModule: GAME OVER
        }
    }

    public void changeGrowth(double n) {
        this.human_growth_rate = this.human_growth_rate + n;

        if (this.human_growth_rate > 10) { // maximal human growth rate
            setGrowth(10);
        }
        if (this.human_growth_rate < -10) { // minimal human growth rate
            setGrowth(-10);
        }
    }

    public void changeAiSuspicion(int n) {
        this.aiSuspicion = this.aiSuspicion + n;

        if (this.aiSuspicion > 100) { // maximal AI Suspicion
            setAiSuspicion(100);
            EndGame("high suspicion"); // triggering EndModule: GAME OVER
        }
        if (this.aiSuspicion < 0) { // minimal AI Suspicion
            setAiSuspicion(0);
        }
    }

    public void printWorldState() {
        System.out.println(
                "| pop. "
                        + population
                        + " | env. "
                        + this.environment
                        + " | eco. "
                        + this.economy
                        + " | sci. "
                        + this.science
                        + " | sus. "
                        + this.aiSuspicion
                        + " |"
        );
    }
}
