package HWK.HW03;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Spinning extends JPanel {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int FPS = 100;

	// Right now spheres isn't being used
	public static Sphere[] spheres;

	public Spinning() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// Initializing spheres position
		for (int i = 0; i < spheres.length; i++) {
			if (i == 0) {
				spheres[i] = new Sphere(200, HEIGHT - 200, 25 * (i + 1), Color.ORANGE);
			} else {
				spheres[i] = new Sphere(300 * i, 300 * i, 10 * (i + 1), Color.WHITE);
			}
		}
	}

	public void Go() {
		System.out.println("numberrr of spheeresssssss" + spheres.length);
		while (true) {
			for (Sphere s : spheres) {
				s.update(1.0 / (double) FPS);
			}

			// while (true) {
			// //You'll be changing the following
			// double deltaX = positionX - CENTERX;
			// double deltaY = positionY - CENTERY;

			// velocityX = -deltaY;
			// velocityY = deltaX;

			// positionX += velocityX * 1.0 / (double) FPS;
			// positionY += velocityY * 1.0 / (double) FPS;

			// double deltaX2 = positionX2 - CENTERX;
			// double deltaY2 = positionY2 - CENTERY;

			// velocityX2 = -deltaY2;
			// velocityY2 = deltaX2;

			// positionX2 += velocityX2 * 1.0 / (double) FPS;
			// positionY2 += velocityY2 * 1.0 / (double) FPS;

			// double deltaX3 = positionX3 - CENTERX;
			// double deltaY3 = positionY3 - CENTERY;

			// velocityX3 = deltaY3;
			// velocityY3 = -deltaX3;

			// positionX3 += velocityX3 * 1.0 / (double) FPS;
			// positionY3 += velocityY3 * 1.0 / (double) FPS;

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

		// Drawing spheres
		for (Sphere s : spheres) {
			s.draw(g);
		}
	}
}

// You'll implement this class
class Sphere {
	// Put fields here:
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
		// This is the constructor
		this.positionX = positionX;
		this.positionY = positionY;
		this.RADIUS = RADIUS;
		// this.deltaX = positionX - CENTERX;
		// this.deltaY = positionY - CENTERY;
		this.color = color;
		// this.velocityX = -deltaY;
		// this.velocityY = deltaX;
	}

	// Put methods (update, draw, and whatever else you decide to implement) here:
	public void update(double time) {
		this.deltaX = this.positionX - CENTERX;
		this.deltaY = this.positionY - CENTERY;
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