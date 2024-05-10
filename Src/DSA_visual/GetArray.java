package Src.DSA_visual;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class GetArray {
    public int[] array;

    public GetArray(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            long lines = Files.lines(Paths.get(filename)).count();
            array = new int[(int)lines];
            int i = 0;
            while(scanner.hasNextInt()) {
                array[i++] = scanner.nextInt();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            array = null;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            array = null;
        }
    }
}
