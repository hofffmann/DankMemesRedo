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
				
				if(key == KeyEvent.VK_W) tempObject.setUp(true);
				if(key == KeyEvent.VK_S) tempObject.setDown(true);
				if(key == KeyEvent.VK_D) tempObject.setRight(true);
				if(key == KeyEvent.VK_A) tempObject.setLeft(true);
			}
			if(tempObject.getId() == ID.Player2) {
				//Key Events for Player 2
				
				if(key == KeyEvent.VK_UP) tempObject.setUp(true);
				if(key == KeyEvent.VK_DOWN) tempObject.setDown(true);
				if(key == KeyEvent.VK_RIGHT) tempObject.setRight(true);
				if(key == KeyEvent.VK_LEFT) tempObject.setLeft(true);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//Key Events for Player 1
				
				if(key == KeyEvent.VK_W) tempObject.setUp(false);
				if(key == KeyEvent.VK_S) tempObject.setDown(false);
				if(key == KeyEvent.VK_D) tempObject.setRight(false);
				if(key == KeyEvent.VK_A) tempObject.setLeft(false);
			}
			if(tempObject.getId() == ID.Player2) {
				//Key Events for Player 2
				
				if(key == KeyEvent.VK_UP) tempObject.setUp(false);
				if(key == KeyEvent.VK_DOWN) tempObject.setDown(false);
				if(key == KeyEvent.VK_RIGHT) tempObject.setRight(false);
				if(key == KeyEvent.VK_LEFT) tempObject.setLeft(false);
			}
		}
	}
}