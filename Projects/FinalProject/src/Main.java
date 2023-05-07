import javax.swing.JFrame;
import java.awt.*;

public class Main extends JFrame{
    public static void main(String[] args){
       JFrame mainframe = new JFrame();
       mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       mainframe.setResizable(false);
       mainframe.setTitle("Final Project");
//       mainframe.setLayout(new GridLayout(1, 2)); // One row, two columns
       GamePanel1 panel = new GamePanel1();
//       Trivia1 triviaPanel = new Trivia1();
       mainframe.add(panel);
//       mainframe.add(triviaPanel);
       mainframe.pack();
       mainframe.setLocationRelativeTo(null);
       mainframe.setVisible(true);
    }
}
