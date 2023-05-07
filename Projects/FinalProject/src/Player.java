import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import java.io.File;
import java.io.IOException;

public class Player extends JPanel{
    Menu menu;
    public int x, y;
    public int speed;
    public boolean uppressed, downpressed, leftpressed, rightpressed,mousepressed;
    public BufferedImage downA, downB, upA, upB, rightA, rightB, leftA, leftB;
    public String Direction;
    public int spritetracker = 0;

    public int id = 1;
    Tiles tileset;
    JButton hintBtn;

    int lives;

    int hintCounts = 3;

    public Player() {
        x = 0;
        y = 0;
        speed = 4;
        lives = 3;
        getPlayerImage();
        Direction = "down";
        menu = new Menu();
        tileset = new Tiles();
//        drawHintButton();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void drawHintButton(){
//        hintBtn = new JButton();
//        if(hintCounts == 0)
//            hintBtn.setEnabled(false);
//        hintBtn.setBounds(0,0,20,20);
//        hintBtn.setText("Click to get a Hint!! You got" + hintCounts +"remaining");
//        hintBtn.addActionListener(e -> handleHint());
//        this.add(hintBtn);
//        hintBtn.addActionListener(e -> hintBtn.setEnabled(false));
//        hintBtn.addActionListener(e -> {
////            dispose();
//            new Trivia();
//        });
//        while(hintCounts > 0){
//            hintCounts--;
//        }
//    }

    public void handleHint(){
        // display Menu then from Menu, if click category take them to trivia scene and show question
        // on return call the giveHint method to give a direction. download arrow keys png to use in giving direction
        System.out.println("Working hehe");
//        menu = new Menu();
//        menu.drawmenu();
    }

    public void handleBombs(){
        // get player position

        // check if position is in array of bombs list

        // show effect at that position and decrease life by 1

    }

    public void keypressed(KeyEvent e) {
        char pressed = e.getKeyChar();
            if (pressed == 'w') {
                Direction = "up";
                uppressed = true;
            }
            if (pressed == 's') {
                Direction = "down";
                downpressed = true;

            }
            if (pressed == 'd') {
                Direction = "right";
                rightpressed = true;
            }
            if (pressed == 'a') {
                Direction = "left";
                leftpressed = true;

            }


    }

    public void keyreleased(KeyEvent e) {
        char pressed = e.getKeyChar();
        if (pressed == 'w') {

            uppressed = false;
        }
        if (pressed == 's') {
            downpressed = false;
        }
        if (pressed == 'd') {
            rightpressed = false;
        }
        if (pressed == 'a') {
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
        }
    }
}