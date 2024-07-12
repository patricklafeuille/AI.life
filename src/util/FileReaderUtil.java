package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<Question> readQuestionsFromFile(String filePath) {
        List<String> lines = readLinesFromFile(filePath);
        List<Question> questions = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(";");
            int difficulty = Integer.parseInt(parts[0]);
            String questionText = parts[1];
            String answer = parts[2];
            questions.add(new Question(difficulty, questionText, answer));
        }
        return questions;
    }

    public List<Influence> readInfluenceFromFile(String filePath) {
        List<Influence> influenceList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line and trim the strings to remove any leading or trailing whitespace
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    int difficulty = Integer.parseInt(parts[0].trim());
                    String text = parts[1].trim();
                    int value = Integer.parseInt(parts[2].trim());
                    influenceList.add(new Influence(difficulty, text, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return influenceList;
    }

}
