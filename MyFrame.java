import javax.swing.*;

public class MyFrame extends JFrame {
    MyFrame(){
        super("HANG____ _ _ _M_ ________MAN");
        JPanel panel = new MyPanel();
        add(panel);
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300,200);
        setVisible(true);
        getContentPane().setLayout(null);
        repaint();
    }
}
