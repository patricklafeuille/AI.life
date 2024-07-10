package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import util.FileReaderUtil;
import util.Question;

public class Quiz {

    private int score;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    private int difficultyLevel;

    public Quiz() {
        this.score = 0;
        this.totalQuestions = 0;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.difficultyLevel = 0;
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
            if (incorrectAnswers > totalQuestions / 2) {
                System.out.println("Too many incorrect. You have failed the test");
                return;
            }
        }

        score = (int) ((double) correctAnswers / totalQuestions * 100);
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score is: " + score + "%");
    }
    public void startTriviaQuiz(String category) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        FileReaderUtil fileReader = new FileReaderUtil();
        List<Question> questions = fileReader.readQuestionsFromFile("src/util/txt/" + category + ".txt");

        if (questions.isEmpty()) {
            System.out.println("No questions available for this category.");
            return;
        }

        List<Question> selectedQuestions = new ArrayList<>();
        List<Question> askedQuestions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            final int difficulty = i;
            List<Question> difficultyQuestions = questions.stream()
                    .filter(q -> q.getDifficulty() == difficulty && !askedQuestions.contains(q))
                    .collect(Collectors.toList());
            for (int j = 0; j < 5; j++) {
                Question selectedQuestion = difficultyQuestions.get(random.nextInt(difficultyQuestions.size()));
                selectedQuestions.add(selectedQuestion);
                askedQuestions.add(selectedQuestion);
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
            if (incorrectAnswers > totalQuestions / 2) {
                System.out.println("Too many incorrect. You have failed the test.");
                return;
            }
        }

        score = (int) ((double) correctAnswers / totalQuestions * 100);
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score is: " + score + "%");
    }


    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        System.out.println("""
        Welcome to the Quiz section!
        What type of Quiz do you want to do?
        [1] Math Quiz [2] Trivia Quiz
        """);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                quiz.startMathQuiz();
                break;
            case 2:
                System.out.println("""
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
        System.out.println("Final score: " + quiz.getScore() + "%");
    }
}
