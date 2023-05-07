import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {
    boolean isMovies;
    boolean isScience;
    boolean isGeography;
    boolean isCelebrity;
    public boolean gpressed, spressed, cpressed, mpressed, hpressed;
    BufferedImage menuboard;
    JButton start, exit, help;

    public Menu(){
        isMovies = false;
        isScience = false;
        isGeography = false;
        isCelebrity = false;
    }
    public void keyressed(KeyEvent e){
        char pressed = e.getKeyChar();
        if(pressed == 'g' ){
            isGeography = true;
            gpressed = true;
        }
        if(pressed == 'k' ){
            isScience = true;
            spressed = true;
        }
        if(pressed == 'c' ){
            isCelebrity = true;
            cpressed = true;

        }
        if(pressed == 'm' ){
            isMovies = true;
            mpressed = true;
        }
    }
    public void keyreleased(KeyEvent e){
        char pressed = e.getKeyChar();
        if(pressed == 'g' ){
            isGeography = false;
            gpressed = false;
        }
        if(pressed == 'k' ){
            isScience = false;
            spressed = false;
        }
        if(pressed == 'c' ){
            isCelebrity = false;
            cpressed = false;

        }
        if(pressed == 'm' ){
            isMovies = false;
            mpressed = false;
        }
    }
    public void getmenuImage(){
        try{
            menuboard = ImageIO.read(new File("sprites/menuboard.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void drawmenu(Graphics g){
        Graphics2D g2 =  (Graphics2D)g;
//        getmenuImage();
//        g2.drawImage(menuboard,500,520,700,500, null);
//        g2.setFont(new Font("Apple Chancery",Font.BOLD,50));
        g2.setFont(new Font("Apple Chancery",Font.BOLD,30));
        g2.setColor(Color.black);
        g2.drawString("START", 600,200);
        g2.drawString("HELP", 600,250);
        g2.drawString("EXIT", 600,300);

//        start.setBounds(750,260, 100,100);
//        start.setText("START");
//        help.setBounds(750,320,100,100);
//        help.setText("INSTRUCTIONS");
//        exit.setBounds(750,380,100,100);
//        exit.setText("EXIT");
    }
}
