package main;

import java.awt.event.KeyEvent;

/* 
 * This class only exists for development's sake, do not intend to use it in the final product.
 */
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public int lastTyped = -1;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		lastTyped = e.getKeyCode();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void clearTypeBuffer() {
		lastTyped = -1;
	}
}
