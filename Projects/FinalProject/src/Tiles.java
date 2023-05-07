import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {

    public BufferedImage image;
    public boolean collision = false;
    Tiles [] tiles;

    public Tiles(){
        tiles = new Tiles [10];
    }

    public void getTileImage(){

        try{
            tiles[0] = new Tiles();
            tiles[0].image = ImageIO.read(new File("sprites/plaingrass.png"));

            tiles[1] = new Tiles();
            tiles[1].image = ImageIO.read(new File("sprites/appletree.png"));

            tiles[2] = new Tiles();
            tiles[2].image = ImageIO.read(new File("sprites/goal.png"));

            tiles[3] = new Tiles();
            tiles[3].image = ImageIO.read(new File("sprites/brick.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
        //Graphics2D g2 = (Graphics2D)g;
        this.getTileImage();
        for(int i = -460 ; i < 1000; i+=44) {
            for (int j = -468; j < 1000; j += 106) {
                g.drawImage(tiles[0].image, j, i, 1024, 768, null);
            }
        }
            for (int j = -468; j < 80; j += 106) {
                g.drawImage(tiles[1].image, j, -180, 768, 800, null);
            }
        for (int j = -180; j < 1000; j += 60) {
            g.drawImage(tiles[1].image, 140, j, 768, 800, null);
        }
        for (int j = -600; j < 80; j += 106) {
            g.drawImage(tiles[1].image, j, 520, 768, 800, null);
        }
        for (int j = -80; j < 550; j += 60) {
            g.drawImage(tiles[1].image, -600, j, 768, 800, null);
        }
        // Goal
        g.drawImage(tiles[2].image, -300, -140, 800, 800, null);
            //chess table
        g.setColor(new Color(153,102,0));
        g.fillRect(160,122,600,600);
        g.setColor(new Color(51,0,0));
        g.fillRect(160,122,120,120);
        g.fillRect(400,122,120,120);
        g.fillRect(640,122,120,120);
        g.fillRect(280,242,120,120);
        g.fillRect(520,242,120,120);
        g.fillRect(160,362,120,120);
        g.fillRect(400,362,120,120);
        g.fillRect(640,362,120,120);
        g.fillRect(280,482,120,120);
        g.fillRect(520,482,120,120);
        g.fillRect(160,602,120,120);
        g.fillRect(400,602,120,120);
        g.fillRect(640,602,120,120);

    }
}
