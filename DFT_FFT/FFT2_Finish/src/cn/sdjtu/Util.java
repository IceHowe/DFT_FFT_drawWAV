package cn.sdjtu;

public class Util {
	//将提取的数据转换成复数
		public static Complex[] changeToComplex(int[] data) {
			int length = data.length;
			Complex[] res = new Complex[length];
			for (int i = 0; i < res.length; i++) {
				res[i] = new Complex(data[i]);
			}
			return res;
		}
		//将复数的实数部分提取到小数数组
		public static double[] returnToDouble(Complex[] complex) {
			double [] res = new double[complex.length];
			for (int i = 0; i < res.length; i++) {
				res[i] = complex[i].getReal();
			}
			return res;
		}
		//将小数数组数据绝对值化
		public static double[] returnAbsData(double[] doubleDatas) {
			double[] absDatas = new double[doubleDatas.length];
			for (int i = 0; i < doubleDatas.length; i++) {
				absDatas[i] = Math.abs(doubleDatas[i]);
			}
			return absDatas;
		}
		
		// int 数组 转换到 double数组
		// JavaPlot 只支持double数组的绘制
		public static double[] Integers2Doubles(int[] raw) {
			double[] res = new double[raw.length];
			for (int i = 0; i < res.length; i++) {
				res[i] = raw[i];
			}
			return res;
		}
}
