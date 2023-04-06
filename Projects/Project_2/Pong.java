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
    double dampening;
    Color color;

    public Ball() {
        Random rand = new Random();
        position = new Pair(1024 / 2, 768 / 2);
        velocity = new Pair(1000, 1000);
        // velocity = new Pair((double) (rand.nextInt(1000) - 500), (double)
        // (rand.nextInt(1000) - 500));
        acceleration = new Pair(0, 0);
        radius = 12;
        dampening = 1.3;
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

    private void bounce(World w) {
        Boolean bounced = false;

        double posY = w.paddles[0].getPosition().x;
        double posX = w.paddles[0].getPosition().y;

        if (position.x + radius == w.paddles[0].getPosition().x
                || position.x + radius == w.paddles[1].getPosition().x) {
            System.out.println("Ball " + position.x + "Paddle " + w.paddles[1].getPosition().x);
        }
        // if (position.y > posY && position.y < posY + Paddle.height) {

        if (position.x - radius < 0) {
            velocity.flipX();
            position.x = radius;
            bounced = true;
        } else if (position.x + radius > w.width) {
            velocity.flipX();
            position.x = w.width - radius;
            bounced = true;
        }

        // }    

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
            // velocity = velocity.divide(dampening);
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

    public Paddle(Pair pos) {
        Random rand = new Random();
        position = pos;
        velocity = new Pair((double) (rand.nextInt(1000) - 500), (double) (rand.nextInt(1000) - 500));
        acceleration = new Pair(0.0, 200.0);
        dampening = 1.3;
        color = new Color(255, 255, 255);
        // color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    public void update(World w, double time) {
        position = position.add(velocity.times(time));
        velocity = velocity.add(acceleration.times(time));
        // bounce(w);
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

    // public double flipX() {
    // acceleration.flipX();
    // return 0.0;
    // }

    // public double flipY() {
    // acceleration.flipY();
    // return 0.0;
    // }

    public void draw(Graphics g) {
        Color c = g.getColor();

        g.setColor(color);
        g.drawRect((int) (position.x - width), (int) (position.y - height), (int) (width), (int) (height));
        g.setColor(c);
    }

    // private void bounce(World w) {
    // Boolean bounced = false;
    // if (position.x - 2 < 0) {
    // velocity.flipX();
    // position.x = radius;
    // bounced = true;
    // } else if (position.x + radius > w.width) {
    // velocity.flipX();
    // position.x = w.width - radius;
    // bounced = true;
    // }
    // if (position.y - radius < 0) {
    // velocity.flipY();
    // position.y = radius;
    // bounced = true;
    // } else if (position.y + radius > w.height) {
    // velocity.flipY();
    // position.y = w.height - radius;
    // bounced = true;
    // }
    // if (bounced) {
    // velocity = velocity.divide(dampening);
    // }
    // }
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

        paddlePos[0] = new Pair(20, 404);
        paddlePos[1] = new Pair(1020, 404);

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
}

public class Pong extends JPanel implements KeyListener {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    World world;
    // HandleFile hf;
    // Pair spheresCond[] = new Pair[51];
    // File f = new File("./qs.txt");

    class Runner implements Runnable {
        public void run() {
            while (true) {
                // world.updatePaddles(1.0 / (double) FPS);
                world.updateBall(1.0 / (double) FPS);
                repaint();
                try {
                    Thread.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                }
            }

        }
    }

    // public Pair[] readFromFile() {
    // Pair points[] = new Pair[world.spheres.length];
    // Pair point;
    // int i = 0;
    // try {
    // Scanner sc = new Scanner(f);
    // while (sc.hasNext()) {
    // if (sc.hasNextDouble()){
    // point = new Pair(sc.nextDouble(), sc.nextDouble());
    // points[i] = point;
    // }
    // i++;
    // }
    // sc.close();
    // } catch (FileNotFoundException e) {
    // e.printStackTrace();
    // System.err.println("File not found.");
    // }
    // return points;
    // }

    // public void writeToFile(Pair[] points) {
    // try {
    // PrintWriter pw = new PrintWriter(f);
    // for (int i = 0; i < points.length-1; i++) {
    // if(i==0){
    // String acceleration = points[0].x + " " + points[0].y;
    // pw.write(acceleration);
    // pw.write("\n");
    // continue;
    // }
    // pw.write(points[i].x + " " + points[i].y);
    // pw.write("\n");
    // }

    // pw.close();
    // } catch (FileNotFoundException e) {
    // System.err.println("File not found.");
    // }
    // }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);

        Pair newAcc = null;
        switch (c) {
            case 'w':
                newAcc = new Pair(0, -98);
                break;
            case 'a':
                newAcc = new Pair(-98, 0);
                break;
            case 's':
                newAcc = new Pair(0, 98);
                break;
            case 'd':
                newAcc = new Pair(98, 0);
                break;
            case 'q':
                // this.quickSave();
                break;
            case 'l':
                // this.quickLoad();
        }
        if (newAcc != null)
            for (Paddle s : world.paddles)
                s.setAcceleration(newAcc);

    }

    // public void quickSave() {
    // Pair currentAcceleration = world.spheres[0].getAcceleration();
    // for (int i = 0; i < world.spheres.length; i++) {
    // if(i==0){
    // spheresCond[0] = new Pair(currentAcceleration.x,
    // currentAcceleration.y);
    // continue;
    // }
    // spheresCond[i] = world.spheres[i].getPosition();
    // }
    // writeToFile(spheresCond);
    // }

    // public void quickLoad() {
    // Pair points[] = readFromFile();
    // Pair prevAcceleration = points[0];

    // for (int i = 1; i < world.spheres.length; i++) {
    // world.spheres[i].setAcceleration(prevAcceleration);
    // world.spheres[i].setPosition(points[i]);
    // }
    // }

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
