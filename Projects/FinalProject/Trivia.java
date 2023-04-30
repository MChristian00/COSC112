import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Node<T> {
    T data;
    ArrayList<Node<T>> children;

    public Node(T data) {
        this.data = data;
        children = new ArrayList<Node<T>>();
    }

    public void addChild(Node<T> child) {
        children.add(child);
    }
}

class Question {
    Node<String> root;
    ArrayList<String> categories = new ArrayList<>();

    public Question(String category, String question) {
        // Assign question to appropriate category
        if (category != null && question != null) {
        }
    }

    public void getQuestions() {
        // String req = "Give me an array of 5 different trivia categories in an array
        // format";
        // HttpResponse<String> res = makeRequest(req);
        // System.out.println(res.body().indexOf("History"));

        String[] historyQuestions = {
                "Who was the first president of the United States?",
                "In what year did World War II begin?",
                "Which ancient civilization built the pyramids?"
        };
        String[] geographyQuestions = {
                "What is the largest country in South America?",
                "What river runs through Paris?",
                "What is the smallest country in the world?"
        };
        String[] scienceQuestions = {
                "What is the smallest unit of life?",
                "What type of energy is produced by nuclear fusion?",
                "What is the only substance that can exist in all three states of matter at room temperature?"
        };

        String[][] historyAnswers = {
                { "George Washington", "John Adams", "Thomas Jefferson", "James Madison" },
                { "1939", "1941", "1943", "1945" },
                { "Ancient Egyptians", "Mayans", "Aztecs", "Incas" }
        };

        String[][] geographyAnswers = {
                { "Brazil", "Argentina", "Colombia", "Peru" },
                { "Seine", "Thames", "Danube", "Rhine" },
                { "Vatican City", "Monaco", "Nauru", "Tuvalu" }
        };

        String[][] scienceAnswers = {
                { "Cell", "Atom", "Molecule", "Organelle" },
                { "Nuclear energy", "Thermal energy", "Radiant energy", "Kinetic energy" },
                { "Water", "Mercury", "Carbon dioxide", "Helium" }
        };

        root = new Node<>("Root");
        Node<String> his = new Node<>("History");
        Node<String> geo = new Node<>("Geography");
        Node<String> sci = new Node<>("Science");

        for (int j = 0; j < 3; j++) {
            Node<String> hisNode = new Node<>(historyQuestions[j]);
            Node<String> geoNode = new Node<>(geographyQuestions[j]);
            Node<String> sciNode = new Node<>(scienceQuestions[j]);

            for (int i = 0; i < 4; i++) {
                Node<String> hisLeaf = new Node<>(historyAnswers[j][i]);
                Node<String> geoLeaf = new Node<>(geographyAnswers[j][i]);
                Node<String> sciLeaf = new Node<>(scienceAnswers[j][i]);

                hisNode.addChild(hisLeaf);
                geoNode.addChild(geoLeaf);
                sciNode.addChild(sciLeaf);
            }
            his.addChild(hisNode);
            geo.addChild(geoNode);
            sci.addChild(sciNode);
        }

        root.addChild(his);
        root.addChild(geo);
        root.addChild(sci);

    }

    public ArrayList<String> getCategories() {
        ArrayList<String> catList = new ArrayList<>();
        for (Node cat : root.children) {
            catList.add(cat.data.toString());
        }
        return catList;
    }

    public Node<String> getQuestionByCategory(int categoryInd) {
        Random rand = new Random();
        int randInd = rand.nextInt(3);
        Node<String> catNode = root.children.get(categoryInd);
        Node<String> qsNode = catNode.children.get(randInd);
        return qsNode;
    }

    public String[] getChoices(Node<String> qsNode) {
        String[] choices = new String[4];
        for (int i = 0; i < 4; i++) {
            choices[i] = qsNode.children.get(i).data;
        }
        return choices;
    }

    public boolean checkAnswer(char choiceInd) {
        // return the answer to the hint caller method to handle loss or hint
        return choiceInd == 'a';
    }

    // public HttpResponse<String> makeRequest(String req){
    // HttpResponse<String> res = null;
    // String p = "{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\":
    // \"user\",\"content\": \""+ req +"\"}]}";
    // try{
    // HttpRequest request = HttpRequest.newBuilder()
    // .uri(URI.create("https://chatgpt-best-price.p.rapidapi.com/v1/chat/completions"))
    // .header("content-type", "application/json")
    // .header("X-RapidAPI-Key",
    // "ed44b06a38msh7815dcf021c9555p17f24fjsn4c521268a96b")
    // .header("X-RapidAPI-Host", "chatgpt-best-price.p.rapidapi.com")
    // .method("POST", HttpRequest.BodyPublishers.ofString(p))
    // .build();
    // HttpResponse<String> response = HttpClient.newHttpClient().send(request,
    // HttpResponse.BodyHandlers.ofString());

    // // source for this
    // https://jenkov.com/tutorials/java-json/jackson-objectmapper.html#convert-jsonnode-to-object
    // // ObjectMapper om = new ObjectMapper();
    // // JsonNode js = om.readTree(response);
    // // JsonNode content = js.get("content");

    // // Assume jsonString contains the JSON string you want to parse
    // JsonElement element = JsonParser.parseString(response);
    // JsonObject jsonObject = element.getAsJsonObject();

    // // Access the content array
    // JsonArray contentArray = jsonObject.getAsJsonArray("content");

