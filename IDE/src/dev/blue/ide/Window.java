package dev.blue.ide;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import dev.blue.ide.input.KeyManager;
import dev.blue.ide.input.MouseManager;

public class Window extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	
	public boolean isSmall = false;
	
	public Window() {
		canvas = new Canvas();
		this.add(canvas);
		
		this.setTitle("IDE");
		this.setUndecorated(false);
		this.setPreferredSize(new Dimension(900, 600));
		this.setResizable(true);
		
		MouseManager mouseManager = new MouseManager();
		KeyManager keyManager = new KeyManager();
		addKeyListener(keyManager);
		addMouseListener(mouseManager);
		addMouseMotionListener(mouseManager);
		addMouseWheelListener(mouseManager);
		
		canvas.addKeyListener(keyManager);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		canvas.addMouseWheelListener(mouseManager);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.stop();
			}
		});
		
		this.pack();
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		canvas.createBufferStrategy(2);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}
