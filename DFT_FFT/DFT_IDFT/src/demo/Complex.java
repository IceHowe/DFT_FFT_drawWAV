package demo;

public class Complex {
	private double real;
	private double image;
	public Complex() {
		
	}
	public Complex plus(Complex x) {
		this.real += x.getReal();
		this.image += x.getImage();
		return this;
	}
	public Complex multiply(Complex x) {
		this.real = this.real * x.getReal() - this.image * x.getImage();
		this.image = this.image * x.getReal() + this.real * x.getImage();
		return this;
	}
	public Complex(double real,double image) {
		this.image = image;
		this.real = real;
	}
	public double getReal() {
		return real;
	}
	public void setReal(double real) {
		this.real = real;
	}
	public double getImage() {
		return image;
	}
	public void setImage(double image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "real=" + real + ", image=" + image;
	}
	
}
