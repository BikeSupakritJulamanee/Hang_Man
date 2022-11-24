import java.util.ArrayList;
import javax.swing.*;

abstract interface Action{
    public abstract void call_word();
    public abstract void check_key(char key, ArrayList<JButton> button, MyPanel p,JLabel h);
    public abstract int random_word();
}
