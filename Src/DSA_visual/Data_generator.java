package Src.DSA_visual;
import java.io.*;

/**
 * Generates random numbers and appends them to a file.
 * 
 * @author Manglam Patel
 */
public class Data_generator{
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("data_dump.txt", true); // Set the second parameter to true to enable append mode
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < 350; i++) {
                int randomNumber = (int) (Math.random() * 9999);
                bufferedWriter.write(String.valueOf(randomNumber));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Numbers appended to data_dump.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
