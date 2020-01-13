package cn.sdjtu;

public class TestReverse {
	//测试倒叙
	public static void main(String[] args) {
		Complex[] A = new Complex[8];
		for(int i =0;i < 8;i++) {
			A[i] = new Complex(i); 
		}
//		for (int i = 0; i < A.length; i++) {
//			System.out.println(A[i]);
//		}
		FFT.reverse(A);
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}
}
