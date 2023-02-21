import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spinning extends JPanel {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int FPS = 100;
	public static Sphere[] spheres;

	public Spinning() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// Intitializing spheres
		for (int i = 0; i < spheres.length; i++) {
			if (i == 0) {
				spheres[i] = new Sphere(400, HEIGHT - 400, 25 * (i + 1), Color.ORANGE);
			} else {
				spheres[i] = new Sphere(200 + (i * 50), 200 + (i * 50), 10 * (i + 1), Color.GRAY);
			}
		}
	}

	public void Go() {

		// Coordinates sphere movement
		while (true) {
			for (Sphere s : spheres) {
				s.update(1.0 / (double) FPS);
			}

			// don't mess too much with the rest of this method
			repaint();
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
			}
		}
	}

	public static void main(String[] args) {

		for (String s : args) {
			System.out.println(s);
		}

		int numSpheres = 6;
		if (args.length < 1) {
			System.out.println("When you run this, you can specifiy the number of spheres.");
			System.out.println("e.g., java Spinning 10");
		} else {
			System.out.println("You specified that there should be " + args[0] + " spheres.");
			numSpheres = Integer.parseInt(args[0]);
		}

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

		// Drawing spheres
		for (Sphere s : spheres) {
			s.draw(g);
		}
	}
}

class Sphere {
	final int WIDTH = 1024;
	final int HEIGHT = 768;
	final double CENTERX = WIDTH / 2.0;
	final double CENTERY = HEIGHT / 2.0;
	final int RADIUS;
	Color color;
	double time;
	double positionX, positionY;
	double velocityX, velocityY;
	double deltaX, deltaY;

	public Sphere(double positionX, double positionY, int RADIUS, Color color) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.RADIUS = RADIUS;
		this.color = color;
	}

	public void update(double time) {
		this.deltaX = positionX - CENTERX;
		this.deltaY = positionY - CENTERY;
		this.velocityX = -this.deltaY;
		this.velocityY = this.deltaX;
		this.positionX += this.velocityX * time;
		this.positionY += this.velocityY * time;
	}

	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval((int) this.positionX, (int) this.positionY, this.RADIUS * 2, this.RADIUS * 2);
	}
}