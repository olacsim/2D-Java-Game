/*
 * This section of code sets pixel measurements of objects, as well as window size and background.
 * The FPS of the game is via the run method.
 *  Images are displayed via the paint component and update methods.
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS
    int fps = 60;
    // System

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    private final Sound music;
    private final Sound soundEffect;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI (this);

    Thread gameThread;

    //Entity And Object
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[500];

    //Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    
    public GamePanel() throws LineUnavailableException{
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        music = new Sound();
        soundEffect = new Sound();
    }

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint();
                delta --;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            music.play();
        }
        if (gameState == pauseState) {
            stopMusic();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // tile
        tileM.draw(g2);

        //object
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // player
        player.draw(g2);

        //UI
        ui.draw(g2);
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSe(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
