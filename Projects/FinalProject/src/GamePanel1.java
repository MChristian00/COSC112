import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GamePanel1 extends JPanel implements Runnable{
    public static final int originaltilesize = 16;
    public static final int scale = 3;
    public static final int tilesize = originaltilesize * scale;
    public static final int maxScreencol = 16;
    public static final int maxscreenrow = 12;
    public static final int screenwidth = 1400;
    public static final int screenheight = 800;
    public static final int FPS = 60;

    static JButton hintBtn = new JButton();

    static int hintCounts = 3;

    static int playerLives = 3;

    static int pointsCount = 0;

    static boolean trueAns = false;

    static int gameState = 0;

    Thread gameThread;
    ActionListener KL = new ActionListener();
    MouseListener ML = new AL();

    Player1 character;
    Tiles tiles;
    Trivia1 trivia1;
    Menu menu;
    boolean displayT = false;

    public GamePanel1() {
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        character = new Player1();
        tiles = new Tiles();
        menu = new Menu();
//        trivia1 = new Trivia1();
        this.addKeyListener(KL);
        this.addMouseListener(ML);
        this.setFocusable(true);
        startgamethread();
    }

    public void drawHintButton(){
        hintBtn.setVisible(true);
//        if(hintCounts == 0) {
//            if (playerLives == 3)
//                hintBtn.setEnabled(false);
//        }
        hintBtn.setBounds(1100,40,200,20);
        hintBtn.setText("HEART++ " + hintCounts +" remaining");
//        hintBtn.addActionListener(e -> hintBtn.setEnabled(false));
        hintBtn.addActionListener(e -> handleHint());
        add(hintBtn);
    }

    public void handleHint(){
        hintCounts--;
        if(playerLives < 3)
            playerLives++;
        new Trivia1();
    }

    public void startgamethread(){
        gameThread = new Thread(this);
        gameThread.start();
        drawHintButton();

    }

    @Override
    public void run() {
        while(true){
                update();
                repaint();
//            System.out.println("line 66 + lives" + playerLives+ "hints " + hintCounts + "points " + pointsCount);
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
//            trivia1.displayTriviaPanel(g);
//            drawHintButton();
        }

    class ActionListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            char c = e.getKeyChar();
            character.keypressed(e);
//            trivia1.keyPressed(e);
//            Toggle draw menu or hide menu here (menu has pause, save, quit)
//            if(c == 'm'){
//                menu.drawmenu();
//            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            character.keyreleased(e);
//            trivia1.keyReleased(e);
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


