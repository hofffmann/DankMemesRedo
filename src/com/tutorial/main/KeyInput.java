package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//Key Events for Player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(tempObject.getVelY()-1);
				if(key == KeyEvent.VK_S) tempObject.setVelY(tempObject.getVelY()+1);
				if(key == KeyEvent.VK_D) tempObject.setVelX(tempObject.getVelX()+1);
				if(key == KeyEvent.VK_A) tempObject.setVelX(tempObject.getVelX()-1);
			}
			if(tempObject.getId() == ID.Player2) {
				//Key Events for Player 2
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(tempObject.getVelY()-1);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(tempObject.getVelY()+1);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(tempObject.getVelX()+1);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(tempObject.getVelX()-1);
			}
			
			
			
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//Key Events for Player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
			}
			if(tempObject.getId() == ID.Player2) {
				//Key Events for Player 2
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
			}
			
			
			
		}
		
	}

}
