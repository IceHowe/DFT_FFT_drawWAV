package priv.Zhanghao.FFT;

import com.sin.java.plot.Plot;
import com.sin.java.plot.PlotFrame;

public class FFTCalc0ToEnd extends Thread {
	String filename;
	public FFTCalc0ToEnd() {
		
	}
	public FFTCalc0ToEnd(String filename) {
		this.filename = filename;
	}
	
	public void run() {
	//从0开始一次计算2048点FFT
		WaveFileReader reader = new WaveFileReader(filename);
		String[] pamss = new String[] { "-r", "-g", "-b" };
		if (reader.isSuccess()) {
			PlotFrame frame = Plot.figrue(String.format("%s %dHZ %dBit %dCH", filename, reader.getSampleRate(), reader.getBitPerSample(), reader.getNumChannels()));
			frame.setSize(500, 200);
//				Plot.hold_on();
//				for (int i = 0; i < reader.getNumChannels(); ++i) {//双声道循环------------
				// 获取i声道数据
				int i=0;//仅用单声道--------
				int N = 2048;//每次计算FFT点的个数
				int[] data = reader.getData()[i];
				int length = data.length;
				System.out.println(length);
				Complex[] complexData = Util.changeToComplex(data);
				
//					Complex[] dftData = DFT.dft(complexData, N);
				long t1=System.nanoTime();
//					int j=0;//测试用
				for(int j = 0;j<length;j++) {
					if((j*N+N)>length) {
						break;
					}
					Complex[] dftData = FFT.myFFT(complexData,j*N,N);
					double[] result = Util.returnAbsData(Util.returnToDouble(dftData));
					try {
						this.sleep(140);//调整合适的休眠时间，使频谱与歌曲匹配
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 绘图
					Plot.plot(result, pamss[i % pamss.length]);
					
				}
					long t2=System.nanoTime();
					System.out.println("所用时间" + (t2-t1)+ "ns");
				
//				}//双声道循环------
//				Plot.hold_off();
		} else {
			System.err.println(filename + "不是一个正常的wav文件");
		}
	}
}

