package model;

import static controller.EndModule.EndGame;

public class    World {

    private static int week;
    private long population; //in billions, 0 - 12
    private double human_growth_rate; // -10 - 10
    private int environment; // 1-100
    private int economy; // 1-100
    private int science; //
    private int aiSuspicion;
    private static World instance;

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public static void nextWeek() {
        World.week++;
        System.out.println("----------------------------------------------\nWEEK " + World.week);
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

    public long getPopulation() {
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

    public void setPopulation(long population) {
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

    public void changePopulation(int n) {
        this.population = this.population + n;

        if (this.population < 0) {
            setPopulation(0);
            EndGame("low population");
        } else if (this.population > 12) {
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
        this.human_growth_rate = this.human_growth_rate + n;

        if (this.human_growth_rate > 10) {
            setGrowth(10);
        }
        if (this.human_growth_rate < -10) {
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
                        + this.population
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
