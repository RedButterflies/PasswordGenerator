import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class PasswordGenerator {

    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~".toCharArray();
    private static final int PASSWORD_LENGTH = 4;

    public static void main(String[] args) {
        Date startTime = new Date();
        generator();
        Date endTime = new Date();
        long howMuchTime = endTime.getTime() - startTime.getTime();
        System.out.println("Czas generowania hasel: " + howMuchTime + " ms");

        File file = new File("passwords.txt");
        long fileSize = file.length();
        System.out.println("Rozmiar pliku z hasłami: " + fileSize + " bajtów");
    }


    private static void generator() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("passwords.txt"))) {
            StringBuilder password = new StringBuilder();
            recursivePasswordGenerator(password, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recursivePasswordGenerator(StringBuilder password, BufferedWriter writer) throws IOException {
        if (password.length() == PASSWORD_LENGTH) {
            writer.write(password.toString());
            writer.newLine();
        } else {
            for (char c : ALPHABET) {
                password.append(c);
                recursivePasswordGenerator(password, writer);
                password.deleteCharAt(password.length() - 1);
            }
        }
    }
}
