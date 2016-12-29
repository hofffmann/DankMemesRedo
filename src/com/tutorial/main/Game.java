package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 640, /*HEIGHT = WIDTH / 12 * 9;*/HEIGHT = 480;

	private Thread thread;
	private boolean running = false;

	private Random r;
	private Handler handler;
	static Window window;

	public static int fps = 60;
	public static double fpsMult = 60 / (double)fps;
	public static long lastRenderTime = 0;
	public static long desiredRenderInterval = 1000000000 / fps;

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
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			sleepNanos((long)desiredRenderInterval);
			
			tick();
			render();
			lastRenderTime = System.nanoTime();
			frames++;

			if(System.currentTimeMillis() - timer > (1000)) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer = System.currentTimeMillis();
			}
		}
		stop();
	}
	
	public static void sleepNanos (long nanoDuration) {
		try {
			long SLEEP_PRECISION = TimeUnit.MILLISECONDS.toNanos(2);
			long SPIN_YIELD_PRECISION = TimeUnit.MILLISECONDS.toNanos(2);
			final long end = System.nanoTime() + nanoDuration;
			long timeLeft = nanoDuration;
			do {
				if (timeLeft > SLEEP_PRECISION)
					Thread.sleep (1);
				else
					if (timeLeft > SPIN_YIELD_PRECISION)
						Thread.yield();

				timeLeft = end - System.nanoTime();
			} while (timeLeft > 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
