/*
 * These lines of code contain images for the main character (getPlayerImage). 
 * Through the use of a spriteCounter, the images(spriteNum) change in sequence to produce an animation.
 * Additionally, this code sets default/starting player position and speed.
 * The players position and direction are changed via the update method.
 * The displayed character sprite is changed through the use of nested if blocks.
 */

package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	public final int screenX, screenY;
	public int hasKey = 0;
		
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 5; 
		direction = "right_idle";
	}
	
	public void getPlayerImage() {
	    // Set up image paths
	    String[] upPaths = {"/U_Run/U_Run0", "/U_Run/U_Run1", "/U_Run/U_Run2", "/U_Run/U_Run3"};
	    String[] downPaths = {"/D_Run/D_Run0", "/D_Run/D_Run1", "/D_Run/D_Run2", "/D_Run/D_Run3"};
	    String[] leftPaths = {"/L_Run/L_Run0", "/L_Run/L_Run1", "/L_Run/L_Run2", "/L_Run/L_Run3",
	                          "/L_Run/L_Run4", "/L_Run/L_Run5", "/L_Run/L_Run6", "/L_Run/L_Run7"};
	    String[] rightPaths = {"/R_Run/R_Run0", "/R_Run/R_Run1", "/R_Run/R_Run2", "/R_Run/R_Run3",
	                           "/R_Run/R_Run4", "/R_Run/R_Run5", "/R_Run/R_Run6", "/R_Run/R_Run7"};
	    String[] leftIdlePaths = {"/L_Idle/l_idle0", "/L_Idle/l_idle1", "/L_Idle/l_idle2", "/L_Idle/l_idle3",
	                              "/L_Idle/l_idle4", "/L_Idle/l_idle5", "/L_Idle/l_idle6", "/L_Idle/l_idle7"};
	    String[] rightIdlePaths = {"/R_Idle/r_idle0", "/R_Idle/r_idle1", "/R_Idle/r_idle2", "/R_Idle/r_idle3",
	                               "/R_Idle/r_idle4", "/R_Idle/r_idle5", "/R_Idle/r_idle6", "/R_Idle/r_idle7"};
	    String[] downIdlePaths = {"/D_Idle/D_Idle0"};
	    String[] upIdlePaths = {"/U_Idle/U_Idle0"};

	    // Load images
	    upImages = loadImages(upPaths);
	    downImages = loadImages(downPaths);
	    leftImages = loadImages(leftPaths);
	    rightImages = loadImages(rightPaths);
	    leftIdleImages = loadImages(leftIdlePaths);
	    rightIdleImages = loadImages(rightIdlePaths);
	    downIdleImages = loadImages(downIdlePaths);
	    upIdleImages = loadImages(upIdlePaths);
	}


	//	Loads a set of images from the specified paths and returns an array of BufferedImages containing the loaded images.
	//	@param An array of strings representing the paths to the images.
	private BufferedImage[] loadImages(String[] paths) {
	    BufferedImage[] images = new BufferedImage[paths.length];  // Create an array to store the loaded images
	    UtilityTool uTool = new UtilityTool();  // Create an instance of the UtilityTool class

	    for (int i = 0; i < paths.length; i++) {
	        try {
	        	// Read the image from the specified path and store it in the images array
	            images[i] = ImageIO.read(getClass().getResourceAsStream(paths[i] + ".png"));
	            // Scale the image to the specified tile size using the utility tool
	            images[i] = uTool.scaleImage(images[i], gp.tileSize, gp.tileSize);
	        } catch (IOException e) {
	            e.printStackTrace(); // Print the stack trace if an IOException occurs while reading the image
	        }
	    }

	    return images;
	}
	
	public void update() {
		//determines the direction of movement based on the values of keyH.xDirection and keyH.yDirection
		//The outer switch statement is based on the value of keyH.xDirection and has three cases: -1, 0, and 1.
		//For each of these cases, there is an inner switch statement based on the value of keyH.yDirection. The inner switch statement also has three cases: -1, 0, and 1.
		//To envision this, imagine a graph, where X and Y can be negative, positive, or zero
		switch (keyH.xDirection) {
	    case -1:
	        switch (keyH.yDirection) {
	            case -1:
	                direction = "up_left";
	                break;
	            case 0:
	                direction = "left";
	                break;
	            case 1:
	                direction = "down_left";
	                break;
	        }
	        break;
	    case 0:
	        switch (keyH.yDirection) {
	            case -1:
	                direction = "up";
	                break;
	            case 0:
	                switch (direction) {
	                    case "right":
	                        direction = "right_idle";
	                        break;
	                    case "down":
	                        direction = "down_idle";
	                        break;
	                    case "left":
	                        direction = "left_idle";
	                        break;
	                    case "up":
	                        direction = "up_idle";
	                        break;
	                }
	                break;
	            case 1:
	                direction = "down";
	                break;
	        }
	        break;
	    case 1:
	        switch (keyH.yDirection) {
	            case -1:
	                direction = "up_right";
	                break;
	            case 0:
	                direction = "right";
	                break;
	            case 1:
	                direction = "down_right";
	                break;
	        }
	        break;
	}
	    
			// Check Tile Collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check Object Collision
			int objIndex = gp.cChecker.checkObjectCollision(this, true);
			pickUpObject(objIndex);
			
			// if Collision = false, player can move
			if (!collisionOn) {
				switch(direction) {
				case "up": 
					worldY -= speed; 
					break;
				case "down": 
					worldY += speed; 
					break;
				case "left": 
					worldX -= speed; 
					break;
				case "right": 
					worldX += speed;
					break;
				case "up_right": 
					worldY -= speed; 
					worldX += speed; 
					break;
				case "up_left": 
					worldY -= speed; 
					worldX -= speed; 
					break;
				case "down_right": 
					worldY += speed; 
					worldX += speed; 
					break;
				case "down_left": 
					worldY += speed; 
					worldX -= speed; 
					break;
				}
			}
			
			sprite_counter++;
			if (sprite_counter > 8) {
			    sprite_num = (sprite_num % 8) + 1;
			    sprite_counter = 1;
			}
	  }

	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName){
			case "Key":
				gp.playSe(1); //use this to add sound effects, change the number to change which is used based on sound class array
				hasKey++;
                gp.obj[i] = null;
				gp.ui.showMessage(" You got a key! ");
				break;
			
			case "Closed_Chest":
				if(hasKey > 0) {
					hasKey--;
				try {
					gp.obj[i].name = "Open_Chest"; 
					gp.obj[i].image = ImageIO.read(getClass().getResourceAsStream("/Props/O_Chest.png"));
					gp.ui.showMessage ("You opened the Chest");

				} catch (IOException e) {
					e.printStackTrace();
			}
		}
				else {
					gp.ui.showMessage("You need a key");
				}
				break;
			}
