package Src.DSA_visual;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NewGuiSortTest {
    private static int[] readArrayFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            long lines = Files.lines(Paths.get(filename)).count();
            int[] array = new int[(int)lines];
            int i = 0;
            while (scanner.hasNextInt()) {
                array[i++] = scanner.nextInt();
            }
            scanner.close();
            return array;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        int[] array = readArrayFromFile("data_dump.txt");
        if (array != null) {
            System.out.println("Array was fetched, launching display...");
            display(array);
        } else {
            System.out.println("Failed to read array from file.");
        }
    }

    public static void display(int[] array) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Array Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TestPane(array));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class TestPane extends JPanel {
    private final int[] array;
    private int current = -1;
    private int compare = -1;

    public TestPane(int[] array) {
        this.array = array;
        setBackground(Color.BLACK);
        sortArray();
    }

    private void sortArray() {
        new Thread(() -> {
            boolean swapped;
            int count = 0;
            for (int i = 0; i < array.length - 1; i++) {
                swapped = false;
                for (int j = 0; j < array.length - 1 - i; j++) {
                    current = j;
                    compare = j + 1;
                    if (array[j] > array[compare]) {
                        int temp = array[j];
                        array[j] = array[compare];
                        array[compare] = temp;
                        swapped = true;
                    }
                    count++;
                    if (count % 10 == 0) {  // Only repaint every 10 comparisons
                        repaint();
                        try {
                            Thread.sleep(1); // Keep short delay for visual effect
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (!swapped) {
                    break; // Stop if the array is sorted
                }
            }
            current = -1;
            compare = -1;
            repaint(); // Final repaint to ensure last state is shown
        }).start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(730, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int max = Arrays.stream(array).max().getAsInt();
        int baseX = 10;
        for (int i = 0; i < array.length; i++) {
            int lineHeight = (int) ((array[i] / (double) max) * 500);
            if (i == current || i == compare) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.WHITE);
            }
            g2d.drawLine(baseX, 550, baseX, 550 - lineHeight);
            baseX += 1;
        }
        g2d.dispose();
    }
}
