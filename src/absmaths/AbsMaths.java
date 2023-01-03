

package absmaths;

public class AbsMaths {
	public static int[][] newTimesTable (int width, int height) { // generates a new 2d array times table of arbitrary size
		int[][] arr = new int[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				arr[x][y] = x * y;
			}
		}
		return arr;
	}
	
	public static int[] sanitizeToBounds (int[] array, int floor, int ceiling) { // i wish to death that i had an array map function here
		int[] result = new int[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Math.min(ceiling, Math.max(floor, array[i]));
		}
		return result;
	}
	
	public static float[] sanitizeToBounds (float[] array, float floor, float ceiling) {
		float[] result = new float[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Math.min(ceiling, Math.max(floor, array[i]));
		}
		return result;
	}
	
	public static double[] sanitizeToBounds (double[] array, double floor, double ceiling) {
		double[] result = new double[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Math.min(ceiling, Math.max(floor, array[i]));
		}
		return result;
	}
	
	public static int sanitizeToBounds (int sanitizand, int floor, int ceiling) {
		return Math.min(ceiling, Math.max(floor, sanitizand));
	}

	public static float sanitizeToBounds (float sanitizand, float floor, float ceiling) {
		return Math.min(ceiling, Math.max(floor, sanitizand));
	}

	public static double sanitizeToBounds (double sanitizand, double floor, double ceiling) {
		return Math.min(ceiling, Math.max(floor, sanitizand));
	}
}
