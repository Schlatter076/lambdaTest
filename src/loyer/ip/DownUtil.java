package loyer.ip;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 定义一个多线程下载工具
 * @author Loyer
 */
public class DownUtil {

  /**定义下载资源的路径*/
  private String path;
  /**下载资源的保存位置*/
  private String targetFile;
  /**需要多少线程下载*/
  private int threadNum;
  /**下载资源的线程对象*/
  private DownThread[] threads;
  /**下载文件的总大小*/
  private int fileSize;
  /**
   * 构造器
   * @param path  下载资源的路径
   * @param targetFile  下载资源的保存位置
   * @param threadNum  需要多少线程下载
   */
  public DownUtil(String path, String targetFile, int threadNum) {
    super();
    this.path = path;
    this.targetFile = targetFile;
    this.threadNum = threadNum;
    this.threads = new DownThread[threadNum];
  }
  /**
   * 下载的方法
   * @throws Exception
   */
  public void downlaod() throws Exception {
    
    URL url = new URL(path);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setConnectTimeout(5 * 1000);
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
        + "application/x-shockwave-flash, application/xaml+xml, "
        + "application/vnd.ms-xpsdocment, application/x-ms-xbap, "
        + "application/x-ms-application, application/vnd.ms-excel, "
        + "application/vnd.ms-powerpoint, application/msword, */*");
    
    conn.setRequestProperty("Accept-Language", "zh-CN");
    conn.setRequestProperty("Charset", "UTF-8");
    conn.setRequestProperty("Connection", "Keep-Alive");
    //得到文件大小
    fileSize = conn.getContentLength();
    conn.disconnect();
    int currentPartSize = fileSize / threadNum + 1;
    RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
    //设置本地文件大小
    file.setLength(fileSize);
    file.close();
    for(int i = 0; i < threadNum; i++) {
      //计算每个线程下载的开始位置
      int startPos = i * currentPartSize;
      //每个线程使用一个任意访问文件进行下载
      RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
      //创建下载线程
      threads[i] = new DownThread(startPos, currentPartSize, currentPart);
      //启动线程下载
      threads[i].start();      
    }
  }
  /**
   * 获取下载完成的百分比
   * @return
   */
  public double getCompleteRate() {
    //统计多个线程下载的总大小
    int sumSize = 0;
    for(int i = 0; i < threadNum; i++) {
      sumSize += threads[i].length;
    }
    return sumSize * 1.0 / fileSize;
  }
  /**
   * 定义一个下载线程类
   * @author Loyer
   */
  private class DownThread extends Thread {

    /**当前线程的下载位置*/
    private int startPos;
    /**当前线程负责下载的文件大小*/
    private int currentPartSize;
    /**当前线程负责下载的部分*/
    private RandomAccessFile currentPart;
    /**当前线程已下载的字节数*/
    private int length;
    
    public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
      super();
      this.startPos = startPos;
      this.currentPartSize = currentPartSize;
      this.currentPart = currentPart;
    }

    @Override
    public void run() {
      try {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
            + "application/x-shockwave-flash, application/xaml+xml, "
            + "application/vnd.ms-xpsdocment, application/x-ms-xbap, "
            + "application/x-ms-application, application/vnd.ms-excel, "
            + "application/vnd.ms-powerpoint, application/msword, */*");
        
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        InputStream in = conn.getInputStream();
        //跳过startPos个字节，表明该线程只下载自己负责的那部分文件
        in.skip(this.startPos);
        byte[] buffer = new byte[1024];
        int hasRead = 0;
        //读取网络数据，并写入本地文件
        while(length < currentPartSize && (hasRead = in.read(buffer)) != -1) {
          currentPart.write(buffer, 0, hasRead);
          //累计该线程下载的总大小
          length += hasRead;
        }
        currentPart.close();
        in.close();
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
    }
    
  }
}
