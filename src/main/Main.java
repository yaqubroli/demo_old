package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		// Initialize window
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); // Change later
		
		// New DemoPanel functions as background surface for all blits
		DemoPanel demoPanel = new DemoPanel();
		window.add(demoPanel);
		
		// Pack to size of subcomponents
		window.pack();
		
		// Show
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// Start the demo thread
		demoPanel.initDemo();
	}
}
