package util;
import java.util.Scanner;

public class Tools {

    static String ans = "";

    public static void scan() {
        Scanner scanner = new Scanner(System.in);
        ans = scanner.nextLine();
    }

    public static String getAns() {
        return ans;
    }

}
