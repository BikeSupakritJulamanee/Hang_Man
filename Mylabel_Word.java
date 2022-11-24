import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Mylabel_Word extends JLabel implements Action {
    private ArrayList<String> animal = new ArrayList<String>(Arrays.asList("ZEBRA","APPLE", "HOUSE", "RUN" ,"MUSEAM", "BANANA", "GUN", "LION","MOUNTAIN","PIZZA","FOREST"));
    public ArrayList<String> hint = new ArrayList<String>(Arrays.asList("ANIMAL","FRUIT", "PLACE", "VERB" ,"PLACE", "FRUIT" ,"THING", "ANIMAL","PLACE","FOOD","PLACE"));
    protected Random rd = new Random();
    static String s = "";
    static int count = 0;
    int hangman_index = 0;
    int array_pos = rd.nextInt(animal.size());

    public Mylabel_Word() {
        int n = animal.get(array_pos).length();
        for (int i = 0; i < n; i++) {
            s += "_ ";
        }
        setText(s);
        setLocation(50, 350);
        setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        setSize(1000, 80);
        setForeground(Color.WHITE);
    }

    @Override 
    public int random_word(){
        int array_pos = rd.nextInt(animal.size());
        return array_pos;
    }

    @Override
    public void call_word() {
        int n = animal.get(array_pos).length();
        for (int i = 0; i < n; i++) {
            s += "_ ";
        }
        setText(s);
        setLocation(50, 350);
        setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        setSize(1000, 80);
        setForeground(Color.WHITE);
        repaint();
    }

    @Override
    public void check_key(char key, ArrayList<JButton> button, MyPanel p,JLabel h) {
        String tmp = "";
        int status = 0;
        for (int i = 0; i < animal.get(array_pos).length(); i++) {
            if (key == animal.get(array_pos).charAt(i)) {
                tmp += key;
                tmp += ' ';
                count++;
                status = 1;
                
            } else {
                tmp += s.charAt(i * 2);
                tmp += ' ';
            }
        }
        if(status == 0){hangman_index++;};
        if(hangman_index == 4){JOptionPane.showMessageDialog(p, "You Lose!!");System.exit(0);}
        
        setText(tmp);
        s = tmp;
        if (count == animal.get(array_pos).length()) {
            JOptionPane.showMessageDialog(p, "You win,Go to next round!!");
            remove(this);
            repaint();
            animal.remove(array_pos);
            hint.remove(array_pos);
            s = "";
            for (int i = 0; i < 26; i++) {
                button.get(i).setEnabled(true);
            }
            array_pos = rd.nextInt(animal.size());
            call_word();
            count = 0;
            hangman_index = 0;
            h.setText("Hint : "+hint.get(array_pos));
            repaint();
        }
    }
}
