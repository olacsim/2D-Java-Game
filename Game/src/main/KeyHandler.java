/*
 * This Section of Code Detects User Key Input (WASD)
 */

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public int yDirection = 0;
	public int xDirection = 0;
	
//ADD DODGE ROLL, PAUSE MENU, AND ATTACKS
	
	public KeyHandler(GamePanel gp) {
		this.gp  =  gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    
	    switch (keyCode) {
	        case KeyEvent.VK_W:
	            yDirection = -1;
	            break;
	            
	        case KeyEvent.VK_S:
	            yDirection = 1;
	            break;
	            
	        case KeyEvent.VK_A:
	            xDirection = -1;
	            break;
	         
	        case KeyEvent.VK_D:
	            xDirection = 1;      
	            break;
	            
	        case KeyEvent.VK_P:
	        	if(gp.gameState == gp.playState) {
					gp.gameState = gp.pauseState;
				}
				
				else if(gp.gameState == gp.pauseState)
					gp.gameState = gp.playState;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    
	    switch (keyCode) {
	        case KeyEvent.VK_W:
	        case KeyEvent.VK_S:
	            yDirection = 0;
	            break;
	            
	        case KeyEvent.VK_A:
	        case KeyEvent.VK_D:
	            xDirection = 0;
	            break;
	    }
	}
}