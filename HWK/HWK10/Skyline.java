import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;

public class Skyline extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Skyline");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Skyline());
        frame.pack();
        frame.setVisible(true);
    }

    public Skyline() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void paintComponent(Graphics gOri) {
        Graphics2D g = (Graphics2D) gOri;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint sunSet = new GradientPaint(0, 0, Color.BLACK, 0, HEIGHT, new Color(55, 0, 0));
        g.setPaint(sunSet);
        g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));

        Random rand = new Random();
        // Your code here
        g.setColor(Color.lightGray);
        int randH;

        // Horizon
        randH = rand.nextInt(95, 105);
        for (int i = 1; i <= WIDTH; i++) {
            g.drawLine(i, HEIGHT, i, HEIGHT - randH);
            randH += rand.nextInt(11) - 5;
        }

        // Stars
        for (int i = 1; i <= 100; i++) {
            g.fillOval(rand.nextInt(0, WIDTH), rand.nextInt(0, HEIGHT), 2, 2);
        }

        // Cluster
        g.setColor(Color.WHITE);
        int x = 500;
        int y = 500;
        for (int i = 1; i <= 100; i++) {
            g.fillOval((int) (20 * rand.nextGaussian() + x), +(int) (20 * rand.nextGaussian() + y), 2, 2);
        }
    }
}