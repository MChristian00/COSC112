// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.JPanel;
import javax.swing.JFrame;
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

        String[] historyAnswers = {
            "George Washington",
            "1939",
            "Egyptian civilization"
        };
        String[] geographyAnswers = {
            "Brazil",
            "Seine",
            "Vatican City"
        };
        String[] scienceAnswers = {
            "Cell",
            "Nuclear energy",
            "Water"
        };

        Node<String> root = new Node<>("Root");
        Node<String> his = new Node<>("History");
        Node<String> geo = new Node<>("Geography");
        Node<String> sci = new Node<>("Science");

        for(int j=0; j<3; j++){
            Node<String> hisNode= new Node<>(historyQuestions[j]);
            Node<String> geoNode= new Node<>(geographyQuestions[j]);
            Node<String> sciNode= new Node<>(scienceQuestions[j]);

            for (int i=0; i<3; i++){
                Node<String> hisLeaf= new Node<>(historyAnswers[i]);
                Node<String> geoLeaf= new Node<>(geographyAnswers[i]);
                Node<String> sciLeaf= new Node<>(scienceAnswers[i]);

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
        
        System.out.println(root);

        // for(int i=0; i<3; i++){
        //     for(int j=0; j<3; j++){
        //         String catTitle = categories.get(i);
        //         if(i == 0)
        //             questions[i][j] = historyQuestions[j];
        //         if(i == 1)
        //             questions[i][j] = geographyQuestions[j];
        //         if(i == 2)
        //             questions[i][j] = scienceQuestions[j];
        //     }
        // }

    }

    public void getCategories(){
        this.categories.add("History");
        this.categories.add("Geography");
        this.categories.add("Science");
        // this.categories.add("Sports");
        // this.categories.add("Technology");
    }

    public String getQuestionByCategory(String category){
        Random rand = new Random(1);
        int randInd = rand.nextInt(3);
        int catInd = categories.indexOf(category);
        Node<String> catNode = root.children.get(catInd);
        Node<String> qsNode = catNode.children.get(randInd);
        System.out.println(qsNode.data);
        return qsNode.data;
        }

    

    public void checkAnswer(){

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
        int height;
        int width;
    
        Ball ball;
        int numPaddles = 2;
        Paddle paddles[];
        Pair paddlePos[] = new Pair[2];
    
        public World(int initWidth, int initHeight) {
            // 
        }

    }

public class Main extends JPanel implements KeyListener {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);

        switch (c) {
            case 'a':
                break;
            case 'b':
                break;
            case 'c':
                break;
            case 'd':
                break;
            case 'q':
                // this.quickSave();
                break;
            case 'l':
                // this.quickLoad();
        }
    }

    public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
    }
    public void keyReleased(KeyEvent e){
        char c = e.getKeyChar();
    }

    public Main(){
            // world = new World(WIDTH, HEIGHT);
            addKeyListener(this);
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            // Thread mainThread = new Thread(new Runner());
            // mainThread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pong mainInstance = new Pong();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);

        Question q = new Question(null, null);
        q.getCategories();
        q.getQuestions();

    }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
    
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
    
            // world.drawPaddles(g);
            // world.drawBall(g);
    
        }
}