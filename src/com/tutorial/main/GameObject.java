package com.tutorial.main;

import java.awt.Graphics;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected double velX, velY;
	protected boolean up, down, left, right;
	Game game;
	
	public GameObject(int x, int y, ID id, Game game) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.game = game;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	public boolean getUp() {
		return up;
	}
	public boolean getDown() {
		return down;
	}
	public boolean getLeft() {
		return left;
	}
	public boolean getRight() {
		return right;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	
}
