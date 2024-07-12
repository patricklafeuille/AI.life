package util;

public class Influence {
    private int difficulty;
    private String text;
    private int value;

    public Influence(int difficulty, String text, int value) {
        this.difficulty = difficulty;
        this.text = text;
        this.value = value;
    }

    public String getText() {return text;}

    public int getDifficulty() {return difficulty;}

    public int getValue() {return value;}
}
