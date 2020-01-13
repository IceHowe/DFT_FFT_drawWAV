package demo;

public class DFT {
	
	public static Complex[] dft(Complex[] x,int number) {
		int N = number;
		Complex result[] = new Complex[N]; 
		for(int n = 0; n < N; n++) {
			result[n] = new Complex(0,0);
			for(int k = 0; k<N; k++) {
				double W = -2*k*Math.PI/N;
				Complex c = new Complex(Math.cos(W),Math.sin(W));
				result[n].plus(x[k].multiply(c));
			}
		}
		return result;
	}

}
