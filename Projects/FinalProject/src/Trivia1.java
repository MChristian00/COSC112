import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.net.URI;
import java.net.http.HttpClient;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class Node1<T> {
    T data;
    ArrayList<Node1<T>> children;

    public Node1(T data) {
        this.data = data;
        children = new ArrayList<Node1<T>>();
    }

    public void addChild(Node1<T> child) {
        children.add(child);
    }

    public void removeChild(int ind){
        children.remove(ind);
    }
}

class Question1 {
    static Node1<String> root = new Node1<>("Root");
    final static String API_URL = "https://opentdb.com/api.php?";
    static Node1<String> catNode;
    static Node1<String> ansLeaf;
    static String qsn;
    Node1<String> qsNode;
    static String correctAns;
    static JSONArray incAns;
    static JSONObject dataList;
    ArrayList<String> catList;
    ArrayList<Integer> considered;
    static final int numberOfQuestions = 10;
    boolean questionsRetrieved = false;

    // List of categories by ID needed for the API URL
    ArrayList<String> categoriesByID = new ArrayList<String>(){{
        add("23"); // history id
        add("22"); // geo id
        add("17"); // science id
        add("18");
    }};
    ArrayList<String> categoriesByTitle = new ArrayList<String>(){{
        add("History");
        add("Geography");
        add("Science");
        add("Computers");
    }};

    public Question1() {
        // Assign question to appropriate category
        for(int i=0 ; i<categoriesByID.size(); i++){
            catNode = new Node1<>(categoriesByTitle.get(i));
            getQuestions(categoriesByID.get(i));
            root.addChild(catNode);
        }
    }
    public static void addToTree(String catQsn, ArrayList<String> qsnAns){
        Node1<String> qsnNode = new Node1<>(catQsn);
        for(String choice: qsnAns){
            ansLeaf = new Node1<>(choice);
            qsnNode.addChild(ansLeaf);
        }
        catNode.addChild(qsnNode);
    }
    public static void getQuestions(String category){
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "amount="+ numberOfQuestions +"&category="+ category +"&difficulty=medium&type=multiple"))
                .build();
        HttpResponse<String> res = null;
        try {
            res = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String r = res.body();
        System.out.println(r);
        JSONParser parser = new JSONParser();
        JSONObject jObj = new JSONObject();
        try{
            jObj = (JSONObject) parser.parse(r);
        } catch (ParseException e){
            e.printStackTrace();
        }

        JSONArray data = (JSONArray) jObj.get("results");
        for(int i=0; i< data.size(); i++){
            dataList = (JSONObject) data.get(i);
            qsn = (String) dataList.get("question");
            correctAns = (String) dataList.get("correct_answer");
            incAns = (JSONArray) dataList.get("incorrect_answers");
            incAns.add(0,correctAns);
            addToTree(qsn,incAns);
        }

    }

    public ArrayList<String> getCategories() {
        catList = new ArrayList<>();
        for (Node1<String> cat : root.children) {
            catList.add(cat.data.toString());
        }
        return catList;
    }

    public Node1<String> getQuestionByCategory(int categoryInd) {
        Random rand = new Random();
        Node1<String> catNode;
        catNode = root.children.get(categoryInd);
        int randInd = rand.nextInt(catNode.children.size());
        qsNode = catNode.children.get(randInd);
        catNode.removeChild(randInd);
        System.out.println(catNode.children.size());
//        }
        return qsNode;
    }
    public ArrayList<String> getChoices(Node1<String> qsNode) {
        ArrayList<String> choices = new ArrayList<>();
            for(Node1<String> choice: qsNode.children){
                choices.add(choice.data);
            }
        // store correct answer and shuffle the choices
        correctAns = choices.get(0);
        Collections.shuffle(choices, new Random());
        return choices;
    }

    public boolean checkAnswer(String choice) {
        // return the answer to the hint caller method to handle loss or hint
        return choice == correctAns;
    }
}
public class Trivia1 extends JPanel implements KeyListener{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;

    static JFrame frame;
    static JPanel triviaPanel;
    static Question1 q;

    static Graphics2D g2;

    public BufferedImage board;

    Node1<String> qsnNode;

    ArrayList<String> catList;
    private JTree tree;

    int yPos;

    int resAns;

    int catInd;
    ArrayList<String> choices;

    boolean chooseCat = false;
    boolean qsnDisplay = false;
    boolean answered = false;

    public void display(Graphics g, String toDisplay, int x, int y) {
        g2 = (Graphics2D) g;
        g2.setFont(new Font("Comic Sans", Font.BOLD, 15));
        g2.setColor(Color.WHITE);
        g2.drawString(toDisplay, x, y);
    }

    // Show categories
    public void displayCats(Graphics g) {
        catList = q.getCategories();
        yPos = 130;
        display(g, "Choose a Category", 300, 100);
        int charCode = 65;
        for (String cat : catList) {
            display(g, (char )charCode + ". " +cat, 320, yPos);
            yPos += 30;
            charCode += 1;
        }
//        catList = new ArrayList<>();
    }

