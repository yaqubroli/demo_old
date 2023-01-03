package absmaths;

import java.util.Arrays;

public class Matrix {
	double[][] matrix;

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public Matrix(double[][] matrix) {
		this.setMatrix(matrix);
	}
	
	public Matrix(int rows, int columns) {
		this.setMatrix(new double[rows][columns]);
	}

	public Matrix getProduct(Matrix m) { // regular matrix multiplication
		double[][] multiplicand = m.getMatrix();
		double[][] result = new double[matrix.length][multiplicand[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < multiplicand[0].length; j++) {
				result[i][j] = vectorDotProduct(result[i], getColumnAsArray(j, multiplicand));
			}
		}
		return new Matrix(result);
	}

	public double[] getColumnAsArray(int indicand) {
		return getColumnAsArray(indicand, this.matrix);
	}
	
	public static double[] getColumnAsArray(int indicand, Matrix x) {
		return getColumnAsArray(indicand, x.getMatrix());
	}
	
	public static double[] getColumnAsArray(int indicand, double[][] x) {
		double[] result = new double[x.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = x[i][indicand];
		}
		return result;
	}
	
	public static double vectorDotProduct(double[] x, double[] y) {
		double product = 0;
		if (x.length == y.length) {
			for (int i = 0; i < x.length; i++) {
				product += x[i] * y[i];
			}
		} else {
			System.out.println("Dot product length wrong!");
		}
		return product;
	}
	
	public String toString() {
		return Arrays.deepToString(matrix);
	}
}
