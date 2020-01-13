package cn.sdjtu;

import com.sin.java.plot.Plot;
import com.sin.java.plot.PlotFrame;

public class Demo extends Thread{

	public static void main(String[] args) {
//		playAndDraw();
		drawSpectrogram("G:\\rawwavs\\Honor.wav");
//		drawWaveFile("G:\\rawwavs\\Honor.wav");
//		drawSpectrogram("G:\\rawwavs\\wav_40_16_2_pcm.wav");
//		drawWaveFile("G:\\rawwavs\\wav_40_16_2_pcm.wav");
//		drawWaveFile("‪G:\\rawwavs\\wav_40_16_2_pcm.wav");
//		drawSpectrogram("G:\\rawwavs\\TEST.wav");
//		drawWaveFile("G:\\rawwavs\\TEST.wav");
		
		
	}
	
	public static void playAndDraw() {
		//边画频谱边播放音乐
		Thread t1 = new WavPlay();
		Thread t2 = new Demo();
		t1.start();
		t2.start();
	}
	public void run() {
		drawSpectrogram("G:\\rawwavs\\Honor.wav");
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

	// 绘制时域波形图
	public static void drawWaveFile(String filename) {
		WaveFileReader reader = new WaveFileReader(filename);
		String[] pamss = new String[] { "-r", "-g", "-b" };
		if (reader.isSuccess()) {
			PlotFrame frame = Plot.figrue(String.format("%s %dHZ %dBit %dCH", filename, reader.getSampleRate(), reader.getBitPerSample(), reader.getNumChannels()));
			frame.setSize(500, 200);
			Plot.hold_on();
			for (int i = 0; i < reader.getNumChannels(); ++i) {//双声道循环------------
				// 获取i声道数据
//				int i=0;//仅用单声道--------
				int N = 2048;//前多少点dft
				int[] data = reader.getData()[i];
				for (int j = 0; j < N; j++) {
					System.out.println(j + " " + data[j]);
				}
				// 绘图
				Plot.plot(Integers2Doubles(data), pamss[i % pamss.length]);
			}//双声道循环------
			Plot.hold_off();
		} else {
			System.err.println(filename + "不是一个正常的wav文件");
		}
	}
	//绘制频谱图
	public static void drawSpectrogram(String filename) {
		WaveFileReader reader = new WaveFileReader(filename);
		//
		String[] pamss = new String[] { "-r", "-g", "-b" };
		if (reader.isSuccess()) {
			PlotFrame frame = Plot.figrue(String.format("%s %dHZ %dBit %dCH", filename, reader.getSampleRate(), reader.getBitPerSample(), reader.getNumChannels()));
			frame.setSize(500, 200);
			Plot.hold_on();
			for (int i = 0; i < reader.getNumChannels(); ++i) {//双声道循环------------
				// 获取i声道数据
//				int i=0;//仅用单声道--------
				int N = 2048;//前多少点dft
				int[] data = reader.getData()[i];
				for (int j = 0; j < N; j++) {
					System.out.println(j + " " + data[j]);
				}
				Complex[] complexData = changeToComplex(data);
//				for (int j = 0; j < complexData.length; j++) {
//					System.out.println(j + " " + complexData[j].getReal());
//				}
				System.out.println("start dft");
				long t1=System.currentTimeMillis();
//				Complex[] dftData = DFT.dft(complexData, N);
//				Complex[] dftData = FFT.fft(complexData, N);
				Complex[] dftData = FFT.myFFT(complexData, N);
				long t2=System.currentTimeMillis();
//				Complex[] dftData1 = FFT.fft(complexData, N);
//				for (int j = 0; j < dftData.length; j++) {
//					System.out.println(j + " " + dftData[j].getReal() + " " + dftData1[j].getReal());
//				}
				System.out.println("finish dft");
				System.out.println("所用时间" + (t2-t1)+ "ms");
				double[] result = returnAbsData(returnToDouble(dftData));
//				double[] result = returnToDouble(dftData);
//				for (int j = 0; j < result.length; j++) {
//					System.out.println(j + " " + result[j]);
//				}
				
				// 绘图
				Plot.plot(result, pamss[i % pamss.length]);
			}//双声道循环------
			Plot.hold_off();
		} else {
			System.err.println(filename + "不是一个正常的wav文件");
		}
		
	}
	
	//将提取的数据转换成复数
	static Complex[] changeToComplex(int[] data) {
		int length = data.length;
		Complex[] res = new Complex[length];
		for (int i = 0; i < res.length; i++) {
			res[i] = new Complex(data[i]);
		}
		return res;
	}
	//将复数的实数部分提取到小数数组
	static double[] returnToDouble(Complex[] complex) {
		double [] res = new double[complex.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = complex[i].getReal();
		}
		return res;
	}
	//将小数数组数据绝对值化
	static double[] returnAbsData(double[] doubleDatas) {
		double[] absDatas = new double[doubleDatas.length];
		for (int i = 0; i < doubleDatas.length; i++) {
			absDatas[i] = Math.abs(doubleDatas[i]);
		}
		return absDatas;
	}
}
