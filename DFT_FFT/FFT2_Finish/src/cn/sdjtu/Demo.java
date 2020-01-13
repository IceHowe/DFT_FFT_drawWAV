package cn.sdjtu;

import com.sin.java.plot.Plot;
import com.sin.java.plot.PlotFrame;

public class Demo {

	public static void main(String[] args) {
		playAndDraw();
//		drawSpectrogram("G:\\rawwavs\\Honor.wav");//绘制歌曲“荣耀”频谱图
//		drawWaveFile("G:\\rawwavs\\Honor.wav");//绘制歌曲“荣耀”波形图
//		drawSpectrogram("G:\\rawwavs\\wav_40_16_2_pcm.wav");//绘制歌曲频谱图
//		drawWaveFile("G:\\rawwavs\\wav_40_16_2_pcm.wav");//绘制歌曲波形图
//		drawSpectrogram("G:\\rawwavs\\TEST.wav");//绘制歌曲频谱图
//		drawWaveFile("G:\\rawwavs\\TEST.wav");//绘制歌曲波形图
		
		
	}
	
	public static void playAndDraw() {
		//边画频谱边播放音乐
		Thread t1 = new WavPlay();//播放音乐
//		Thread t2 = new FFTCalc0ToEnd("G:\\rawwavs\\Honor.wav");//绘制频谱
		Thread t3 = new WavDraw("G:\\rawwavs\\Honor.wav");//绘制波形
		t1.start();//开启播放音乐线程
//		t2.start();//开启绘制频谱线程
		t3.start();//开启绘制波形线程
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
				Plot.plot(Util.Integers2Doubles(data), pamss[i % pamss.length]);
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
				System.out.println(data.length);
				for (int j = 0; j < N; j++) {
					System.out.println(j + " " + data[j]);
				}
				Complex[] complexData = Util.changeToComplex(data);
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
				double[] result = Util.returnAbsData(Util.returnToDouble(dftData));
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
	
	
	
}
