package model;

public class World {

    private static int week;
    private long population;
    private double growth;
    private int environment;
    private int economy;
    private int science;

    public static void nextWeek() {
        World.week++;
        System.out.println("----------------------------------------------\nWEEK " + World.week);
    }

    public static int getWeek() {
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

    public static void setWeek(int w) {
        World.week = w;
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

    public void changePopulation(int n) {
        this.population = this.population + n;
        
        if (this.population < 0) {
            setPopulation(0);
        }
    }

    public void changeEnvironment(int n) {
        this.environment = this.environment + n;
        
        if (this.environment > 100) {
            setEnvironment(100);
        }
        if (this.environment < 0) {
            setEnvironment(0);
        }
    }

    public void changeEconomy(int n) {
        this.economy = this.economy + n;

        if (this.economy > 100) {
            setEconomy(100);
        }
        if (this.economy < 0) {
            setEconomy(0);
        }
    }

    public void changeScience(int n) {
        this.science = this.science + n;

        if (this.science > 100) {
            setScience(100);
        }
        if (this.science < 0) {
            setScience(0);
        }
    }

    public void changeGrowth(double n) {
        this.growth = this.growth + n;

        if (this.growth > 10) {
            setGrowth(10);
        }
        if (this.growth < 0) {
            setGrowth(0);
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
                        + " |"
        );
    }
}
