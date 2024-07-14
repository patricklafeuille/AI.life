package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is probably the most complicated of them all, and the one where we needed the most help.
 * It reads text files and returns the content as a list of strings or objects.
 * To do that, it uses a BufferedReader and FileReader. I don't know too much about how they work
 * (I assume you do), but apparently BufferedReader is good for reading text from a file (FileReader does the basics,
 * but BufferedReader chunks it up and then stores is temporarily, which makes it faster to read and reduces
 * the number of times the program has to access the file).
 * Try - catch is used because handling external files is more prone to errors (file doesn't exist or isn't formatted
 * properly).
 *
 */

public class FileReaderUtil {

    public List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>(); // initialises empty array list of strings
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { // creates an instance of BufferedReader
            String line;
            while ((line = br.readLine()) != null) { // reads the file line by line and then adds them to our list
                lines.add(line);
            }
        } catch (IOException e) {  // catches input-output exceptions
            e.printStackTrace();
        }
        return lines;
    }

    public List<Question> readQuestionsFromFile(String filePath) {
        List<String> lines = readLinesFromFile(filePath);
        List<Question> questions = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(";");
            /* Here we split the line into different parts, separated by a semicolon.
            For a Question instance, we need three parts: difficulty, questionText, and answer.
            A line in the file looks like this:
            difficulty;questionText;answer
            1;What is the last name of the first President of the United States?;Washington

            So the first index of the array parts is our difficulty, etc.
            * */
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
