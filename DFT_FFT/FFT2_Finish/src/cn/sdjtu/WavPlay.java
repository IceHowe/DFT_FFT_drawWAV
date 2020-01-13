package cn.sdjtu;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WavPlay extends Thread{
	public void run() {
		AudioInputStream as;
		try {
			as = AudioSystem.getAudioInputStream(new File("G:\\rawwavs\\Honor.wav"));//音频文件在项目根目录的img文件夹下
			AudioFormat format = as.getFormat();
			SourceDataLine sdl = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(format);
			sdl.start();
			int nBytesRead = 0;
			byte[] abData = new byte[512];
			while (nBytesRead != -1) {
				nBytesRead = as.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					sdl.write(abData, 0, nBytesRead);
			}
		    //关闭SourceDataLine
			sdl.drain();
			sdl.close();
			}catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
