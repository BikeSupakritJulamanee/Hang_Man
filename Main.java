import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public abstract class Main{
    public static void main(String[] args)throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new MyFrame();
        try (Scanner scanner = new Scanner(System.in)) {
            File file = new File("All-I-Am-Dyalla.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            scanner.next();
        } 
    }
}