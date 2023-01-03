package eyecandy;

import java.awt.Color;
import java.awt.Graphics2D;

public class Sorter implements Drawable{
	final int width = 320;
	final int height = 200;
	final int setLength = 93;
	final int setRange = 100;
	final int widthOffset = 20;
	final int heightOffset = 150;
	final int poleDistance = 3;
	
	int[] set = new int[setLength];
	int[] work = new int[setLength];
	
	public enum Modes {
		MERGE_MODE,
		BUBBLE_MODE,
		QUICK_MODE,
	}
	
	private Modes mode;
	/*
	private class BubbleRegisters {
		int hold;
		boolean changed;
	}

	private class MergeRegisters {
		int x;
	}
	
	private class QuickRegisters {
		int x;
	}
	*/
	public Sorter() {
		for (int i = 0; i < setLength; i++) {
			set[i] = (int)Math.floor(Math.random() * setRange);
		}
		mode = Modes.BUBBLE_MODE;
	}
	
	public void setMode(Modes m) {
		switch (m) {
		case MERGE_MODE:
			mode = Modes.MERGE_MODE;
			break;
		case BUBBLE_MODE:
			mode = Modes.BUBBLE_MODE;
			break;
		case QUICK_MODE:
			mode = Modes.QUICK_MODE;
			break;
		}
	}
	
	private int[] bubbleSort(int[] s) {
		boolean changed = false;
		do {
			for (int i = 0; i < s.length - 1; i++) {
				if (s[i] > s[i + 1]) {
					int hold = s[i]; // swap entries
					s[i] = s[i + 1];
					s[i + 1] = hold;
				}
			}
		} while (changed);
		return s;
	}
	
	private int[] topDownMergeSort(int[] s, int n) {
		int[] w = new int[s.length]; // work array, hence 'w'
		
		return s;
	}
	
	private void topDownSplitMerge() {
		
	}
	@Override
	public void update() {
		switch (mode) {
		case MERGE_MODE:
			set = topDownMergeSort(set);
			break;
		case BUBBLE_MODE:
			set = bubbleSort(set);
			break;
		case QUICK_MODE:
			break;
		}
	}
	
	public void renderSet(int offset, Graphics2D graphics2d) {
		for (int i = 0; i < setLength; i++) {
			graphics2d.drawLine(
					widthOffset + (i*poleDistance) + offset, 
					heightOffset, 
					widthOffset + (i*poleDistance) + offset, 
					heightOffset - set[i]
				);
		}
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		switch (mode) {
			case MERGE_MODE: 
				graphics2d.setColor(Color.cyan);
				break;
			case BUBBLE_MODE: 
				graphics2d.setColor(Color.magenta);
				break;
			case QUICK_MODE: 
				graphics2d.setColor(Color.green);
				break;
		}
		renderSet(0, graphics2d);
		switch (mode) {
			case MERGE_MODE: 
				graphics2d.setColor(Color.blue);
				break;
			case BUBBLE_MODE: 
				graphics2d.setColor(Color.red);
				break;
			case QUICK_MODE: 
				graphics2d.setColor(Color.green);
				break;
		}
		graphics2d.setColor(Color.black);
		renderSet(2, graphics2d);
	}

}