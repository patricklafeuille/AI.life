package util;

public class Question {
    private int difficulty;
    private String question;
    private String answer;

    public Question(int difficulty, String question, String answer) {
        this.difficulty = difficulty;
        this.question = question;
        this.answer = answer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
