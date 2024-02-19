package dev.blue.ide;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import windowAPI.ui.UIObjectRegistry;

public class Engine implements Runnable {
	
	private Window window;
	private Thread thread;
	private boolean running;
	private BufferStrategy bs;
	private static UIObjectRegistry registry = new UIObjectRegistry();
	
	private static EventManager eventManager;
	
	/**
	 *
	 **/
	public Engine(Window window) {
		this.thread = new Thread(this);
		eventManager = new EventManager();
		this.window = window;
		bs = this.window.getCanvas().getBufferStrategy();
		initializeBufferStrategy();
		new EditorLoader(window.getGraphics());
	}

	/**
	 *
	 **/
	@Override
	public void run() {
		long now;
		long lastTick = System.nanoTime();
		double delta = 0.0;
		double TPS = 30;
		while (running) {
			now = System.nanoTime();
			delta = (now - lastTick) / 1000000;
			if (delta >= 1000d / TPS) {
				delta -= (1000d / TPS);
				pollEvents();
				render();
				lastTick = now;
			}
		}
	}
	
	private void pollEvents() {
		if(eventManager.getEventCt() > 0) {
			eventManager.executeEvents();
			render();
		}
	}
	
	public static EventManager getEventManager() {
		return eventManager;
	}
	
	public static UIObjectRegistry getUIObjectRegistry() {
		return registry;
	}
	
	/**
	 *
	 **/
	public void render() {
	    initializeBufferStrategy();

	    Graphics g = bs.getDrawGraphics();

	    try {
	        if (g instanceof Graphics2D) {
	            Graphics2D g2 = (Graphics2D) g;
	            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        }

	        // Clear the screen
	        g.setColor(new Color(0, 100, 100));
	        g.fillRect(0, 0, this.window.getWidth(), this.window.getHeight());

	        // Render everything in the registry
	        registry.render(g);

	    } finally {
	        g.dispose();
	        bs.show();
	    }
	}
	
	/**
	 *
	 **/
	public void start() {
		running = true;
		thread.start();
	}
	
	/**
	 *
	 **/
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *
	 **/
	public boolean isRunning() {
		return running;
	}
	
	/**
	 *
	 **/
	private void initializeBufferStrategy() {
		if (bs == null) {
			try {
				this.window.getCanvas().createBufferStrategy(2);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
