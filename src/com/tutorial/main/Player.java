package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		
	}

	public void tick() {
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
			
		
		//System.out.println(velX + "\n" + velY);
		
		x += velX;
		y += velY;
		
		
	}

	public void render(Graphics g) {
		if(id == ID.Player) g.setColor(Color.white);
		else if(id == ID.Player2) g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
	}

}
