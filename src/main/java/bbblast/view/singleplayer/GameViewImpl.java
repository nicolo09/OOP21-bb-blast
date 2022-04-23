package bbblast.view.singleplayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

import javafx.scene.Scene;

public abstract class GameViewImpl implements GameView{
    
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    private static final Path PATH = Path
            .of(System.getProperty("user.home") + SEPARATOR + ".bbblast-test" + SEPARATOR + "music.mp3");
    
    private SingleplayerGameViewController controller;
    final AudioFilePlayer player = new AudioFilePlayer();
    
    public GameViewImpl() {
        
    }

    @Override
    public void playMusic() {
        // TODO Auto-generated method stub
        public void play(String filePath) {
            final File file = new File(filePath);
     
            try (final AudioInputStream in = getAudioInputStream(file)) {
                 
                final AudioFormat outFormat = getOutFormat(in.getFormat());
                final Info info = new Info(SourceDataLine.class, outFormat);
     
                try (final SourceDataLine line =
                         (SourceDataLine) AudioSystem.getLine(info)) {
     
                    if (line != null) {
                        line.open(outFormat);
                        line.start();
                        stream(getAudioInputStream(outFormat, in), line);
                        line.drain();
                        line.stop();
                    }
                }
     
            } catch (UnsupportedAudioFileException 
                   | LineUnavailableException 
                   | IOException e) {
                throw new IllegalStateException(e);
            }
        }
        
    }

    @Override
    public void pauseMusic() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playSoundEffect(Sound e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setController(SingleplayerGameViewController controller) {
        this.controller = controller;
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Scene getScene() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
