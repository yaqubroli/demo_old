package eyecandy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import absmaths.Matrix;

public class CubeRotator implements Drawable {
	
	final int scaleFactor = 50;
	
	final int width = 320;
	final int height = 200;
	
	final int offsetWidth = width / 2;
	final int offsetHeight = height / 2;
	
	enum Axis {
		X,
		Y,
		Z
	}
	
	final Matrix[] verticesPrimary = new Matrix[] {
			new Matrix(new double[][] {{-1}, {-1}, {1}}), //incredibly cursed I know
			new Matrix(new double[][] {{1}, {-1}, {1}}),
			new Matrix(new double[][] {{1}, {1}, {1}}),
			new Matrix(new double[][] {{-1}, {1}, {1}}),
			new Matrix(new double[][] {{-1}, {-1}, {-1}}),
			new Matrix(new double[][] {{1}, {-1}, {-1}}),
			new Matrix(new double[][] {{1}, {1}, {-1}}),
			new Matrix(new double[][] {{-1}, {1}, {-1}})
	};
	
	Matrix[] vertices = verticesPrimary.clone();
	
	Matrix[] verticesProjected = new Matrix[8];
	
	Matrix projectionMatrix = new Matrix(new double[][] {
		{1, 0, 0},
		{0, 1, 0},
		{0, 0, 0}
	});
	
	BufferedImage ball = null;

	public CubeRotator() {
		try {
			ball = ImageIO.read(getClass().getResourceAsStream("ball.gif"));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public Matrix generateTransform(Axis axis, double theta) {
		switch (axis) {
		case X:
			return new Matrix(new double[][] {
				{1, 0, 0},
				{0, Math.cos(theta), Math.sin(theta) * -1},
				{0, Math.sin(theta), Math.cos(theta)}
			});
		case Y:
			return new Matrix(new double[][] {
				{Math.cos(theta), 0, Math.sin(theta)},
				{0, 1, 0},
				{Math.sin(theta) * -1, 0, Math.cos(theta)}
			});
		case Z:
			return new Matrix(new double[][] {
				{Math.cos(theta), Math.sin(theta) * - 1, 0},
				{Math.sin(theta), Math.cos(theta), 0},
				{0, 0, 1}
			});
		default:
			return null;
		}
	}
	
	@Override
	public void update() {
		Matrix testMatrixA = new Matrix(
				new double[][] {
					{0, 3, 5},
					{5, 5, 2}
				}
		);
		Matrix testMatrixB = new Matrix(
				new double[][] {
					{3, 4},
					{3, -2},
					{4, -2}
				}
		);
		System.out.println(Matrix.getColumnAsArray(0, testMatrixB));
		//System.out.println(testMatrixA.getProduct(testMatrixB).toString());
		for (int i = 0; i < vertices.length; i++) {
			verticesProjected[i] = vertices[i].getProduct(projectionMatrix);
		}
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		for (int i = 0; i < verticesProjected.length; i++) {
			double[][] coords = verticesProjected[i].getMatrix();
			graphics2d.drawImage(
					ball, 
					null, 
					((int)coords[0][0] * scaleFactor) + offsetWidth, 
					((int)coords[0][1] * scaleFactor) + offsetHeight
			);
		}
	}

}
