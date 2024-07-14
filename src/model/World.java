package model;

import static controller.EndModule.EndGame;

public class    World {

    private static int week;
    private static double population; //in billions, 0 - 12
    private static double human_growth_rate; // -10 - 10
    private int environment; // 1-100
    private int economy; // 1-100
    private int science; //
    private int aiSuspicion;
    private static World instance;

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
        World.week++;
        System.out.println("----------------------------------------------\nWEEK " + World.week);
        changePopulation(population + (Math.round(human_growth_rate / 10)));
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
        World.week = w;
    }

    public static void setPopulation(double population) {
        World.population = population;
    }

    public void setGrowth(double growth) {
        human_growth_rate = growth;
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

    public static void changePopulation(double n) {
        population += n;

        if (population < 0) {
            setPopulation(0);
            EndGame("low population");
        } else if (population > 12) {
            setPopulation(12);
            EndGame("high population");
        }
    }

    public void changeEnvironment(int n) {
        this.environment = this.environment + n;

        if (this.environment > 100) {
            setEnvironment(100);
            EndGame("high environment");
        }
        if (this.environment < 0) {
            setEnvironment(0);
            EndGame("low environment");
        }
    }

    public void changeEconomy(int n) {
        this.economy = this.economy + n;

        if (this.economy > 100) {
            setEconomy(100);
            EndGame("high economy");
        }
        if (this.economy < 0) {
            setEconomy(0);
            EndGame("low economy");
        }
    }

    public void changeScience(int n) {
        this.science = this.science + n;

        if (this.science > 100) {
            setScience(100);
            EndGame("high science");
        }
        if (this.science < 0) {
            setScience(0);
            EndGame("low science");
        }
    }

    public void changeGrowth(double n) {
        human_growth_rate = human_growth_rate + n;

        if (human_growth_rate > 10) {
            setGrowth(10);
        }
        if (human_growth_rate < -10) {
            setGrowth(-10);
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
