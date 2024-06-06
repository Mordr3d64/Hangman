package v3hangman;

import acm.util.MediaTools;

import java.applet.AudioClip;

public class Sound {
    AudioClip dead = MediaTools.loadAudioClip("assets/dead.mp3");
    AudioClip glitch = MediaTools.loadAudioClip("assets/glitch.mp3");
    AudioClip bgm = MediaTools.loadAudioClip("assets/bgm.mp3");


    public void playDead() {
        dead.play();
    }
    public void stopDead() {
        dead.stop();
    }
    public void playGlitch() {
        glitch.play();
    }
    public void playBgm() {
        bgm.play();
    }
    public void stopBgm() {
        bgm.stop();
    }
}
