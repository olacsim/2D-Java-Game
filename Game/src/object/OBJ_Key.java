package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		this.gp = gp;
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Props/Key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		solidArea.setBounds(0, 0, image.getWidth(), image.getHeight());
		collision = true;
	}

}