    // System.out.println(content);
    // res = response;
    // } catch(Exception e){
    // System.out.println(e);
    // }
    // return res;
    // }

}

// class World {
// public World(int initWidth, int initHeight) {
// //
// }

// }

public class Trivia extends JPanel implements KeyListener {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;

    static JFrame frame;
    static Question q;

    static Graphics2D g2;

    public BufferedImage board;

    Node<String> qsnNode;

    ArrayList<String> catList;

    int yPos;

    char resAns;

    int resQsn;

    boolean catsDisplayed = false;
    boolean qsnDisplayed = false;
    boolean chDisplayed = false;
    boolean ansDisplayed = false;

    public void display(Graphics g, String toDisplay, int x, int y) {
        this.removeAll();
        g2 = (Graphics2D) g;
        // System.out.println("Here thooooo in display");
        g2.setFont(new Font("Comic Sans", Font.BOLD, 30));
        g2.setColor(Color.WHITE);

        // display question
        g2.drawString(toDisplay, x, y);
    }

    // Show categories
    public void displayCats(Graphics g) {
        yPos = 70;
        // Show categories
        display(g, "Choose a Category", 350, 40);
        for (String cat : catList) {
            display(g, cat, 350, yPos);
            yPos += 30;
        }
        // validate();
        frame.getContentPane().removeAll();
        // catsDisplayed = true;
    }

    public void displayQuestion(Graphics g) {
        // this.removeAll();
        // update(g);
        // repaint();
        this.qsnNode = q.getQuestionByCategory(resQsn);
        display(g, "Choose right answer", 200, 250);
        display(g, qsnNode.data, 100, 280);
        // qsnDisplayed = true;
    }

    public void displayChoices(Graphics g) {
        String[] choices = q.getChoices(qsnNode);
        yPos = 320;
        for (int i = 0; i < choices.length; i++) {
            display(g, choices[i], 350, yPos);
            yPos += 30;
        }
        // chDisplayed = true;
    }

    public void displayResult(Graphics g) {
        if (q.checkAnswer(resAns)) {
            display(g, "Well Done!!!", 500, 500);
            // redirect to Game Frame with triviaWon == true -- give hint;
            // setVisible(false);
            return;

        } else if (resAns == 'b' || resAns == 'c' || resAns == 'd') {
            display(g, "Wrong answer!!", 500, 500);
            // redirect to Game Frame with triviaWon == false;
            return;
        }
        ansDisplayed = true;
    }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);

        switch (c) {
            case 'a':
                resQsn = 0;
                break;
            case 'b':
                resQsn = 1;
                break;
            case 'c':
                resQsn = 2;
                break;
            case 'd':
                resQsn = 3;
                break;
            case '1':
                resAns = 'a';
                break;
            case '2':
                resAns = 'b';
                break;
            case '3':
                resAns = 'c';
                break;
            case '4':
                resAns = 'd';
        }
        repaint();
        // removeAll();
    }

    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    }

    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public Trivia() {
        addKeyListener(this);
        // frame = new JFrame("Game!!!");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.darkGray);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        // BufferedImage image = new BufferedImage(500, 500,
        // BufferedImage.TYPE_INT_ARGB);
        // g2 = (Graphics2D) image.createGraphics();

        q = new Question(null, null);
        q.getQuestions();
        this.catList = q.getCategories();
        // Thread mainThread = new Thread(new Runner());
        // mainThread.start();
        // frame.pack();
        // frame.setVisible(true);
    }

    // class Runner implements Runnable {
    // public void run() {
    // while (true) {
    // // world.updateBall(1.0 / (double) FPS);
    // validate();
    // repaint();
    // try {
    // Thread.sleep(1000 / FPS);
    // } catch (InterruptedException e) {
    // }
    // }
    // }
    // }

    public static void main(String[] args) {
        frame = new JFrame("Game!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Trivia mainInstance = new Trivia();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }

    public void getImage() {
        try {
            this.board = ImageIO.read(new File("sprites/menuboard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawBoard(Graphics g) {
        System.out.println("in drawboard");
        Graphics2D g2 = (Graphics2D) g;
        getImage();
        g2.drawImage(board, 100, 120, 700, 500, null);
        g2.setFont(new Font("Apple Chancery", Font.BOLD, 50));
        g2.setColor(Color.RED);

        // display question
        g2.drawString("Question", 250, 180);
        g2.setColor(Color.black);

        // display the choices
        g2.setFont(new Font("Apple Chancery", Font.BOLD, 30));
        g2.drawString("Geography", 350, 260);
        g2.drawString("Science", 350, 320);
        g2.drawString("Movies", 350, 380);
        g2.drawString("Celebrities", 350, 440);
        g2.drawString("Help", 350, 500);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // System.out.println("in here");
        if (catsDisplayed == false) {
            displayCats(g);
            // catsDisplayed = true;
            // qsnDisplayed = false;
        }
        if (qsnDisplayed == false) {
            displayQuestion(g);
            displayChoices(g);
            // qsnDisplayed = true;
            // ansDisplayed = false;
        }
        // if (chDisplayed == false) {

        // }
        // if (ansDisplayed == false) {
        displayResult(g);
        // ansDisplayed = true;
        // }
        // g2 = (Graphics2D) g;
        // drawBoard(g);
        // display("Welcome To Trivia", 350, 280);
    }
}