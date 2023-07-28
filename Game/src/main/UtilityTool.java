package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

	// Method to scale an image to the specified width and height
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		// Create a new BufferedImage with the specified width and height
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		// Get the Graphics2D object for the scaledImage
		Graphics2D g2 = scaledImage.createGraphics();
		// Draw the original image onto the scaledImage
		g2.drawImage(original, 0, 0, width, height, null);
		// Dispose of the Graphics2D object
		g2.dispose();

		// Return the scaledImage
		return scaledImage;
	}
}
