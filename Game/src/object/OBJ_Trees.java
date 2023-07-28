//Loads a random Tree image, scales it,  and sets up collision detection.

package object;

//Import necessary packages
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_Trees extends SuperObject {
	
	// List of image file paths for the trees
	private static final List<String> IMAGE_PATHS = List.of("/Trees/Tree0.png", "/Trees/Tree1.png", "/Trees/Tree2.png");
	
	// Scale factor for the image
	private static final double IMAGE_SCALE = 2.5;
		
	public OBJ_Trees(GamePanel gp) {
		super();
		name = "Trees";
		
		// Select a random image file path from the list
		String selectedImagePath = IMAGE_PATHS.get(ThreadLocalRandom.current().nextInt(IMAGE_PATHS.size()));
		
		try {
			// Load the image from the selected file path
			image = ImageIO.read(getClass().getResourceAsStream(selectedImagePath));
			
			// Scale the image to the size of a tile in the game panel
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			// If the image fails to load, throw a runtime exception
			throw new RuntimeException("Failed to load image: " + selectedImagePath, e);
		}
		
		// Set the bounds of the solid area and enable collision
		solidArea.setBounds(0, 0, image.getWidth(), image.getHeight());
		collision = true;
		
		// Set the scale of the image
		setScale(IMAGE_SCALE);
	}
}
