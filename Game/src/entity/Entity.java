// Importing necessary libraries
package entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// Declaring Entity class
public class Entity {
	// Declaring constant variable to be used in the class
	public static final int NUM_FRAMES = 8;

	// Declaring instance variables to be used in the class
	public int worldX, worldY, speed;
	public static BufferedImage[] upImages = new BufferedImage[NUM_FRAMES];
	public static BufferedImage[] downImages = new BufferedImage[NUM_FRAMES];
	public static BufferedImage[] leftImages = new BufferedImage[NUM_FRAMES];
	public static BufferedImage[] rightImages = new BufferedImage[NUM_FRAMES];
	public  static BufferedImage[] upIdleImages = new BufferedImage[NUM_FRAMES];
	public static BufferedImage[] downIdleImages = new BufferedImage[NUM_FRAMES];
	public  static BufferedImage[] leftIdleImages = new BufferedImage[NUM_FRAMES];
	public  static BufferedImage[] rightIdleImages = new BufferedImage[NUM_FRAMES];
	public String direction;
	public int sprite_counter = 0;
	public int sprite_num = 1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}
