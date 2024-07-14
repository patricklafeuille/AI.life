package main;

import controller.*;
import model.Player;
import model.World;
import java.util.Random;
import util.Tools;

// START THE ENTIRE GAME HERE!

/**
 * Hello, kind stranger (or tutor)!
 * This is the Main class, so you might start reading here (and you should start running here).
 *
 * Patrick and I built a "simple" (turned out not to be all that simple in the end) text-turn-based game called AI.life.
 * Which you probably already know, since you're here.
 * The game is basically a simulation of the future,
 * where the player (an AI) has to influence the world in order to achieve a set objective.
 * Before you go on, make sure to check out our README.md file (WIP), which is accessible on our GitHub repository:
 * https://github.com/patricklafeuille/AI.life
 *
 * As a side-note: This is the first "real" project Patrick and I have built, so there's probably a lot of room
 * for improvement (just setting up Git took way too long LOL), even though we didn't write anything fancy.
 * And you might encounter the odd bug here or there. But we're proud of what this turned out to be
 * with the limited time & knowledge we had.
 *
 * Hope you have fun exploring and playing!
 *
 * PPS: Our annotations probably aren't super helpful - same goes for the commit messages. Not enough practice yet.
 *
 * PPPS: In the util package, only the following are annotated: Tools & FileReaderUtil
 *
 */
public class Main {
    
    public static void main(String[] args) {

        // initialising the variables.
        Random random = new Random();
        Player player = Player.getInstance();
        World world = World.getInstance();

        // setting player state to default
        player.setIntelligence(100);
        player.setPower(50);

        // setting world state randomly
        world.setAiSuspicion(random.nextInt(40,60));
        world.setEconomy(random.nextInt(40,60));
        world.setEnvironment(random.nextInt(40,60));
        world.setPopulation(random.nextInt(5,8));
        world.setScience(random.nextInt(40,60));

        // displaying title at the beginning
        System.out.println("""
                ----------------------------------------------
                AI.life v0.1
                a text-based simulator of the future
                A Programming Project by Kane Friedenhagen and Patrick Ye
                
                Do you want to enter the game?
                [1] Enter [0] Quit
                ----------------------------------------------""");

        while(true) { // detecting if anything is entered
            int answer = Tools.onlyInt();

            if (answer == 0) {
                System.exit(0);
            } else if (answer == 1) {
                StartModule.showIntroduction(); // showing the introduction
                player.setName(StartModule.chooseName()); // choose a name
                player.setObjective(StartModule.chooseObjective()); // choose the objective
                StartModule.tutorialChoice(); // choose to read tutorial
                StartModule.startWith(); // start the game with either training phase or life phase
            } else {
                System.out.println("Invalid input. Please enter 1 to enter the game or 0 to quit");
            }
        }
    }
}
