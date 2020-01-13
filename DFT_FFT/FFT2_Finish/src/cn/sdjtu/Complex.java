package cn.sdjtu;

public class Complex {
	//复数类，实现复数的加减乘除以及重写toString方法
	private double real;
	private double image;
	
	public Complex() {
		this(0,0);
	}
	
	public Complex (double real) {
		this(real,0);
	}
	
	public Complex(double real,double image) {
		this.real = real;
		this.image = image;
	}
	
	public Complex plus(Complex complex) {
		double real2 = complex.getReal();
		double image2 = complex.getImage();
		double newReal =real + real2;
		double newImage =image + image2;
		return new Complex(newReal,newImage);
	}
	
	public Complex minus(Complex complex) {
		double real2 = complex.getReal();
		double image2 = complex.getImage();	
		double newReal =real - real2;
		double newImage =image - image2;
		return new Complex(newReal,newImage);
	}
	
	public Complex multiply(Complex complex) {
		double real2 = complex.getReal();
		double image2 = complex.getImage();
		double newReal = real * real2 - image * image2;
		double newImage = image * real2 + real * image2;
		return new Complex(newReal,newImage);
	}
	
	public Complex divide(Complex complex) {
		double real2 = complex.getReal();
		double image2 = complex.getImage();
		double newReal = (real*real2 + image*image2)/(real2*real2 + image2*image2);
		double newImage = (image*real2 - real*image2)/(real2*real2 + image2*image2);
		return new Complex(newReal,newImage);
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