//			to beat game set gp.ui.gameFinished = true;
		}
	}
	
	// This method draws a sprite image on a Graphics2D object.
	public void draw(Graphics2D g2) {
		
//		declares a BufferedImage variable named "image" that will be used to store the image to be drawn.
	    BufferedImage image;
	    
	    // Define an array of indices to select which image to use for each direction.
	    int[] modulos = {
	        (sprite_num - 1) % upImages.length,
	        (sprite_num - 1) % downImages.length,
	        (sprite_num - 1) % leftImages.length,
	        (sprite_num - 1) % rightImages.length,
	        (sprite_num  - 1) % rightIdleImages.length,
	        (sprite_num - 1) % upIdleImages.length,
	        (sprite_num  - 1) % downIdleImages.length,
	        (sprite_num  - 1) % leftIdleImages.length
	    };

	 // Create a Map object to store image arrays for each direction.
	    Map<String, BufferedImage[]> imageMap = new HashMap<>();

	 // Add images for movement directions
	    imageMap.put("up", upImages);
	    imageMap.put("down", downImages);
	    imageMap.put("left", leftImages);
	    imageMap.put("right", rightImages);

	    // Add images for diagonal movement directions, reusing existing images
	    imageMap.put("up_left", imageMap.get("left"));
	    imageMap.put("down_left", imageMap.get("left"));
	    imageMap.put("up_right", imageMap.get("right"));
	    imageMap.put("down_right", imageMap.get("right"));

	    // Add images for idle directions
	    imageMap.put("right_idle", rightIdleImages);
	    imageMap.put("up_idle", upIdleImages);
	    imageMap.put("down_idle", downIdleImages);
	    imageMap.put("left_idle", leftIdleImages);

	    BufferedImage[] images = imageMap.get(direction);
	    if (images == null) {
	        return;
	    }

	    // Retrieves the image array from "imageMap" that corresponds to the current direction of movement. 
	    // If the array is null, the method returns without drawing anything.
	    int index = 0;
	    switch (direction) {
	        case "up":
	        case "down":
	            index = modulos[0];
	            break;

	        case "left":
	        case "up_left":
	        case "down_left":
	            index = modulos[2];
	            break;

	        case "right":
	        case "up_right":
	        case "down_right":
	            index = modulos[3];
	            break;

	        case "right_idle":
	            index = modulos[4];
	            break;

	        case "up_idle":
	            index = modulos[5];
	            break;

	        case "down_idle":
	            index = modulos[6];
	            break;

	        case "left_idle":
	            index = modulos[7];
	            break;

	        default:
	            return;
	    }

	    image = images[index];
	    if (image == null) {
	        return;
	    }

	    // draws the image on the screen of the character sprite	    
	    g2.drawImage(image, screenX, screenY, null);
	}
}