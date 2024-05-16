package Src.DSA_visual;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


import Src.DSA_visual.sorts.*;

import java.util.Arrays;
import java.util.Random;

public class NewGuiSortTest {

    
    public static void main(String[] args) {
       GetArray Obj = new GetArray("data_dump.txt");
        int[] array = Obj.array;

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

class TestPane extends JPanel implements SortVisualizerCallback {
    private final int[] array;
    private int current = -1;
    private int compare = -1;

    public TestPane(int[] array) {
        this.array = array;
        setBackground(Color.BLACK);
        new Thread(() -> {
            // BubbleSort bSort = new BubbleSort(this.array, null, this, 1);
            // bSort.startSort();
            // QuickSort qSort = new QuickSort(this.array, null, this, 5);
            // qSort.startSort();
            InsertionSort iSort = new InsertionSort(this.array, null, this, 10);
            iSort.startSort();
        }).start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(730, 580);
    }

    @Override
    public void update(int[] array, int current, int compare) {
        this.current = current;
        this.compare = compare;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int max = Arrays.stream(array).max().getAsInt();
        int baseX = 10;


        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("This is sort", 10, 20);


        for (int i = 0; i < array.length; i++) {
            int lineHeight = (int) ((array[i] / (double) max) * 500);
            float ratio = array[i] / (float) max; // Ratio of the current value to the maximum value
            int red = (int) (ratio * 255);
            int green = (int) ((1 - ratio) * 255);
            Color lineColor = new Color(red, green, 0);
            if (i == current || i == compare) {
                g2d.setColor(new Color(0, 255, 255));
            } else {
                // g2d.setColor(Color.WHITE);
                g2d.setColor(lineColor);
            }
            g2d.drawLine(baseX, 550, baseX, 550 - lineHeight);
            baseX += 2;
        }
        g2d.dispose();
    }
}


