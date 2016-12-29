package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball extends GameObject {
	
	Random r = new Random();
	protected int speed;
	final int size = 32;
	
	public Ball(int x, int y, ID id) {
		super (x, y, id);
		velX = 5;
		velY = 5;
	}
	
	public void checkScore() {
		//if(x > .x)
	}
	
	public void collision() {
		if(x<0) {
			x = 0;
			velX = -1 * velX;
		}
		if(x > Game.WIDTH - (Game.window.frame.getInsets().left + Game.window.frame.getInsets().right) - size) {
			x = Game.WIDTH - (Game.window.frame.getInsets().left + Game.window.frame.getInsets().right) - size;
			velX = -1 * velX;
		}
		if(y<0) {
			y = 0;
			velY = -1 * velY;
		}
		if(y > Game.HEIGHT - (Game.window.frame.getInsets().top + Game.window.frame.getInsets().bottom) - size) {
			y = Game.HEIGHT - (Game.window.frame.getInsets().top + Game.window.frame.getInsets().bottom) - size;
			velY = -1 * velY;
		}
	}
	
	public int speed() {
		int x = 24 * (int)Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
		if(x > 255)x = 255;
		return x;
	}

	public void tick() {
		collision();
		//checkScore();
		
		long nanosecondsSinceLastRender = (System.nanoTime() - Game.lastRenderTime);
		float interval = 1.7E7f;
		float mult = nanosecondsSinceLastRender / interval;
		x += velX * mult;
		y += velY * mult;
	}

	public void render(Graphics g) {
		g.setColor(new Color(255, 255 - speed(), 255 - speed()));
		g.fillOval(x, y, size, size);
	}

}
