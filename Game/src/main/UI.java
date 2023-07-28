package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	
	GamePanel gp; 
	Graphics2D g2;
	Font yrsa_32, yrsa_80;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message =  (" ");
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		yrsa_32 = new Font("Yrsa", Font.BOLD, 32);
		yrsa_80 = new Font("Yrsa", Font.BOLD, 80);
		OBJ_Key  key = new OBJ_Key(gp);
		keyImage = key.image;
	}
	
	public void showMessage (String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(yrsa_32);
		g2.setColor(Color.WHITE);
		
		if(gp.gameState == gp.playState) {
//			BEAT THE GAME WHEN gameFinished == true (set in player class)
			if (gameFinished == true) {
			
			}
				else {
					g2.setFont(yrsa_32);
					g2.setColor(Color.WHITE);
				g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize,null);
					g2.drawString(" " +gp.player.hasKey, 75, 75);
				}
					if (messageOn == true) {
						// change font size and draw at location,messages set through gp.ui.showmessage("what you want to say" ), often set in Player class
						g2.setFont(g2.getFont().deriveFont(32F));
						g2.drawString(message, 0, 700);
						
						messageCounter++;
						
						if (messageCounter > 120) {
							messageCounter = 0;
							messageOn = false;
						}
					}
				}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(yrsa_80);
			String text = "Paused";
			int x = getXForCenteredText(text);
			int y = gp.screenHeight/2;
			g2.drawString(text, x, y);
			
//			BELOW shows keys on screen in pause state
			g2.setFont(yrsa_32);
			g2.setColor(Color.WHITE);
		g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize,null);
			g2.drawString(" " +gp.player.hasKey, 75, 75);
	}
	
	public int getXForCenteredText(String text) {
		int length  = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
