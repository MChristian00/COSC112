import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TriviaPanel extends JPanel{
    public static final int originaltilesize = 16;
    public static final int scale = 3;
    public static final int tilesize = originaltilesize * scale;
    public static final int maxScreencol = 16;
    public static final int maxscreenrow = 12;
    public static final int screenwidth = 1400;
    public static final int screenheight = 800;
    JButton hintBtn;

    int hintCounts = 3;


    Thread gameThread;
    Trivia1 trivia1;

    JPanel pan;


    public TriviaPanel(){


//    this.setPreferredSize(new Dimension(100, 100));
//    this.setBackground(Color.cyan);
        this.add(new JLabel("Panel 2"));
}
}
