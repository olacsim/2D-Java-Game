package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

/**
 * SuperObject is the base class for all objects in the game.
 * It contains methods and fields common to all objects.
 */
public class SuperObject {

	// The image for the object
	public BufferedImage image;
	
	// The name of the object
	public String name;
	
	// Whether the object can collide with other objects
	public boolean collision = false;
	
	// The position of the object in the world
	public int worldX, worldY;
	
	// The default area of the object that can collide with other objects
	public Rectangle solidArea = new Rectangle(0,0,16,16);
	
	// The default position of the solid area
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	// The utility tool used to scale the image
	UtilityTool uTool = new UtilityTool ();
	
	// The scale of the object, defaults to 1
	public double scaleObject = 1;
	
	/**
	 * Draws the object on the screen.
	 * 
	 * @param g2 The graphics object to draw the object with.
	 * @param gp The GamePanel to draw the object in.
	 */
	public void draw(Graphics2D g2, GamePanel gp) {
		
		// Calculate the position of the object on the screen
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		// Only draw the object if it is within the screen bounds
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			// Scale the image based on the scaleObject variable
			int scaledSize = (int) (gp.tileSize * scaleObject);
			g2.drawImage(image, screenX, screenY, scaledSize, scaledSize, null);
		}
	}
	
	/**
	 * Sets the scale of the object.
	 * 
	 * @param scale The new scale of the object.
	 */
	public void setScale(double scale) {
		scaleObject = scale;
	}
}