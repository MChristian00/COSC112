import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Pair {
    public double x;
    public double y;

    public Pair(double initX, double initY) {
        x = initX;
        y = initY;
    }

    public Pair add(Pair toAdd) {
        return new Pair(x + toAdd.x, y + toAdd.y);
    }

    public Pair divide(double denom) {
        return new Pair(x / denom, y / denom);
    }

    public Pair times(double val) {
        return new Pair(x * val, y * val);
    }

    public void flipX() {
        x = -x;
    }

    public void flipY() {
        y = -y;
    }
}

class Ball {
    Pair position;
    Pair velocity;
    Pair acceleration;
    double radius;
    Color color;

    public Ball() {
        position = new Pair(1024 / 2, 768 / 2);
        velocity = new Pair(200, 0);
        acceleration = new Pair(0, 0);
        radius = 12;
        color = new Color(255, 255, 255);
    }

    public void update(World w, double time) {
        position = position.add(velocity.times(time));
        velocity = velocity.add(acceleration.times(time));
        bounce(w);
    }

    public void setPosition(Pair p) {
        position = p;
    }

    public void setVelocity(Pair v) {
        velocity = v;
    }

    public void setAcceleration(Pair a) {
        acceleration = a;
    }

    public Pair getPosition() {
        return position;
    }

    public Pair getVelocity() {
        return velocity;
    }

    public Pair getAcceleration() {
        return acceleration;
    }

    public double flipX() {
        acceleration.flipX();
        return 0.0;
    }

    public double flipY() {
        acceleration.flipY();
        return 0.0;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();

        g.setColor(color);
        g.drawOval((int) (position.x - radius), (int) (position.y - radius), (int) (2 * radius), (int) (2 * radius));
        g.setColor(c);
    }

    public double calcAngle(Pair ballCenter, Pair paddleCenter) {
        // calculates the angle of reflection
        double factor = (paddleCenter.y - ballCenter.y) / ballCenter.y;
        return factor * 75;
    }

    private void bounce(World w) {
        Boolean bounced = false;

        Boolean alignWP0 = false;
        Boolean alignWP1 = false;

        double reflectionAngle = 0.0;

        Pair paddle1 = w.paddles[0].getPosition();
        Pair paddle2 = w.paddles[1].getPosition();

        // Find center for each paddle
        Pair centerPaddle0 = new Pair((paddle1.x - (Paddle.width / 2)),
                (paddle1.x - (Paddle.width / 2) - (Paddle.height / 2)));
        Pair centerPaddle1 = new Pair((paddle2.x - (Paddle.width / 2)),
                (paddle2.x - (Paddle.width / 2) - (Paddle.height / 2)));

        if (position.y < paddle1.y && position.y > (paddle1.y - Paddle.height)) {
            alignWP0 = true;
            reflectionAngle = calcAngle(position, paddle1);
        }

        if (position.y < paddle2.y && position.y > (paddle2.y - Paddle.height)) {
            alignWP1 = true;
            reflectionAngle = calcAngle(position, paddle2);

        }

        if (alignWP0 && position.x - radius < 0) {
            velocity.flipX();
            position.x = radius;
            System.out.println(reflectionAngle);
            bounced = true;
        } else if (alignWP1 && position.x + radius > w.width) {
            velocity.flipX();
            position.x = w.width - radius;
            System.out.println(reflectionAngle);
        }
        if (position.y - radius < 0) {
            velocity.flipY();
            position.y = radius;
            bounced = true;
        } else if (position.y + radius > w.height) {
            velocity.flipY();
            position.y = w.height - radius;
            bounced = true;
        }
        if (bounced) {
            velocity.x = velocity.x + (velocity.y * Math.cos(reflectionAngle));
            velocity.y = velocity.y * Math.sin(reflectionAngle);
            setVelocity(new Pair(velocity.x, velocity.y));
        }

        // handle ball position after win
        if (position.x < 0 || position.x > w.width) {
            setPosition(new Pair(w.width / 2, w.height / 2));
        }

    }

}

class Paddle {
    Pair position;
    Pair velocity;
    Pair acceleration;
    static double height = 70;
    static double width = 20;
    double dampening;
    Color color;
    int score = 0;

    public Paddle(Pair pos) {
        Random rand = new Random();
        position = pos;
        velocity = new Pair(0, 100);
        acceleration = new Pair(0.0, 0);
        dampening = 1.3;
        color = new Color(255, 255, 255);
    }

    public void update(World w, double time) {
        position = position.add(velocity.times(time));
        velocity = velocity.add(acceleration.times(time));
    }

    public void setPosition(Pair p) {
        position = p;
    }

    public void setVelocity(Pair v) {
        velocity = v;
    }

    public void setAcceleration(Pair a) {
        acceleration = a;
    }

    public Pair getPosition() {
        return position;
    }

    public Pair getVelocity() {
        return velocity;
    }

    public Pair getAcceleration() {
        return acceleration;
    }

    public double flipX() {
        acceleration.flipX();
        return 0.0;
    }

    public double flipY() {
        acceleration.flipY();
        return 0.0;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();

        g.setColor(color);
        g.drawRect((int) (position.x - width), (int) (position.y - height), (int) (width), (int) (height));
        g.setColor(c);
    }
}

class World {
    int height;
    int width;

    Ball ball;
    int numPaddles = 2;
    Paddle paddles[];
    Pair paddlePos[] = new Pair[2];

    public World(int initWidth, int initHeight) {
        width = initWidth;
        height = initHeight;

        ball = new Ball();
        paddles = new Paddle[numPaddles];

        paddlePos[0] = new Pair(20, 440);
        paddlePos[1] = new Pair(1020, 440);

        for (int i = 0; i < numPaddles; i++) {
            paddles[i] = new Paddle(paddlePos[i]);
        }
    }

    public void drawPaddles(Graphics g) {
        for (int i = 0; i < numPaddles; i++) {
            paddles[i].draw(g);
        }
    }

    public void updatePaddles(double time) {
        for (int i = 0; i < numPaddles; i++)
            paddles[i].update(this, time);
    }

    public void drawBall(Graphics g) {
        ball.draw(g);
    }

    public void updateBall(double time) {
        ball.update(this, time);
    }

    public void updateScore(Paddle p) {
        p.score++;
    }
}

public class Pong extends JPanel implements KeyListener {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 250;
    World world;

    class Runner implements Runnable {
        public void run() {
            while (true) {
                world.updateBall(1.0 / (double) FPS);
                repaint();
                try {
                    Thread.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);

        Pair moveBy = null;
        Paddle p = null;
        switch (c) {
            case 'w':
                p = world.paddles[0];
                moveBy = new Pair(0, -20);
                break;
            case 's':
                p = world.paddles[0];
                moveBy = new Pair(0, 20);
                break;
            case 'i':
                p = world.paddles[1];
                moveBy = new Pair(0, -20);
                break;
            case 'k':
                p = world.paddles[1];
                moveBy = new Pair(0, 20);
                break;
            case 'a':
                p = world.paddles[0];
                moveBy = new Pair(0, 0);
                break;
            case 'l':
                p = world.paddles[1];
                moveBy = new Pair(0, 0);
        }
        if (moveBy != null) {
            p.setPosition(p.getPosition().add(moveBy));
            // p.update(world, 1 / 2);
        }

    }

    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
    }

    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public Pong() {
        world = new World(WIDTH, HEIGHT);
        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pong mainInstance = new Pong();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        world.drawPaddles(g);
        world.drawBall(g);

    }
}
