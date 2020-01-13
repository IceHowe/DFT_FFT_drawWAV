# DFT_FFT_drawWAV
利用Java实现DFT、FFT，读取WAV文件，并绘制波形图和频谱图，音频播放频谱或波形图实时显示效果。

几个工程打包于文件夹DFT_FFT：

1. DFT_IDFT：计算前N点DFT，并绘制波形图或频谱图；
2. FFT：计算前N点FFT，并绘制波形图或频谱图，能播放WAV音乐，不过不能实时显示频谱或波形；
3. FFT2_backup和FFT2_Finish：计算N点DFT或FFT，能播放WAV音频，且能实时显示波形或频谱，音频与波形或频谱不同步，需自己调节，但做不到十分精准。

对WaveFileReader.java进行了修改，使之可以读取部分格式工厂转化的WAV文件。

rawwavs:这是我用的一些WAV音乐文件，修改下代码中路径就可以直接运行使用。

WAV文件解析参考以下链接：

1. https://blog.csdn.net/imxiangzi/article/details/80265978
   注意，看了第1个链接你会发现很简单，而你也确实可以解析一些WAV文件了，但是还有许多是其它格式的。
   
2. https://www.cnblogs.com/Free-Thinker/p/10489491.html
   
   ![img](https://upload-images.jianshu.io/upload_images/1877190-9b3280fb45d4a183.png)
   ![img](https://upload-images.jianshu.io/upload_images/1877190-259a4c9edfc4092c.png)

主要就是参考第二个链接里的这两个图片修改的。