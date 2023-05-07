//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//class Pair {
//    public double x;
//    public double y;
//
//    public Pair(double initX, double initY) {
//        x = initX;
//        y = initY;
//    }
//
//    public Pair add(Pair toAdd) {
//        return new Pair(x + toAdd.x, y + toAdd.y);
//    }
//
//    public Pair divide(double denom) {
//        return new Pair(x / denom, y / denom);
//    }
//
//    public Pair times(double val) {
//        return new Pair(x * val, y * val);
//    }
//
//    public void flipX() {
//        x = -x;
//    }
//
//    public void flipY() {
//        y = -y;
//    }
//}
//
//
//class Ball {
//    Pair position;
//    Color color;
//    public BufferedImage image;
//    public boolean collision = false;
//    static int count = 1;
//    int height = 120;
//    int width = 120;
//    int yPos;
//    int xPos;
//    Ball[] ball = new Ball[30];
//
//    public Ball(Color c, Pair p) {
//        position = p;
//        color = c;
//        count++;
//    }
//
//    public Ball() throws IOException {
//        ball[0].image = ImageIO.read(new File());
//    }
//
//    public void update(Color color) {
////        position = position.add(velocity.times(time));
////            bounce(w);
//        this.color = color;
//    }
//
//    public void setPosition(Pair p) {
//        position = p;
//    }
//
//    public Pair getPosition() {
//        return position;
//    }
//
//    public void getTileImage(){
//
//        try{
//            ball[0] = new Ball();
//            ball[0].image = ImageIO.read(new File("sprites/plaingrass.png"));
//
//            ball[1] = new Ball();
//            ball[1].image = ImageIO.read(new File("sprites/appletree.png"));
//
//            ball[2] = new Ball();
//            ball[2].image = ImageIO.read(new File("sprites/goal.png"));
//
//            ball[3] = new Ball();
//            ball[3].image = ImageIO.read(new File("sprites/brick.png"));
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//
////    public void draw(Graphics g) {
////        this.getTileImage();
////        for(int i = -460 ; i < 1000; i+=44) {
////            for (int j = -468; j < 1000; j += 106) {
////                g.drawImage(ball[0].image, j, i, 1024, 768, null);
////            }
////        }
////        for (int j = -468; j < 80; j += 106) {
////            g.drawImage(ball[1].image, j, -180, 768, 800, null);
////        }
////        for (int j = -180; j < 1000; j += 60) {
////            g.drawImage(ball[1].image, 140, j, 768, 800, null);
////        }
////        for (int j = -600; j < 80; j += 106) {
////            g.drawImage(ball[1].image, j, 520, 768, 800, null);
////        }
////        for (int j = -80; j < 550; j += 60) {
////            g.drawImage(ball[1].image, -600, j, 768, 800, null);
////        }
////
////        // Goal
////        g.drawImage(ball[2].image, -300, -140, 800, 800, null);
////
////    }
//
//    public static void main(String[] args){
//        int y = 122;
//        int xInc = 240;
//        int yInc = 120;
//        Ball tl;
//        Color color;
//        for(int j=1; j<=5; j++){
//            int x = 160;
//            for(int i=1; i<=5; i++){
//                if(count%2 == 0){
//                    color = new Color(51,0,0);
//                    tl = new Ball(color, new Pair(x,y));
//                    continue;
//                }
//                color = new Color(153,102,0);
//                tl = new Ball(color, new Pair(x,y));
//                y+=yInc;
//            }
//            x+=xInc;
//        }
//
//    }
//}
//
////public class Tiles1 {
////
////    public BufferedImage image;
////    public boolean collision = false;
////    Tiles1[] tiles;
////
////    public Tiles1(){
////        tiles = new Tiles1[10];
////    }
////
////
////
////    public void getTileImage(){
////
////        try{
////            tiles[0] = new Tiles1();
////            tiles[0].image = ImageIO.read(new File("sprites/plaingrass.png"));
////
////            tiles[1] = new Tiles1();
////            tiles[1].image = ImageIO.read(new File("sprites/appletree.png"));
////
////            tiles[2] = new Tiles1();
////            tiles[2].image = ImageIO.read(new File("sprites/goal.png"));
////
////            tiles[3] = new Tiles1();
////            tiles[3].image = ImageIO.read(new File("sprites/brick.png"));
////
////        }catch(IOException e){
////            e.printStackTrace();
////        }
////    }
////
////    public void draw(Graphics g){
////        //Graphics2D g2 = (Graphics2D)g;
////        this.getTileImage();
////        for(int i = -460 ; i < 1000; i+=44) {
////            for (int j = -468; j < 1000; j += 106) {
////                g.drawImage(tiles[0].image, j, i, 1024, 768, null);
////            }
////        }
////            for (int j = -468; j < 80; j += 106) {
////                g.drawImage(tiles[1].image, j, -180, 768, 800, null);
////            }
////        for (int j = -180; j < 1000; j += 60) {
////            g.drawImage(tiles[1].image, 140, j, 768, 800, null);
////        }
////        for (int j = -600; j < 80; j += 106) {
////            g.drawImage(tiles[1].image, j, 520, 768, 800, null);
////        }
////        for (int j = -80; j < 550; j += 60) {
////            g.drawImage(tiles[1].image, -600, j, 768, 800, null);
////        }
////        // Goal
////        g.drawImage(tiles[2].image, -300, -140, 800, 800, null);
////            //chess table
////        g.setColor(new Color(153,102,0));
////        g.fillRect(160,122,600,600);
//
//
//
////        g.setColor(new Color(51,0,0));
////
////        g.fillRect(160,122,120,120);
////        g.fillRect(400,122,120,120);
////        g.fillRect(640,122,120,120);
////
////        g.fillRect(280,242,120,120);
////        g.fillRect(520,242,120,120);
////
////        g.fillRect(160,362,120,120);
////        g.fillRect(400,362,120,120);
////        g.fillRect(640,362,120,120);
////
////        g.fillRect(280,482,120,120);
////        g.fillRect(520,482,120,120);
////
////        g.fillRect(160,602,120,120);
////        g.fillRect(400,602,120,120);
////        g.fillRect(640,602,120,120);
////// change y by 120 for the red tiles (3-2-3-2-3 pattern)
////        // change x by by 240
////    }
////}
