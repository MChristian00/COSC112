import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Bouncing extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 50;
    double positionX, positionX1;
    double positionY, positionY1;

    // Note: The following are not used yet, you should use them in writing your
    // code.
    double velocityX, velocityX1;
    double velocityY, velocityY1;

    double accelerationX;
    double accelerationY;

    class Runner implements Runnable {
        public Runner() {
            // Feel free to change these default values
            positionX = 275;
            positionY = HEIGHT - 275;
            velocityX = 100;
            velocityY = -100;
            accelerationY = 98;
            // your code here for adding the second sphere

            positionX1 = 100;
            positionY1 = HEIGHT - 200;
            velocityX1 = 70;
            velocityY1 = -70;
        }

        public void run() {
            while (true) {
                // your code here
                // Implement Movement here
                // (Use velocityX and velocityY rather than fixed constants)

                positionX += velocityX / (double) FPS;
                positionY += velocityY / (double) FPS;

                positionX1 += velocityX1 / (double) FPS;
                positionY1 += velocityY1 / (double) FPS;

                // Implement bouncing here
                if (0 >= positionX - RADIUS || WIDTH - positionX <= RADIUS || 0 >= positionY + RADIUS
                        || positionY >= HEIGHT) {
                    velocityX = -velocityX;
                    velocityY = -velocityY;
                }

                if (0 >= positionX1 - RADIUS || WIDTH - positionX1 <= RADIUS || 0 >= positionY1 + RADIUS
                        || positionY1 >= HEIGHT) {
                    velocityX1 = -velocityX1;
                    velocityY1 = -velocityY1;
                }

                // Implement gravity here (Bonus)
                accelerationX = velocityX / FPS;
                velocityX = velocityX + (accelerationX / FPS);
                velocityY = velocityY + (accelerationY / FPS);

                // don't mess too much with the rest of this method
                repaint();
                try {
                    Thread.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public Bouncing() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Physics!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Bouncing world = new Bouncing();
        frame.setContentPane(world);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // The cannon you see is actually *not* a photograph of a real cannon.
        // It's drawn by the following.
        g.setColor(Color.ORANGE);
        int xpts[] = { 75, 275, 275, 350, 325, 150 };
        int ypts[] = { HEIGHT - 50, HEIGHT - 250, HEIGHT - 275, HEIGHT - 175, HEIGHT - 175, HEIGHT - 25 };
        g.fillPolygon(xpts, ypts, 6);

        g.setColor(Color.BLUE);
        g.fillOval(150, HEIGHT - 200, 200, 200);

        // this is where the sphere is drawn. As a bonus make it draw something else
        // (e.g., your object from the previous homework).
        g.setColor(Color.WHITE);
        g.drawOval((int) positionX, (int) positionY, RADIUS, RADIUS);

        // your code here for drawing the second sphere
        g.setColor(Color.GREEN);
        g.drawOval((int) positionX1, (int) positionY1, RADIUS, RADIUS);
    }
}