import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class DrawToScreen extends JPanel {
    public static final int BOX_WIDTH = 1024;
    public static final int BOX_HEIGHT = 768;

    public DrawToScreen() {
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
    }

    // Your code here, if you want to define additional methods.

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Your code here: feel free to remove what is below

        final int X_START = 300;
        final int Y_START = 200;
        final int WIDTH = 40;
        final int HEIGHT = 20;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                g.setColor(Color.ORANGE);

                // Rectangle
                g.drawRect(60 * i, 40 * j, 40, 20);

                // Triangle
                g.drawLine(60 * i, 40 * j, (60 * i + (40 / 2)), 40 - 20);
                g.drawLine((60 * i + (40 / 2)), (40 * j) - 20, (60 * i + 40), 40);
                g.drawLine(60 * i, 40 * j, (60 * i + 40), 40);

                // Square
                g.fillRect(60 * i + (40 / 3), 40 * j + (20 / 3), 10, 10);

                // Circle
                g.fillOval((60 * i) + (40 / 2), 40 * j - (20 / 2), 5, 5);

                // Oval
                g.drawOval(90 * i, 40 * j + (20 / 2), 4, 2);
            }
        }

    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("DrawToScreen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new DrawToScreen());
        frame.pack();
        frame.setVisible(true);
    }
}