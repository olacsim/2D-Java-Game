package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Open_Chest extends SuperObject{
	
	GamePanel gp;

	public OBJ_Open_Chest(GamePanel gp) {
		this.gp = gp;
		
		name = "Open_Chest";
		try {
			// Load image from file
			image = ImageIO.read(getClass().getResourceAsStream("/Props/O_Chest.png"));
			// Scale the image to tileSize
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			// Print error message if image could not be loaded
			e.printStackTrace();
		}
		solidArea.setBounds(0, 0, image.getWidth(), image.getHeight());
		collision = true;
	}
}
