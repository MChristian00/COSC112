import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

// import java.io.InputStream;
// import java.net.HttpURLConnection;
// import java.net.URI;
// import java.net.URL;
// import java.net.http.HttpClient;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Random;
// import com.fasterxml.jackson.databind.ObjectMapper;


class Node<T>{
    T data;
    ArrayList<Node<T>> children;
    public Node(T data){
        this.data = data;
        children = new ArrayList<Node<T>>();
    }

    public void addChild(Node<T> child){
        children.add(child); 
    }
}

class Question{
    Node<String> root;
    ArrayList<String> categories = new ArrayList<>();
    String[][] questions = new String[3][3];
    public Question(String category, String question){
        // Assign question to appropriate category
        if(category != null && question != null){
            int catInd = categories.indexOf(category);
            int lastInd = questions.length;
            questions[catInd][lastInd+1] = question;
        }
    }

    public void getQuestions(){
        // String req = "Give me an array of 5 different trivia categories in an array format";
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
            {"George Washington", "John Adams", "Thomas Jefferson", "James Madison"},
            {"1939", "1941", "1943", "1945"},
            {"Ancient Egyptians", "Mayans", "Aztecs", "Incas"}
        };
        
        String[][] geographyAnswers = {
            {"Brazil", "Argentina", "Colombia", "Peru"},
            {"Seine", "Thames", "Danube", "Rhine"},
            {"Vatican City", "Monaco", "Nauru", "Tuvalu"}
        };
        
        String[][] scienceAnswers = {
            {"Cell", "Atom", "Molecule", "Organelle"},
            {"Nuclear energy", "Thermal energy", "Radiant energy", "Kinetic energy"},
            {"Water", "Mercury", "Carbon dioxide", "Helium"}
        };

        root = new Node<>("Root");
        Node<String> his = new Node<>("History");
        Node<String> geo = new Node<>("Geography");
        Node<String> sci = new Node<>("Science");

        for(int j=0; j<3; j++){
            Node<String> hisNode= new Node<>(historyQuestions[j]);
            Node<String> geoNode= new Node<>(geographyQuestions[j]);
            Node<String> sciNode= new Node<>(scienceQuestions[j]);

            for (int i=0; i<4; i++){
                Node<String> hisLeaf= new Node<>(historyAnswers[j][i]);
                Node<String> geoLeaf= new Node<>(geographyAnswers[j][i]);
                Node<String> sciLeaf= new Node<>(scienceAnswers[j][i]);

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

    public void getCategories(){
        // this.categories.add("History");
        // this.categories.add("Geography");
        // this.categories.add("Science");
        // this.categories.add("Sports");
        // this.categories.add("Technology");
    }

    public Node<String> getQuestionByCategory(int categoryInd){
        Random rand = new Random();
        int randInd = rand.nextInt(3);
        Node<String> catNode = root.children.get(categoryInd);
        Node<String> qsNode = catNode.children.get(randInd);
        return qsNode;
        }

    public String[] getChoices(Node<String> qsNode){
        String[] choices = new String[4];
        for(int i = 0; i< 4; i++){
            choices[i] = qsNode.children.get(i).data;
        }
        return choices;
    }

    

    public boolean checkAnswer(int choiceInd){
        // return the answer to the hint caller method to handle loss or hint
        return choiceInd == 0;
    }

        // public HttpResponse<String> makeRequest(String req){
        //     HttpResponse<String> res = null;
        //     String p = "{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"user\",\"content\": \""+ req +"\"}]}";
        //     try{
        //         HttpRequest request = HttpRequest.newBuilder()
        //         .uri(URI.create("https://chatgpt-best-price.p.rapidapi.com/v1/chat/completions"))
        //         .header("content-type", "application/json")
        //         .header("X-RapidAPI-Key", "ed44b06a38msh7815dcf021c9555p17f24fjsn4c521268a96b")
        //         .header("X-RapidAPI-Host", "chatgpt-best-price.p.rapidapi.com")
        //         .method("POST", HttpRequest.BodyPublishers.ofString(p))
        //         .build();
        // HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        
        // // source for this https://jenkov.com/tutorials/java-json/jackson-objectmapper.html#convert-jsonnode-to-object
        // // ObjectMapper om = new ObjectMapper();
        // // JsonNode js = om.readTree(response);
        // // JsonNode content = js.get("content");
        
        // // Assume jsonString contains the JSON string you want to parse
        // JsonElement element = JsonParser.parseString(response);
        // JsonObject jsonObject = element.getAsJsonObject();
        
        // // Access the content array
        // JsonArray contentArray = jsonObject.getAsJsonArray("content");
    
        // System.out.println(content);
        //         res = response;
        //     } catch(Exception e){
        //         System.out.println(e);
        //     }
        //     return res;
        // }

    }

    class World {
        public World(int initWidth, int initHeight) {
            // 
        }

    }

public class Main extends JPanel implements KeyListener {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    World world;
    JFrame frame;
    Question q = new Question(null, null);
    Font font = new Font("Arial", Font.BOLD, 24);

    public void display(JFrame frame, String toDisplay){
        this.frame = frame;
        JLabel label = new JLabel(toDisplay);
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);
        frame.add(label);
        System.out.println(toDisplay);
        frame.repaint();
    }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);

        int res = 0;
        switch (c) {
            case 'a':
                res = 0;
                break;
            case 'b':
                res = 1;
                break;
            case 'c':
                res = 2;
                break;
            case 'd':
                res = 3;
                break;
            case 'q':
                // this.quickSave();
                break;
            case 'l':
                // this.quickLoad();
        }

        // if(res==0){
            // JLabel label = new JLabel("Well Done!!");
            q.getCategories();
            q.getQuestions();
            Node<String> qsnNode = q.getQuestionByCategory(res);
            display(frame, qsnNode.data);
            String[] choices = q.getChoices(qsnNode);
            for(String choice : choices){
                display(frame, choice);
            }
            // display(frame, "Choose the correct answer");
            // boolean isCorrect = q.checkAnswer(res);
            // System.out.println(isCorrect);

        // }
    }

    public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
    }
    public void keyReleased(KeyEvent e){
        char c = e.getKeyChar();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public Main(){
            world = new World(WIDTH, HEIGHT);
            addKeyListener(this);
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            // Thread mainThread = new Thread(new Runner());
            // mainThread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main mainInstance = new Main();
        frame.setContentPane(mainInstance);
        mainInstance.display(frame, "<html>Choose a category<br/> A. History<br/> B. Geography<br/> C. Science<html><br/>");
        frame.pack();
        frame.setVisible(true);

    }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
    
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
    
            // world.drawPaddles(g);
            // world.drawBall(g);
    
        }
}