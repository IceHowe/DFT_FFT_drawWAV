package priv.Zhanghao.FFT;

public class TestFFT {
	//测试DFT、FFT运算
	public static void main(String[] args) {
//		test1();
		test2();
//		test3();
	}
	public static void test1() {
		Complex[] x = new Complex[5];
		x[0] = new Complex(4);
		x[1] = new Complex(3);
		x[2] = new Complex(2);
		x[3] = new Complex(1);
		x[4] = new Complex(0);
		for(Complex a:x) {
			System.out.println(a);
		}
		System.out.println("---------------------");
		long t1=System.nanoTime();
		Complex[] x1 = DFT.dft(x, 5);
		long t2=System.nanoTime();
		for(Complex a:x1) {
			System.out.println(a);
		}
		System.out.println("所用时间" + (t2-t1)+ "ns");
	}
	public static void test2() {
		Complex[] x = new Complex[8];
		for(int i = 0;i < x.length;i++) {
			x[i] = new Complex(i);
		}
		for(Complex a:x) {
			System.out.println(a);
		}
		System.out.println("---------------------");
		long t1=System.nanoTime();
//		Complex[] x1 = DFT.dft(x, 8);
//		Complex[] x1 = FFT.fft(x, 8);
		Complex[] x1 = FFT.myFFT(x, 8);
		long t2=System.nanoTime();
		for (int i = 0; i < x.length; i++) {
			System.out.println(x1[i]);
		}
		System.out.println("所用时间" + (t2-t1)+ "ns");
	}
	
	public static void test3() {
		Complex[] x = new Complex[16];
		for(int i = x.length-1;i >=0;i--) {
			x[x.length-1-i] = new Complex(i);
		}
		for(Complex a:x) {
			System.out.println(a);
		}
		Complex[] x1 = FFT.myFFT(x,8,8);
		for(Complex a:x1) {
			System.out.println(a);
		}
	}
}


