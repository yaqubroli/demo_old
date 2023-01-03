package eyecandy;

import java.awt.Color;
import java.lang.Math;

import absmaths.AbsMaths;
import absmaths.Matrix;

/*
 *  TODO:
 *  * remove rotationIteration [DONE]
 *  * remove plazmaIteration [DONE]
 *  * provide a way to switch modes with a cool transition
 *  * use LUTs to save compute time each time
 *  * export 
 */
public class Modulo extends StackableBackground {
	final int rotationIterationCycle = 768;
	final int plazmaIterationCycle = 234;
	final int plazmaSpeedFactor = 3; // to be honest I forget what this does
	
	final boolean procedural = true;
	
	final Matrix transformYUVtoRGB = new Matrix( // transformation matrix that turns YUV PAL colours into RGB; also stolen from wikipedia
		new double[][] {
			{1, 0, 1.13983},
			{1, -0.39465, -0.58060},
			{1, 2.03211, 0}
		}
	);
	
	final int width = 640;
	final int height = 400;
		
	int timeOfLastTransition;
	
	int[][] moduloTable = AbsMaths.newTimesTable(width, height);
	
	public enum Modes {
		HUE_MODE, // modulo value -> H in HSV -> RGB
		PAL_MODE, // modulo value -> -U and V in YUV -> RGB
		GREY_MODE // modulo value -> all RGB
	}
	
	public int getModuloTableEntry(int x) { //who knows
		return (int) (
				(x / (float)(
					(
						((Math.sin((float)iteration / (plazmaIterationCycle / plazmaSpeedFactor)) + 1) * (plazmaIterationCycle / 2)
					) / 5
				) + 0.25)) % 255
			);
	}
	
	public int getColourThroughPAL(int x) {
		x = getModuloTableEntry(x);
		
		// PAL colour spanning via matrix hackery
		Matrix coloursYUV = new Matrix(new double[][]{
			{0},
			{255 - x},
			{x},
		});
		double[] coloursRGB = AbsMaths.sanitizeToBounds(transformYUVtoRGB.getProduct(coloursYUV).getColumnAsArray(0), 0, 255);
		// System.out.println(Arrays.toString(coloursRGB));
		Color color = new Color((int)coloursRGB[0], (int)coloursRGB[1], (int)coloursRGB[2]);
		return color.getRGB();
	}
	
	public int getColourThroughHue(int x) {
		//giant piecewise function stolen from wikipedia and javafied
		float h = (float)getModuloTableEntry(x) / (255f / 6); // hue as float so the multiplication works out
		float w = (float)(1 - Math.abs((h % 2) - 1));
		
		switch ((int)Math.floor(h)) {
			case 0:
				return new Color(1, w, 0).getRGB();
			case 1:
				return new Color(w, 1, 0).getRGB();			
			case 2:
				return new Color(0, 1, w).getRGB();
			case 3:
				return new Color(0, w, 1).getRGB();
			case 4:
				return new Color(w, 0, 1).getRGB();
			case 5:
				return new Color(1, 0, w).getRGB();
			default:
				return 0;
		}
	}
	
	public int getColourStraight(int x) {
		x = getModuloTableEntry(x);
		Color color = new Color(x, x, x);
		return color.getRGB();
	}
	
	@Override
	public int getColour(int x, int y) {
		return getColourStraight(moduloTable[x][y]);
	}
	
	@Override
	public int[] transformScreen() {
		//System.out.println("this is being called");
		return new int[]{
			(int) (Math.sin(((float)(iteration % rotationIterationCycle) / rotationIterationCycle) * 2 * Math.PI) * (width / 4) - (width / 4)),
			(int) (Math.cos(((float)(iteration % rotationIterationCycle) / rotationIterationCycle) * 2 * Math.PI) * (height / 4) - (height / 4))
		};
	}

}
