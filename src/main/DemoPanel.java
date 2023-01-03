package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import eyecandy.Modulo;
import eyecandy.Sorter;
import eyecandy.CubeRotator;
import eyecandy.Mandelbrot;

public class DemoPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = -3550958944572506053L;

	// Screen Settings
	final int scale = 3;
	//final int screenWidth = 320;
	//final int screenHeight = 200; // VGA resolution
	
	final int screenWidth = 320;
	final int screenHeight = 200;
	
	// FPS
	final int FPS = 60;
	final double drawInterval = 1000000000/FPS;
	
	// Draw spanner
	double nextDrawTime;
	double remainingTime;
	
	Thread demoThread;
	//Modulo modulo = new Modulo();
	CubeRotator modulo = new CubeRotator(); // I know it makes no sense, just roll with it
	KeyHandler keyHandler = new KeyHandler();
	
	public DemoPanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	
	public void initDemo() {
		demoThread = new Thread(this); // Passing own object to demoThread
		demoThread.start();
	}
	
	@Override
	public void run() {
		while (demoThread != null) {
			update();
			repaint();
			
			try {
				nextDrawTime = System.nanoTime() + drawInterval;
				remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		modulo.update();
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2 = (Graphics2D)graphics;
		modulo.draw(g2);
		g2.dispose();
	}
}
