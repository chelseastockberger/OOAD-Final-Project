package Main;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

    Clip sound;
    File music;

    public Audio(){

       music = new File("resources/audio/music.wav");

       setAudio();
    }

    public void setAudio(){
        try{
            AudioInputStream a = AudioSystem.getAudioInputStream(music);
            sound = AudioSystem.getClip();
            sound.open(a);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        sound.start();
    }
    public void loop(){
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        sound.stop();
    }


}
