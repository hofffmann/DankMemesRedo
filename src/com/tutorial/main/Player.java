package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	final int width = 24, height = 128;
	
	public Player(int x, int y, ID id, Game game) {
		super(x, y, id, game);
	}
	
	public void movement() {
		long nanosecondsSinceLastRender = (System.nanoTime() - Game.lastRenderTime);
		float interval = 1.7E7f;
		float mult = nanosecondsSinceLastRender / interval;
		
		if(up && down) 
		{
			if(velY < 0) {
				velY += 1 * mult;
				
			if(velY > -2) 
				velY = 0;
			}
			
			if(velY > 0) {
				velY -= 1 * mult;
				
			if(velY < 2)
				velY = 0;
			}
		}
		else
		{
			if(up) {
				if(Math.abs(velX) > 5)velY -= 2 * mult;
				else
				velY -= 1 * mult;
			}
			if(down) {
				if(Math.abs(velX) > 5)velY += 2 * mult;
				else
				velY += 1 * mult;
			}
		}
		
		if(!up && !down)
		{
			if(velY < 0) {
				velY += 1 * mult;
				
			if(velY > -2) 
				velY = 0;
			}
			
			if(velY > 0) {
				velY -= 1 * mult;
				
			if(velY < 2)
				velY = 0;
			}
		}
		
		if(velX > 10 * mult)velX = 10 * mult;
		if(velX < -10 * mult)velX = -10 * mult;
		if(velY > 10 * mult)velY = 10 * mult;
		if(velY < -10 * mult)velY = -10 * mult;
	}
	
	public void collision() {
		if(y<0) {
			y = 0;
			velY = 0;
		}
		if(y > Game.HEIGHT - (Game.window.frame.getInsets().top + Game.window.frame.getInsets().bottom) - height) {
			y = Game.HEIGHT - (Game.window.frame.getInsets().top + Game.window.frame.getInsets().bottom) - height;
			velY = 0;
		}
	}

	public void tick() {
		movement();
		
		x += velX;
		y += velY;
		
		collision();
	}

	public void render(Graphics g) {
		if(id == ID.Player1) g.setColor(Color.white);
		else if(id == ID.Player2) g.setColor(Color.blue);
		g.fillRect((x-width/2), (y-height/2), width, height);
	}

}
