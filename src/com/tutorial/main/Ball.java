package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball extends GameObject {

	protected int speed;
	int diameter = 32;

	public Ball(int x, int y, int diameter, ID id, Game game) {
		super (x-diameter/2, y-diameter, id, game);
		velX = 5;
		velY = 5;
		this.diameter = diameter;
	}

	public void checkScore() {
		
	}

	public void collision() {
		wallCollision();
		paddleCollision(ID.Player1);
		paddleCollision(ID.Player2);
	}

	public void wallCollision() {
		if(x<0) {
			x = 0;
			velX = -1 * velX;
		}
		if(x > Game.WIDTH - (Game.window.frame.getInsets().left + Game.window.frame.getInsets().right) - diameter) {
			x = Game.WIDTH - (Game.window.frame.getInsets().left + Game.window.frame.getInsets().right) - diameter;
			velX = -1 * velX;
		}
		if(y<0) {
			y = 0;
			velY = -1 * velY;
		}
		if(y > Game.HEIGHT - (Game.window.frame.getInsets().top + Game.window.frame.getInsets().bottom) - diameter) {
			y = Game.HEIGHT - (Game.window.frame.getInsets().top + Game.window.frame.getInsets().bottom) - diameter;
			velY = -1 * velY;
		}
	}

	public void paddleCollision(ID playerID) {
		Player player = (Player)game.handler.getObject(playerID);

		if((Math.abs(x - player.x) <= (diameter + player.width/2)) && (Math.abs(y - player.y) <= (player.height/2))){
			// If the x velocity of the ball is going away from the paddle, don't switch
			// First, determine which side of the paddle the ball is on
			if(x > player.x){
				// Ball is on the right
				// If the x velocity is positive, don't modify it
				if(velX > 0){

				}else{
					System.out.println("Collision! Ball: (" + x + "," + y + "), " + playerID.toString() + ": (" + player.x + "," + player.y + ")");
					velX = -1 * velX;
				}
			}else{
				// Ball is on the left
				// If the x velocity is negative, don't modify it
				if(velX < 0){

				}else{
					System.out.println("Collision! Ball: (" + x + "," + y + "), " + playerID.toString() + ": (" + player.x + "," + player.y + ")");
					velX = -1 * velX;
				}
			}
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
		g.fillOval(x-(diameter/2), y-(diameter/2), diameter, diameter);
		//g.setColor(Color.BLUE);
		//g.fillOval(640/2-diameter/2-game.window.frame.getInsets().left, 480/2-diameter, diameter, diameter);
		//g.setColor(Color.BLACK);
		//g.drawLine(640/2, 480/2, 640/2, 480/2);
	}

}