    public void displayQuestion(Graphics g) {
        this.qsnNode = q.getQuestionByCategory(catInd);
        display(g, "Choose right answer", 300, 250);
        System.out.println("in disp qsn - qsn" + qsnNode.data);
        display(g, qsnNode.data, 300, 280);
        displayChoices(g);
        // qsnDisplayed = true;
    }

    public void displayChoices(Graphics g) {
        choices = q.getChoices(qsnNode);
        yPos = 320;
        for (int i = 0; i < choices.size(); i++) {
            display(g, (i +1) + ". "+ choices.get(i), 330, yPos);
            yPos += 30;
        }
    }

    public void displayResult(Graphics g) {
        if (q.checkAnswer(choices.get(resAns))) {
            display(g, "Well Done!!!", 300, 500);
            // redirect to Game Frame with triviaWon == true -- give hint;
            // setVisible(false);
            GamePanel1.trueAns = true;
            GamePanel1.pointsCount += 100;
        } else{
            display(g, "Wrong answer!!", 300, 500);
            // redirect to Game Frame with triviaWon == false;
//            this.getTopLevelAncestor().setVisible(true);
            GamePanel1.trueAns = false;
            GamePanel1.playerLives--;
        }
    //    disposePanel();
    }

    public void displayTriviaPanel(Graphics g){
        System.out.println("in disp panel");

        if (!chooseCat) {
            displayCats(g);
//            catsDisplayed = true;
//            qsnDisplayed = true;
        }
        if (qsnDisplay) {
            displayQuestion(g);
//            qsnDisplayed = true;
//            chDisplayed = true;
        }
         if (answered) {
            displayResult(g);
        // ansDisplayed = true;
         }
    }

    public void disposePanel(){
        frame.dispose();
    }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);

        switch (c) {
            case 'a':
                catInd = 0;
                chooseCat = true;
                qsnDisplay = true;
                break;
            case 'b':
                catInd = 1;
                chooseCat = true;
                qsnDisplay = true;

                break;
            case 'c':
                catInd = 2;
                chooseCat = true;
                qsnDisplay = true;

                break;
            case 'd':
                catInd = 3;
                chooseCat = true;
                qsnDisplay = true;

                break;
            case '1':
                resAns = 0;
                answered = true;
                qsnDisplay = false;
                break;
            case '2':
                resAns = 1;
                answered = true;
                qsnDisplay = false;

                break;
            case '3':
                resAns = 2;
                answered = true;
                qsnDisplay = false;

                break;
            case '4':
                resAns = 3;
                answered = true;
                qsnDisplay = false;

        }

        repaint();
        // removeAll();
    }

    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();

    }

    public void keyReleased(KeyEvent e) {
//        char c = e.getKeyChar();
//        switch (c) {
//            case 'a':
//                catInd = 0;
//                chooseCat = false;
//                qsnDisplay = false;
//                break;
//            case 'b':
//                catInd = 1;
//                chooseCat = false;
//                qsnDisplay = false;
//
//                break;
//            case 'c':
//                catInd = 2;
//                chooseCat = false;
//                qsnDisplay = false;
//
//                break;
//            case 'd':
//                catInd = 3;
//                chooseCat = false;
//                qsnDisplay = false;
//
//                break;
//            case '1':
//                resAns = 'a';
//                answered = false;
//                qsnDisplay = false;
//                break;
//            case '2':
//                resAns = 'b';
//                answered = false;
//                qsnDisplay = false;
//
//                break;
//            case '3':
//                resAns = 'c';
//                answered = false;
//                qsnDisplay = false;
//
//                break;
//            case '4':
//                resAns = 'd';
//                answered = false;
//                qsnDisplay = false;
//
//        }
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public Trivia1() {
        q = new Question1();
//        catList = new ArrayList<>();
//        JPanel jp = new JPanel();
//        jp.add(new JLabel("JFKSLJFKLSJFKLSS"));
//        this.add(jp);
//        setVisible(true);

        frame = new JFrame("Trivia!!!");
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.darkGray);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       displayTriviaPanel(g);
    }
}


/**
 * Use Collections.shuffle(options) to shuffle the choices;
 *
 * For handling next question with btn or label.setText
 *      questionLabel.setText(question.getText());
*             List<String> answerOptions = getAnswerOptions(question);
*             for (int i = 0; i < answerButtons.size(); i++) {
*                 answerButtons.get(i).setText(answerOptions.get(i));
*             }
 *
 *
 *             String[] parts = questionStr.split("\\|");
 * // String text = parts[0];
 * // String correctAnswer = parts[1];
 * // List<String> incorrectAnswers = Arrays.asList(parts).subList(2,
 * // parts.length);return new Question(text,correctAnswer,incorrectAnswers);
 * // }
 */