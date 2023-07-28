package tile;

import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	
	GamePanel gp;
	public Tile [] tile;
	public int[][] mapTileNum;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
//		CHANGE LINE BELOW TO CHANGE NUMBER OF TILES to use for images
		tile = new Tile[500];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadmap("/Maps/world.txt"); // change this to change map
	}
	
	public void getTileImage() {
//		Parameters =	file number, then image path, then collision
		
			//Tiles 0 - 11 are grass and flower tiles	
				setup(0, "/Grass/Grass0", false);
				setup(1, "/Grass/Grass1", false);
				setup(2, "/Grass/Grass2", false);
				setup(3, "/Grass/Grass3", false);
				setup(4, "/Grass/Grass4", false);
				setup(5, "/Grass/Grass5", false);
				setup(6, "/Grass/Grass6", false);
				setup(7, "/Grass/Grass7", false);
				setup(8, "/Grass/Grass8", false);
				setup(9, "/Grass/Grass9", false);
				setup(10, "/Grass/Grass10", false);
				setup(11, "/Grass/Grass11", false);
				
				//Tiles 12 - 16 are Grass and stone path tiles
				
				// all sides
				setup(12, "/Grass/Grass12",false);
				// two right
				setup(13, "/Grass/Grass13",false);
				// two left
				setup(14, "/Grass/Grass14",false);
				// two bottom
				setup(15, "/Grass/Grass15",false);
				// two top
				setup(16, "/Grass/Grass16", false);
				// Tiles 65 is water tile
				setup(65, "/Water0",true);	
	}
	
	public void setup(int index, String imagePath, boolean collision) {
			UtilityTool uTool =new UtilityTool();
			
			try {
				tile[index]  = new Tile();
				tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
				tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
				tile[index].collision = collision;
				
			}catch(IOException e) {
				e.printStackTrace(); 
			}
	}
	
	public void loadmap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol &&  row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col  < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
					
				}
				
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
			
		}catch(Exception e) {
//		e.printStackTrace();
		}	
	}

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
		worldCol++;
		
		if(worldCol == gp.maxWorldCol) {
			worldCol = 0;
			worldRow++;
			}
		}
	}	
}