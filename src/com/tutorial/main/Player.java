package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	final int size = 32;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		
	}
	
	public void movement() {
		if(left && right) 
		{
			left = false;
			right = false;
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
				if(velX > 2) 
					velX = 0;
			}
			
			if(velX > 0) {
				velX -= 1;
				if(velX < 2)
					velX = 0;
			}
		}
		
		
		
		if(up && down) 
		{
			up = false;
			down = false;
		}
		else
		{
			if(up) {
				if(Math.abs(velX) > 5)velY -= 2;
				else
				velY -= 0.5;
			}
			if(down) {
				if(Math.abs(velX) > 5)velY += 2;
				else
				velY += 0.5;
			}
		}
		
		if(!up && !down)
		{
			if(velY < 0) {
				velY += 1;
				if(velY > 2) 
					velY = 0;
			}
			
			if(velY > 0) {
				velY -= 1;
				if(velY < 2)
					velY = 0;
			}
		}
		
		if(velX > 10)velX = 10;
		if(velX < -10)velX = -10;
		if(velY > 10)velY = 10;
		if(velY < -10)velY = -10;
	}
	
	public void collision() {
		if(x<0) {
			x = 0;
			velX = 0;
		}
		if(x > Game.WIDTH - (Game.eggs.frame.getInsets().left + Game.eggs.frame.getInsets().right) - size) {
			x = Game.WIDTH - (Game.eggs.frame.getInsets().left + Game.eggs.frame.getInsets().right) - size;
			velX = 0;
		}
		if(y<0) {
			y = 0;
			velY = 0;
		}
		if(y > Game.HEIGHT - (Game.eggs.frame.getInsets().top + Game.eggs.frame.getInsets().bottom) - size) {
			y = Game.HEIGHT - (Game.eggs.frame.getInsets().top + Game.eggs.frame.getInsets().bottom) - size;
			velY = 0;
		}
		
	}

	public void tick() {
		
		movement();
		
		//System.out.println(velX + "\n" + velY);
		
		x += velX;
		y += velY;
		
		//System.out.println(x + "\n" + y);
		
		collision();
		
		
	}

	public void render(Graphics g) {
		if(id == ID.Player) g.setColor(Color.white);
		else if(id == ID.Player2) g.setColor(Color.blue);
		g.fillRect(x, y, size, size);
	}

}
