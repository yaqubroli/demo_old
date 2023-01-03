package eyecandy;

import java.awt.Color;

public class Mandelbrot extends StackableBackground {
	final int width = 320;
	final int height = 200;
	
	final int offsetX = width / 2;
	final int offsetY = height / 2;
	
	final int mandelCycles = 2000; // how many iterations
	
	public Color getFractalValue(double c_r, double c_i) {
		int i = 0;
		double x = 0, y = 0;
		/*
		 * Instead of creating an object for complex numbers I used two doubles.
		 * The reason is because there was some guy on a blog who explained it this way
		 * and I don't have the patience to make it work better
		 */
		while (x*x+y*y <= 4 && i < mandelCycles) {
			double x_new = x*x - y*y + c_r;
            y = 2*x*y + c_i;
            x = x_new;
            i++;
		}
		if (i < mandelCycles) {
			//System.out.println(c_i + " ," + c_r);
			return Color.white;
		} else {
			return Color.black;
		}
	}
	
	@Override
	public int getColour(int x, int y) {
		/*int mandelValue = (int)iterate(
				(double)(x - offsetX) / 40, 
				(double)(y - offsetY) / 40
			);
		mandelValue = Math.min(Math.abs(mandelValue / 5), 255);*/
		return getFractalValue(
				(double)(x - offsetX) / 80, 
				(double)(y - offsetY) / 80
			).getRGB();
	}
	
	@Override
	public int[] transformScreen() {
		return new int[]{0, 0};
	}
}
