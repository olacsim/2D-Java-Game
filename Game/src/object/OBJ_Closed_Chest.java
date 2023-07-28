package object;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Closed_Chest extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Closed_Chest(GamePanel gp) {
		this.gp = gp;
		
		name = "Closed_Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Props/T_Chest.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		// change to change size of interaction/collision area
		solidArea.setBounds(0, 0, image.getWidth(), image.getHeight());
		collision = true;
	}
}