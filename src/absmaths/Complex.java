package absmaths;

public class Complex {
	public double i; // imaginary
	public double r; // real
	
	public Complex(double r, double i) {
		this.i = i;
		this.r = r;
	}
	
	public Complex square() {
		this.r = this.r * this.r - this.i * this.i;
		this.i = 0;
		return this;
	}
	
	public Complex add(Complex x) {
		this.r += x.r;
		this.i += x.i;
		return this;
	}
}
