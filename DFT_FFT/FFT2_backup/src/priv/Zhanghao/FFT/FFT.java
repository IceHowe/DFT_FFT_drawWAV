package priv.Zhanghao.FFT;

public class FFT {
	public static Complex[] fft(Complex[] x,int N) {
		//定义式计算FFT（方法一）
		if(N == 1 ) {
			return x;
		}
		// 如果信号数为奇数，使用dft计算
		if(N % 2 != 0) {
			return DFT.dft(x, N);
		}
		// 提取下标为偶数的原始信号值进行递归fft计算
		Complex[] even = new Complex[N / 2];
		for (int k = 0; k < even.length; k++) {
			even[k] = x[2 * k];
		}
		Complex[] evenValue = FFT.fft(even, even.length);
		// 提取下标为奇数的原始信号值进行fft计算
		// 节约内存
		Complex[] odd = even;
		for (int k = 0; k < odd.length; k++) {
			odd[k] = x[2 * k + 1];
		}
		Complex[] oddValue = FFT.fft(odd,odd.length);
		// 偶数+奇数
		Complex[] result = new Complex[N];
		for (int k = 0; k < N / 2; k++) {
			double W = -2*k*Math.PI/N;
			Complex m = new Complex(Math.cos(W), Math.sin(W));
			result[k] = evenValue[k].plus(m.multiply(oddValue[k]));
			result[k + N / 2] = evenValue[k].minus(m.multiply(oddValue[k]));
		}
		return result;
	}
	
	public static void reverse(Complex[] A,int N) {
		//倒叙前N个数据
		int LH = N/2;
		int J = LH;
		int N1 = N-2;
		for(int I = 1;I <= N1; I++) {
			if(!(I >=J)) {
				Complex T = A[I];
				A[I] = A[J];
				A[J] = T;
			}
			int K = LH;
			while(!(J < K)) {
				J = J - K;
				K = K/2;
			}
			J += K;
			
		}
//		return null;
	}
	
	//对复数数组倒叙
	public static void reverse(Complex[] A) {
		int N = A.length;
		int LH = N/2;
		int J = LH;
		int N1 = N-2;
		for(int I = 1;I <= N1; I++) {
			if(!(I >=J)) {
				Complex T = A[I];
				A[I] = A[J];
				A[J] = T;
			}
			int K = LH;
			while(!(J < K)) {
				J = J - K;
				K = K/2;
			}
			J += K;
			
		}
//		return null;
	}
	public static Complex[] myFFT(Complex[] A,int N) {
		//FFT计算（方法二）推荐这一种，错误小
		int M = returnM(N);
		Complex[] x = new Complex[N];
		System.arraycopy(A, 0, x, 0, N);
		reverse(x);
		for(int L = 1;L <= M;L++) {
			int B = (int)Math.pow(2, L-1);
			for(int J = 0;J <= B-1;J++) {
				int P = (int)Math.pow(2, M-L)*J;
				for(int k = J;k <= N-1;k += Math.pow(2, L)) {
					double W = -2*Math.PI*P/N;
					Complex c = x[k+B].multiply(new Complex(Math.cos(W),Math.sin(W)));
					Complex T = x[k].plus(c);
					x[k+B] = x[k].minus(c);
					x[k] = T;
				}
			}
		}
		return x;
	}
	
	public static Complex[] myFFT(Complex[] A,int offset,int N) {
		//FFT计算，计算从offset开始的N条数据
		int M = returnM(N);
		Complex[] x = new Complex[N];
		System.arraycopy(A, offset, x, 0, N);
		reverse(x);
		for(int L = 1;L <= M;L++) {
			int B = (int)Math.pow(2, L-1);
			for(int J = 0;J <= B-1;J++) {
				int P = (int)Math.pow(2, M-L)*J;
				for(int k = J;k <= N-1;k += Math.pow(2, L)) {
					double W = -2*Math.PI*P/N;
					Complex c = x[k+B].multiply(new Complex(Math.cos(W),Math.sin(W)));
					Complex T = x[k].plus(c);
					x[k+B] = x[k].minus(c);
					x[k] = T;
				}
			}
		}
		return x;
	}
	
	//返回一个数是2的几次幂
	public static int returnM(int N) {
//		int N = x.length;
		if((N&(N-1))!=0) {
			throw new RuntimeException("非2的整数幂");
		}
		int M=0;
		while((N = N / 2) != 0) {
			M++;
		}
		return M;
	}
}


