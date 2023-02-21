import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Spinning extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 25;
    public static final int RADIUS2 = 10;
    public static final int RADIUS3 = 50;
    public static final double CENTERX = WIDTH / 2.0;
    public static final double CENTERY = HEIGHT / 2.0;

    public Color color = Color.white;
    public Color color2 = Color.green;
    public Color color3 = Color.orange;

    // Right now spheres isn't being used
    public static Sphere[] spheres;

    // You'll get rid of the following set of variables
    // Instead, let each Sphere keep track of its own velocity and position
    double positionX;
    double positionY;

    double velocityX;
    double velocityY;

    double positionX2;
    double positionY2;

    double velocityX2;
    double velocityY2;

    double positionX3;
    double positionY3;

    double velocityX3;
    double velocityY3;

    public Spinning() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // Feel free to set default values as you see fit
        positionX = 275;
        positionY = HEIGHT - 275;

        positionX2 = 500;
        positionY2 = 500;

        positionX3 = 300;
        positionY3 = 300;
    }

    public void Go() {
        while (true) {
            // You'll be changing the following
            double deltaX = positionX - CENTERX;
            double deltaY = positionY - CENTERY;

            velocityX = -deltaY;
            velocityY = deltaX;

            positionX += velocityX * 1.0 / (double) FPS;
            positionY += velocityY * 1.0 / (double) FPS;

            double deltaX2 = positionX2 - CENTERX;
            double deltaY2 = positionY2 - CENTERY;

            velocityX2 = -deltaY2;
            velocityY2 = deltaX2;

            positionX2 += velocityX2 * 1.0 / (double) FPS;
            positionY2 += velocityY2 * 1.0 / (double) FPS;

            double deltaX3 = positionX3 - positionY;
            double deltaY3 = positionY3 - positionX;

            velocityX3 = deltaY3;
            velocityY3 = -deltaX3;

            positionX3 += velocityX3 * 1.0 / (double) FPS;
            positionY3 += velocityY3 * 1.0 / (double) FPS;

            // don't mess too much with the rest of this method
            repaint();
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        int numSpheres = 3;
        if (args.length < 1) {
            System.out.println("When you run this, you can specifiy the number of spheres.");
            System.out.println("e.g., java Spinning 10");
        } else {
            System.out.println("You specified that there should be " + args[0] + " spheres.");
            numSpheres = Integer.parseInt(args[0]);
        }

        // here you'll set up the spheres array
        spheres = new Sphere[numSpheres];

        JFrame frame = new JFrame("Spinning Spheres");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Spinning world = new Spinning();
        frame.setContentPane(world);
        frame.pack();
        frame.setVisible(true);
        world.Go();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // your code here for drawing the other spheres (you'll replace the following
        // lines)
        g.setColor(color);
        g.fillOval((int) positionX, (int) positionY, RADIUS * 2, RADIUS * 2);
        g.setColor(color2);
        g.fillOval((int) positionX2, (int) positionY2, RADIUS2 * 2, RADIUS2 * 2);
        g.setColor(color3);
        g.fillOval((int) positionX3, (int) positionY3, RADIUS3 * 2, RADIUS3 * 2);

    }
}

// You'll implement this class
class Sphere {
    // Put fields here:

    public Sphere() {
        // This is the constructor

    }

    // Put methods (update, draw, and whatever else you decide to implement) here:

}
