/*
 * This is a 2D Adventure game
 * Created by Michael Olacsi
 * Uses Piskel and itch.io for images, and YouTube for music
 */

/*
 * THIS CLASS: Creates a new JFrame window and adds a GamePanel to it. 
 * Sets various properties of the window, such as title and resizeability. 
 * Initializes the game and starts the game thread to update and render the game.
 */

package main;

//Import necessary packages
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

public class Main {
	//Exception  in main method can be thrown when attempting to create a new instance of the Sound class if  there are no available output lines on which
	//to play the requested sound, such as if too many sound files are already playing or the audio output hardware is otherwise occupied.
    public static void main(String[] args) throws LineUnavailableException {
        // Create a new JFrame window
        JFrame window = new JFrame();

        // Set the close operation when the user clicks the window's close button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Allow the user to resize the window
        window.setResizable(true);

        // Set the title of the window
        window.setTitle("Adventure");

        // Create a new game panel and add it to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // Pack the window to fit the size of the game panel and center it on the screen
        window.pack();
        window.setLocationRelativeTo(null);

        // Display the window
        window.setVisible(true);

        // Call the setupGame method in the game panel to initialize the game
        gamePanel.setupGame();

        // Start the game thread to update and render the game
        gamePanel.startGameThread();
    }
}