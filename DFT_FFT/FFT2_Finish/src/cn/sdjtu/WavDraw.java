package cn.sdjtu;

import com.sin.java.plot.Plot;
import com.sin.java.plot.PlotFrame;

public class WavDraw extends Thread{
	String filename;
	public WavDraw() {
		
	}
	public WavDraw(String filename) {
		this.filename = filename;
	}
	public void run() {
		WaveFileReader reader = new WaveFileReader(filename);
		String[] pamss = new String[] { "-r", "-g", "-b" };
		if (reader.isSuccess()) {
			PlotFrame frame = Plot.figrue(String.format("%s %dHZ %dBit %dCH", filename, reader.getSampleRate(), reader.getBitPerSample(), reader.getNumChannels()));
			frame.setSize(500, 200);
//			Plot.hold_on();
			for (int i = 0; i < reader.getNumChannels(); ++i) {//双声道循环------------
				// 获取i声道数据
//				int i=0;//仅用单声道--------
				int N = 2048;//前多少点dft
				int[] data = reader.getData()[i];
				int length = data.length;
				int[] partData = new int[N];
				for(int j = 0;j<length;j++) {
					if((j*N+N)>length) {
						break;
					}
					System.arraycopy(data, N*j, partData, 0, N);
					// 绘图
					Plot.plot(Util.Integers2Doubles(partData), pamss[i % pamss.length]);
					try {
						this.sleep(140);//调整合适的休眠时间，使频谱与歌曲匹配
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}//双声道循环------
//			Plot.hold_off();
		} else {
			System.err.println(filename + "不是一个正常的wav文件");
		}
	}
}
