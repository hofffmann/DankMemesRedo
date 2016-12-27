package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, /*HEIGHT = WIDTH / 12 * 9;*/HEIGHT = 480;
	
	private Thread thread;
	private boolean running = false;
		
	private Random r;
	private Handler handler;
	static Window window;
	
	public Game() {
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		
		window = new Window(WIDTH, HEIGHT, "How to Game", this);
		
		r = new Random();
		
		handler.addObject(new Player((int)128 - window.frame.getInsets().left, (int)HEIGHT/2 - (64 + (window.frame.getInsets().top)), ID.Player));
		handler.addObject(new Player((int)WIDTH-(128 + 2 * (window.frame.getInsets().left + window.frame.getInsets().right)), (int)HEIGHT/2 - (64 + (window.frame.getInsets().top)), ID.Player2));
		handler.addObject(new Ball(WIDTH/2, HEIGHT/2, ID.Ball));
		
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				render();
				delta--;
				frames++;
			}
			//if(running)
				//render();
		
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		new Game();
	}
	
}
