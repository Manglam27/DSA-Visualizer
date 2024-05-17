package Src.DSA_visual;

import java.awt.*;
import javax.swing.*;
import Src.DSA_visual.sorts.*;
import java.util.Arrays;

public class NewGuiSort {

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
            frame.setLayout(new CardLayout());

            // Create main menu panel
            JPanel mainMenu = new JPanel();
            mainMenu.setLayout(new GridLayout(5, 1, 10, 10));
            mainMenu.setBackground(Color.DARK_GRAY);
            mainMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel titleLabel = new JLabel("Choose a Sorting Algorithm for the visulazation", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(Color.WHITE);
            mainMenu.add(titleLabel);

            JButton bubbleSortButton = createStyledButton("Bubble Sort");
            JButton quickSortButton = createStyledButton("Quick Sort");
            JButton insertionSortButton = createStyledButton("Insertion Sort");
            JButton selectionSortButton = createStyledButton("Selection Sort");


            mainMenu.add(bubbleSortButton);
            mainMenu.add(quickSortButton);
            mainMenu.add(insertionSortButton);
            mainMenu.add(selectionSortButton);

            frame.add(mainMenu, "Main Menu");

            // Create sorting display panels
            SortPanel bubbleSortPane = new SortPanel(array.clone(), "Bubble Sort", frame, array.clone());
            SortPanel quickSortPane = new SortPanel(array.clone(), "Quick Sort", frame, array.clone());
            SortPanel insertionSortPane = new SortPanel(array.clone(), "Insertion Sort", frame, array.clone());
            SortPanel selectionSortPane = new SortPanel(array.clone(), "Selection Sort", frame, array.clone());


            frame.add(bubbleSortPane, "Bubble Sort");
            frame.add(quickSortPane, "Quick Sort");
            frame.add(insertionSortPane, "Insertion Sort");
            frame.add(selectionSortPane, "Selection Sort");


            bubbleSortButton.addActionListener(e -> {
                bubbleSortPane.reset();
                CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                cl.show(frame.getContentPane(), "Bubble Sort");
                bubbleSortPane.startSorting(() -> {
                    BubbleSort bSort = new BubbleSort(bubbleSortPane.array, null, bubbleSortPane, 10);
                    bSort.startSort();
                });
            });

            quickSortButton.addActionListener(e -> {
                quickSortPane.reset();
                CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                cl.show(frame.getContentPane(), "Quick Sort");
                quickSortPane.startSorting(() -> {
                    QuickSort qSort = new QuickSort(quickSortPane.array, null, quickSortPane, 10);
                    qSort.startSort();
                });
            });

            insertionSortButton.addActionListener(e -> {
                insertionSortPane.reset();
                CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                cl.show(frame.getContentPane(), "Insertion Sort");
                insertionSortPane.startSorting(() -> {
                    InsertionSort iSort = new InsertionSort(insertionSortPane.array, null, insertionSortPane, 10);
                    iSort.startSort();
                });
            });
            selectionSortButton.addActionListener(e -> {
                insertionSortPane.reset();
                CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
                cl.show(frame.getContentPane(), "Selection Sort");
                insertionSortPane.startSorting(() -> {
                    SelectionSort sSort = new SelectionSort(selectionSortPane.array, null, selectionSortPane, 10);
                    sSort.startSort();
                });
            });

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(60, 63, 65));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }
}

class SortPanel extends JPanel implements SortVisualizerCallback {
    public final int[] array;
    private final int[] originalArray;
    private int current = -1;
    private int compare = -1;
    private final String sortName;
    // private final JFrame frame;
    private volatile boolean cancelled = false;
    private Thread sortingThread;

    public SortPanel(int[] array, String sortName, JFrame frame, int[] originalArray) {
        this.array = array;
        this.sortName = sortName;
        // this.frame = frame;
        this.originalArray = originalArray;
        setBackground(Color.BLACK);

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> {
            cancelSorting();
            reset();
            CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
            cl.show(frame.getContentPane(), "Main Menu");
        });

        setLayout(new BorderLayout());
        add(backButton, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(60, 63, 65));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    public void reset() {
        System.arraycopy(originalArray, 0, array, 0, originalArray.length);
        cancelled = false;
    }

    public void startSorting(Runnable sortingAlgorithm) {
        sortingThread = new Thread(sortingAlgorithm);
        sortingThread.start();
    }

    public void cancelSorting() {
        cancelled = true;
        if (sortingThread != null && sortingThread.isAlive()) {
            sortingThread.interrupt();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(730, 610);
    }

    @Override
    public void update(int[] array, int current, int compare) {
        if (!cancelled) {
            this.current = current;
            this.compare = compare;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int max = Arrays.stream(array).max().getAsInt();
        int baseX = 10;

        // Draw the sort name at the top
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString(sortName, 10, 20);

        for (int i = 0; i < array.length; i++) {
            int lineHeight = (int) ((array[i] / (double) max) * 500);
            float ratio = array[i] / (float) max; // Ratio of the current value to the maximum value
            int red = (int) (ratio * 255);
            int green = (int) ((1 - ratio) * 255);
            Color lineColor = new Color(red, green, 0);

            if (i == current || i == compare) {
                g2d.setColor(Color.CYAN);
            } else {
                g2d.setColor(lineColor);
            }

            g2d.drawLine(baseX, 550, baseX, 550 - lineHeight);
            baseX += 2;
        }

        g2d.dispose();
    }
}
