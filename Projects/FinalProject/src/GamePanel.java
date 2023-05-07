import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.JButton;


import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;


public class GamePanel extends JPanel implements Runnable {
    public static final int originaltilesize = 16;
    public static final int scale = 3;
    public static final int tilesize = originaltilesize * scale;
    public static final int maxScreencol = 16;
    public static final int maxscreenrow = 12;
    public static final int screenwidth = 900;
    public static final int screenheight = 800;
    public static final int FPS = 60;

    JButton hintBtn;

    int hintCounts = 3;


    Thread gameThread;
    ActionListener KL = new ActionListener();
    MouseListener ML = new AL();

    Player1 character;
    Tiles tiles;
    Trivia trivia;
    Menu menu;





    public GamePanel() {
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        character = new Player1();
        tiles = new Tiles();
        menu = new Menu();
        this.addKeyListener(KL);
        this.addMouseListener(ML);
        this.setFocusable(true);
        startgamethread();
    }

    public void drawHintButton(Graphics g){
        hintBtn = new JButton();
        if(hintCounts == 0)
            hintBtn.setEnabled(false);
        hintBtn.setBounds(300,10,400,20);
        hintBtn.setText("Click to get a Hint!! You got " + hintCounts +" remaining");
        hintBtn.addActionListener(e -> handleHint());
        hintBtn.addActionListener(e -> hintBtn.setEnabled(false));
        while(hintCounts > 0){
            hintCounts--;
        }
        hintBtn.addActionListener(e -> {
//            dispose();
            new Trivia();
//            JFrame n = (JFrame) this.getTopLevelAncestor();
//            n.dispose();
        });
        this.add(hintBtn);
    }

    public void handleHint(){
        // display Menu then from Menu, if click category take them to trivia scene and show question
        // on return call the giveHint method to give a direction. download arrow keys png to use in giving direction
        System.out.println("Working hehe");
//        menu = new Menu();
//        menu.drawmenu();
    }

    public void startgamethread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(true){
                update();
                repaint();
            try{
                Thread.sleep(1000/FPS);
            }
            catch(InterruptedException e){}
        }


    }
    public void update() {
            character.update();

    }
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            character.draw(g);
            drawHintButton(g);
        }



    class ActionListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            char c = e.getKeyChar();
            character.keypressed(e);
//
//            Toggle draw menu or hide menu here (menu has pause, save, quit)
//            if(c == 'm'){
//                menu.drawmenu();
//            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            character.keyreleased(e);
        }
    }
    class AL implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        // check if button clicked


        }

        @Override
        public void mousePressed(MouseEvent e) {
            character.mousepressed(e);

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}


