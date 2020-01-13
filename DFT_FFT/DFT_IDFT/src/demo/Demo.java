package demo;

import javax.swing.JFrame;

public class Demo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String filename = "‪‪‪G:\\rawwavs\\Zhanghao.wav";
		String filename = "G:\\rawwavs\\wav_40_16_2_pcm.wav";
		JFrame frame = new JFrame();
		WaveFileReader reader = new WaveFileReader(filename);
		if(reader.isSuccess()){
			int[] data = reader.getData()[0]; //获取第一声道
		
			DrawPanel drawPanel = new DrawPanel(data); // 创建一个绘制波形的面板
			frame.add(drawPanel);
			frame.setTitle(filename);
			frame.setSize(800, 400);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		else{
			System.err.println(filename + "不是一个正常的wav文件");
		}
	}
	static Complex[] change(int[] data) {
		int length = data.length;
		Complex[] res = new Complex[length];
		for (int i = 0; i < res.length; i++) {
			res[i] = new Complex();
			res[i].setReal(data[i]);
			res[i].setImage(0);
		}
		return res;
	}
	static int[] returnValue(Complex[] afterDFT) {
		int N = afterDFT.length;
		int[] res = new int[N];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) afterDFT[i].getReal();
		}
		return res;
	}
}
