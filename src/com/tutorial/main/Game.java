package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 640, /*HEIGHT = WIDTH / 12 * 9;*/HEIGHT = 480;

	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	static Window window;

	public static double fps = 64.0;
	public static long lastRenderTime = 0;

	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));

		window = new Window(WIDTH, HEIGHT, "How to Game", this);

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
	
	public void run2() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		long timeOfLastRender = System.nanoTime();
		long differenceSum = 0;
		long sums = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();

				long difference = System.nanoTime() - timeOfLastRender;
				long differenceBetweenDesiredInterval = difference - (long)ns;
				//renderSum += differenceBetweenDesiredInterval;
				sums++;
				//System.out.println("Average delay in ns: " + (renderSum / sums));
				System.out.println("Time passed: " + differenceBetweenDesiredInterval);
				
				//System.out.println("Average difference: " + differenceSum / sums);
				render();
				timeOfLastRender = System.nanoTime();
				delta--;
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	public void run() {
		long timer = System.currentTimeMillis();
		long fpsTimer = System.currentTimeMillis();
		long longTimer = System.currentTimeMillis();
		int frames = 0;
		int quarterFrames = 0;
		lastRenderTime = System.nanoTime();
		
		int frameMonitorChecksPerSecond = 4;
		int frameMonitorSleepTime = 1000 / frameMonitorChecksPerSecond;
		FrameMonitor frameMonitor = new FrameMonitor((int)fps, frameMonitorChecksPerSecond);
		
		long renderSum = 0;
		long sums = 0;
		
		long oneMillisecondInNS = 1000000;
		
		double dx = 0;
		
		long now = System.nanoTime();
		long lastTime = System.nanoTime();
		
		while(running) {
			sleepNanos((long)(frameMonitor.renderInterval * .9));
			//sleepNanos((long)(1000000000 / fps));
			
			//dx += ((double)(System.nanoTime() - lastRenderTime) / (double)frameMonitor.renderInterval);
			//System.out.println(dx);
			
			int count_t = 0;
			while((lastRenderTime + frameMonitor.renderInterval) - System.nanoTime() > 600000){
				// Sleep for half the remaining time
				sleepNanos( (long) (.2) * (lastRenderTime + frameMonitor.renderInterval) - System.nanoTime());
				count_t++;
			}
			//System.out.println(count_t + " little sleeps");
			
			while(dx < 1){
				now = System.nanoTime();
				long timePassed = now - lastTime;
				lastTime = now;
				//dx += ((double)(System.nanoTime() - lastRenderTime) / (double)frameMonitor.renderInterval);
				dx += (timePassed / (1000000000.0/60.0));
				//System.out.println("dx: " + dx);
			}
			
			while(dx >= 1){
				long difference = System.nanoTime() - lastRenderTime;
				long differenceBetweenDesiredInterval = difference - frameMonitor.renderInterval;
				tick();
				render();
				lastRenderTime = System.nanoTime();
				dx--;
				frames++;
				quarterFrames++;
				
				renderSum += differenceBetweenDesiredInterval;
				sums++;
				//System.out.println("Time passed: " + differenceBetweenDesiredInterval);
			}
			
			if(System.currentTimeMillis() - fpsTimer >= frameMonitorSleepTime) {
				//frameMonitor.updateAverageFrames(quarterFrames);
				fpsTimer = System.currentTimeMillis();
				quarterFrames = 0;
			}
			
			if(System.currentTimeMillis() - timer >= (1000)) {
				System.out.println("FPS: " + frames);
				//System.out.println("Render interval: " + frameMonitor.renderInterval);
				frames = 0;
				timer = System.currentTimeMillis();
			}
			
			if(System.currentTimeMillis() - longTimer >= (5000)) {
				//frameMonitor.updateRenderInterval();
				longTimer = System.currentTimeMillis();
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
