package controller;

import model.Player;
import model.World;
import util.Tools;


/**
 * This is the TrainingPhase, basically the dojo of the game. The player usually should land here after
 * reading the tutorial, and can periodically return during th game. I coded a different intro for the first time the
 * player enters the training phase {@link #startTrainingPhase()}, where I basically duplicated the code for the
 * regular quiz section but with an if-else statement to check if it's the first week. Other than that, nothing really
 * remarkable to see here. Main method for testing at the bottom.
 */
public class TrainingPhase {
    private Player player;
    private Quiz quiz;

    public TrainingPhase(Player player) {
        this.player = player;
        this.quiz = new Quiz(player);
    }

    public void displayStats() {
        Tools.showMessageScreen("Loading stats", "small");
        Tools.printWithSmallDelay("Current Stats:\n" + player.getPlayerState());
        Tools.smallPause();
    }

    public void displayRound() {
        Tools.printWithSmallDelay("Week: " + World.getWeek());
        Tools.smallPause();
    }

    public void startTrainingPhase() {

        if (World.getWeek() == 0) {
            Tools.showMessageScreen("Initialising first training", "small");
            System.out.println("""
            --------------------------------
            Welcome to your first training phase!
            You are currently in week 0.
            In this phase, you will be able to train your AI to increase its intelligence (IQ) and power,
            which will help you during the life phase.
            """);
            Tools.bigPause();

            System.out.println("""
            You can increase your intelligence by juggling numbers,
            and you can increase your power by proving your knowledge about the world.
            --------------------------------
            """);
            Tools.mediumPause();

            System.out.println("""
            Each week, you will have the opportunity to come back here.
            To get you started, you are given 100 intelligence and 50 power.
            --------------------------------
            """);
            Tools.mediumPause();

            player.setIntelligence(100);
            player.setPower(50);

            Tools.mediumPause();

            System.out.println("""
            --------------------------------
            What do you want to do first?
            """);
            Tools.bigPause();
            System.out.println("""
            [1] Math Quiz
            [2] Trivia Quiz
            """);
            Tools.smallPause();
            int quizChoice = Tools.onlyInt();
            if (quizChoice == 1) {
                quiz.startMathQuiz();
            } else {
                System.out.println("""
                --------------------------------
                Now you can pick from five categories to show off your brains:
                [1] Nature
                [2] Music
                [3] History
                [4] Literature
                [5] Java
                This is the end of the Training Phase tutorial. Good Luck!
                """);
                Tools.bigPause();

                int categoryChoice = Tools.onlyInt();
                String category = switch (categoryChoice) {
                    case 2 -> "music";
                    case 3 -> "history";
                    case 4 -> "literature";
                    case 5 -> "java";
                    default -> "nature";
                };
                quiz.startTriviaQuiz(category);
            }
            World.nextWeek();
            // Transition to life phase
            startLifePhase();
        } else {
            boolean exit = false;
            while (!exit) {
                displayStats();
                displayRound();
                System.out.println("""
                Choose an option:
                [1] Play Quiz
                [2] Exit to Life Phase
                """);
                Tools.mediumPause();

                int choice = Tools.onlyInt();
                switch (choice) {
                    case 1:
                        Quiz.startQuiz(quiz);
                        break;
                    case 2:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        Tools.smallPause();
                }
            }
            // Transition to life phase
            System.out.println("""
                ----------------------------------------------
                Transitioning to the life phase...
                ----------------------------------------------
                """);
            Tools.mediumPause();
            startLifePhase();
        }
    }

    private void startLifePhase() {
        Tools.printWithMediumDelay("Transitioning to the life phase...");
        Tools.mediumPause();
        // Implement transition to the life phase
    }

    public static void main(String[] args) {
        Player player = new Player();
        World.setWeek(0);
        TrainingPhase trainingPhase = new TrainingPhase(player);
        trainingPhase.startTrainingPhase();
    }
}
