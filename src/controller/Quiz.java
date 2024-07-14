package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import util.FileReaderUtil;
import util.Question;
import model.Player;

public class Quiz {

    // Here, we initialise the variables that we'll use in the quiz.
    private int score; // tracks the players score
    private int totalQuestions; // tracks the total questions asked
    private int correctAnswers; // tracks the amount of correct answers
    private int incorrectAnswers; // tracks the amount of incorrect answers
    private int difficultyLevel; // some questions will be harder than others, this tracks the difficulty level
    private static int bonus = 0; // bonus points that will decide how the player's attributes change
    private Player player = Player.getInstance();

    public Quiz(Player player) { // Constructor for the Quiz class
        this.score = 0;
        this.totalQuestions = 0;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.difficultyLevel = 0;
        this.player = player;
    }

    // No getters & setters needed for this class, bc we don't need to access them from outside

    public void startMathQuiz() { // Method to start the math quiz
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] operations = {"+", "-", "*", "%"}; // array of the different operations (difficulties)

        // player can pick how many questions they want to answer
        System.out.println("""
                Choose the number of questions:
                [1] 12 [2] 20 (+5 Bonus) [3] 40 (+ 10 Bonus)
                """);
        int choice = scanner.nextInt();
        int numQuestions = 0;
        int difficultyIncrement = 0;

        switch (choice) {
            case 1:
                numQuestions = 12;
                difficultyIncrement = 3;
                break;
            case 2:
                numQuestions = 20;
                difficultyIncrement = 5;
                bonus += 5;
                break;
            case 3:
                numQuestions = 40;
                difficultyIncrement = 10;
                bonus += 10;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to 12 questions.");
                numQuestions = 12;
                difficultyIncrement = 3;
                break;
        }

        // main part of the math quiz, here we generate the questions and check if the player answered correctly
        for (int i = 0; i < numQuestions; i++) {
            if (i % difficultyIncrement == 0 && i != 0) {
                difficultyLevel++;
            }

            int a = random.nextInt(20) + 1;
            int b = random.nextInt(20) + 1;

            String operation = operations[difficultyLevel % 4];
            int correctAnswer = 0;
            switch (operation) {
                case "+":
                    correctAnswer = a + b;
                    break;
                case "-":
                    correctAnswer = a - b;
                    break;
                case "*":
                    correctAnswer = a * b;
                    break;
                case "%": // picked modulo since it can be answered in int
                    b = random.nextInt(9) + 1;
                    a *= random.nextInt(9);
                    correctAnswer = a % b;
                    break;
            }

            System.out.println("Q " + (i + 1) + ": What is " + a + " " + operation + " " + b + "?");
            int answer = scanner.nextInt();

            totalQuestions++;
            if (answer == correctAnswer) {
                correctAnswers++;
                System.out.println("That's right!");
            } else {
                incorrectAnswers++;
                System.out.println("Wrong. The correct answer is " + correctAnswer + ".");
            }

            // Check if the user fails the test in this category
            failedTest(incorrectAnswers, totalQuestions);
        }

        updateScoreAndBonus("intelligence");
        askToPlayAgain();
    }

    // method to check if player failed the test (pretty arbitrary logic, but it's fine for this purpose)
    private void failedTest(int incorrectAnswers, int totalQuestions) {
        if (incorrectAnswers > totalQuestions / 2 && totalQuestions > 3){
            System.out.println("Too many incorrect. You have failed the test.");
            askToPlayAgain();
        }
    }


    public void startTriviaQuiz(String category) { // Method to start the trivia quiz
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        FileReaderUtil fileReader = new FileReaderUtil();
        List<Question> questions = fileReader.readQuestionsFromFile("src/util/txt/quiz/" + category + ".txt");

        if (questions.isEmpty()) {
            System.out.println("No questions available for this category.");
            return;
        }

        // example for ChatGPT use:

        List<Question> selectedQuestions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            final int difficulty = i;
            List<Question> difficultyQuestions = questions.stream()
                    .filter(q -> q.getDifficulty() == difficulty)
                    .collect(Collectors.toList());
            for (int j = 0; j < 5; j++) {
                if (difficultyQuestions.isEmpty()) {
                    break; // Break if there are no more questions available for this difficulty
                }
                Question selectedQuestion = difficultyQuestions.remove(random.nextInt(difficultyQuestions.size()));
                selectedQuestions.add(selectedQuestion);
            }
        }

        for (int i = 0; i < selectedQuestions.size(); i++) {
            Question question = selectedQuestions.get(i);
            System.out.println("D" + question.getDifficulty() + " : " + "Q " + (i + 1) + ": " + question.getQuestion());
            String answer = scanner.nextLine().toLowerCase().trim(); // this makes sure that capitalisation & white spaces are ignored

            totalQuestions++;
            if (answer.equals(question.getAnswer().toLowerCase().trim())) {
                correctAnswers++;
                System.out.println("That's right!");
            } else {
                incorrectAnswers++;
                System.out.println("Wrong. The correct answer is " + question.getAnswer() + ".");
            }

            failedTest(incorrectAnswers, totalQuestions);
        }

        updateScoreAndBonus("power");
        askToPlayAgain();
    }

    // method to update the score and award bonus points
    private void updateScoreAndBonus(String attribute) {
        score = (int) ((double) correctAnswers / totalQuestions * 100);
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score is: " + score + "%");

        if (score < 70) {
            System.out.println("You scored below 70%. You will receive a -2 penalty, and no bonus from the score.");
            bonus -= 2;  // Penalty for scoring below 70
        } else if (score > 95) {
            System.out.println("You scored near perfectly. You will receive a +2 extra bonus.");
            bonus += score + 2;
        } else bonus += score;

        calculateBonus(attribute);
    }

    private void calculateBonus(String attribute) {
        int totalBonus = bonus;

        if (attribute.equals("intelligence")) {
            player.changeIntelligence(totalBonus);
            System.out.println("Intelligence changed by " + totalBonus);
        } else if (attribute.equals("power")) {
            player.changePower(totalBonus);
            System.out.println("Power changed by " + totalBonus);
        }
    }

    private void askToPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play again? [0] Exit [1] Play Again");
        int choice = scanner.nextInt();
        if (choice == 1) {
            main(new String[]{});
        } else {
            LifePhase.startLifePhase();
        }
    }

    public static void startQuiz(Quiz quiz, Player player) {
        System.out.println("""
        Welcome to the Quiz section!
        What type of Quiz do you want to do?
        You can either increase your intelligence, or your power.
        [1] Math Quiz [2] Power Quiz
        """);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                quiz.startMathQuiz();
                break;
            case 2:
                System.out.println("""
                Welcome to Trivia. Time to test your knowledge!
                Most answers are one-word, so often only last names of people are asked.
                Choose a category: 
                [1] Nature [2] Music [3] History [4] Literature [5] Java
                """);
                int categoryChoice = scanner.nextInt();
                String category = switch (categoryChoice) {
                    case 1 -> "nature";
                    case 2 -> "music";
                    case 3 -> "history";
                    case 4 -> "literature";
                    case 5 -> "java";
                    default -> "nature";
                };
                quiz.startTriviaQuiz(category);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Math Quiz.");
                quiz.startMathQuiz();
                break;
        }
    }
    public static void main(String[] args) {
        Player player = new Player();
        Quiz quiz = new Quiz(player);
        startQuiz(quiz, player);
    }
}
