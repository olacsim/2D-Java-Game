//Creates  and sets game objects to be displayed.

package main;

//Import necessary libraries
import java.util.HashMap;
import java.util.Map;
import object.*;

//Define a class named AssetSetter
public class AssetSetter {

	// Define a GamePanel object named gamePanel
    private final GamePanel gamePanel;
    
    // Define integer constants for indices of the game objects
    private static final int KEY_INDEX = 0;
    private static final int CLOSED_CHEST_INDEX = 1;
    private static final int[] TREE_INDICES = {2, 3, 4, 5};
    private static final int[] BUSH_INDICES = {6};
    
    // Define a 2D integer array containing positions of the game objects
    private static final int[][] OBJECT_POSITIONS = {
            {23, 7},  // key
            {23, 9},  // chest
            {23, 14}, // trees 1
            {27, 18}, // trees 2
            {19, 10}, // trees 3
            {22, 16}, // trees 4
            {29, 15}, // bush
    };
    
    // Define a Map containing the asset class names for each object index
    private final Map<Integer, Class<? extends SuperObject>> assetClasses;
    
    // Constructor for AssetSetter class
    public AssetSetter(GamePanel gamePanel) {
    	// Initialize the GamePanel object gamePanel
        this.gamePanel = gamePanel;
        
     // Initialize the asset class map
        assetClasses = new HashMap<>();
        
     // Add the key and chest asset classes to the map
        assetClasses.put(KEY_INDEX, OBJ_Key.class);
        assetClasses.put(CLOSED_CHEST_INDEX, OBJ_Closed_Chest.class);
        
     // Add the bush  asset class to the map
        for (int i : BUSH_INDICES) {
            assetClasses.put(i, OBJ_Bushes.class);
        }

     // Add the tree  asset class to the map
        for (int i : TREE_INDICES) {
            assetClasses.put(i, OBJ_Trees.class);
        }
    }

    // Method to set the game objects on the GamePanel
    public void setObject() {
    	// Initialize an array of SuperObject of size OBJECT_POSITIONS.length
        SuperObject[] assets = new SuperObject[OBJECT_POSITIONS.length];
        
        // Loop through the OBJECT_POSITIONS array
        for (int i = 0; i < OBJECT_POSITIONS.length; i++) {
        	// Get the position of the current object
            int[] position = OBJECT_POSITIONS[i];
            // Get the asset class name for the current object index
            Class<? extends SuperObject> assetClass = assetClasses.get(i);

            // If the asset class is not found, throw an IllegalArgumentException
            if (assetClass == null) {
                throw new IllegalArgumentException("Unknown asset index: " + i);
            }

            // Try to create an instance of the asset class
            try {
            	// Create an instance of the asset class with the GamePanel object gamePanel as parameter
                SuperObject asset = assetClass.getDeclaredConstructor(GamePanel.class).newInstance(gamePanel);

                // Set the worldX and worldY positions of the asset based on its position in OBJECT_POSITIONS
                asset.worldX = position[0] * gamePanel.tileSize;
                asset.worldY = position[1] * gamePanel.tileSize;

                // Add the asset to the assets array
                assets[i] = asset;

                // If an exception occurs during asset creation, throw a RuntimeException
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Error creating asset for index " + i, e);
            }
        }
        
        // Set the gamePanel's obj array to the assets array
        gamePanel.obj = assets;
    }
}