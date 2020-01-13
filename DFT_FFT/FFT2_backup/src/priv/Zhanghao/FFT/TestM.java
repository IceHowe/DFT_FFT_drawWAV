package priv.Zhanghao.FFT;


public class TestM {
	//测试FFT.returnM
	public static void main(String[] args) {
		int N = 64;
		// TODO Auto-generated method stub
		Complex[] x = new Complex[N];
		for(int i = 0;i < x.length;i++) {
			x[i] = new Complex(i);
		}
		for(Complex a:x) {
			System.out.println(a);
		}
		int M = FFT.returnM(x.length);
		System.out.println(M);
		System.out.println(Math.pow(2, M));
	}

}
