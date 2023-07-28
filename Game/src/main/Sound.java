//Defines a Sound class that can be used to load, play, loop, and stop music and sound effects.
package main;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    // Array of URLs of sound files to be played
    private static final URL[] soundUrls = {
        Sound.class.getResource("/Overworld.wav"),
        Sound.class.getResource("/Key_Pickup.wav")
    };

    // Clip object used for playing the sound file
    private final Clip clip;

    // AudioInputStream objects for each sound file
    private final AudioInputStream[] audioStreams;

    // Index of the currently loaded sound file
    private int currentIndex = -1;

    // Constructor initializes the Clip object and AudioInputStream array
    public Sound() throws LineUnavailableException {
        clip = AudioSystem.getClip();
        audioStreams = new AudioInputStream[soundUrls.length];
    }

    // Sets the sound file to be played based on the index in the URL array
    public void setFile(int i) {
        // Only load and play if the index is different from the currently loaded file
        if (currentIndex != i) {
            currentIndex = i;
            try {
                // Load the AudioInputStream for the sound file if it hasn't been loaded already
                if (audioStreams[i] == null) {
                    audioStreams[i] = AudioSystem.getAudioInputStream(soundUrls[i]);
                }
                // Stop any previously playing sound, reset the position of the Clip, close it and load the new sound file
                clip.stop();
                clip.setFramePosition(0);
                clip.close();
                clip.open(audioStreams[i]);
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Starts playing the sound file
    public void play() {
        // Only play if the Clip isn't already running
        if (!clip.isRunning()) {
            clip.start();
        }
    }

    // Starts playing the sound file on a loop
    public void loop() {
        // Only loop if the Clip isn't already running
        if (!clip.isRunning()) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // Stops playing the sound file
    public void stop() {
        // Only stop if the Clip is running
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        currentIndex = -1;
    }
}