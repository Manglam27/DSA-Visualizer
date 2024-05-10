package Src.DSA_visual;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Src.DSA_visual.sorts.BubbleSort;
import Src.DSA_visual.sorts.SortVisualizerCallback;

import java.util.Arrays;

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
            BubbleSort bSort = new BubbleSort(this.array, null, this);
            bSort.startSort();
        }).start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(730, 600);
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


