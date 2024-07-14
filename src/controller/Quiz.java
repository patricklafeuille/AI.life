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

    private int score;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    private int difficultyLevel;
    private static List<Integer> scores = new ArrayList<>();
    private static int bonus = 0;
    private Player player = Player.getInstance();

    public Quiz(Player player) {
        this.score = 0;
        this.totalQuestions = 0;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.difficultyLevel = 0;
        this.player = player;
    }

    public int getScore() {
        return this.score;
    }

    public int getTotalQuestions() {
        return this.totalQuestions;
    }

    public int getCorrectAnswers() {
        return this.correctAnswers;
    }

    public int getIncorrectAnswers() {
        return this.incorrectAnswers;
    }

    public int getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public static int getBonus() {
        return bonus;
    }

    public void startMathQuiz() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] operations = {"+", "-", "*", "%"};

        // Ask the user to choose the number of questions
        System.out.println("""
                Choose the number of questions: 
                [1] 12 [2] 20 [3] 40
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
                break;
            case 3:
                numQuestions = 40;
                difficultyIncrement = 10;
                break;
            default:
                System.out.println("Invalid choice. Defaulting to 12 questions.");
                numQuestions = 12;
                difficultyIncrement = 3;
                break;
        }

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
                case "%":
                    // Ensure no division by zero and valid division
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
            if (incorrectAnswers > totalQuestions / 2 && totalQuestions > 3) {
                System.out.println("Too many incorrect. You have failed the test");
                askToPlayAgain();
                return;
            }
        }

        updateScoreAndBonus("intelligence");
        askToPlayAgain();
    }

    public void startTriviaQuiz(String category) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        FileReaderUtil fileReader = new FileReaderUtil();
        List<Question> questions = fileReader.readQuestionsFromFile("src/util/txt/quiz/" + category + ".txt");

        if (questions.isEmpty()) {
            System.out.println("No questions available for this category.");
            return;
        }

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
            String answer = scanner.nextLine().toLowerCase().trim();

            totalQuestions++;
            if (answer.equals(question.getAnswer().toLowerCase().trim())) {
                correctAnswers++;
                System.out.println("That's right!");
            } else {
                incorrectAnswers++;
                System.out.println("Wrong. The correct answer is " + question.getAnswer() + ".");
            }

            // Check if the user fails the test in this category
            if (incorrectAnswers > totalQuestions / 2 && totalQuestions > 3) {
                System.out.println("Too many incorrect. You have failed the test.");
                askToPlayAgain();
                return;
            }
        }

        updateScoreAndBonus("power");
        askToPlayAgain();
    }

    private void updateScoreAndBonus(String attribute) {
        score = (int) ((double) correctAnswers / totalQuestions * 100);
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score is: " + score + "%");

        if (score < 75) {
            System.out.println("You scored below 75%. You will receive a -2 penalty, and no bonus from the score.");
            bonus -= 2;  // Penalty for scoring below 75%
            scores.add(0);
        } else if (score > 95) {
            System.out.println("You scored near perfectly. You will receive a +2 extra bonus.");
            bonus += 2;
            scores.add(score);// Bonus for scoring 100%
        } else scores.add(score);

        calculateBonus(attribute);
    }

    private void calculateBonus(String attribute) {
        int totalScore = 0;
        if (scores.isEmpty()) {
            return;
        }

        for (int score : scores) {
            totalScore += score;
        }
        int totalBonus = 0;
        int attributeBonus = 0;

        if (!(scores.size() == 1 && scores.get(0) == 0)) {
            double averageScore = (double) totalScore / scores.size();
            System.out.println("Average score: " + averageScore);
            attributeBonus = (int) Math.round(averageScore / 13);
            totalBonus = bonus + attributeBonus;
        } else {
            totalBonus = bonus;
        }

        if (attribute.equals("intelligence")) {
            player.changeIntelligence(totalBonus);
            System.out.println("Intelligence increased by " + totalBonus);
        } else if (attribute.equals("power")) {
            player.changePower(totalBonus);
            System.out.println("Power increased by " + totalBonus);
        }

        bonus += attributeBonus;
    }

    private void askToPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play again? [0] Exit [1] Play Again");
        int choice = scanner.nextInt();
        if (choice == 1) {
            main(new String[]{});
        } else {
            System.out.println("Your final bonus is: " + bonus);
            System.out.println("Player's final state:");
            player.printPlayerState();
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
