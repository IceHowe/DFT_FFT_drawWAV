package cn.sdjtu;

public class TestFFT {
	//测试DFT、FFT运算
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
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
		Complex[] x1 = DFT.dft(x, 5);
		for(Complex a:x1) {
			System.out.println(a);
		}
	}
	public static void test2() {
		Complex[] x = new Complex[8];
		for(int i = 0;i < x.length;i++) {
			x[i] = new Complex(i);
		}
		for(Complex a:x) {
			System.out.println(a);
		}
		Complex[] x1 = DFT.dft(x, 8);
		Complex[] x2 = FFT.fft(x, 8);
		Complex[] x3 = FFT.myFFT(x, 8);
		
		for (int i = 0; i < x3.length; i++) {
			System.out.println(x1[i] + "/" + x2[i] + "/" + x3[i]);
		}
	}
}


