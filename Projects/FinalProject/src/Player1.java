import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;

public class Player1 extends JPanel {
    Menu menu;
    public int x, y;
    public int speed;
    public boolean uppressed, downpressed, leftpressed, rightpressed,mousepressed;
    public BufferedImage downA, downB, upA, upB, rightA, rightB, leftA, leftB, heart;
    public String Direction;
    public int spritetracker = 0;

    public int id = 1;
    Tiles tileset;
    JButton hintBtn;

    int lives;

    int hintCounts = 3;

    public Player1() {
        x = 0;
        y = 0;
        speed = 4;
        lives = 3;
        getPlayerImage();
        Direction = "down";
        menu = new Menu();
        tileset = new Tiles();
        this.removeAll();
    }

    public void getPlayerImage() {
        try {
            downA = ImageIO.read(new File("sprites/downA.png"));
            downB = ImageIO.read(new File("sprites/down.png"));
            upA = ImageIO.read(new File("sprites/upA.png"));
            upB = ImageIO.read(new File("sprites/upB.png"));
            rightA = ImageIO.read(new File("sprites/rightA.png"));
            rightB = ImageIO.read(new File("sprites/rightB.png"));
            leftA = ImageIO.read(new File("sprites/leftA.png"));
            leftB = ImageIO.read(new File("sprites/leftB.png"));
            heart = ImageIO.read(new File("sprites/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleBombs(){
        // get player position

        // check if position is in array of bombs list

        // show effect at that position and decrease life by 1

    }

    public void keypressed(KeyEvent e) {
        int pressed = e.getKeyCode();
        if ( pressed == e.VK_UP) {
            Direction = "up";
            uppressed = true;
        }
        if ( pressed == e.VK_DOWN) {
            Direction = "down";
            downpressed = true;

        }
        if ( pressed == e.VK_RIGHT) {
            Direction = "right";
            rightpressed = true;
        }
        if ( pressed == e.VK_LEFT) {
            Direction = "left";
            leftpressed = true;

        }


    }

    public void keyreleased(KeyEvent e) {
        int pressed = e.getKeyCode();
        if ( pressed == e.VK_UP) {
            uppressed = false;
        }
        if (pressed == e.VK_DOWN) {
            downpressed = false;
        }
        if (pressed == e.VK_RIGHT) {
            rightpressed = false;
        }
        if (pressed == e.VK_LEFT) {
            leftpressed = false;

        }
    }
    public void mousepressed(MouseEvent e){
        mousepressed = true;
    }

    public void update() {
        if(mousepressed) {
            if (uppressed == true) {
                y -= speed;
            }
            if (downpressed == true) {
                y += speed;
            }
            if (leftpressed == true) {
                x -= speed;
            }
            if (rightpressed == true) {
                x += speed;
            }

            spritetracker++;
            if (spritetracker > 20) {
                if (id == 1) {
                    id = 2;
                } else if (id == 2) {
                    id = 1;
                }
                spritetracker = 0;
            }

        }

    }

    public void displayPoints(Graphics2D g2){
//        System.out.println("in display points" + GamePanel1.pointsCount);
        g2.setFont(new Font("Apple Chancery",Font.BOLD,20));
        g2.setColor(Color.WHITE);
        g2.drawString("SCORE: "+ GamePanel1.pointsCount, 1100, 30);
    }

    public void updateLives(Graphics2D g2){
//        System.out.println("in update lIves" + GamePanel1.playerLives);
        int xPos = 900;
        for(int i=1; i<= GamePanel1.playerLives; i++){
            g2.drawImage(heart, xPos, 10, 30, 30, null);
            xPos += 60;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (!mousepressed) {
            menu.drawmenu(g);
        } else {
            BufferedImage image = null;
            switch (Direction) {
                case "up":
                    if (id == 1) {
                        image = upA;
                    }
                    if (id == 2) {
                        image = upB;
                    }
                    break;
                case "down":
                    if (id == 1) {
                        image = downA;
                    }
                    if (id == 2) {
                        image = downB;
                    }
                    break;
                case "left":
                    if (id == 1) {
                        image = leftA;
                    }
                    if (id == 2) {
                        image = leftB;
                    }
                    break;
                case "right":
                    if (id == 1) {
                        image = rightA;
                    }
                    if (id == 2) {
                        image = rightB;
                    }
                    break;
            }
            tileset.draw(g);
            g2.drawImage(image, x, y, 700, 500, null);
            updateLives(g2);
            displayPoints(g2);
        }
    }
}
