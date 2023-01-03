package eyecandy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StackableBackground implements Drawable {
	
	final int width = 640;
	final int height = 400;
	
	int iteration = 0; // java doesn't handle unsigned ints; this is effectively our "absolute timer" we treat as unsigned.
	
	boolean procedural = false; // not really necessary, might take it out. just enables/disables rendering.
	
	BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
	public int getColour(int x, int y) {
		return 255;
	}
	
	@Override
	public void update() {
		iteration++;
		if (iteration < 0) {
			iteration = Math.abs(iteration); // replace with bitwise hack later
		}
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				canvas.setRGB(x, y, getColour(x, y));
			}
		}
		//System.out.println(iteration);
	}
	
	public int[] transformScreen() {
		return new int[] {0, 0};
	}
	

	@Override
	public void draw(Graphics2D graphics2D) {
		int[] currentTransform = transformScreen();
		graphics2D.drawImage(
				canvas, 
				null, 
				currentTransform[0], 
				currentTransform[1]
			);
	}

}
