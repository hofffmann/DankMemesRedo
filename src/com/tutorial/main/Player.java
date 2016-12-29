package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	final int height = 128, size = 24;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
	}
	
	public void movement() {
		//x axis is unused for movement in Pong.
/*		if(left && right) 
		{
			if(velX < 0) {
				velX += 1;
				
			if(velX > -2) 
				velX = 0;
			}
			
			if(velX > 0) {
				velX -= 1;
				
			if(velX < 2)
				velX = 0;
			}
		}
		else
		{
			if(left) {
				if(Math.abs(velY) > 5)velX -= 2;
				else
				velX -= 0.5;
			}
			if(right) {
				if(Math.abs(velY) > 5)velX += 2;
				else
				velX += 0.5;
			}
		}

		if(!left && !right)
		{
			if(velX < 0) {
				velX += 1;
				
			if(velX > -2) 
				velX = 0;
			}
			
			if(velX > 0) {
				velX -= 1;
				
			if(velX < 2)
				velX = 0;
			}
		}*/
		
		long nanosecondsSinceLastRender = (System.nanoTime() - Game.lastRenderTime);
		float interval = 1.7E7f;
		float mult = nanosecondsSinceLastRender / interval;
		System.out.println(mult);
		
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
		//x is unused in Pong.
		
		/*if(x<0) {
			x = 0;
			velX = 0;
		}
		if(x > Game.WIDTH - (Game.window.frame.getInsets().left + Game.window.frame.getInsets().right) - size) {
			x = Game.WIDTH - (Game.window.frame.getInsets().left + Game.window.frame.getInsets().right) - size;
			velX = 0;
		}*/
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

		//System.out.println(velX + "\n" + velY);
		//System.out.println(x + "\n" + y);
	}

	public void render(Graphics g) {
		if(id == ID.Player) g.setColor(Color.white);
		else if(id == ID.Player2) g.setColor(Color.blue);
		g.fillRect(x, y, size, height);
	}

}
