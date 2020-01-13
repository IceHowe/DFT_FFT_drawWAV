package cn.sdjtu;

public class DFT {
	//DFT计算
	public static Complex[] dft(Complex[] x,int N) {

		if(N == 1 || x.length == 1) {
			return x;
		}
		Complex result[] = new Complex[N]; 
		for(int k = 0; k < N; k++) {
			result[k] = new Complex();
			for(int n = 0; n<N; n++) {
				double W = -2*n*k*Math.PI/N;
				Complex c = new Complex(Math.cos(W),Math.sin(W));
				result[k] = result[k].plus(x[n].multiply(c));
			}
		}
		return result;
	}

}
