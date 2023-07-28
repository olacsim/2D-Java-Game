package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bushes extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Bushes(GamePanel gp) {
		this.gp = gp;
		
		name = "Bush";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Trees/Tree4.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		// change to change size of interaction/collision area
		solidArea.setBounds(0, 0, image.getWidth(), image.getHeight());
		collision = true;
	}
}