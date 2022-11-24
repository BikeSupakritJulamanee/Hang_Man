import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements ActionListener {
    int ufo1X = 20;
    int ufo2X = 600;
    int AYpos = 80;
    int logo_1_posx = 0;
    int logo_swap = 0;
    Image logo1;
    Image logo2;
    Image backgroundImage;
    Image h0;
    Image h1;
    Image h2;
    Image h3;
    Image h4;
    Image ufo1;
    Image ufo2;
    ArrayList<JButton> button = new ArrayList<JButton>();
    ArrayList<Image> hangman = new ArrayList<Image>();
    ArrayList<Image> logo = new ArrayList<Image>();
    Mylabel_Word my_word = new Mylabel_Word();
    Listener lisa = new Listener();
    JLabel hint = new JLabel("Hint : "+my_word.hint.get(my_word.array_pos));
    Thread timer;

    public MyPanel(){
        super();
        setBounds(0, 0, 1000, 600);
        setFocusable(true);
        setLayout(null);
        setBackground(Color.BLACK);

        timer = new Thread(new Runnable() {
            @Override
            public void run(){
                while(true){
                    try {
                        repaint();
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                }
            }
        });
        timer.start();

        backgroundImage = new ImageIcon("space.png").getImage();
        logo1 = new ImageIcon("logo1.png").getImage();
        logo2 = new ImageIcon("logo2.png").getImage();
        logo.add(logo1);
        logo.add(logo2);
        backgroundImage = new ImageIcon("space.png").getImage();
        h0 = new ImageIcon("h0.png").getImage();
        h1 = new ImageIcon("h1.png").getImage();
        h2 = new ImageIcon("h2.png").getImage();
        h3 = new ImageIcon("h3.png").getImage();
        h4 = new ImageIcon("h4.png").getImage();
        hangman.add(h0);
        hangman.add(h1);
        hangman.add(h2);
        hangman.add(h3);
        hangman.add(h4);
        ufo1 = new ImageIcon("ufo1.png").getImage();
        ufo2 = new ImageIcon("ufo2.png").getImage();

        create_button();
        
        add(my_word);

        JLabel ques = new JLabel("What is the Wold?");
        ques.setLocation(30, 280);
        ques.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        ques.setSize(1000, 80);
        ques.setForeground(Color.WHITE);
        add(ques);

        hint.setLocation(440, 330);
        hint.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        hint.setSize(1000, 80);
        hint.setForeground(Color.YELLOW);
        add(hint);

        repaint();
    }

    public void create_button(){ //create button
        int p_x = 5;
        int p_y = 450;
        int idx = 0;
        for (char key = 'A'; key != 'N'; key++) {
            String tmp = "" + key;
            button.add(new JButton(tmp));
            button.get(idx).setBounds(50, 100, 75, 50);
            button.get(idx).setLocation(p_x, p_y);
            button.get(idx).addActionListener(lisa);
            button.get(idx).setBackground(Color.BLACK);
            button.get(idx).setForeground(Color.WHITE);
            button.get(idx).setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
            add(button.get(idx));
            p_x += 75;
            idx++;
        }

        p_x = 5;
        p_y = 500;
        for (char key = 'N'; key != 'Z' + 1; key++) {
            String tmp = "" + key;
            button.add(new JButton(tmp));
            button.get(idx).setBounds(50, 100, 75, 50);
            button.get(idx).setLocation(p_x, p_y);
            button.get(idx).addActionListener(lisa);
            button.get(idx).setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
            button.get(idx).setBackground(Color.BLACK);
            button.get(idx).setForeground(Color.WHITE);
            add(button.get(idx));
            p_x += 75;
            idx++;
        }
    }

    public void paint(Graphics g) { // paint image
        super.paint(g); 
        Graphics2D g2D = (Graphics2D) g;
        if(logo_swap==2){logo_swap=0;}
        g2D.drawImage(backgroundImage, 0, 0, null);
        g2D.drawImage(logo.get(logo_swap), 200, 0, null);
        g2D.drawImage(ufo1, ufo1X, 80, null);
        g2D.drawImage(ufo2, ufo2X, 180, null);
        g2D.drawImage(hangman.get(my_word.hangman_index), 650, 20, null);
        logo_swap++;
        ufo1X+=10;
        ufo2X-=10;
        if(ufo1X == 600){ufo1X = 20;}
        if(ufo2X == 0){ufo2X = 600;}
    }

    @Override // create animation
    public void actionPerformed(ActionEvent e) {
        if(logo_1_posx<=700){
            logo_1_posx+=5;
        }
        else{
            logo_1_posx-=5;
        }
        repaint();
    }

    class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (char i = 'A'; i <= 'Z'; i++) {
                if (e.getActionCommand().equals("" + i)) {
                    int tmp = i - 65;
                    button.get(tmp).setEnabled(false);
                    my_word.check_key(i,button,new MyPanel(),hint);
                }
            }
        }
    }
}
